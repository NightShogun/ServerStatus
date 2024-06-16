package xyz.skylar11d.minecraftp.serverstatus.utilities.builder;

import com.google.gson.JsonObject;

public class StatusPacket {

    private JsonObject object;

    public StatusPacket(){
        this.object = new JsonObject();
    }

    public StatusPacket version(String display, int protocol){

        JsonObject version = new JsonBuilder()
                .addProperty("name", display)
                .addProperty("protocol", protocol)
                .build();

        this.object.add("version", version);

        return this;
    }

    public StatusPacket players(String hover){

        JsonObject sample = new JsonBuilder()
                .addProperty("name", hover)
                .build();

        JsonObject players = new JsonBuilder()
                .addKey("sample", sample)
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
                .addKey("sample", sample)
                .build();

        this.object.add("players", players);

        return this;
    }

    public StatusPacket motd(String text){

        this.object.addProperty("description", text);

        return this;
    }

    public JsonObject toJSON(){return this.object;}

}
