package io.github.sefiraat.charmtech.lib.utils;

import io.github.sefiraat.charmtech.CharmTech;
import io.github.sefiraat.charmtech.finals.ItemDetails;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    @Nullable
    public static List<ItemStack> getPlayerCharms(CharmTech plugin, Player p) {
        List<ItemStack> l = new ArrayList<>();
        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null && Flags.hasFlagIsCharm(plugin, i)) {
                l.add(i);
            }
        }
        if (!l.isEmpty()) {
            return l;
        } else {
            return null;
        }
    }

    public static long getNextItemID(CharmTech plugin) {
        ConfigurationSection sec = plugin.getInstance().getCharmItemsConfig().getConfigurationSection("CHARMS");
        int nextValue = 1;
        if (sec != null) {
            for (String key : sec.getKeys(false)) {
                int value = Integer.parseInt(key);
                if (value > nextValue) {
                    nextValue = value;
                }
            }
            nextValue++;
        }
        return nextValue;
    }

    public static List<ItemStack> getAllCharms(CharmTech plugin) {
        List<ItemStack> l = new ArrayList<>();
        ConfigurationSection sec = plugin.getInstance().getCharmItemsConfig().getConfigurationSection("CHARMS");
        if (sec != null) {
            for (String s : sec.getKeys(false)) {
                Long charmID = Long.valueOf(s);
                ItemStack i = sec.getItemStack(charmID + ".ITEM").clone();
                NamespacedKey key = new NamespacedKey(plugin,"charm-id");
                ItemMeta im = i.getItemMeta();
                im.getPersistentDataContainer().set(key, PersistentDataType.LONG, charmID);
                i.setItemMeta(im);
                l.add(i);
            }
        }
        return l;
    }

    public static PotionEffectType getPotionTypeFromString(String s) {
        switch (s) {
            case "ABSORPTION": { return PotionEffectType.ABSORPTION; }
            case "BAD_OMEN": { return PotionEffectType.BAD_OMEN; }
            case "BLINDNESS": { return PotionEffectType.BLINDNESS; }
            case "CONDUIT_POWER": { return PotionEffectType.CONDUIT_POWER; }
            case "CONFUSION": { return PotionEffectType.CONFUSION; }
            case "DAMAGE_RESISTANCE": { return PotionEffectType.DAMAGE_RESISTANCE; }
            case "DOLPHINS_GRACE": { return PotionEffectType.DOLPHINS_GRACE; }
            case "FAST_DIGGING": { return PotionEffectType.FAST_DIGGING; }
            case "FIRE_RESISTANCE": { return PotionEffectType.FIRE_RESISTANCE; }
            case "GLOWING": { return PotionEffectType.GLOWING; }
            case "HARM": { return PotionEffectType.HARM; }
            case "HEAL": { return PotionEffectType.HEAL; }
            case "HEALTH_BOOST": { return PotionEffectType.HEALTH_BOOST; }
            case "HERO_OF_THE_VILLAGE": { return PotionEffectType.HERO_OF_THE_VILLAGE; }
            case "HUNGER": { return PotionEffectType.HUNGER; }
            case "INCREASE_DAMAGE": { return PotionEffectType.INCREASE_DAMAGE; }
            case "INVISIBILITY": { return PotionEffectType.INVISIBILITY; }
            case "JUMP": { return PotionEffectType.JUMP; }
            case "LEVITATION": { return PotionEffectType.LEVITATION; }
            case "NIGHT_VISION": { return PotionEffectType.NIGHT_VISION; }
            case "POISON": { return PotionEffectType.POISON; }
            case "REGENERATION": { return PotionEffectType.REGENERATION; }
            case "SATURATION": { return PotionEffectType.SATURATION; }
            case "SLOW": { return PotionEffectType.SLOW; }
            case "SLOW_DIGGING": { return PotionEffectType.SLOW_DIGGING; }
            case "SLOW_FALLING": { return PotionEffectType.SLOW_FALLING; }
            case "SPEED": { return PotionEffectType.SPEED; }
            case "UNLUCK": { return PotionEffectType.UNLUCK; }
            case "WATER_BREATHING": { return PotionEffectType.WATER_BREATHING; }
            case "WEAKNESS": { return PotionEffectType.WEAKNESS; }
            case "WITHER": { return PotionEffectType.WITHER; }
            case "LUCK":
            default: { return PotionEffectType.LUCK; }
        }
    }

    public static List<String> buildLore(CharmTech plugin, ItemStack i) {
        List<String> l = new ArrayList<>();
        if (Flags.hasFlagLore1(plugin, i)) {
            l.add(ChatColor.GRAY + Flags.getFlagLore1(plugin, i));
        }
        if (Flags.hasFlagLore2(plugin, i)) {
            l.add(ChatColor.GRAY + Flags.getFlagLore2(plugin, i));
        }
        if (Flags.hasFlagLore3(plugin, i)) {
            l.add(ChatColor.GRAY + Flags.getFlagLore3(plugin, i));
        }
        if (Flags.hasFlagLore4(plugin, i)) {
            l.add(ChatColor.GRAY + Flags.getFlagLore4(plugin, i));
        }
        if (Flags.hasFlagLore5(plugin, i)) {
            l.add(ChatColor.GRAY + Flags.getFlagLore5(plugin, i));
        }
        return l;
    }

    public static void rebuildCharmMeta(CharmTech plugin, ItemStack i) {
        String mythos = Flags.getFlagMythos(plugin, i);
        String name = Flags.getFlagName(plugin, i);
        String value = String.valueOf(Flags.getFlagValue(plugin, i));
        ChatColor col = getMythosColor(mythos);
        List<String> lore = buildLore(plugin, i);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(col + name);
        List<String> all = new ArrayList<>();
        all.add(ChatColor.DARK_PURPLE + "Charm");
        all.add("");
        all.add(ChatColor.WHITE + "Item Tier: " + ChatColor.GRAY + "[" + col + getMythosUser(mythos) + ChatColor.GRAY + "]");
        all.add("");
        if (!lore.isEmpty()) {
            all.add(ChatColor.WHITE + "Item Description:");
            for (String s : lore) {
                all.add(s);
            }
            all.add("");
        }
        if (value.equals("-1")) {
            value = "Priceless";
        }
        all.add(ChatColor.WHITE + "Value: " + ChatColor.GOLD + "⛃ " + value);
        im.setLore(all);
        i.setItemMeta(im);
    }

    public static ChatColor getMythosColor(String s) {
        switch(s) {
            case "COMMON": return ItemDetails.COLOR_RARITY_2;
            case "UNCOMMON": return ItemDetails.COLOR_RARITY_3;
            case "RARE": return ItemDetails.COLOR_RARITY_4;
            case "EPIC": return ItemDetails.COLOR_RARITY_5;
            case "LEGENDARY": return ItemDetails.COLOR_RARITY_6;
            case "MYTHIC": return  ItemDetails.COLOR_RARITY_7;
            default: return ItemDetails.COLOR_RARITY_1;
        }
    }

    public static String getMythosUser(String s) {
        switch(s) {
            case "COMMON": return "Common";
            case "UNCOMMON": return "Uncommon";
            case "RARE": return "Rare";
            case "EPIC": return "Epic";
            case "LEGENDARY": return "Legendary";
            case "MYTHIC": return  "Mythic";
            default: return "Trash";
        }
    }



    /**
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(Random r, int min, int max) {
        int randomNum = r.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}
