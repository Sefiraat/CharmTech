package io.github.sefiraat.charmtech;

import co.aikar.commands.CommandManager;
import co.aikar.commands.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import io.github.sefiraat.charmtech.commands.Commands;
import io.github.sefiraat.charmtech.implimentation.charms.EnchantCharm;
import io.github.sefiraat.charmtech.listeners.RightClickListener;
import io.github.sefiraat.charmtech.timers.InventoryCheck;
import org.bstats.bukkit.Metrics;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.logging.Level;

public class CharmTech extends JavaPlugin {

    private CharmTech instance;

    private CommandManager commandManager;
    private final Timer repeater = new Timer();

    private InventoryCheck inventoryCheckTask;

    private EnchantCharm enchantCharm;

    public CommandManager getCommandManager() {
        return commandManager;
    }
    public CharmTech getInstance() {
        return instance;
    }

    public EnchantCharm getEnchantCharm() {
        return enchantCharm;
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
        registerCommands();

        inventoryCheckTask = new InventoryCheck(this.instance);
        inventoryCheckTask.runTaskTimer(this.instance, 0, 100L);

        new RightClickListener(this.instance);

        int pluginId = 11209;
        Metrics metrics = new Metrics(this, pluginId);

        enchantCharm = new EnchantCharm(new NamespacedKey(this,"charm"));

    }

    private void registerEnchants(Enchantment enchantment) {
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
    }
}
