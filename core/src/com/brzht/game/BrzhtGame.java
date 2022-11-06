package com.brzht.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.brzht.game.support.CamSupport;
import com.brzht.game.support.Globals;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BrzhtGame extends Game {
	public SpriteBatch batch;
	public void create() {

		Globals.camSupport = new CamSupport(new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
		Globals.camSupport.camera.setToOrtho(false, 640, 480);
		Globals.camSupport.camera.position.set(Globals.camSupport.camera.viewportWidth / 2f, Globals.camSupport.camera.viewportHeight / 2f, 0);

		Globals.camSupport.camera.zoom = 1;
		batch = new SpriteBatch();
		this.setScreen(new GameScreen(this));
	}

	public void render() {

		super.render(); // important!
		batch.begin();
		Globals.shapeRenderer.begin(ShapeRenderer.ShapeType.Point);
		//world.render();
		Globals.shapeRenderer.end();
		batch.end();
	}

	public void dispose() {
		batch.dispose();

	}
//	@Override
//	public void resize(int width, int height) {
//		super.resize(width, height);
//		Globals.camSupport.camera.setToOrtho(false, width, height);
//	}
//	@Override
//	public void resize(int width, int height) {
//		Globals.camSupport.viewport.update(width, height);
//		Globals.camSupport.camera.update();
//	}
}
