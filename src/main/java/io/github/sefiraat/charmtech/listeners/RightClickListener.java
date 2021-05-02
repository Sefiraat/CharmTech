package io.github.sefiraat.charmtech.listeners;

import io.github.sefiraat.charmtech.CharmTech;
import io.github.sefiraat.charmtech.lib.utils.Flags;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class RightClickListener implements Listener {

    final CharmTech parent;

    public RightClickListener(@Nonnull CharmTech plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        parent = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getItem() != null) {
            if (e.getItem().getItemMeta() != null) {
                ItemStack i = e.getItem();
                if (Flags.hasFlagIsCharm(parent, i)) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onConsume(PlayerItemConsumeEvent e) {
        if (e.getItem() != null) {
            if (e.getItem().getItemMeta() != null) {
                ItemStack i = e.getItem();
                if (Flags.hasFlagIsCharm(parent, i)) {
                    e.setCancelled(true);
                }
            }
        }
    }

}
