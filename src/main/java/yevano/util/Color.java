package yevano.util;

public class Color {
    public interface Context {
        default Color rgb(double r, double g, double b) {
            return Color.rgb(r, g, b);
        }
    }

    public static Color rgb(double r, double g, double b) {
        return new Color(r, g, b);
    }

    private final double r;
    private final double g;
    private final double b;

    Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public double r() {
        return r;
    }

    public double g() {
        return g;
    }

    public double b() {
        return b;
    }
}
