package io.github.sefiraat.charmtech.finals;

import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIItems {

    private GUIItems() {
        throw new IllegalStateException("Utility class");
    }

    public static GuiItem guiPackFiller() {
        GuiItem g = new GuiItem(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack i = g.getItemStack();
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ItemDetails.GUI_DISPLAY_NAME_FILLER);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i.setItemMeta(im);
        g.setItemStack(i);
        g.setAction(event -> event.setCancelled(true));
        return g;
    }
    public static GuiItem guiDeletedCharm() {
        GuiItem g = new GuiItem(Material.BARRIER);
        ItemStack i = g.getItemStack();
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ItemDetails.GUI_CHARM_DELETED);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i.setItemMeta(im);
        g.setItemStack(i);
        g.setAction(event -> event.setCancelled(true));
        return g;
    }
}
