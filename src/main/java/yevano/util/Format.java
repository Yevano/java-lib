package yevano.util;

import java.io.PrintWriter;

public class Format {
    public static String stringf(String format, Object... args) {
        return String.format(format, args);
    }

    public static void printf(PrintWriter writer, String format, Object... args) {
        writer.printf(format, args);
    }

    public static void printfln(PrintWriter writer, String format, Object... args) {
        writer.printf(format + "%n", args);
    }
}
