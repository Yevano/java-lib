package yevano.util;

import java.io.PrintWriter;

public class Format {
    public interface Context {
        default String stringf(String format, Object... args) {
            return Format.string(format, args);
        }

        public static void printf(PrintWriter writer, String format, Object... args) {
            Format.print(writer, format, args);
        }

        public static void printfln(PrintWriter writer, String format, Object... args) {
            Format.println(writer, format, args);
        }
    }

    public static String string(String format, Object... args) {
        return String.format(format, args);
    }

    public static void print(PrintWriter writer, String format, Object... args) {
        writer.printf(format, args);
    }

    public static void println(PrintWriter writer, String format, Object... args) {
        writer.printf(format + "%n", args);
    }

    public static class FormatExt extends Format {
        public static void a() {
            FormatExt.string("format");
        }
    }
}
