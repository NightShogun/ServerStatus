package xyz.skylar11d.minecraftp.serverstatus.utilities.builder;

import com.google.gson.JsonObject;

public class JsonBuilder {

    private final JsonObject jsonObject;

    public JsonBuilder(){
        this.jsonObject = new JsonObject();
    }

    public JsonBuilder addProperty(String name, String value) {

        jsonObject.addProperty(name, value);

        return this;
    }

    public JsonBuilder addProperty(String name, Integer value) {

        jsonObject.addProperty(name, value);

        return this;
    }

    public JsonBuilder addProperty(String name, Boolean value) {

        jsonObject.addProperty(name, value);

        return this;
    }

    public JsonBuilder addProperty(String name, Character value) {

        jsonObject.addProperty(name, value);

        return this;
    }

    public JsonBuilder addKey(String name, JsonObject object){

        jsonObject.add(name, object);

        return this;
    }

    public JsonObject build(){
        return this.jsonObject;
    }

}
