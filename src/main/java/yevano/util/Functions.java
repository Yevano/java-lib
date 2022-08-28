package yevano.util;

import java.util.function.Consumer;

public class Functions {
    public static <A> Fun<A, Unit> k(Consumer<A> f) {
        return x -> {
            f.accept(x);
            return Unit.unit;
        };
    }

    public static <A> A id(A x) { return x; }

    public static <A1, A2, B> Fun2<A1, A2, B> roll2(Fun<A1, Fun<A2, B>> f) {
        return (x1, x2) -> f.apply(x1).apply(x2);
    }

    public static <A1, A2, A3, B> Fun3<A1, A2, A3, B> roll3(Fun<A1, Fun<A2, Fun<A3, B>>> f) {
        return (x1, x2, x3) -> f.apply(x1).apply(x2).apply(x3);
    }

    public static <A1, A2, A3, A4, B> Fun4<A1, A2, A3, A4, B> roll4(Fun<A1, Fun<A2, Fun<A3, Fun<A4, B>>>> f) {
        return (x1, x2, x3, x4) -> f.apply(x1).apply(x2).apply(x3).apply(x4);
    }

    public static <A1, A2, A3, A4, A5, B> Fun5<A1, A2, A3, A4, A5, B> roll5(Fun<A1, Fun<A2, Fun<A3, Fun<A4, Fun<A5, B>>>>> f) {
        return (x1, x2, x3, x4, x5) -> f.apply(x1).apply(x2).apply(x3).apply(x4).apply(x5);
    }

    public static <A1, A2, A3, A4, A5, A6, B> Fun6<A1, A2, A3, A4, A5, A6, B> roll6(Fun<A1, Fun<A2, Fun<A3, Fun<A4, Fun<A5, Fun<A6, B>>>>>> f) {
        return (x1, x2, x3, x4, x5, x6) -> f.apply(x1).apply(x2).apply(x3).apply(x4).apply(x5).apply(x6);
    }

    public static <A1, A2, A3, A4, A5, A6, A7, B> Fun7<A1, A2, A3, A4, A5, A6, A7, B> roll7(Fun<A1, Fun<A2, Fun<A3, Fun<A4, Fun<A5, Fun<A6, Fun<A7, B>>>>>>> f) {
        return (x1, x2, x3, x4, x5, x6, x7) -> f.apply(x1).apply(x2).apply(x3).apply(x4).apply(x5).apply(x6).apply(x7);
    }

    public static <A1, A2, A3, A4, A5, A6, A7, A8, B> Fun8<A1, A2, A3, A4, A5, A6, A7, A8, B> roll8(Fun<A1, Fun<A2, Fun<A3, Fun<A4, Fun<A5, Fun<A6, Fun<A7, Fun<A8, B>>>>>>>> f) {
        return (x1, x2, x3, x4, x5, x6, x7, x8) -> f.apply(x1).apply(x2).apply(x3).apply(x4).apply(x5).apply(x6).apply(x7).apply(x8);
    }

    public static <A1, A2, B> Fun2<A1, A2, B> fun(Fun2<A1, A2, B> f) { return f; }
    public static <A1, A2, A3, B> Fun3<A1, A2, A3, B> fun(Fun3<A1, A2, A3, B> f) { return f; }
    public static <A1, A2, A3, A4, B> Fun4<A1, A2, A3, A4, B> fun(Fun4<A1, A2, A3, A4, B> f) { return f; }
    public static <A1, A2, A3, A4, A5, B> Fun5<A1, A2, A3, A4, A5, B> fun(Fun5<A1, A2, A3, A4, A5, B> f) { return f; }
    public static <A1, A2, A3, A4, A5, A6, B> Fun6<A1, A2, A3, A4, A5, A6, B> fun(Fun6<A1, A2, A3, A4, A5, A6, B> f) { return f; }
    public static <A1, A2, A3, A4, A5, A6, A7, B> Fun7<A1, A2, A3, A4, A5, A6, A7, B> fun(Fun7<A1, A2, A3, A4, A5, A6, A7, B> f) { return f; }
    public static <A1, A2, A3, A4, A5, A6, A7, A8, B> Fun8<A1, A2, A3, A4, A5, A6, A7, A8, B> fun(Fun8<A1, A2, A3, A4, A5, A6, A7, A8, B> f) { return f; }

    public interface Fun2<A1, A2, B> {
        B apply(A1 x1, A2 x2);
        default Fun<A1, Fun<A2, B>> unroll() { return x1 -> x2 -> apply(x1, x2); }
        default Fun<A2, B> apply(A1 x1) { return x2 -> apply(x1, x2); }
        default Fun<A1, B> apply2(A2 x2) { return x1 -> apply(x1, x2); }
    }

