package yevano.util;

public class Tuple {
    public static <T1, T2> Of2<T1, T2> of(
        T1 t1, T2 t2
    ) {
        return new Of2<>(t1, t2);
    }

    public static <T1, T2, T3> Of3<T1, T2, T3> of(
        T1 t1, T2 t2, T3 t3
    ) {
        return new Of3<>(t1, t2, t3);
    }

    public static <T1, T2, T3, T4> Of4<T1, T2, T3, T4> of(
        T1 t1, T2 t2, T3 t3, T4 t4
    ) {
        return new Of4<>(t1, t2, t3, t4);
    }

    public static <T1, T2, T3, T4, T5> Of5<T1, T2, T3, T4, T5> of(
        T1 t1, T2 t2, T3 t3, T4 t4, T5 t5
    ) {
        return new Of5<>(t1, t2, t3, t4, t5);
    }

    public static <T1, T2, T3, T4, T5, T6> Of6<T1, T2, T3, T4, T5, T6> of(
        T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6
    ) {
        return new Of6<>(t1, t2, t3, t4, t5, t6);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> Of7<T1, T2, T3, T4, T5, T6, T7> of(
        T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7
    ) {
        return new Of7<>(t1, t2, t3, t4, t5, t6, t7);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Of8<T1, T2, T3, T4, T5, T6, T7, T8> of(
        T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8
    ) {
        return new Of8<>(t1, t2, t3, t4, t5, t6, t7, t8);
    }


    public static class Of2<T1, T2> {
        interface F<T1, T2, R> {
            public R apply(T1 t1, T2 t2);
        }

        interface VF<T1, T2> {
            public void apply(T1 t1, T2 t2);
        }

        private final T1 t1;
        private final T2 t2;

        Of2(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public T1 _1() { return t1; }
        public T2 _2() { return t2; }

        public <R> R match(F<T1, T2, R> f) {
            return f.apply(t1, t2);
        }

        public void run(VF<T1, T2> f) {
            f.apply(t1, t2);
        }

        public boolean equals(Object rhs) {
            if(!Of2.class.isInstance(rhs)) return false;
            Of2<?, ?> rhsTup = Of2.class.cast(rhs);
            if(!this.t1.equals(rhsTup.t1)) return false;
            if(!this.t2.equals(rhsTup.t2)) return false;
            return true;
        }
    }

    public static class Of3<T1, T2, T3> {
        interface F<T1, T2, T3, R> {
            public R apply(T1 t1, T2 t2, T3 t3);
        }

        interface VF<T1, T2, T3> {
            public void apply(T1 t1, T2 t2, T3 t3);
        }

        private final T1 t1;
        private final T2 t2;
        private final T3 t3;

        Of3(T1 t1, T2 t2, T3 t3) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
        }

        public T1 _1() { return t1; }
        public T2 _2() { return t2; }
        public T3 _3() { return t3; }

        public <R> R match(F<T1, T2, T3, R> f) {
            return f.apply(t1, t2, t3);
        }

        public void run(VF<T1, T2, T3> f) {
            f.apply(t1, t2, t3);
        }

        public boolean equals(Object rhs) {
            if(!Of3.class.isInstance(rhs)) return false;
            Of3<?, ?, ?> rhsTup = Of3.class.cast(rhs);
            if(!this.t1.equals(rhsTup.t1)) return false;
            if(!this.t2.equals(rhsTup.t2)) return false;
            if(!this.t3.equals(rhsTup.t3)) return false;
            return true;
        }
    }

    public static class Of4<T1, T2, T3, T4> {
        interface F<T1, T2, T3, T4, R> {
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4);
        }

        interface VF<T1, T2, T3, T4> {
            public void apply(T1 t1, T2 t2, T3 t3, T4 t4);
        }

        private final T1 t1;
        private final T2 t2;
        private final T3 t3;
        private final T4 t4;

        Of4(T1 t1, T2 t2, T3 t3, T4 t4) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
            this.t4 = t4;
        }

        public T1 _1() { return t1; }
        public T2 _2() { return t2; }
        public T3 _3() { return t3; }
        public T4 _4() { return t4; }

        public <R> R match(F<T1, T2, T3, T4, R> f) {
            return f.apply(t1, t2, t3, t4);
        }

        public void run(VF<T1, T2, T3, T4> f) {
            f.apply(t1, t2, t3, t4);
        }

        public boolean equals(Object rhs) {
            if(!Of4.class.isInstance(rhs)) return false;
            Of4<?, ?, ?, ?> rhsTup = Of4.class.cast(rhs);
            if(!this.t1.equals(rhsTup.t1)) return false;
            if(!this.t2.equals(rhsTup.t2)) return false;
            if(!this.t3.equals(rhsTup.t3)) return false;
            if(!this.t4.equals(rhsTup.t4)) return false;
            return true;
        }
    }

