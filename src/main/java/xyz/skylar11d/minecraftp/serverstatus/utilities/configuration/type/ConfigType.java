package xyz.skylar11d.minecraftp.serverstatus.utilities.configuration.type;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.skylar11d.minecraftp.serverstatus.Main;

import java.io.File;

public enum ConfigType {

    properties(YamlConfiguration.loadConfiguration(new File(Main.getPlugin(Main.class).getDataFolder(), "properties.yml")));

    private YamlConfiguration yamlConfiguration;

    ConfigType(YamlConfiguration yamlConfiguration) {
        this.yamlConfiguration = yamlConfiguration;
    }

    public YamlConfiguration getYamlConfiguration() {
        return yamlConfiguration;
    }
}
