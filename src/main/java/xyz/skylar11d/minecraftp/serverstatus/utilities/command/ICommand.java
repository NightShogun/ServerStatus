package xyz.skylar11d.minecraftp.serverstatus.utilities.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public abstract class ICommand implements CommandExecutor {

    private ACommand aCommand;

    public ICommand(){
        this.aCommand = this.getClass().getAnnotation(ACommand.class);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if ((aCommand.requires().isEmpty()) && !(sender.hasPermission(aCommand.requires()))){
            sender.sendMessage(
                    Component
                            .text("Access required")
                            .color(NamedTextColor.RED)
                            .hoverEvent(
                                    HoverEvent.showText(Component.text(aCommand.requires())
                                            .color(NamedTextColor.RED))
                            )
            );
        return false;
    }
        if(aCommand.onlyPlayers()){

            if(!(sender instanceof Player)) {
                sender.sendMessage(Component.text("Only accessible to players").color(NamedTextColor.RED));
                return false;
            }

            onExecute((Player) sender, args);

            return true;
        }

        onExecute(sender, args);

        return true;
    }

    public abstract void onExecute(Player player, String[] args);
    public abstract void onExecute(CommandSender sender, String[] args);

    public ACommand getaCommand() {
        return aCommand;
    }
}
