package xyz.skylar11d.minecraftp.serverstatus.utilities.builder;

import com.google.gson.JsonObject;

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

        if(display.isEmpty())return this;

        JsonObject version = new JsonBuilder()
                .addProperty("name", display)
                .build();

        this.object.add("version", version);

        return this;
    }

    public StatusPacket version(String display, int protocol){

        if(display.isEmpty() && protocol != 0) {
            this.version(protocol);
            return this;
        }

        if(!display.isEmpty() && protocol == 0) {
            this.version(display);
            return this;
        }

        JsonObject version = new JsonBuilder()
                .addProperty("name", display)
                .addProperty("protocol", protocol)
                .build();

        this.object.add("version", version);

        return this;
    }

    public StatusPacket players(String hover){

        if(hover.isEmpty())
            throw new IllegalArgumentException("The 'hover' field shoudn't be empty, check the respective configuration file");

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

        if(text.isEmpty())throw new IllegalArgumentException("The 'text' field in the motd section shoudn't be empty, check the respective configuration file");

        this.object.addProperty("description", text);

        return this;
    }

    public JsonObject toJSON(){return this.object;}

}
