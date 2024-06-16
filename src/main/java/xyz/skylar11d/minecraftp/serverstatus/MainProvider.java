package xyz.skylar11d.minecraftp.serverstatus;

import com.github.retrooper.packetevents.PacketEvents;
import xyz.skylar11d.minecraftp.serverstatus.utilities.configuration.ConfigManager;
import xyz.skylar11d.minecraftp.serverstatus.utilities.plugin.PluginManager;
import xyz.skylar11d.minecraftp.serverstatus.utilities.plugin.impl.Provider;

public class MainProvider implements Provider {

    private PluginManager pluginManager;
    private ConfigManager configManager;

    public MainProvider(Main instance){
        this.pluginManager = new PluginManager(instance);
        this.configManager = new ConfigManager(instance);
    }

    @Override
    public void onEnable() {
        pluginManager.initAll();
        configManager.generateConfig();
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public ConfigManager getConfigManager() {
        return configManager;
    }
}
