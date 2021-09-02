package com.timfunky.tinysurvivor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TinySurvivor extends ApplicationAdapter {

	SpriteBatch batch;
	Player p;
	WorldManager wg;

	Camera cam;
	Vector2 camOffset;

	float halfScreenWidth, halfScreenHeight;
	int leftBorder, rightBorder;

	@Override
	public void create () {
		batch = new SpriteBatch();
		p = new Player();

		halfScreenWidth = (float)Gdx.graphics.getWidth()/2;
		halfScreenHeight = (float)Gdx.graphics.getHeight()/2;

		wg = new WorldManager();
		wg.generate();

		leftBorder = 0;
		rightBorder = wg.world.length*64-64;

		cam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camOffset = new Vector2(p.s.getX()+32,halfScreenHeight);
	}

	@Override
	public void render () {
		halfScreenWidth = (float)Gdx.graphics.getWidth()/2;
		halfScreenHeight = (float)Gdx.graphics.getHeight()/2;
		ScreenUtils.clear(0.04f, 0.06f, 0.13f, 1);

		batch.setProjectionMatrix(cam.combined);
		batch.begin();

		wg.draw(batch);
		p.update();
		p.handleWorldBorders(leftBorder,rightBorder);
		p.draw(batch);

		if(p.s.getX()+32>halfScreenWidth && p.s.getX()+32<wg.world.length*64-halfScreenWidth){
			camOffset.x = p.s.getX()+32;
		}

		cam.position.set(camOffset.x,camOffset.y,0);
		cam.update();

		System.out.println(p.s.getX()+" "+p.s.getY());

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		p.disposeTextures();
		wg.dispose();
	}
}
