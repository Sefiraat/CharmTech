package io.github.sefiraat.charmtech.finals;

import net.md_5.bungee.api.ChatColor;

public class ItemDetails {

    private ItemDetails() {
        throw new IllegalStateException("Utility class");
    }

    public static final ChatColor COLOR_RARITY_1 = ChatColor.DARK_GRAY;
    public static final ChatColor COLOR_RARITY_2 = ChatColor.GRAY;
    public static final ChatColor COLOR_RARITY_3 = ChatColor.GREEN;
    public static final ChatColor COLOR_RARITY_4 = ChatColor.BLUE;
    public static final ChatColor COLOR_RARITY_5 = ChatColor.LIGHT_PURPLE;
    public static final ChatColor COLOR_RARITY_6 = ChatColor.GOLD;
    public static final ChatColor COLOR_RARITY_7 = ChatColor.DARK_RED;

    public static final String ITEM_RARITY_1 = "" + COLOR_RARITY_1 + "[Trash]";
    public static final String ITEM_RARITY_2 = "" + COLOR_RARITY_2 + "[Common]";
    public static final String ITEM_RARITY_3 = "" + COLOR_RARITY_3 + "[Uncommon]";
    public static final String ITEM_RARITY_4 = "" + COLOR_RARITY_4 + "[Rare]";
    public static final String ITEM_RARITY_5 = "" + COLOR_RARITY_5 + "[Epic]";
    public static final String ITEM_RARITY_6 = "" + COLOR_RARITY_6 + "[Legendary]";
    public static final String ITEM_RARITY_7 = "" + COLOR_RARITY_7 + "[Mythic]";

    public static final String ADDITIONAL_EFFECTS = "" + ChatColor.RED + "Additional Effects:";

    public static final String GUI_DISPLAY_NAME_FILLER = "" + ChatColor.GRAY + "Aren't you charming?";
    public static final String GUI_CHARM_DELETED = "" + ChatColor.RED + "Charm Deleted";
}