    public static class Of5<T1, T2, T3, T4, T5> {
        interface F<T1, T2, T3, T4, T5, R> {
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
        }

        interface VF<T1, T2, T3, T4, T5> {
            public void apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
        }

        private final T1 t1;
        private final T2 t2;
        private final T3 t3;
        private final T4 t4;
        private final T5 t5;

        Of5(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
            this.t4 = t4;
            this.t5 = t5;
        }

        public T1 _1() { return t1; }
        public T2 _2() { return t2; }
        public T3 _3() { return t3; }
        public T4 _4() { return t4; }
        public T5 _5() { return t5; }

        public <R> R match(F<T1, T2, T3, T4, T5, R> f) {
            return f.apply(t1, t2, t3, t4, t5);
        }

        public void run(VF<T1, T2, T3, T4, T5> f) {
            f.apply(t1, t2, t3, t4, t5);
        }

        public boolean equals(Object rhs) {
            if(!Of5.class.isInstance(rhs)) return false;
            Of5<?, ?, ?, ?, ?> rhsTup = Of5.class.cast(rhs);
            if(!this.t1.equals(rhsTup.t1)) return false;
            if(!this.t2.equals(rhsTup.t2)) return false;
            if(!this.t3.equals(rhsTup.t3)) return false;
            if(!this.t4.equals(rhsTup.t4)) return false;
            if(!this.t5.equals(rhsTup.t5)) return false;
            return true;
        }
    }

    public static class Of6<T1, T2, T3, T4, T5, T6> {
        interface F<T1, T2, T3, T4, T5, T6, R> {
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6);
        }

