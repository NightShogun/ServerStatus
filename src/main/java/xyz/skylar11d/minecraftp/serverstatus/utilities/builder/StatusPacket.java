package xyz.skylar11d.minecraftp.serverstatus.utilities.builder;

import com.google.gson.JsonObject;
import org.bukkit.Bukkit;

import java.util.List;

public class StatusPacket {

    private final JsonObject object;

    public StatusPacket(){
        this.object = new JsonObject();
    }

    public StatusPacket version(int protocol){

        if(protocol == 0)return this;

        JsonObject version = new JsonBuilder()
                .addProperty("protocol", protocol)
                .build();

        this.object.add("version", version);

        return this;
    }

    public StatusPacket version(String display){

        if (display == null){
            return this;
        }

        JsonObject version = new JsonBuilder()
                .addProperty("name", display)
                .build();

        this.object.add("version", version);

        return this;
    }

    public StatusPacket version(String display, int protocol){

        if (display == null){
            return this;
        }

        JsonObject version = new JsonBuilder()
                .addProperty("name", display)
                .addProperty("protocol", protocol)
                .build();

        this.object.add("version", version);

        return this;
    }

    public StatusPacket players(String hover, String uuid){

        if(hover == null){

            JsonObject players = new JsonBuilder()
                    .addProperty("max", Bukkit.getMaxPlayers())
                    .addProperty("online", Bukkit.getOnlinePlayers().size())
                    //.addProperty("sample", List.of(sample).toString())
                    .build();

            this.object.add("players", players);

            return this;
        }

        JsonObject sample = new JsonBuilder()
                .addProperty("name", hover)
                .addProperty("id", uuid)
                .build();

        JsonObject players = new JsonBuilder()
                .addProperty("max", Bukkit.getMaxPlayers())
                .addProperty("online", Bukkit.getOnlinePlayers().size())
                .addProperty("sample", List.of(sample).toString())
                .build();

        this.object.add("players", players);

        return this;
    }

    public StatusPacket players(int max, String hover, String uuid){

        if(hover == null){
            JsonObject players = new JsonBuilder()
                    .addProperty("max", Bukkit.getMaxPlayers())
                    .addProperty("online", Bukkit.getOnlinePlayers().size())
                    //.addProperty("sample", List.of(sample).toString())
                    .build();

            this.object.add("players", players);

            return this;
        }

        JsonObject sample = new JsonBuilder()
                .addProperty("name", hover)
                .addProperty("id", uuid)
                .build();

        JsonObject players = new JsonBuilder()
                .addProperty("max", max)
                .addProperty("online", Bukkit.getOnlinePlayers().size())
                .addProperty("sample", List.of(sample).toString())
                .build();

        this.object.add("players", players);

        return this;
    }

    public StatusPacket motd(String motd){

        if(motd == null){
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
