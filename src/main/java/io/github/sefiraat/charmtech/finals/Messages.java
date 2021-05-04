package io.github.sefiraat.charmtech.finals;

import net.md_5.bungee.api.ChatColor;

public class Messages {

    private Messages() {
        throw new IllegalStateException("Utility class");
    }

    // General
    public static final String PREFIX = "" + ChatColor.GRAY + "[CharmTech] ";
    public static final String SUFFIX = "" + ChatColor.GRAY + "";

    public static final String WARNING = "" + ChatColor.YELLOW;
    public static final String ERROR = "" + ChatColor.RED;
    public static final String NOTICE = "" + ChatColor.WHITE;
    public static final String PASSIVE = "" + ChatColor.GRAY;
    public static final String SUCCESS = "" + ChatColor.GREEN;

    // Commands
    public static final String MESSAGE_COMMAND_SUBCOMMAND = PREFIX + NOTICE + "Please select a valid sub command.";
    public static final String MESSAGE_COMMAND_SET_CHARM_MUST_HOLD = PREFIX + WARNING + "You must be holding an item.";
    public static final String MESSAGE_COMMAND_SET_CHARM_SUCCESS = PREFIX + SUCCESS + "Item converted to charm.";
    public static final String MESSAGE_COMMAND_ADD_EFFECT_MUST_CHARM = PREFIX + WARNING + "You must be holding a charm.";
    public static final String MESSAGE_COMMAND_ADD_EFFECT_SUCCESS = PREFIX + SUCCESS + "Effect set added to charm.";
    public static final String MESSAGE_COMMAND_CHARM_SAVED = PREFIX + SUCCESS + "Charm added to library.";
    public static final String MESSAGE_COMMAND_ADD_MYTHOS_SUCCESS = PREFIX + SUCCESS + "Mythos set.";
    public static final String MESSAGE_COMMAND_ADD_LORE_SUCCESS = PREFIX + SUCCESS + "Lore line set.";
    public static final String MESSAGE_COMMAND_ADD_VALUE_SUCCESS = PREFIX + SUCCESS + "Value set.";

    // GUI - Admin

    public static final String MESSAGE_GUI_CHARM_DELETED = PREFIX + WARNING + "Charm Deleted.";
    public static final String MESSAGE_GUI_CHARM_GIVEN = PREFIX + SUCCESS + "Charm summoned.";
}
