package io.github.sefiraat.charmtech.lib.utils;

import io.github.sefiraat.charmtech.CharmTech;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;

public class Flags {

    public static final String flagIsCharm = "ct_is_charm";
    public static final String flagCharmEffects = "ct_charm_effects";

    public static void setFlagIsCharm(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagIsCharm);
        c.set(key, PersistentDataType.INTEGER, 1);
        i.setItemMeta(im);
    }

    public static boolean hasFlagIsCharm(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagIsCharm);
        return c.has(key, PersistentDataType.INTEGER);
    }

    public static void addFlagEffect(CharmTech plugin, ItemStack i, String effect) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmEffects);
        if (c.has(key, PersistentDataType.STRING)) {
            String currentEffects = c.get(key, PersistentDataType.STRING);
            currentEffects = currentEffects + ";" + effect;
            c.set(key, PersistentDataType.STRING, currentEffects);
        } else {
            c.set(key, PersistentDataType.STRING, effect);
        }
        i.setItemMeta(im);
    }

    @Nullable
    public static String getFlagEffects(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmEffects);
        if (c.has(key, PersistentDataType.STRING)) {
            return c.get(key, PersistentDataType.STRING);
        } else {
            return null;
        }
    }

}
