package xyz.skylar11d.minecraftp.serverstatus.utilities.plugin.impl;

import xyz.skylar11d.minecraftp.serverstatus.utilities.plugin.PluginManager;

public interface Provider {

    default void onEnable() {}

    default void onDisable() {}

    PluginManager getPluginManager();

}
