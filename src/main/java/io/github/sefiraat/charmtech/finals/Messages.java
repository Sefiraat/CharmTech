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
    public static final String MessageCommandAddEffectSuccess = Prefix + Success + "Effect set added to charm.";
    public static final String MessageCommandCharmSaved = Prefix + Success + "Charm added to library.";
    public static final String MessageCommandAddMythosSuccess = Prefix + Success + "Mythos set.";
    public static final String MessageCommandAddLoreSuccess = Prefix + Success + "Lore line set.";
    public static final String MessageCommandAddValueSuccess = Prefix + Success + "Value set.";

    // GUI - Admin

    public static final String MessageGUICharmDeleted = Prefix + Warning + "Charm Deleted.";
    public static final String MessageGUICharmGiven = Prefix + Success + "Charm summoned.";
}
