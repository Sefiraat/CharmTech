package io.github.sefiraat.charmtech.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import dev.triumphteam.gui.guis.PaginatedGui;
import io.github.sefiraat.charmtech.CharmTech;
import io.github.sefiraat.charmtech.finals.Messages;
import io.github.sefiraat.charmtech.gui.CharmAdminDisplay;
import io.github.sefiraat.charmtech.lib.utils.Flags;
import io.github.sefiraat.charmtech.lib.utils.Utils;
import jdk.jfr.Description;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("CharmTech")
@Description("CharmTech Main")
public class Commands extends BaseCommand {

    public final CharmTech parent;

    public Commands(CharmTech parent) {
        this.parent = parent;
    }

    @Default
    public void onDefault(CommandSender sender) {
        if (sender instanceof Player) {
            sender.sendMessage(Messages.MESSAGE_COMMAND_SUBCOMMAND);
        }
    }

    @Subcommand("MakeCharm")
    @CommandPermission("CharmTech.Admin")
    @Description("Converts held item into a charm")
    public class MakeCharm extends BaseCommand {

        @Default
        @CommandCompletion("<DisplayName> @Mythos <ItemBuyValue>")
        public void onDefault(CommandSender sender, String charmName, String mythos, Integer value) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                ItemStack i = p.getInventory().getItemInMainHand();
                if (i.getType() != Material.AIR) {
                    Flags.addFlagIsCharm(parent, i);
                    Flags.addFlagName(parent, i, charmName);
                    Flags.addFlagMythos(parent, i, mythos);
                    Flags.addFlagValue(parent, i, value);
                    Utils.rebuildCharmMeta(parent, i);
                    p.sendMessage(Messages.MESSAGE_COMMAND_SET_CHARM_SUCCESS);
                } else {
                    p.sendMessage(Messages.MESSAGE_COMMAND_SET_CHARM_MUST_HOLD);
                }
            }
        }
    }

    @Subcommand("AddCharmDetail")
    @CommandPermission("CharmTech.Admin")
    @Description("Adds a charm detail (lore/price/mythos/effect etc.) the the selected charm")
    public class AddCharmDetail extends BaseCommand {

        @Subcommand("AddEffect")
        @CommandPermission("CharmTech.Admin")
        @Description("Adds a charm effect selected ItemStack")
        @CommandCompletion("@PotEff @range:5-100 @range:1-20 @EffReq @Targets")
        public void onAddEffect(CommandSender sender, @Values("@PotEff") String effectType, Integer duration, Integer efficacy, @Values("@EffReq") String effReq, @Values("@Targets") String target) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                ItemStack i = p.getInventory().getItemInMainHand();
                if (i.getType() != Material.AIR) {
                    if (Flags.hasFlagIsCharm(parent, i)) {
                        String effectString = "{" + effectType + "," + (duration * 20) + "," + (efficacy - 1) + "," + effReq + "," + target + "}";
                        Flags.addFlagEffect(parent, i, effectString);
                        p.sendMessage(Messages.MESSAGE_COMMAND_ADD_EFFECT_SUCCESS);
                    } else {
                        p.sendMessage(Messages.MESSAGE_COMMAND_ADD_EFFECT_MUST_CHARM);
                    }
                } else {
                    p.sendMessage(Messages.MESSAGE_COMMAND_SET_CHARM_MUST_HOLD);
                }
            }
        }

        @Subcommand("SetLoreLine")
        @CommandPermission("CharmTech.Admin")
        @Description("Adds a charm effect selected ItemStack")
        @CommandCompletion("@range:1-5 <Detail>")
        public void onAddLoreLine(CommandSender sender, Integer lineNo, String detail) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                ItemStack i = p.getInventory().getItemInMainHand();
                if (i.getType() != Material.AIR) {
                    if (Flags.hasFlagIsCharm(parent, i)) {
                        switch (lineNo) {
                            case 1: {
                                Flags.addFlagLore1(parent, i, detail);
                                break;
                            }
                            case 2: {
                                Flags.addFlagLore2(parent, i, detail);
                                break;
                            }
                            case 3: {
                                Flags.addFlagLore3(parent, i, detail);
                                break;
                            }
                            case 4: {
                                Flags.addFlagLore4(parent, i, detail);
                                break;
                            }
                            default: {
                                Flags.addFlagLore5(parent, i, detail);
                                break;
                            }
                        }
                        Utils.rebuildCharmMeta(parent, i);
                        p.sendMessage(Messages.MESSAGE_COMMAND_ADD_LORE_SUCCESS);
                    } else {
                        p.sendMessage(Messages.MESSAGE_COMMAND_ADD_EFFECT_MUST_CHARM);
                    }
                } else {
                    p.sendMessage(Messages.MESSAGE_COMMAND_SET_CHARM_MUST_HOLD);
                }
            }
        }

        @Subcommand("SetMythos")
        @CommandPermission("CharmTech.Admin")
        @Description("Adds a charm mythos selected ItemStack")
        @CommandCompletion("@Mythos")
        public void onAddMythos(CommandSender sender, @Values("@Mythos") String mythos) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                ItemStack i = p.getInventory().getItemInMainHand();
                if (i.getType() != Material.AIR) {
                    if (Flags.hasFlagIsCharm(parent, i)) {
                        Flags.addFlagMythos(parent, i, mythos);
                        Utils.rebuildCharmMeta(parent, i);
                        p.sendMessage(Messages.MESSAGE_COMMAND_ADD_EFFECT_SUCCESS);
                    } else {
                        p.sendMessage(Messages.MESSAGE_COMMAND_ADD_MYTHOS_SUCCESS);
                    }
                } else {
                    p.sendMessage(Messages.MESSAGE_COMMAND_SET_CHARM_MUST_HOLD);
                }
            }
        }

        @Subcommand("SetValue")
        @CommandPermission("CharmTech.Admin")
        @Description("Adds a charm mythos selected ItemStack")
        @CommandCompletion("<Value>")
        public void onAddMythos(CommandSender sender, Integer value) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                ItemStack i = p.getInventory().getItemInMainHand();
                if (i.getType() != Material.AIR) {
                    if (Flags.hasFlagIsCharm(parent, i)) {
                        Flags.addFlagValue(parent, i, value);
                        Utils.rebuildCharmMeta(parent, i);
                        p.sendMessage(Messages.MESSAGE_COMMAND_ADD_VALUE_SUCCESS);
                    } else {
                        p.sendMessage(Messages.MESSAGE_COMMAND_ADD_EFFECT_MUST_CHARM);
                    }
                } else {
                    p.sendMessage(Messages.MESSAGE_COMMAND_SET_CHARM_MUST_HOLD);
                }
            }
        }

    }

    @Subcommand("SaveCharm")
    @CommandPermission("CharmTech.Admin")
    @Description("Saves the item in hand to the charm library")
    public class SaveCharm extends BaseCommand {

        @Default
        public void onDefault(CommandSender sender) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                ItemStack i = p.getInventory().getItemInMainHand();
                if (i.getType() != Material.AIR) {
                    if (Flags.hasFlagIsCharm(parent, i)) {
                        ItemStack stackToSave = i.clone();
                        stackToSave.setAmount(1);
                        long nextItem = Utils.getNextItemID(parent);
                        FileConfiguration c = parent.getCharmItemsConfig();
                        c.createSection("CHARMS." + nextItem);
                        c.set("CHARMS." + nextItem + ".ITEM", stackToSave);
                        parent.saveCharmItemsConfig();
                        p.sendMessage(Messages.MESSAGE_COMMAND_CHARM_SAVED);
                    } else {
                        p.sendMessage(Messages.MESSAGE_COMMAND_ADD_EFFECT_MUST_CHARM);
                    }
                } else {
                    p.sendMessage(Messages.MESSAGE_COMMAND_SET_CHARM_MUST_HOLD);
                }
            }
        }

    }

    @Subcommand("ViewCharms")
    @Description("Opens a GUI of saved charms")
    @CommandPermission("DankTech.Admin")
    public class ViewCharms extends BaseCommand {

        @Default
        public void onDefault(CommandSender sender) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                PaginatedGui adminGUI = CharmAdminDisplay.getAdminGui(parent);
                adminGUI.open(p);
            }
        }

    }

    @Subcommand("GiveCharm")
    @Description("Gives a saved charm to a player")
    @CommandPermission("DankTech.Admin")
    public class GiveCharm extends BaseCommand {

        @Default
        @CommandCompletion("@players <CharmID>")
        public void onDefault(CommandSender sender, OnlinePlayer onlinePlayer, Integer charmID) {
            Player p = onlinePlayer.getPlayer();
            ItemStack i = parent.getInstance().getCharmItemsConfig().getItemStack("CHARMS." + charmID + ".ITEM");
            if (i != null) {
                p.getInventory().addItem(i);
                p.sendMessage(Messages.MESSAGE_COMMAND_GIVE_CHARM);
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + p.getDisplayName() + " received " + i.getItemMeta().getDisplayName() + ChatColor.YELLOW + " Charm" );
            } else {
                sender.sendMessage(Messages.MESSAGE_COMMAND_GIVE_CHARM_FAILED);
            }
        }

    }


}
