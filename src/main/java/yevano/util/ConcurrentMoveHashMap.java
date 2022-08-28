package yevano.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A {@link Map} backed by {@link ConcurrentHashMap} which supports locking all reads and writes
 * while performing a reassignment of all elements from this {@link ConcurrentMoveHashMap} to
 * another Map using {@link ConcurrentMoveHashMap#move(Map)}.
 */
public class ConcurrentMoveHashMap<K, V> implements Map<K, V> {
    public static <V> KeySet<V> newKeySet() {
        return new KeySet<>(new ConcurrentMoveHashMap<>());
    }

    private AtomicBoolean mustWaitForMove = new AtomicBoolean(false);
    private AtomicInteger nonMoveOperationsInProgress = new AtomicInteger(0);
    private AtomicInteger nonMoveOperationsWaiting = new AtomicInteger(0);
    private ConcurrentHashMap<K, V> backingMap = new ConcurrentHashMap<>();

    public synchronized void move(Map<? super K, ? super V> other) {
        acquireMoveLock();
        for (var e : backingMap.entrySet()) {
            other.put(e.getKey(), e.getValue());
        }
        backingMap.clear();
        releaseMoveLock();
    }

    public boolean isMoveLocked() {
        return mustWaitForMove.get();
    }

    private void acquireMoveLock() {
        // Wait while non-moves which were waiting for a previous move are able to complete.
        while(nonMoveOperationsWaiting.get() != 0) {
            Thread.onSpinWait();
        }

        // Notify that new non-move operations must wait for this move to complete.
        mustWaitForMove.set(true);

        // Wait for non-moves to finish.
        while(nonMoveOperationsInProgress.get() != 0) {
            Thread.onSpinWait();
        }
    }

    private void releaseMoveLock() {
        // Notify that non-moves may continue.
        mustWaitForMove.set(false);
    }

    private void acquireNonMoveOperationLock() {
        nonMoveOperationsWaiting.incrementAndGet();

        while(mustWaitForMove.get()) {
            Thread.onSpinWait();
        }

        // Ordered this way so that a move doesn't get to start before a non-move which was waiting
        // can execute.
        nonMoveOperationsInProgress.incrementAndGet();
        nonMoveOperationsWaiting.decrementAndGet();
    }

    private void releaseNonMoveOperationLock() {
        nonMoveOperationsInProgress.decrementAndGet();
    }

    private <T> T releaseNonMoveOperationLock(T result) {
        nonMoveOperationsInProgress.decrementAndGet();
        return result;
    }

    @Override
    public int size() {
        acquireNonMoveOperationLock();
        return releaseNonMoveOperationLock(backingMap.size());
    }

    @Override
    public boolean isEmpty() {
        acquireNonMoveOperationLock();
        return releaseNonMoveOperationLock(backingMap.isEmpty());
    }

    @Override
    public boolean containsKey(Object key) {
        acquireNonMoveOperationLock();
        return releaseNonMoveOperationLock(backingMap.containsKey(key));
    }

    @Override
    public boolean containsValue(Object value) {
        acquireNonMoveOperationLock();
        return releaseNonMoveOperationLock(backingMap.containsValue(value));
    }

    @Override
    public V get(Object key) {
        acquireNonMoveOperationLock();
        return releaseNonMoveOperationLock(backingMap.get(key));
    }

    @Override
    public V put(K key, V value) {
        acquireNonMoveOperationLock();
        return releaseNonMoveOperationLock(backingMap.put(key, value));
    }

    @Override
    public V remove(Object key) {
        acquireNonMoveOperationLock();
        return releaseNonMoveOperationLock(backingMap.remove(key));
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        acquireNonMoveOperationLock();
        backingMap.putAll(m);
        releaseNonMoveOperationLock();
    }

    @Override
    public void clear() {
        acquireNonMoveOperationLock();
        backingMap.clear();
        releaseNonMoveOperationLock();
    }

    @Override
    public Set<K> keySet() {
        return backingMap.keySet();
    }

    @Override
    public Collection<V> values() {
        return backingMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return backingMap.entrySet();
    }

    public static class KeySet<E> implements Set<E> {
        private ConcurrentMoveHashMap<E, Boolean> map;
    
        KeySet(ConcurrentMoveHashMap<E, Boolean> map) {
            this.map = map;
        }
    
        public synchronized void move(Set<? super E> other) {
            map.acquireMoveLock();
            for (var e : map.backingMap.entrySet()) {
                other.add(e.getKey());
            }
            map.backingMap.clear();
            map.releaseMoveLock();
        }

        public boolean isMoveLocked() {
            return map.isMoveLocked();
        }
    
        @Override
        public int size() {
            return map.size();
        }
    
        @Override
        public boolean isEmpty() {
            return map.isEmpty();
        }
    
        @Override
        public boolean contains(Object o) {
            return map.containsKey(o);
        }
    
        @Override
        public Iterator<E> iterator() {
            return map.keySet().iterator();
        }
    
        @Override
        public Object[] toArray() {
            map.acquireNonMoveOperationLock();
            return map.releaseNonMoveOperationLock(map.keySet().toArray());
        }
    
        @Override
        public <T> T[] toArray(T[] a) {
            map.acquireNonMoveOperationLock();
            return map.releaseNonMoveOperationLock(map.keySet().toArray(a));
        }
    
        @Override
        public boolean add(E e) {
            return map.put(e, true) == null;
        }
    
        @Override
        public boolean remove(Object o) {
            return map.remove(o) != null;
        }
    
        @Override
        public boolean containsAll(Collection<?> c) {
            map.acquireNonMoveOperationLock();
            if (c != this) {
                for (Object e : c) {
                    if (e == null || !map.backingMap.containsKey(e))
                        return false;
                }
            }
            return map.releaseNonMoveOperationLock(true);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            map.acquireNonMoveOperationLock();
            if (c == null) throw new NullPointerException();
            boolean changed = false;
            for (var e : c) {
                if (!contains(e)) {
                    map.backingMap.put(e, true);
                } else {
                    changed = true;
                }
            }
            return map.releaseNonMoveOperationLock(changed);
        }
    
        @Override
        public boolean retainAll(Collection<?> c) {
            map.acquireNonMoveOperationLock();
            if (c == null) throw new NullPointerException();
            boolean modified = false;
            for (Iterator<E> it = iterator(); it.hasNext();) {
                if (!c.contains(it.next())) {
                    it.remove();
                    modified = true;
                }
            }
            return map.releaseNonMoveOperationLock(modified);
        }
    
        @Override
        public boolean removeAll(Collection<?> c) {
            map.acquireNonMoveOperationLock();
            var modified = false;
            for (var e : c) {
                if (map.backingMap.containsKey(e)) {
                    map.backingMap.remove(e);
                    modified = true;
                }
            }
            return map.releaseNonMoveOperationLock(modified);
        }
    
        @Override
        public void clear() {
            map.clear();
        }
    }    
}
