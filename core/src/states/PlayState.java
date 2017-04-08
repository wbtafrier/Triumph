package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayState extends State {
	//private Bird bird;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        //bird = new Bird(50,100);
        //cam.setToOrtho();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        //bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
        sb.end();

    }
}
