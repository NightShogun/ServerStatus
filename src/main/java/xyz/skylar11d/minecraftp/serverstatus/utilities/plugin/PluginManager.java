package xyz.skylar11d.minecraftp.serverstatus.utilities.plugin;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.jetbrains.annotations.NotNull;
import xyz.skylar11d.minecraftp.serverstatus.Main;
import xyz.skylar11d.minecraftp.serverstatus.listeners.ClientBoundStatusInterceptor;

public class PluginManager {
    private @NotNull Main main;

    public PluginManager(Main instance){
        this.main = instance;
    }

    public void initAll(){

        main.getLogger().info("Initializing..");
        initAPI();
        registerListeners();
        registerCommands();

    }

    public void initAPI(){
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(main));

        PacketEvents.getAPI().getSettings()
                .bStats(false)
                .checkForUpdates(true)
                .reEncodeByDefault(false);

        PacketEvents.getAPI().init();
    }
    public void registerCommands(){}
    public void registerListeners(){
        PacketEvents.getAPI().getEventManager().registerListener(new ClientBoundStatusInterceptor(), PacketListenerPriority.NORMAL);
    }

}
