package yevano.util;

import yevano.util.adt.Higher;

public class Union {
    public static <A, T1, T2> Of2<A, T1, T2> union(Class<A> valueClass, A value) {
        return new Union.Of2<>(Pair.of(valueClass, value));
    }

    public static <A, T1, T2> Of2<
        A, T1, T2
    > union2(Class<A> valueClass, A value)
    {
        return union(valueClass, value);
    }

    public static <A, T1, T2, T3> Of3<
        A, T1, T2, T3
    > union3(Class<A> valueClass, A value) {
        return new Union.Of3<>(Pair.of(valueClass, value));
    }

    public static <A, T1, T2, T3, T4> Of4<
        A, T1, T2, T3, T4
    > union4(Class<A> valueClass, A value) {
        return new Union.Of4<>(Pair.of(valueClass, value));
    }

    public static <A, T1, T2, T3, T4, T5> Of5<
        A, T1, T2, T3, T4, T5
    > union5(Class<A> valueClass, A value) {
        return new Union.Of5<>(Pair.of(valueClass, value));
    }

    public static <A, T1, T2, T3, T4, T5, T6> Of6<
        A, T1, T2, T3, T4, T5, T6
    > union6(Class<A> valueClass, A value) {
        return new Union.Of6<>(Pair.of(valueClass, value));
    }

    public static <A, T1, T2, T3, T4, T5, T6, T7> Of7<
        A, T1, T2, T3, T4, T5, T6, T7
    > union7(Class<A> valueClass, A value) {
        return new Union.Of7<>(Pair.of(valueClass, value));
    }

    public static <A, T1, T2, T3, T4, T5, T6, T7, T8> Of8<
        A, T1, T2, T3, T4, T5, T6, T7, T8
    > union8(Class<A> valueClass, A value) {
        return new Union.Of8<>(Pair.of(valueClass, value));
    }

    public static class Of2<A, T1, T2> extends Ref<Pair<Class<A>, A>>
        implements Higher<Of2.K, Tuple.Of3<A, T1, T2>>
    {
        public static class K { }

        Of2(Pair<Class<A>, A> value) {
            super(value);
        }

        @SuppressWarnings("unchecked") public T1 _1() { return (T1) super.value; }
        @SuppressWarnings("unchecked") public T2 _2() { return (T2) super.value; }

        @SuppressWarnings("unchecked")
        public <B> B match(Fun<T1, B> f1, Fun<T2, B> f2) {
            return value.match((valueClass, value) -> {
                if       (valueClass.isInstance(value)) {
                    return f1.apply((T1) value);
                } else if(valueClass.isInstance(value)) {
                    return f2.apply((T2) value);
                }

                return null;
            });
        }

        @Override
        public String toString() {
            return value.toString();
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(obj);
        }
    }

    public static class Of3<A, T1, T2, T3> extends Ref<Pair<Class<A>, A>> {
        Of3(Pair<Class<A>, A> value) {
            super(value);
        }

        @SuppressWarnings("unchecked") public T1 _1() { return (T1) super.value; }
        @SuppressWarnings("unchecked") public T2 _2() { return (T2) super.value; }
        @SuppressWarnings("unchecked") public T3 _3() { return (T3) super.value; }

        @SuppressWarnings("unchecked")
        public <B> B match(
            Fun<T1, B> f1,
            Fun<T2, B> f2,
            Fun<T3, B> f3
        ) {
            return value.match((valueClass, value) -> {
                if       (valueClass.isInstance(value)) {
                    return f1.apply((T1) value);
                } else if(valueClass.isInstance(value)) {
                    return f2.apply((T2) value);
                } else if(valueClass.isInstance(value)) {
                    return f3.apply((T3) value);
                }

                return null;
            });
        }

        @Override
        public String toString() {
            return value.toString();
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(obj);
        }
    }
    public static class Of4<A, T1, T2, T3, T4> extends Ref<Pair<Class<A>, A>> {
        Of4(Pair<Class<A>, A> value) {
            super(value);
        }

