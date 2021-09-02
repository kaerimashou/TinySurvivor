package com.timfunky.tinysurvivor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Player {
    Texture p1,p2;
    Sprite s;

    float dx,dy;
    float jumpStrength;
    float moveSpeed;

    byte animCooldown;

    boolean isRunning;

    Player(){
        p1 = new Texture("img/p1.png");
        p2 = new Texture("img/p2.png");
        s = new Sprite(p1);
        moveSpeed = 0.4f;
        jumpStrength = 5;
        setPos(1000,0);
    }

    void update(){
        //Controls handling
        dx*=0.9f;
        dy-=0.2f;
        if(dx < 0.1f && dx > -0.1f){dx=0;}

        isRunning = false;

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            isRunning = true;
            dx+=moveSpeed/2;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            isRunning = true;
            dx-=moveSpeed/2;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && s.getY()<65){
            dy = jumpStrength;
        }
        dx = MathUtils.clamp(dx,-5f, 5f);
        dy = MathUtils.clamp(dy,-5f, 5f);
        move(dx,dy);
        setPos(s.getX(),MathUtils.clamp(s.getY(),64, 480));

        //Animation handling
        if(isRunning){animCooldown++;}
        if(animCooldown>10){
            animCooldown = 0;
            if(s.getTexture() == p1){
                s.setTexture(p2);
            } else {
                s.setTexture(p1);
            }

        }
    }
    void setPos(float x, float y){
        s.setPosition(x,y);
    }
    void disposeTextures(){
        p1.dispose();
        p2.dispose();
    }
    void handleWorldBorders(int a, int b){ s.setPosition(MathUtils.clamp(s.getX(),a,b),s.getY()); }
    void handleWorldCollision(BlockType w[]){
        for (int i = 0; i<w.length; i++) {
            if(w[i] == BlockType.AIR){
                if (s.getX()-32>i*64-32 && s.getX()-32<i*64+32){

                }
            }

        }
    }
    void move(float x,float y){ s.setPosition(s.getX()+x,s.getY()+y);}
    void draw(SpriteBatch sb){ s.draw(sb); }
}
