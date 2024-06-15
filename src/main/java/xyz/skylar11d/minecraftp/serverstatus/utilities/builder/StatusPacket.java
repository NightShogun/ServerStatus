package xyz.skylar11d.minecraftp.serverstatus.utilities.builder;

import com.google.gson.JsonObject;

public class StatusPacket {

    private JsonObject object;

    public StatusPacket version(String display, int protocol){

        JsonObject version = new JsonBuilder()
                .addProperty("name", display)
                .addProperty("protocol", protocol)
                .build();

        this.object.add("version", version);

        return this;
    }

    public StatusPacket players(String hover){


        return this;
    }

    public StatusPacket players(int max, String hover){

        JsonObject sample = new JsonBuilder()
                .addProperty("max", max)
                .build();

        this.object.add("players", sample);

        return this;
    }

    public JsonObject build(){return this.object;}

}
