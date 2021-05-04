package io.github.sefiraat.charmtech.gui;

import io.github.sefiraat.charmtech.CharmTech;
import io.github.sefiraat.charmtech.finals.GUIItems;
import io.github.sefiraat.charmtech.finals.Messages;
import io.github.sefiraat.charmtech.lib.utils.Utils;
import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.mattstudios.mfgui.gui.guis.PaginatedGui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharmAdminDisplay {

    private CharmAdminDisplay() {
        throw new IllegalStateException("Utility class");
    }

    public static PaginatedGui getDankAdminGUI(CharmTech parent) {

        Integer[] arrayFillerSlots = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 45, 47, 48, 49, 50, 51, 53};
        Integer backSlot = 46;
        Integer forwardSlot = 52;
        List<Integer> listFillerSlots = Arrays.asList(arrayFillerSlots);

        PaginatedGui g = new PaginatedGui(6, "Charms Admin GUI");

        g.setItem(listFillerSlots, GUIItems.guiPackFiller());
        g.setItem(backSlot, ItemBuilder.from(Material.PAPER).setName("Previous").asGuiItem(event -> g.previous()));
        g.setItem(forwardSlot, ItemBuilder.from(Material.PAPER).setName("Next").asGuiItem(event -> g.next()));

        for (ItemStack i : Utils.getAllCharms(parent)) {
            ItemMeta im = i.getItemMeta();
            List<String> lore;
            if (im.hasLore()) {
                lore = im.getLore();
            } else {
                lore = new ArrayList<>();
            }
            lore.add("");
            lore.add(ChatColor.GREEN + "Left click to clone");
            lore.add(ChatColor.GREEN + "Shift Right click delete");
            im.setLore(lore);
            i.setItemMeta(im);
            NamespacedKey key = new NamespacedKey(parent,"charm-id");
            long charmID = im.getPersistentDataContainer().get(key, PersistentDataType.LONG);
            GuiItem dankGuiItem = new GuiItem(i, event -> adminClickCharm(event, charmID, parent, g));
            g.addItem(dankGuiItem);
        }

        g.setDefaultClickAction(event -> event.setCancelled(true));
        g.setDragAction(event -> event.setCancelled(true));

        return g;
    }

    public static void adminClickCharm(InventoryClickEvent event, Long charmID, CharmTech plugin, PaginatedGui g) {
        Player p = (Player) event.getWhoClicked();
        if (event.getClick() == ClickType.LEFT) {
            ItemStack i = plugin.getInstance().getCharmItemsConfig().getItemStack("CHARMS." + charmID + ".ITEM");
            p.getInventory().addItem(i);
            p.sendMessage(Messages.MESSAGE_GUI_CHARM_GIVEN);
        } else if (event.getClick() == ClickType.SHIFT_RIGHT) {
            plugin.getInstance().getCharmItemsConfig().set("CHARMS." + charmID, null);
            plugin.saveCharmItemsConfig();
            p.sendMessage(Messages.MESSAGE_GUI_CHARM_DELETED);
            g.close(p);
        }
    }

}
