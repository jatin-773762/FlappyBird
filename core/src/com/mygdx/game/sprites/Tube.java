package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {


    public static final int TUBE_WIDTH = 130;
    private static final int FLUCTUATION = 430;
    private static final int TUBEGAP = 130;
    private static final int LOWESTOPENING = 300;
    private Texture toptube,bottomtube;
    private Vector2 posTopTube,posBottomTube;
    private Random rand;
    private Rectangle bountTop,boundBottom;
    public Texture getToptube() {
        return toptube;
    }

    public Texture getBottomtube() {
        return bottomtube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public Tube(float x){
        toptube = new Texture("toptube.png");
        bottomtube = new Texture("bottomtube.png");
        rand = new Random();
        posTopTube = new Vector2(x,rand.nextInt(FLUCTUATION)+TUBEGAP+LOWESTOPENING);
        posBottomTube = new Vector2(x,posTopTube.y-TUBEGAP-bottomtube.getHeight());
        bountTop = new Rectangle(posTopTube.x,posTopTube.y,toptube.getWidth(),toptube.getHeight());
        boundBottom = new Rectangle(posBottomTube.x,posBottomTube.y,bottomtube.getWidth(),bottomtube.getHeight());

    }
    public void reposition(float x){
        posTopTube.set(x,rand.nextInt(FLUCTUATION)+TUBEGAP+LOWESTOPENING);
        posBottomTube = new Vector2(x,posTopTube.y-TUBEGAP-bottomtube.getHeight());
        bountTop.setPosition(posTopTube.x,posTopTube.y);
        boundBottom.setPosition(posBottomTube.x,posBottomTube.y);
    }
    public boolean collide(Rectangle player){
        return player.overlaps(bountTop)||player.overlaps(boundBottom);
    }
    public void dispose(){
        toptube.dispose();
        bottomtube.dispose();
    }

}