        @SuppressWarnings("unchecked") public T1 _1() { return (T1) super.value; }
        @SuppressWarnings("unchecked") public T2 _2() { return (T2) super.value; }
        @SuppressWarnings("unchecked") public T3 _3() { return (T3) super.value; }
        @SuppressWarnings("unchecked") public T4 _4() { return (T4) super.value; }

        @SuppressWarnings("unchecked")
        public <B> B match(
            Fun<T1, B> f1,
            Fun<T2, B> f2,
            Fun<T3, B> f3,
            Fun<T4, B> f4
        ) {
            return value.match((valueClass, value) -> {
                if       (valueClass.isInstance(value)) {
                    return f1.apply((T1) value);
                } else if(valueClass.isInstance(value)) {
                    return f2.apply((T2) value);
                } else if(valueClass.isInstance(value)) {
                    return f3.apply((T3) value);
                } else if(valueClass.isInstance(value)) {
                    return f4.apply((T4) value);
                }

                return null;
            });
        }

        @Override
        public String toString() {
            return value.toString();
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(obj);
        }
    }
    public static class Of5<A, T1, T2, T3, T4, T5> extends Ref<Pair<Class<A>, A>> {
        Of5(Pair<Class<A>, A> value) {
            super(value);
        }

        @SuppressWarnings("unchecked") public T1 _1() { return (T1) super.value; }
        @SuppressWarnings("unchecked") public T2 _2() { return (T2) super.value; }
        @SuppressWarnings("unchecked") public T3 _3() { return (T3) super.value; }
        @SuppressWarnings("unchecked") public T4 _4() { return (T4) super.value; }
        @SuppressWarnings("unchecked") public T5 _5() { return (T5) super.value; }

        @SuppressWarnings("unchecked")
        public <B> B match(
            Fun<T1, B> f1,
            Fun<T2, B> f2,
            Fun<T3, B> f3,
            Fun<T4, B> f4,
            Fun<T5, B> f5
        ) {
            return value.match((valueClass, value) -> {
                if       (valueClass.isInstance(value)) {
                    return f1.apply((T1) value);
                } else if(valueClass.isInstance(value)) {
                    return f2.apply((T2) value);
                } else if(valueClass.isInstance(value)) {
                    return f3.apply((T3) value);
                } else if(valueClass.isInstance(value)) {
                    return f4.apply((T4) value);
                } else if(valueClass.isInstance(value)) {
                    return f5.apply((T5) value);
                }

                return null;
            });
        }

        @Override
        public String toString() {
            return value.toString();
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(obj);
        }
    }
    public static class Of6<A, T1, T2, T3, T4, T5, T6> extends Ref<Pair<Class<A>, A>> {
        Of6(Pair<Class<A>, A> value) {
            super(value);
        }

        @SuppressWarnings("unchecked") public T1 _1() { return (T1) super.value; }
        @SuppressWarnings("unchecked") public T2 _2() { return (T2) super.value; }
        @SuppressWarnings("unchecked") public T3 _3() { return (T3) super.value; }
        @SuppressWarnings("unchecked") public T4 _4() { return (T4) super.value; }
        @SuppressWarnings("unchecked") public T5 _5() { return (T5) super.value; }
        @SuppressWarnings("unchecked") public T6 _6() { return (T6) super.value; }

        @SuppressWarnings("unchecked")
        public <B> B match(
            Fun<T1, B> f1,
            Fun<T2, B> f2,
            Fun<T3, B> f3,
            Fun<T4, B> f4,
            Fun<T5, B> f5,
            Fun<T6, B> f6
        ) {
            return value.match((valueClass, value) -> {
                if       (valueClass.isInstance(value)) {
                    return f1.apply((T1) value);
                } else if(valueClass.isInstance(value)) {
                    return f2.apply((T2) value);
                } else if(valueClass.isInstance(value)) {
                    return f3.apply((T3) value);
                } else if(valueClass.isInstance(value)) {
                    return f4.apply((T4) value);
                } else if(valueClass.isInstance(value)) {
                    return f5.apply((T5) value);
                } else if(valueClass.isInstance(value)) {
                    return f6.apply((T6) value);
                }

                return null;
            });
        }

