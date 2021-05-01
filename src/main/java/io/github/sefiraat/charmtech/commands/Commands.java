package io.github.sefiraat.charmtech.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import io.github.sefiraat.charmtech.CharmTech;
import io.github.sefiraat.charmtech.finals.Messages;
import io.github.sefiraat.charmtech.lib.utils.Flags;
import io.github.sefiraat.charmtech.lib.utils.Utils;
import jdk.jfr.Description;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("CharmTech")
@Description("CharmTech Main")
public class Commands extends BaseCommand {

    public final CharmTech parent;

    public Commands(CharmTech Parent) {
        this.parent = Parent;
    }

    @Default
    public void onDefault(CommandSender sender) {
        if (sender instanceof Player) {
            sender.sendMessage(Messages.MessageCommandSubcommand);
        }
    }

    @Subcommand("MakeCharm")
    @CommandPermission("CharmTech.Admin")
    @Description("Converts held item into a charm")
    public class MakeCharm extends BaseCommand {

        @Default
        public void onDefault(CommandSender sender) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                ItemStack i = p.getInventory().getItemInMainHand();
                if (i != null) {
                    Flags.setFlagIsCharm(parent, i);
                    p.sendMessage(Messages.MessageCommandSetCharmSuccess);
                } else {
                    p.sendMessage(Messages.MessageCommandSetCharmMustHold);
                }
            }
        }
    }

    @Subcommand("AddEffect")
    @CommandPermission("CharmTech.Admin")
    @Description("Adds a charm effect the the selected charm")
    public class AddCharmEffect extends BaseCommand {

        @Default
        @CommandCompletion("@PotEff @range:5-100 @range:1-20")
        public void onDefault(CommandSender sender, @Values("@PotEff") String effectType, Integer duration, Integer Efficacy) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                ItemStack i = p.getInventory().getItemInMainHand();
                if (i != null) {
                    if (Flags.hasFlagIsCharm(parent, i)) {
                        String effectString = "{" + effectType + "," + (duration * 20) + "," + (Efficacy - 1) + "}";
                        Flags.addFlagEffect(parent, i, effectString);
                        p.sendMessage(Messages.MessageCommandAddEffectSuccess);
                    } else {
                        p.sendMessage(Messages.MessageCommandAddEffectMustCharm);
                    }
                } else {
                    p.sendMessage(Messages.MessageCommandSetCharmMustHold);
                }
            }
        }

    }




}
