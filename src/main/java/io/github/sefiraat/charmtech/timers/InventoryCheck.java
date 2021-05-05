package io.github.sefiraat.charmtech.timers;

import io.github.sefiraat.charmtech.CharmTech;
import io.github.sefiraat.charmtech.lib.utils.Flags;
import io.github.sefiraat.charmtech.lib.utils.Utils;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                    if (effects != null) {
                        List<String> effectList = Arrays.asList(effects.split("\\s*;\\s*"));
                        for (String s : effectList) {

                            String cleaned = s.replace("{","").replace("}","");
                            List<String> effectDetails = Arrays.asList(cleaned.split("\\s*,\\s*"));

                            String effectName = effectDetails.get(0);
                            Integer duration = Integer.valueOf(effectDetails.get(1));
                            Integer efficacy = Integer.valueOf(effectDetails.get(2));
                            String effReq = effectDetails.get(3);
                            String targetType = "SELF";
                            if (effectDetails.size() > 4) {targetType = effectDetails.get(4);}

                            List<LivingEntity> targets = getTargets(targetType, p, parent);

                            if (isInCorrectSlot(p, effReq, i)) {
                                for (LivingEntity target : targets) {
                                    addEffect(target, effectName, duration, efficacy);
                                }
                            }


                        }
                    }
                }
            }
        }
    }

    public void addEffect(LivingEntity target, String effectName, Integer duration, Integer efficacy) {
        PotionEffect pot = new PotionEffect(getPotionTypeFromString(effectName), duration, efficacy);
        target.addPotionEffect(pot);

    }

    public List<LivingEntity> getTargets(String targetType, Player p, CharmTech plugin) {
        List<LivingEntity> targets = new ArrayList<>();
        switch (targetType) {
            case "ENTITY_SINGLE_RANDOM": {
                List<LivingEntity> potentials = getNearbyLivingEntities(p);
                if (!potentials.isEmpty()) { targets.add(potentials.get(Utils.randInt(plugin.getRandom(), 0, (potentials.size() - 1)))); }
                break;
            }
            case "ENTITY_ALL": {
                List<LivingEntity> potentials = getNearbyLivingEntities(p);
                targets.addAll(potentials);
                break;
            }
            case "PLAYER_SINGLE_RANDOM": {
                List<Player> potentials = getNearbyPlayers(p);
                if (!potentials.isEmpty()) {
                    targets.add(potentials.get(Utils.randInt(plugin.getRandom(), 0, (potentials.size() - 1))));
                }
                break;
            }
            case "PLAYER_ALL": {
                List<Player> potentials = getNearbyPlayers(p);
                targets.addAll(potentials);
                break;
            }
            case "MOB_SINGLE_RANDOM": {
                List<Mob> potentials = getNearbyMobs(p);
                if (!potentials.isEmpty()) {
                    targets.add(potentials.get(Utils.randInt(plugin.getRandom(), 0, (potentials.size() - 1))));
                }
                break;
            }
            case "MOB_ALL": {
                List<Mob> potentials = getNearbyMobs(p);
                targets.addAll(potentials);
                break;
            }
            case "PASSIVE_SINGLE_RANDOM": {
                List<Animals> potentials = getNearbyPassives(p);
                if (!potentials.isEmpty()) {
                    targets.add(potentials.get(Utils.randInt(plugin.getRandom(), 0, (potentials.size() - 1))));
                }
                break;
            }
            case "PASSIVE_ALL": {
                List<Animals> potentials = getNearbyPassives(p);
                targets.addAll(potentials);
                break;
            }
            case "HOSTILE_SINGLE_RANDOM": {
                List<Monster> potentials = getNearbyHostiles(p);
                if (!potentials.isEmpty()) {
                    targets.add(potentials.get(Utils.randInt(plugin.getRandom(), 0, (potentials.size() - 1))));
                }
                break;
            }
            case "HOSTILE_ALL": {
                List<Monster> potentials = getNearbyHostiles(p);
                targets.addAll(potentials);
                break;
            }
            default: targets.add(p);
        }
        return targets;
    }

    public List<LivingEntity> getNearbyLivingEntities(Player p) {
        List<LivingEntity> targets = new ArrayList<>();
        for (Entity entity : p.getWorld().getNearbyEntities(p.getLocation(), 3, 3, 3)) {
            if (entity instanceof LivingEntity) {
                targets.add((LivingEntity) entity);
            }
        }
        return targets;
    }

    public List<Mob> getNearbyMobs(Player p) {
        List<Mob> targets = new ArrayList<>();
        for (Entity entity : p.getWorld().getNearbyEntities(p.getLocation(), 3, 3, 3)) {
            if (entity instanceof Mob) {
                targets.add((Mob) entity);
            }
        }
        return targets;
    }

    public List<Animals> getNearbyPassives(Player p) {
        List<Animals> targets = new ArrayList<>();
        for (Entity entity : p.getWorld().getNearbyEntities(p.getLocation(), 3, 3, 3)) {
            if (entity instanceof Animals) {
                targets.add((Animals) entity);
            }
        }
        return targets;
    }

    public List<Monster> getNearbyHostiles(Player p) {
        List<Monster> targets = new ArrayList<>();
        for (Entity entity : p.getWorld().getNearbyEntities(p.getLocation(), 3, 3, 3)) {
            if (entity instanceof Monster) {
                targets.add((Monster) entity);
            }
        }
        return targets;
    }

    public List<Player> getNearbyPlayers(Player p) {
        List<Player> targets = new ArrayList<>();
        for (Entity entity : p.getWorld().getNearbyEntities(p.getLocation(), 3, 3, 3)) {
            if (entity instanceof Player) {
                Player player = ((Player) entity).getPlayer();
                if (player != p) {
                    targets.add((Player) entity);
                }
            }
        }
        return targets;
    }

    public boolean isInCorrectSlot(Player p, String effReq, ItemStack i) {
        switch (effReq) {
            case "MAIN_HAND": {
                ItemStack testStack = p.getInventory().getItemInMainHand();
                if (testStack != null && testStack.equals(i)) { return true; } else { return false; }
            }
            case "OFF_HAND": {
                ItemStack testStack = p.getInventory().getItemInOffHand();
                if (testStack != null && testStack.equals(i)) { return true; } else { return false; }
            }
            case "ARMOUR_ALL": {
                for (ItemStack testStack : p.getInventory().getArmorContents()) {
                    if (testStack != null && testStack.equals(i)) { return true; } else { return false; }
                }
            }
            case "ARMOUR_HELM": {
                ItemStack testStack = p.getInventory().getHelmet();
                if (testStack != null && testStack.equals(i)) { return true; } else { return false; }
            }
            case "ARMOUR_CHEST": {
                ItemStack testStack = p.getInventory().getChestplate();
                if (testStack != null && testStack.equals(i)) { return true; } else { return false; }
            }
            case "ARMOUR_LEGS": {
                ItemStack testStack = p.getInventory().getLeggings();
                if (testStack != null && testStack.equals(i)) { return true; } else { return false; }
            }
            case "ARMOUR_FEET": {
                ItemStack testStack = p.getInventory().getBoots();
                if (testStack != null && testStack.equals(i)) { return true; } else { return false; }
            }
            default: {
                return true;
            }
        }
    }

}
