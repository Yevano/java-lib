package yevano.util;

public class Unit {
    public static final Unit unit = new Unit();
    private Unit() { }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Unit;
    }

    @Override
    public String toString() {
        return "()";
    }
}
