package io.github.sefiraat.charmtech.finals;

import net.md_5.bungee.api.ChatColor;

public class Messages {

    // General
    public static final String Prefix = "" + ChatColor.GRAY + "[CharmTech] ";
    public static final String Suffix = "" + ChatColor.GRAY + "";

    public static final String Warning = "" + ChatColor.YELLOW;
    public static final String Error = "" + ChatColor.RED;
    public static final String Notice = "" + ChatColor.WHITE;
    public static final String Passive = "" + ChatColor.GRAY;
    public static final String Success = "" + ChatColor.GREEN;

    // Commands
    public static final String MessageCommandSubcommand = Prefix + Notice + "Please select a valid sub command.";
    public static final String MessageCommandSetCharmMustHold = Prefix + Warning + "You must be holding an item.";
    public static final String MessageCommandSetCharmSuccess = Prefix + Success + "Item converted to charm.";
    public static final String MessageCommandAddEffectMustCharm = Prefix + Warning + "You must be holding a charm.";
    public static final String MessageCommandAddEffectSuccess = Prefix + Success + "Effect added to charm.";

}
