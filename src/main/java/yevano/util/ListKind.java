package yevano.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import yevano.util.adt.Higher;

public class ListKind<A> implements List<A>, Higher<ListKind.K, A> {
    public static class K { }

    private List<A> delegate;

    @SuppressWarnings("all")
    public static <A> ListKind<A> of(A... array) {
        return new ListKind<A>(array);
    }

    public static <A> List<A> narrowK(Higher<ListKind.K, A> hkt) {
        return (ListKind<A>) hkt;
    }

    public static <A> ListKind<A> lift(List<A> as) {
        return new ListKind<>(as);
    }

    @SuppressWarnings("all")
    ListKind(A... array) {
        this(Arrays.asList(array));
    }

    ListKind(List<A> as) {
        delegate = as;
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return delegate.contains(o);
    }

    @Override
    public Iterator<A> iterator() {
        return delegate.iterator();
    }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return delegate.toArray(a);
    }

    @Override
    public boolean add(A e) {
        return delegate.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return delegate.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return delegate.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends A> c) {
        return delegate.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends A> c) {
        return delegate.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return delegate.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return delegate.retainAll(c);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public A get(int index) {
        return delegate.get(index);
    }

    @Override
    public A set(int index, A element) {
        return delegate.set(index, element);
    }

    @Override
    public void add(int index, A element) {
        delegate.add(index, element);
    }

    @Override
    public A remove(int index) {
        return delegate.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return delegate.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return delegate.lastIndexOf(o);
    }

    @Override
    public ListIterator<A> listIterator() {
        return delegate.listIterator();
    }

    @Override
    public ListIterator<A> listIterator(int index) {
        return delegate.listIterator(index);
    }

    @Override
    public List<A> subList(int fromIndex, int toIndex) {
        return delegate.subList(fromIndex, toIndex);
    }
}
