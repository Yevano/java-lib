package yevano.bukkit.command;

public interface ArgParser<A> {
    A parse(String arg);
}
