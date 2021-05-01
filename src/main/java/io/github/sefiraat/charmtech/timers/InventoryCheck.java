package io.github.sefiraat.charmtech.timers;

import io.github.sefiraat.charmtech.CharmTech;
import io.github.sefiraat.charmtech.lib.utils.Flags;
import io.github.sefiraat.charmtech.lib.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

import static io.github.sefiraat.charmtech.lib.utils.Utils.getPlayerCharms;
import static io.github.sefiraat.charmtech.lib.utils.Utils.getPotionTypeFromString;

public class InventoryCheck extends BukkitRunnable {

    CharmTech parent;

    public InventoryCheck(CharmTech plugin) {
        parent = plugin;
    }

    @Override
    public void run() {
        for (Player p : parent.getServer().getOnlinePlayers()) {
            List<ItemStack> l = getPlayerCharms(parent, p);
            if (l != null) {
                for (ItemStack i : l) {
                    String effects = Flags.getFlagEffects(parent, i);
                    List<String> effectList = Arrays.asList(effects.split("\\s*;\\s*"));
                    for (String s : effectList) {
                        String cleaned = s.replace("{","").replace("}","");
                        List<String> effectDetails = Arrays.asList(cleaned.split("\\s*,\\s*"));
                        String effectName = effectDetails.get(0);
                        Integer duration = Integer.valueOf(effectDetails.get(1));
                        Integer efficacy = Integer.valueOf(effectDetails.get(2));
                        PotionEffect pot = new PotionEffect(getPotionTypeFromString(effectName), duration, efficacy);
                        p.addPotionEffect(pot);
                    }
                }
            }
        }
    }

}
