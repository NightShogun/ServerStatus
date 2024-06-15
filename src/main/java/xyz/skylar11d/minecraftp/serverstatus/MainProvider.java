package xyz.skylar11d.minecraftp.serverstatus;

import com.github.retrooper.packetevents.PacketEvents;
import xyz.skylar11d.minecraftp.serverstatus.utilities.plugin.PluginManager;
import xyz.skylar11d.minecraftp.serverstatus.utilities.plugin.impl.Provider;

public class MainProvider implements Provider {
    private Main main;
    private PluginManager pluginManager;

    public MainProvider(Main instance){
        this.main = instance;
        this.pluginManager = new PluginManager(instance);
    }

    @Override
    public void onEnable() {
        pluginManager.initAll();

    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }
}
