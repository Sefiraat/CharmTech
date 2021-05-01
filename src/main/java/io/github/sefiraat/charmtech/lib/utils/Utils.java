package io.github.sefiraat.charmtech.lib.utils;

import io.github.sefiraat.charmtech.CharmTech;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    @Nullable
    public static List<ItemStack> getPlayerCharms(CharmTech plugin, Player p) {
        List<ItemStack> l = new ArrayList<>();
        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null) {
                if (Flags.hasFlagIsCharm(plugin, i)) {l.add(i);}
            }
        }
        if (l.size() > 0) {
            return l;
        } else {
            return null;
        }
    }


}