        interface VF<T1, T2, T3, T4, T5, T6> {
            public void apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6);
        }

        private final T1 t1;
        private final T2 t2;
        private final T3 t3;
        private final T4 t4;
        private final T5 t5;
        private final T6 t6;

        Of6(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
            this.t4 = t4;
            this.t5 = t5;
            this.t6 = t6;
        }

        public T1 _1() { return t1; }
        public T2 _2() { return t2; }
        public T3 _3() { return t3; }
        public T4 _4() { return t4; }
        public T5 _5() { return t5; }
        public T6 _6() { return t6; }

        public <R> R match(F<T1, T2, T3, T4, T5, T6, R> f) {
            return f.apply(t1, t2, t3, t4, t5, t6);
        }

        public void run(VF<T1, T2, T3, T4, T5, T6> f) {
            f.apply(t1, t2, t3, t4, t5, t6);
        }

        public boolean equals(Object rhs) {
            if(!Of6.class.isInstance(rhs)) return false;
            Of6<?, ?, ?, ?, ?, ?> rhsTup = Of6.class.cast(rhs);
            if(!this.t1.equals(rhsTup.t1)) return false;
            if(!this.t2.equals(rhsTup.t2)) return false;
            if(!this.t3.equals(rhsTup.t3)) return false;
            if(!this.t4.equals(rhsTup.t4)) return false;
            if(!this.t5.equals(rhsTup.t5)) return false;
            if(!this.t6.equals(rhsTup.t6)) return false;
            return true;
        }
    }

    public static class Of7<T1, T2, T3, T4, T5, T6, T7> {
        interface F<T1, T2, T3, T4, T5, T6, T7, R> {
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7);
        }

        interface VF<T1, T2, T3, T4, T5, T6, T7> {
            public void apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7);
        }

        private final T1 t1;
        private final T2 t2;
        private final T3 t3;
        private final T4 t4;
        private final T5 t5;
        private final T6 t6;
        private final T7 t7;

        Of7(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
            this.t4 = t4;
            this.t5 = t5;
            this.t6 = t6;
            this.t7 = t7;
        }

        public T1 _1() { return t1; }
        public T2 _2() { return t2; }
        public T3 _3() { return t3; }
        public T4 _4() { return t4; }
        public T5 _5() { return t5; }
        public T6 _6() { return t6; }
        public T7 _7() { return t7; }

        public <R> R match(F<T1, T2, T3, T4, T5, T6, T7, R> f) {
            return f.apply(t1, t2, t3, t4, t5, t6, t7);
        }

        public void run(VF<T1, T2, T3, T4, T5, T6, T7> f) {
            f.apply(t1, t2, t3, t4, t5, t6, t7);
        }

        public boolean equals(Object rhs) {
            if(!Of7.class.isInstance(rhs)) return false;
            Of7<?, ?, ?, ?, ?, ?, ?> rhsTup = Of7.class.cast(rhs);
            if(!this.t1.equals(rhsTup.t1)) return false;
            if(!this.t2.equals(rhsTup.t2)) return false;
            if(!this.t3.equals(rhsTup.t3)) return false;
            if(!this.t4.equals(rhsTup.t4)) return false;
            if(!this.t5.equals(rhsTup.t5)) return false;
            if(!this.t6.equals(rhsTup.t6)) return false;
            if(!this.t7.equals(rhsTup.t7)) return false;
            return true;
        }
    }

    public static class Of8<T1, T2, T3, T4, T5, T6, T7, T8> {
        interface F<T1, T2, T3, T4, T5, T6, T7, T8, R> {
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8);
        }

        interface VF<T1, T2, T3, T4, T5, T6, T7, T8> {
            public void apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8);
        }

        private final T1 t1;
        private final T2 t2;
        private final T3 t3;
        private final T4 t4;
        private final T5 t5;
        private final T6 t6;
        private final T7 t7;
        private final T8 t8;

        Of8(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
            this.t4 = t4;
            this.t5 = t5;
            this.t6 = t6;
            this.t7 = t7;
            this.t8 = t8;
        }

        public T1 _1() { return t1; }
        public T2 _2() { return t2; }
        public T3 _3() { return t3; }
        public T4 _4() { return t4; }
        public T5 _5() { return t5; }
        public T6 _6() { return t6; }
        public T7 _7() { return t7; }
        public T8 _8() { return t8; }

        public <R> R match(F<T1, T2, T3, T4, T5, T6, T7, T8, R> f) {
            return f.apply(t1, t2, t3, t4, t5, t6, t7, t8);
        }

        public void run(VF<T1, T2, T3, T4, T5, T6, T7, T8> f) {
            f.apply(t1, t2, t3, t4, t5, t6, t7, t8);
        }

        public boolean equals(Object rhs) {
            if(!Of8.class.isInstance(rhs)) return false;
            Of8<?, ?, ?, ?, ?, ?, ?, ?> rhsTup = Of8.class.cast(rhs);
            if(!this.t1.equals(rhsTup.t1)) return false;
            if(!this.t2.equals(rhsTup.t2)) return false;
            if(!this.t3.equals(rhsTup.t3)) return false;
            if(!this.t4.equals(rhsTup.t4)) return false;
            if(!this.t5.equals(rhsTup.t5)) return false;
            if(!this.t6.equals(rhsTup.t6)) return false;
            if(!this.t7.equals(rhsTup.t7)) return false;
            if(!this.t8.equals(rhsTup.t8)) return false;
            return true;
        }
    }
}
