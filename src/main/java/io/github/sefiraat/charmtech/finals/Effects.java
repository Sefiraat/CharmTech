package io.github.sefiraat.charmtech.finals;

import io.github.sefiraat.charmtech.implimentation.abstracts.CharmEffect;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Effects {

    public static final CharmEffect getTrashEffects(int ID) throws Exception {
        switch(ID) {
            case 1: return new CharmEffect("Plague", new PotionEffect(PotionEffectType.POISON,10, 2));
            default: throw new Exception("OH FCK");
        }
    }


}
