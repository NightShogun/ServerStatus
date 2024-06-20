package xyz.skylar11d.minecraftp.serverstatus.utilities.plugin;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import xyz.skylar11d.minecraftp.serverstatus.Main;
import xyz.skylar11d.minecraftp.serverstatus.executors.ServerStatus;
import xyz.skylar11d.minecraftp.serverstatus.listeners.ClientBoundStatusInterceptor;
import xyz.skylar11d.minecraftp.serverstatus.utilities.command.ICommand;

import java.util.Objects;

public class PluginManager {
    private final Main main;

    public PluginManager(Main instance){
        this.main = instance;
    }

    public void initAll(){

        initAPI();
        registerListeners();
        registerCommands(new ServerStatus());

    }

    public void initAPI(){
        Main.LOG.info("Initializing the API...");
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(main));

        PacketEvents.getAPI().getSettings()
                .bStats(false)
                .checkForUpdates(true)
                .reEncodeByDefault(false);

        PacketEvents.getAPI().init();
    }
    public void registerCommands(ICommand... commands){
        Main.LOG.info("Initializing and registering the commands..");
        for(ICommand command : commands){
            Objects.requireNonNull(main.getCommand(command.getMeta().name())).setExecutor(command);
            Objects.requireNonNull(main.getCommand(command.getMeta().name())).setTabCompleter(command);
        }
    }
    public void registerListeners(){
        Main.LOG.info("Initializing and registering the packet listeners..");
        PacketEvents.getAPI().getEventManager().registerListener(new ClientBoundStatusInterceptor(main), PacketListenerPriority.NORMAL);
    }

}
