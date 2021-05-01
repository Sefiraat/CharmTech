package io.github.sefiraat.charmtech.implimentation.abstracts;

import org.bukkit.potion.PotionEffect;

public class CharmEffect {

    private PotionEffect potionEffect;
    private String name;








    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    public String getName() {
        return name;
    }




     public CharmEffect(String name, PotionEffect effect) {
        this.name = name;
        this.potionEffect = effect;
     }

}
