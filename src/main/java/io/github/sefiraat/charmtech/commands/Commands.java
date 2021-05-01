package io.github.sefiraat.charmtech.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import io.github.sefiraat.charmtech.CharmTech;
import io.github.sefiraat.charmtech.finals.Messages;
import io.github.sefiraat.charmtech.lib.utils.Flags;
import jdk.jfr.Description;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("CharmTech|CT")
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


}
