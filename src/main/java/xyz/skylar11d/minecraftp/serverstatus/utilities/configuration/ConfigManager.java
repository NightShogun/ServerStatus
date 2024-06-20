package xyz.skylar11d.minecraftp.serverstatus.utilities.configuration;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.skylar11d.minecraftp.serverstatus.Main;
import xyz.skylar11d.minecraftp.serverstatus.utilities.configuration.type.ConfigType;

import java.io.File;
import java.util.Optional;

public class ConfigManager {

    private final Main main;
    private final File propF;
    private final YamlConfiguration propYaml;

    public ConfigManager(Main instance){
        this.main = instance;
        this.propF = new File(main.getDataFolder(),"properties.yml");
        this.propYaml = YamlConfiguration.loadConfiguration(propF);
    }

    public void generateConfig(){

        main.getLogger().info("Checking for plugin's files integrity..");

        if(!propF.exists()) {
            main.getLogger().info(propF.getName().toLowerCase() + " wasn't found!, generating new one..");
            main.saveResource("properties.yml", true);
        }

    }

    public Optional<YamlConfiguration> getConfig(ConfigType type){

        if (type == ConfigType.properties)
            return Optional.ofNullable(propYaml);

        return Optional.empty();
    }

}
