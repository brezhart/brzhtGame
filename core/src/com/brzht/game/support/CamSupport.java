package com.brzht.game.support;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CamSupport {
    public OrthographicCamera camera = null;
    public Viewport viewport = null;
    public CamSupport(OrthographicCamera cam){
        camera = cam;
    }

    public Vector2 coordsToPixel(Vector2 coords){
        return ((coords.cpy().scl(32)).sub(Utils.toVector2(camera.position))).scl(1/camera.zoom).add(camera.viewportWidth/2f, camera.viewportHeight/2f);

    }
    public Vector2 pixelToCoords(Vector2 pixel) {
        return ((pixel.cpy().sub(camera.viewportWidth / 2f, camera.viewportHeight / 2f)) .scl(camera.zoom).add(Utils.toVector2(camera.position))).scl(1/32f);
    }
    public float WIDTH(){
        return camera.viewportWidth;
    }
    public float HEIGHT(){
        return camera.viewportHeight;
    }

}
