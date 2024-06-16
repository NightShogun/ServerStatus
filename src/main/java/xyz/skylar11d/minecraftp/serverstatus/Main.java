package xyz.skylar11d.minecraftp.serverstatus;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.skylar11d.minecraftp.serverstatus.utilities.plugin.impl.Provider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main extends JavaPlugin {

    private Provider provider;

    @Override
    public void onEnable() {

        this.provider = new MainProvider(this);
        this.provider.onEnable();

    }

    @Override
    public void onDisable() {
        this.provider.onDisable();
    }

    public Provider getProvider() {
        return provider;
    }
}
