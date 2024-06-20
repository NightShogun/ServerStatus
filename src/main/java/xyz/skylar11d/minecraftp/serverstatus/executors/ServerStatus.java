package xyz.skylar11d.minecraftp.serverstatus.executors;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.skylar11d.minecraftp.serverstatus.utilities.command.ACommand;
import xyz.skylar11d.minecraftp.serverstatus.utilities.command.ICommand;

@ACommand(name = "serverstatus", requires = "serverstatus.admin")
public class ServerStatus extends ICommand {

    @Override
    public void onExecute(Player p, String[] args) {

        if (args.length < 1)
            Audience.audience(p).sendMessage(
                    Component.text("Syntax: /" + this.getMeta().name() + "<reload>")
            );

    }

    @Override
    public void onExecute(CommandSender s, String[] args) {

    }

}
