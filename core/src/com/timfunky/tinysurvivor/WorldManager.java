package com.timfunky.tinysurvivor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.swing.tree.AbstractLayoutCache;
import java.util.Random;

public class WorldManager {

    Texture stone,ore;
    Sprite s;

    public BlockType[] world;

    WorldManager(){
        stone = new Texture("img/tstone.png");
        ore = new Texture("img/tore.png");
        s = new Sprite(stone);
        world = new BlockType[50];
    }
    
    void generate(){
        boolean isGap = false;
        Random rand = new Random();
        for(int i = 0; i < world.length; i++) {
            if(!isGap){
                if(rand.nextInt(5) == 3){
                    world[i] = BlockType.ORE;
                } else {
                    world[i] = BlockType.STONE;
                }

            } else {
                world[i] = BlockType.AIR;
            }
            if(rand.nextInt(5) == 4){
                isGap = !isGap;
            }
        }
    }
    
    void draw(SpriteBatch b){
        for(int i = 0; i<world.length; i++){
            switch (world[i]){
                case AIR:
                    continue;
                case STONE:
                    s.setPosition(i*64,0);
                    s.setTexture(stone);
                    s.draw(b);
                    break;
                case ORE:
                    s.setPosition(i*64,0);
                    s.setTexture(ore);
                    s.draw(b);
                    break;
            }
        }
    }
    void dispose(){
        stone.dispose();
        ore.dispose();
    }
}