    public interface Fun3<A1, A2, A3, B> {
        B apply(A1 x1, A2 x2, A3 x3);
        default Fun<A1, Fun<A2, Fun<A3, B>>> unroll() { return x1 -> x2 -> x3 -> apply(x1, x2, x3); }
        default Fun2<A2, A3, B> apply (A1 x1) { return (x2, x3) -> apply(x1, x2, x3); }
        default Fun2<A1, A3, B> apply2(A2 x2) { return (x1, x3) -> apply(x1, x2, x3); }
    }

    public interface Fun4<A1, A2, A3, A4, B> {
        B apply(A1 x1, A2 x2, A3 x3, A4 x4);
        default Fun<A1, Fun<A2, Fun<A3, Fun<A4, B>>>> unroll() { return x1 -> x2 -> x3 -> x4 -> apply(x1, x2, x3, x4); }
        default Fun3<A2, A3, A4, B> apply (A1 x1) { return (x2, x3, x4) -> apply(x1, x2, x3, x4); }
        default Fun3<A1, A3, A4, B> apply2(A2 x2) { return (x1, x3, x4) -> apply(x1, x2, x3, x4); }
        default Fun3<A1, A2, A4, B> apply3(A3 x3) { return (x1, x2, x4) -> apply(x1, x2, x3, x4); }
        default Fun3<A1, A2, A3, B> apply4(A4 x4) { return (x1, x2, x3) -> apply(x1, x2, x3, x4); }
    }

    public interface Fun5<A1, A2, A3, A4, A5, B> {
        B apply(A1 x1, A2 x2, A3 x3, A4 x4, A5 x5);
        default Fun<A1, Fun<A2, Fun<A3, Fun<A4, Fun<A5, B>>>>> unroll() { return x1 -> x2 -> x3 -> x4 -> x5 -> apply(x1, x2, x3, x4, x5); }
        default Fun4<A2, A3, A4, A5, B> apply (A1 x1) { return (x2, x3, x4, x5) -> apply(x1, x2, x3, x4, x5); }
        default Fun4<A1, A3, A4, A5, B> apply2(A2 x2) { return (x1, x3, x4, x5) -> apply(x1, x2, x3, x4, x5); }
        default Fun4<A1, A2, A4, A5, B> apply3(A3 x3) { return (x1, x2, x4, x5) -> apply(x1, x2, x3, x4, x5); }
        default Fun4<A1, A2, A3, A5, B> apply4(A4 x4) { return (x1, x2, x3, x5) -> apply(x1, x2, x3, x4, x5); }
        default Fun4<A1, A2, A3, A4, B> apply5(A5 x5) { return (x1, x2, x3, x4) -> apply(x1, x2, x3, x4, x5); }
    }

    public interface Fun6<A1, A2, A3, A4, A5, A6, B> {
        B apply(A1 x1, A2 x2, A3 x3, A4 x4, A5 x5, A6 x6);
        default Fun<A1, Fun<A2, Fun<A3, Fun<A4, Fun<A5, Fun<A6, B>>>>>> unroll() { return x1 -> x2 -> x3 -> x4 -> x5 -> x6 -> apply(x1, x2, x3, x4, x5, x6); }
        default Fun5<A2, A3, A4, A5, A6, B> apply (A1 x1) { return (x2, x3, x4, x5, x6) -> apply(x1, x2, x3, x4, x5, x6); }
        default Fun5<A1, A3, A4, A5, A6, B> apply2(A2 x2) { return (x1, x3, x4, x5, x6) -> apply(x1, x2, x3, x4, x5, x6); }
        default Fun5<A1, A2, A4, A5, A6, B> apply3(A3 x3) { return (x1, x2, x4, x5, x6) -> apply(x1, x2, x3, x4, x5, x6); }
        default Fun5<A1, A2, A3, A5, A6, B> apply4(A4 x4) { return (x1, x2, x3, x5, x6) -> apply(x1, x2, x3, x4, x5, x6); }
        default Fun5<A1, A2, A3, A4, A6, B> apply5(A5 x5) { return (x1, x2, x3, x4, x6) -> apply(x1, x2, x3, x4, x5, x6); }
        default Fun5<A1, A2, A3, A4, A5, B> apply6(A6 x6) { return (x1, x2, x3, x4, x5) -> apply(x1, x2, x3, x4, x5, x6); }
    }

