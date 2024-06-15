package xyz.skylar11d.minecraftp.serverstatus.utilities.configuration;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.skylar11d.minecraftp.serverstatus.Main;
import xyz.skylar11d.minecraftp.serverstatus.utilities.configuration.type.ConfigType;

import java.io.File;
import java.util.Optional;

public class ConfigManager {

    private Main main;

    public ConfigManager(Main instance){
        this.main = instance;
    }

    public void init(){

        if()

    }

    public Optional<YamlConfiguration> getConfig(ConfigType type){

        if (type == ConfigType.properties)
            return Optional.ofNullable(type.getYamlConfiguration());

        return Optional.empty();
    }

}
