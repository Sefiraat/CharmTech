package io.github.sefiraat.charmtech;

import co.aikar.commands.CommandManager;
import co.aikar.commands.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import io.github.sefiraat.charmtech.commands.Commands;
import io.github.sefiraat.charmtech.listeners.RightClickListener;
import io.github.sefiraat.charmtech.timers.InventoryCheck;
import io.github.sefiraat.charmtech.timers.TimerSave;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Timer;

public class CharmTech extends JavaPlugin {

    private CharmTech instance;

    private CommandManager commandManager;
    private final Timer repeater = new Timer();
    private File CharmItemsConfigFile;
    private FileConfiguration CharmItemsConfig;
    private InventoryCheck inventoryCheckTask;
    private final Timer Repeater = new Timer();

    public CommandManager getCommandManager() {
        return commandManager;
    }
    public CharmTech getInstance() {
        return instance;
    }
    public File getCharmItemsConfigFile() {
        return CharmItemsConfigFile;
    }
    public FileConfiguration getCharmItemsConfig() {
        return CharmItemsConfig;
    }

    @Override
    public void onEnable() {

        getLogger().info("########################################");
        getLogger().info("");
        getLogger().info("               Charm Tech               ");
        getLogger().info("            Version 1.0.0.R1            ");
        getLogger().info("");
        getLogger().info("          Created by Sefiraat           ");
        getLogger().info("");
        getLogger().info("########################################");

        instance = this;

        saveDefaultConfig();
        createCharmItemsConfig();
        registerCommands();

        inventoryCheckTask = new InventoryCheck(this.instance);
        inventoryCheckTask.runTaskTimer(this.instance, 0, 100L);

        new RightClickListener(this.instance);

        int pluginId = 11209;
        Metrics metrics = new Metrics(this, pluginId);

        Repeater.schedule(new TimerSave(this.getInstance()),0, 30000);

    }

    private void registerEnchant(Enchantment enchantment) {
        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Enchantment.registerEnchantment(enchantment);
            } catch (IllegalArgumentException e) {
                //if this is thrown it means the id is already taken.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCharmItemsConfig() {
        CharmItemsConfigFile = new File(getDataFolder(), "SavedCharms.yml");
        if (!CharmItemsConfigFile.exists()) {
            CharmItemsConfigFile.getParentFile().mkdirs();
            saveResource("SavedCharms.yml", false);
        }
        CharmItemsConfig = new YamlConfiguration();
        try {
            CharmItemsConfig.load(CharmItemsConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveCharmItemsConfig() {
        try {
            CharmItemsConfig.save(CharmItemsConfigFile);
        } catch (IOException e) {
            this.getLogger().warning("Unable to save " + CharmItemsConfigFile.getName());
        }
    }

    private void registerCommands() {

        commandManager = new PaperCommandManager(this.getInstance());
        commandManager.registerCommand(new Commands(this.getInstance()));

        commandManager.getCommandCompletions().registerCompletion("PotEff", c -> {
            return ImmutableList.of(
                    "ABSORPTION",
                    "BAD_OMEN",
                    "BLINDNESS",
                    "CONDUIT_POWER",
                    "CONFUSION",
                    "DAMAGE_RESISTANCE",
                    "DOLPHINS_GRACE",
                    "FAST_DIGGING",
                    "FIRE_RESISTANCE",
                    "GLOWING",
                    "HARM",
                    "HEAL",
                    "HEALTH_BOOST",
                    "HERO_OF_THE_VILLAGE",
                    "HUNGER",
                    "INCREASE_DAMAGE",
                    "INVISIBILITY",
                    "JUMP",
                    "LEVITATION",
                    "LUCK",
                    "NIGHT_VISION",
                    "POISON",
                    "REGENERATION",
                    "SATURATION",
                    "SLOW",
                    "SLOW_DIGGING",
                    "SLOW_FALLING",
                    "SPEED",
                    "UNLUCK",
                    "WATER_BREATHING",
                    "WEAKNESS",
                    "WITHER"
            );
        });

        commandManager.getCommandCompletions().registerCompletion("EffReq", c -> {
            return ImmutableList.of(
                "ALL",
                "MAIN_HAND",
                "OFF_HAND",
                "ARMOUR_ALL",
                "ARMOUR_HELM",
                "ARMOUR_CHEST",
                "ARMOUR_LEGS",
                "ARMOUR_FEET"
            );
        });

        commandManager.getCommandCompletions().registerCompletion("Mythos", c -> {
            return ImmutableList.of(
                    "TRASH",
                    "COMMON",
                    "UNCOMMON",
                    "RARE",
                    "EPIC",
                    "LEGENDARY"
            );
        });
    }
}
