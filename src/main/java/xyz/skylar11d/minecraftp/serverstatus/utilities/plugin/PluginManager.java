package xyz.skylar11d.minecraftp.serverstatus.utilities.plugin;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import xyz.skylar11d.minecraftp.serverstatus.Main;

public class PluginManager {
    private Main main;

    public PluginManager(Main instance){
        this.main = instance;
    }

    public void init(){

        main.getLogger().info("Initializing dependencies..");

        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this.main));

        PacketEvents.getAPI().getSettings()
                .bStats(false)
                .checkForUpdates(true)
                .reEncodeByDefault(false);

        PacketEvents.getAPI().init();

    }

}
