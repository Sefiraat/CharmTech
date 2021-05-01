package io.github.sefiraat.charmtech.timers;

import io.github.sefiraat.charmtech.CharmTech;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.TimerTask;

import static io.github.sefiraat.charmtech.lib.utils.Utils.getPlayerCharms;

public class InventoryCheck extends TimerTask {

    CharmTech parent;

    public InventoryCheck(CharmTech plugin) {
        parent = plugin;
    }

    @Override
    public void run() {
        for (Player p : parent.getServer().getOnlinePlayers()) {
            p.sendMessage("You are being checked!");
            List<ItemStack> l = getPlayerCharms(parent, p);
            if (l != null) {
                // Player is holding a charm/charms
                p.sendMessage("You are holding a charm!");
            } else {
                p.sendMessage("You are not holding any charms!");
            }
        }
    }

}
