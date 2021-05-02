package io.github.sefiraat.charmtech.lib.utils;

import com.google.common.collect.ImmutableList;
import io.github.sefiraat.charmtech.CharmTech;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.sefiraat.charmtech.lib.utils.Flags.flagIsCharm;

public class Utils {

    @Nullable
    public static List<ItemStack> getPlayerCharms(CharmTech plugin, Player p) {
        List<ItemStack> l = new ArrayList<>();
        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null) {
                if (Flags.hasFlagIsCharm(plugin, i)) {
                    l.add(i);
                }
            }
        }
        if (l.size() > 0) {
            return l;
        } else {
            return null;
        }
    }

    public static PotionEffectType getPotionTypeFromString(String s) {
        switch (s) {
            case "ABSORPTION": { return PotionEffectType.ABSORPTION; }
            case "BAD_OMEN": { return PotionEffectType.BAD_OMEN; }
            case "BLINDNESS": { return PotionEffectType.BLINDNESS; }
            case "CONDUIT_POWER": { return PotionEffectType.CONDUIT_POWER; }
            case "CONFUSION": { return PotionEffectType.CONFUSION; }
            case "DAMAGE_RESISTANCE": { return PotionEffectType.DAMAGE_RESISTANCE; }
            case "DOLPHINS_GRACE": { return PotionEffectType.DOLPHINS_GRACE; }
            case "FAST_DIGGING": { return PotionEffectType.FAST_DIGGING; }
            case "FIRE_RESISTANCE": { return PotionEffectType.FIRE_RESISTANCE; }
            case "GLOWING": { return PotionEffectType.GLOWING; }
            case "HARM": { return PotionEffectType.HARM; }
            case "HEAL": { return PotionEffectType.HEAL; }
            case "HEALTH_BOOST": { return PotionEffectType.HEALTH_BOOST; }
            case "HERO_OF_THE_VILLAGE": { return PotionEffectType.HERO_OF_THE_VILLAGE; }
            case "HUNGER": { return PotionEffectType.HUNGER; }
            case "INCREASE_DAMAGE": { return PotionEffectType.INCREASE_DAMAGE; }
            case "INVISIBILITY": { return PotionEffectType.INVISIBILITY; }
            case "JUMP": { return PotionEffectType.JUMP; }
            case "LEVITATION": { return PotionEffectType.LEVITATION; }
            case "LUCK": { return PotionEffectType.LUCK; }
            case "NIGHT_VISION": { return PotionEffectType.NIGHT_VISION; }
            case "POISON": { return PotionEffectType.POISON; }
            case "REGENERATION": { return PotionEffectType.REGENERATION; }
            case "SATURATION": { return PotionEffectType.SATURATION; }
            case "SLOW": { return PotionEffectType.SLOW; }
            case "SLOW_DIGGING": { return PotionEffectType.SLOW_DIGGING; }
            case "SLOW_FALLING": { return PotionEffectType.SLOW_FALLING; }
            case "SPEED": { return PotionEffectType.SPEED; }
            case "UNLUCK": { return PotionEffectType.UNLUCK; }
            case "WATER_BREATHING": { return PotionEffectType.WATER_BREATHING; }
            case "WEAKNESS": { return PotionEffectType.WEAKNESS; }
            case "WITHER": { return PotionEffectType.WITHER; }
            default: { return PotionEffectType.LUCK; }
        }
    }


}
