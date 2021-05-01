package io.github.sefiraat.charmtech;

import co.aikar.commands.PaperCommandManager;
import io.github.sefiraat.charmtech.commands.Commands;
import io.github.sefiraat.charmtech.timers.InventoryCheck;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Timer;

public class CharmTech extends JavaPlugin {

    private CharmTech instance;

    private PaperCommandManager CommandManager;
    private final Timer repeater = new Timer();

    public PaperCommandManager getCommandManager() {
        return CommandManager;
    }
    public CharmTech getInstance() {
        return instance;
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

        instance = this;

        saveDefaultConfig();
        registerCommands();

        repeater.schedule(new InventoryCheck(this.getInstance()),0, 2000);

    }

    private void registerCommands() {
        CommandManager = new PaperCommandManager(this.getInstance());
        CommandManager.registerCommand(new Commands(this.getInstance()));
    }


}