        @Override
        public String toString() {
            return value.toString();
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(obj);
        }
    }
    public static class Of7<A, T1, T2, T3, T4, T5, T6, T7> extends Ref<Pair<Class<A>, A>> {
        Of7(Pair<Class<A>, A> value) {
            super(value);
        }

        @SuppressWarnings("unchecked") public T1 _1() { return (T1) super.value; }
        @SuppressWarnings("unchecked") public T2 _2() { return (T2) super.value; }
        @SuppressWarnings("unchecked") public T3 _3() { return (T3) super.value; }
        @SuppressWarnings("unchecked") public T4 _4() { return (T4) super.value; }
        @SuppressWarnings("unchecked") public T5 _5() { return (T5) super.value; }
        @SuppressWarnings("unchecked") public T6 _6() { return (T6) super.value; }
        @SuppressWarnings("unchecked") public T7 _7() { return (T7) super.value; }

        @SuppressWarnings("unchecked")
        public <B> B match(
            Fun<T1, B> f1,
            Fun<T2, B> f2,
            Fun<T3, B> f3,
            Fun<T4, B> f4,
            Fun<T5, B> f5,
            Fun<T6, B> f6,
            Fun<T7, B> f7
        ) {
            return value.match((valueClass, value) -> {
                if       (valueClass.isInstance(value)) {
                    return f1.apply((T1) value);
                } else if(valueClass.isInstance(value)) {
                    return f2.apply((T2) value);
                } else if(valueClass.isInstance(value)) {
                    return f3.apply((T3) value);
                } else if(valueClass.isInstance(value)) {
                    return f4.apply((T4) value);
                } else if(valueClass.isInstance(value)) {
                    return f5.apply((T5) value);
                } else if(valueClass.isInstance(value)) {
                    return f6.apply((T6) value);
                } else if(valueClass.isInstance(value)) {
                    return f7.apply((T7) value);
                }

                return null;
            });
        }

        @Override
        public String toString() {
            return value.toString();
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(obj);
        }
    }
    public static class Of8<A, T1, T2, T3, T4, T5, T6, T7, T8> extends Ref<Pair<Class<A>, A>> {
        Of8(Pair<Class<A>, A> value) {
            super(value);
        }

        @SuppressWarnings("unchecked") public T1 _1() { return (T1) super.value; }
        @SuppressWarnings("unchecked") public T2 _2() { return (T2) super.value; }
        @SuppressWarnings("unchecked") public T3 _3() { return (T3) super.value; }
        @SuppressWarnings("unchecked") public T4 _4() { return (T4) super.value; }
        @SuppressWarnings("unchecked") public T5 _5() { return (T5) super.value; }
        @SuppressWarnings("unchecked") public T6 _6() { return (T6) super.value; }
        @SuppressWarnings("unchecked") public T7 _7() { return (T7) super.value; }
        @SuppressWarnings("unchecked") public T8 _8() { return (T8) super.value; }

        @SuppressWarnings("unchecked")
        public <B> B match(
            Fun<T1, B> f1,
            Fun<T2, B> f2,
            Fun<T3, B> f3,
            Fun<T4, B> f4,
            Fun<T5, B> f5,
            Fun<T6, B> f6,
            Fun<T7, B> f7,
            Fun<T8, B> f8
        ) {
            return value.match((valueClass, value) -> {
                if       (valueClass.isInstance(value)) {
                    return f1.apply((T1) value);
                } else if(valueClass.isInstance(value)) {
                    return f2.apply((T2) value);
                } else if(valueClass.isInstance(value)) {
                    return f3.apply((T3) value);
                } else if(valueClass.isInstance(value)) {
                    return f4.apply((T4) value);
                } else if(valueClass.isInstance(value)) {
                    return f5.apply((T5) value);
                } else if(valueClass.isInstance(value)) {
                    return f6.apply((T6) value);
                } else if(valueClass.isInstance(value)) {
                    return f7.apply((T7) value);
                } else if(valueClass.isInstance(value)) {
                    return f8.apply((T8) value);
                }

                return null;
            });
        }

        @Override
        public String toString() {
            return value.toString();
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(obj);
        }
    }
}
