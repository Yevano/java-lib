package yevano.bukkit;

import java.util.regex.Pattern;

import lombok.NonNull;

/**
 * $.b($.i())
 */

public class RichText {
    public class TextBuilder {
        public TextBuilder() {

        }

        public TextBuilder black() {
            //newFormat.color = "0"
            return new TextBuilder();
        }
    
        public TextBuilder darkblue() {
            //newFormat.color = "1"
            return new TextBuilder();
        }
    
        public TextBuilder darkgreen() {
            //newFormat.color = "2"
            return new TextBuilder();
        }
    
        public TextBuilder darkaqua() {
            //newFormat.color = "3"
            return new TextBuilder();
        }
    
        public TextBuilder darkred() {
            //newFormat.color = "4"
            return new TextBuilder();
        }
    
        public TextBuilder darkpurple() {
            //newFormat.color = "5"
            return new TextBuilder();
        }
    
        public TextBuilder gold() {
            //newFormat.color = "6"
            return new TextBuilder();
        }
    
        public TextBuilder gray() {
            //newFormat.color = "7"
            return new TextBuilder();
        }
    
        public TextBuilder darkgray() {
            //newFormat.color = "8"
            return new TextBuilder();
        }
    
        public TextBuilder blue() {
            //newFormat.color = "9"
            return new TextBuilder();
        }
    
        public TextBuilder green() {
            //newFormat.color = "a"
            return new TextBuilder();
        }
    
        public TextBuilder aqua() {
            //newFormat.color = "b"
            return new TextBuilder();
        }
    
        public TextBuilder red() {
            //newFormat.color = "c"
            return new TextBuilder();
        }
    
        public TextBuilder lightpurple() {
            //newFormat.color = "d"
            return new TextBuilder();
        }
    
        public TextBuilder yellow() {
            //newFormat.color = "e"
            return new TextBuilder();
        }
    
        public TextBuilder white() {
            //newFormat.color = "f"
            return new TextBuilder();
        }
    }

    private static final Pattern chatEscapePattern = Pattern.compile("\u00a7.?");

    public interface Context {

    }

    public static String escape(@NonNull String format) {
        return format.replace('&', '\u00a7');
    }

    public static String sanitize(@NonNull String text) {
        return chatEscapePattern.matcher(text).replaceAll("");
    }
}
