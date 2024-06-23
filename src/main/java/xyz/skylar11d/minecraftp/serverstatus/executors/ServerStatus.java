package xyz.skylar11d.minecraftp.serverstatus.executors;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import xyz.skylar11d.minecraftp.serverstatus.Main;
import xyz.skylar11d.minecraftp.serverstatus.utilities.command.ACommand;
import xyz.skylar11d.minecraftp.serverstatus.utilities.command.ICommand;
import xyz.skylar11d.minecraftp.serverstatus.utilities.configuration.type.ConfigType;

import java.io.IOException;

@ACommand(name = "serverstatus", requires = "serverstatus.admin", args = "reload")
public class ServerStatus extends ICommand {

    public ServerStatus(Main instance) {
        super(instance);
    }

    @Override
    public void onExecute(Player p, String[] args) {

        if(!criteriaMet(args)){
            Audience.audience(p).sendMessage(
                    Component.text("Syntax: /" + this.getMeta().name() + "<reload>")
            );
        }

        p.sendMessage(Component.text("Loading configurations.."));

        try {
            super.main.getProvider().getConfigManager().load(ConfigType.PROPERTIES);
        } catch (IOException | InvalidConfigurationException e){
            p.sendMessage(
                    Component
                            .text("An error occurred while loading the configuration file (check the output)")
                            .color(NamedTextColor.RED)
            );
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onExecute(CommandSender s, String[] args) {

        if (!criteriaMet(args)){
            Main.LOG.warning("Syntax: /" + this.getMeta().name() + "<reload>");
        }

        Main.LOG.info("Loading configurations..");

        try {
            super.main.getProvider().getConfigManager().load(ConfigType.PROPERTIES);
        } catch (IOException | InvalidConfigurationException e){
            Main.LOG.severe("An error occurred while loading the configuration file");
            throw new RuntimeException(e);
        }

    }

    private boolean criteriaMet(String... args){
        if((args.length < getMeta().args().length && args.length > getMeta().args().length)){
            return false;
        }

        return args[0] == null || args[0].equalsIgnoreCase(getMeta().args()[0]);
    }

}
