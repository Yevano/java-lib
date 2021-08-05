package yevano.bukkit.command;

@CommandDesc(name = "setattr", aliases = { "setat" })
public class CommandImpl {
    @PositionalArg public String arg1;
    @PositionalArg public String arg2;

    @Flag public boolean flag1;
    @Flag public boolean flag2;

    public void setAttr() {

    }
}
