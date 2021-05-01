package io.github.sefiraat.charmtech.lib.utils;

import io.github.sefiraat.charmtech.CharmTech;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class Flags {

    public static final String flagIsCharm = "ct_is_charm";

    public static void setFlagIsCharm(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagIsCharm);
        c.set(key, PersistentDataType.INTEGER, 1);
    }

    public static boolean hasFlagIsCharm(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagIsCharm);
        return c.has(key, PersistentDataType.INTEGER);
    }


}
