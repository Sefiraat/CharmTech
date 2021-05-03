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
    public static final String flagCharmMythos = "ct_charm_mythos";
    public static final String flagCharmName = "ct_charm_name";
    public static final String flagCharmValue = "ct_charm_value";
    public static final String flagCharmLore1 = "ct_charm_lore1";
    public static final String flagCharmLore2 = "ct_charm_lore2";
    public static final String flagCharmLore3 = "ct_charm_lore3";
    public static final String flagCharmLore4 = "ct_charm_lore4";
    public static final String flagCharmLore5 = "ct_charm_lore5";

    public static void addFlagIsCharm(CharmTech plugin, ItemStack i) {
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

    public static Boolean getFlagIsCharm(CharmTech plugin, ItemStack i) {
        if (hasFlagIsCharm(plugin, i)) {
            ItemMeta im = i.getItemMeta();
            PersistentDataContainer c = im.getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(plugin, flagIsCharm);
            switch(Integer.valueOf(c.get(key, PersistentDataType.STRING))) {
                case 1: return true;
                default: return false;
            }
        } else {
            return false;
        }
    }

    public static void addFlagEffect(CharmTech plugin, ItemStack i, String effect) {
        ItemMeta im = i.getItemMeta();
        assert im != null;
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

    public static void addFlagMythos(CharmTech plugin, ItemStack i, String mythos) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmMythos);
        c.set(key, PersistentDataType.STRING, mythos);
        i.setItemMeta(im);
    }

    public static boolean hasFlagMythos(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmMythos);
        return c.has(key, PersistentDataType.STRING);
    }

    public static String getFlagMythos(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmMythos);
        return c.get(key, PersistentDataType.STRING);
    }

    public static void addFlagName(CharmTech plugin, ItemStack i, String name) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmName);
        c.set(key, PersistentDataType.STRING, name.replace("_"," "));
        i.setItemMeta(im);
    }

    public static boolean hasFlagName(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmName);
        return c.has(key, PersistentDataType.STRING);
    }

    public static String getFlagName(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmName);
        return c.get(key, PersistentDataType.STRING);
    }

    public static void addFlagLore1(CharmTech plugin, ItemStack i, String name) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore1);
        if (name.equals("")) {
            c.set(key, PersistentDataType.STRING, null);
        } else {
            c.set(key, PersistentDataType.STRING, name);
        }
        i.setItemMeta(im);
    }

    public static boolean hasFlagLore1(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore1);
        return c.has(key, PersistentDataType.STRING);
    }

    public static String getFlagLore1(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore1);
        return c.get(key, PersistentDataType.STRING);
    }

    public static void addFlagLore2(CharmTech plugin, ItemStack i, String name) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore2);
        if (name.equals("")) {
            c.set(key, PersistentDataType.STRING, null);
        } else {
            c.set(key, PersistentDataType.STRING, name);
        }
        i.setItemMeta(im);
    }

    public static boolean hasFlagLore2(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore2);
        return c.has(key, PersistentDataType.STRING);
    }

    public static String getFlagLore2(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore2);
        return c.get(key, PersistentDataType.STRING);
    }

    public static void addFlagLore3(CharmTech plugin, ItemStack i, String name) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore3);
        if (name.equals("")) {
            c.set(key, PersistentDataType.STRING, null);
        } else {
            c.set(key, PersistentDataType.STRING, name);
        }
        i.setItemMeta(im);
    }

    public static boolean hasFlagLore3(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore3);
        return c.has(key, PersistentDataType.STRING);
    }

    public static String getFlagLore3(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore3);
        return c.get(key, PersistentDataType.STRING);
    }

    public static void addFlagLore4(CharmTech plugin, ItemStack i, String name) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore4);
        if (name.equals("")) {
            c.set(key, PersistentDataType.STRING, null);
        } else {
            c.set(key, PersistentDataType.STRING, name);
        }
        i.setItemMeta(im);
    }

    public static boolean hasFlagLore4(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore4);
        return c.has(key, PersistentDataType.STRING);
    }

    public static String getFlagLore4(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore4);
        return c.get(key, PersistentDataType.STRING);
    }

    public static void addFlagLore5(CharmTech plugin, ItemStack i, String name) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore5);
        if (name.equals("")) {
            c.set(key, PersistentDataType.STRING, null);
        } else {
            c.set(key, PersistentDataType.STRING, name);
        }
        i.setItemMeta(im);
    }

    public static boolean hasFlagLore5(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore5);
        return c.has(key, PersistentDataType.STRING);
    }

    public static String getFlagLore5(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmLore5);
        return c.get(key, PersistentDataType.STRING);
    }

    public static void addFlagValue(CharmTech plugin, ItemStack i, Integer value) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmValue);
        c.set(key, PersistentDataType.INTEGER, value);
        i.setItemMeta(im);
    }

    public static boolean hasFlagValue(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmValue);
        return c.has(key, PersistentDataType.INTEGER);
    }

    public static Integer getFlagValue(CharmTech plugin, ItemStack i) {
        ItemMeta im = i.getItemMeta();
        PersistentDataContainer c = im.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, flagCharmValue);
        return c.get(key, PersistentDataType.INTEGER);
    }



}
