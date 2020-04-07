package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Game;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State {
    private Bird bird;
    private Texture bg;
    private static final int TUBE_SPACING =225;
    private static final int TUBE_COUNT =4;
    private Array<Tube> tubes;
    private Tube tube;
    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,800);
        cam.setToOrtho(false, Game.WIDTH/2,Game.HEIGHT/2);
        bg = new Texture("bg.png");
        tubes = new Array<Tube>();
        for (int i=1;i<=TUBE_COUNT;i++){
            tubes.add(new Tube(i*(TUBE_SPACING+Tube.TUBE_WIDTH)));
        }

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched())
            bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x+216;
        for(int i=0;i<tubes.size;i++){
            Tube tube = tubes.get(i);
            if(cam.position.x-(cam.viewportWidth/2)>tube.getPosTopTube().x+tube.getToptube().getWidth()){
                tube.reposition(tube.getPosTopTube().x+((Tube.TUBE_WIDTH+TUBE_SPACING)*TUBE_COUNT));
            }
            if(tube.collide(bird.getBounds())){
                gsm.set(new PlayState(gsm));
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x-(cam.viewportWidth),0);
        sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
        for(Tube tube:tubes){
        sb.draw(tube.getToptube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
        sb.draw(tube.getBottomtube(),tube.getPosBottomTube().x,tube.getPosBottomTube().y);}
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for(Tube tube:tubes)
            tube.dispose();
    }
}
