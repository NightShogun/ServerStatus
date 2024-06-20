package xyz.skylar11d.minecraftp.serverstatus.utilities.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ICommand implements CommandExecutor, TabCompleter {

    private final ACommand aCommand;

    public ICommand(){
        this.aCommand = this.getClass().getAnnotation(ACommand.class);
    }

    @Override
    public @Nullable List<String> onTabComplete(
            @NotNull CommandSender commandSender,
            @NotNull Command command,
            @NotNull String s,
            @NotNull String[] strings
    ) {
        return List.of(aCommand.args());
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

    public ACommand getMeta() {
        return aCommand;
    }
}
