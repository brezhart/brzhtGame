package com.brzht.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.json.*;

public class Assets {
    public static Map<String, TextureRegion> textures = new HashMap<>();
    public static Map<String, Animation<TextureRegion>> animations = new HashMap<>();
    //java.util.Map<String, Animation> animations;

    static {
        FileHandle handle = Gdx.files.local("texturesAlign.json");
        JSONObject obj = new JSONObject(handle.readString());
        Texture allTextures = loadTexture("textures.png");
        for (String key: obj.keySet()){
            JSONArray align = obj.getJSONArray(key);
            TextureRegion region = new TextureRegion(allTextures, align.getInt(0), align.getInt(1), align.getInt(2), align.getInt(3));
            textures.put(key, region);
        }


        handle = Gdx.files.local("animationTexturesAlign.json");
        obj = new JSONObject(handle.readString());
        allTextures = loadTexture("animationTextures.png");
        for (String key: obj.keySet()){
            JSONArray align = obj.getJSONObject(key).getJSONArray("align");
//            Vector<TextureRegion> frames = new Vector<TextureRegion>();
            int framesAmount = obj.getJSONObject(key).getInt("framesAmount");
            TextureRegion[] frames = new TextureRegion[framesAmount];
            float duration = obj.getJSONObject(key).getFloat("time");
            int width = align.getInt(2)/framesAmount;
            for (int i = 0; i < framesAmount; i++){
                TextureRegion region = new TextureRegion(allTextures, align.getInt(0) + width*i, align.getInt(1), width, align.getInt(3));
                frames[i] = region;
            }
            Animation<TextureRegion> animation = new Animation<>(duration/framesAmount, frames);
            animation.setPlayMode(Animation.PlayMode.LOOP);
            animations.put(key, animation);

        }

    }
    public static TextureRegion getRegion(String name){
        return textures.get(name);
    }
    public static TextureRegion getAnimFrame(String name, float time){
        return animations.get(name).getKeyFrame(time);
    }

    public static Texture loadTexture (String file) {
        return new Texture(Gdx.files.internal(file));
    }
}
