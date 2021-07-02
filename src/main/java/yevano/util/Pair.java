package yevano.util;

public class Pair<Fst, Snd> extends Tuple.Of2<Fst, Snd> {
    public static <Fst, Snd> Pair<Fst, Snd> of(Fst fst, Snd snd) {
        return new Pair<>(fst, snd);
    }

    Pair(Fst t1, Snd t2) {
        super(t1, t2);
    }
}