    public interface Fun7<A1, A2, A3, A4, A5, A6, A7, B> {
        B apply(A1 x1, A2 x2, A3 x3, A4 x4, A5 x5, A6 x6, A7 x7);
        default Fun<A1, Fun<A2, Fun<A3, Fun<A4, Fun<A5, Fun<A6, Fun<A7, B>>>>>>> unroll() { return x1 -> x2 -> x3 -> x4 -> x5 -> x6 -> x7 -> apply(x1, x2, x3, x4, x5, x6, x7); }
        default Fun6<A2, A3, A4, A5, A6, A7, B> apply (A1 x1) { return (x2, x3, x4, x5, x6, x7) -> apply(x1, x2, x3, x4, x5, x6, x7); }
        default Fun6<A1, A3, A4, A5, A6, A7, B> apply2(A2 x2) { return (x1, x3, x4, x5, x6, x7) -> apply(x1, x2, x3, x4, x5, x6, x7); }
        default Fun6<A1, A2, A4, A5, A6, A7, B> apply3(A3 x3) { return (x1, x2, x4, x5, x6, x7) -> apply(x1, x2, x3, x4, x5, x6, x7); }
        default Fun6<A1, A2, A3, A5, A6, A7, B> apply4(A4 x4) { return (x1, x2, x3, x5, x6, x7) -> apply(x1, x2, x3, x4, x5, x6, x7); }
        default Fun6<A1, A2, A3, A4, A6, A7, B> apply5(A5 x5) { return (x1, x2, x3, x4, x6, x7) -> apply(x1, x2, x3, x4, x5, x6, x7); }
        default Fun6<A1, A2, A3, A4, A5, A7, B> apply6(A6 x6) { return (x1, x2, x3, x4, x5, x7) -> apply(x1, x2, x3, x4, x5, x6, x7); }
        default Fun6<A1, A2, A3, A4, A5, A6, B> apply7(A7 x7) { return (x1, x2, x3, x4, x5, x6) -> apply(x1, x2, x3, x4, x5, x6, x7); }
    }

    public interface Fun8<A1, A2, A3, A4, A5, A6, A7, A8, B> {
        B apply(A1 x1, A2 x2, A3 x3, A4 x4, A5 x5, A6 x6, A7 x7, A8 x8);
        default Fun<A1, Fun<A2, Fun<A3, Fun<A4, Fun<A5, Fun<A6, Fun<A7, Fun<A8, B>>>>>>>> unroll() { return x1 -> x2 -> x3 -> x4 -> x5 -> x6 -> x7 -> x8 -> apply(x1, x2, x3, x4, x5, x6, x7, x8); }
        default Fun7<A2, A3, A4, A5, A6, A7, A8, B> apply (A1 x1) { return (x2, x3, x4, x5, x6, x7, x8) -> apply(x1, x2, x3, x4, x5, x6, x7, x8); }
        default Fun7<A1, A3, A4, A5, A6, A7, A8, B> apply2(A2 x2) { return (x1, x3, x4, x5, x6, x7, x8) -> apply(x1, x2, x3, x4, x5, x6, x7, x8); }
        default Fun7<A1, A2, A4, A5, A6, A7, A8, B> apply3(A3 x3) { return (x1, x2, x4, x5, x6, x7, x8) -> apply(x1, x2, x3, x4, x5, x6, x7, x8); }
        default Fun7<A1, A2, A3, A5, A6, A7, A8, B> apply4(A4 x4) { return (x1, x2, x3, x5, x6, x7, x8) -> apply(x1, x2, x3, x4, x5, x6, x7, x8); }
        default Fun7<A1, A2, A3, A4, A6, A7, A8, B> apply5(A5 x5) { return (x1, x2, x3, x4, x6, x7, x8) -> apply(x1, x2, x3, x4, x5, x6, x7, x8); }
        default Fun7<A1, A2, A3, A4, A5, A7, A8, B> apply6(A6 x6) { return (x1, x2, x3, x4, x5, x7, x8) -> apply(x1, x2, x3, x4, x5, x6, x7, x8); }
        default Fun7<A1, A2, A3, A4, A5, A6, A8, B> apply7(A7 x7) { return (x1, x2, x3, x4, x5, x6, x8) -> apply(x1, x2, x3, x4, x5, x6, x7, x8); }
        default Fun7<A1, A2, A3, A4, A5, A6, A7, B> apply8(A8 x8) { return (x1, x2, x3, x4, x5, x6, x7) -> apply(x1, x2, x3, x4, x5, x6, x7, x8); }
    }
}
