package xyz.skylar11d.minecraftp.serverstatus.utilities.builder;

import com.google.gson.JsonObject;
import org.bukkit.Bukkit;

public class StatusPacket {

    private final JsonObject object;

    public StatusPacket(){
        this.object = new JsonObject();
    }

    public StatusPacket version(int protocol){

        if(protocol == 0)return this;

        JsonObject version = new JsonBuilder()
                .addProperty("name","y")
                .addProperty("protocol", protocol)
                .build();

        this.object.add("version", version);

        return this;
    }

    public StatusPacket version(String display){

        if(display.isEmpty() || display == null){
            return this;
        }

        JsonObject version = new JsonBuilder()
                .addProperty("name", display)
                .build();

        this.object.add("version", version);

        return this;
    }

    public StatusPacket players(String hover){

        if((hover.isEmpty() || hover == null)){
            return this;
        }

        JsonObject sample = new JsonBuilder()
                .addProperty("name", hover)
                .build();

        JsonObject players = new JsonBuilder()
                .addProperty("max", Bukkit.getMaxPlayers())
                .addProperty("online", Bukkit.getOnlinePlayers().size())
                //.addKey("sample", sample)
                .build();

        this.object.add("players", players);

        return this;
    }

    public StatusPacket players(int max, String hover){

        JsonObject sample = new JsonBuilder()
                .addProperty("name", hover)
                .build();

        JsonObject players = new JsonBuilder()
                .addProperty("max", max)
                .addProperty("online", Bukkit.getOnlinePlayers().size())
                //.addKey("sample", sample)
                .build();

        this.object.add("players", players);

        return this;
    }

    public StatusPacket motd(String motd){

        if((motd.isEmpty() || motd == null)) {
            return this;
        }

        JsonObject text = new JsonBuilder()
                .addProperty("text", motd)
                .build();

        this.object.add("description", text);

        return this;
    }

    public JsonObject toJSON(){return this.object;}

}
