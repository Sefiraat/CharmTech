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

    public static final String ITEM_RARITY_1 = "" + COLOR_RARITY_1 + "[TRASH]";
    public static final String ITEM_RARITY_2 = "" + COLOR_RARITY_2 + "[COMMON]";
    public static final String ITEM_RARITY_3 = "" + COLOR_RARITY_3 + "[UNCOMMON]";
    public static final String ITEM_RARITY_4 = "" + COLOR_RARITY_4 + "[RARE]";
    public static final String ITEM_RARITY_5 = "" + COLOR_RARITY_5 + "[EPIC]";
    public static final String ITEM_RARITY_6 = "" + COLOR_RARITY_6 + "[LEGENDARY]";

    public static final String ADDITIONAL_EFFECTS = "" + ChatColor.RED + "Additional Effects:";

    public static final String GUI_DISPLAY_NAME_FILLER = "" + ChatColor.GRAY + "Aren't you charming?";
    public static final String GUI_CHARM_DELETED = "" + ChatColor.RED + "Charm Deleted";
}
