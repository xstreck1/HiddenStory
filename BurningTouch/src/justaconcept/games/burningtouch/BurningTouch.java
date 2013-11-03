package justaconcept.games.burningtouch;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

public class BurningTouch implements ApplicationListener {
    private DrawingManager drawing_manager;
    private UpdateManager update_manager;
    private TouchManager touch_manager;
    private HashMap<String, SceneObject> scene_objects;

    public void loadWorkingMask() {
	GameState.working_mask = new Pixmap(Gdx.files.internal(Sources.getMaskName(GameState.latest_paper)));

	GameState.working_mask.setBlending(Pixmap.Blending.None);
	for (int x = 0; x < Constants.GAME_WIDTH; x++) {
	    for (int y = 0; y < Constants.GAME_HEIGHT; y++) {
		GameState.working_mask.drawPixel(x, y, GameState.working_mask.getPixel(x, y) & 0xFFFFFF00);
	    }
	}
    }

    @Override
    public void create() {
	LayoutManager.testResolution();

	loadWorkingMask();

	scene_objects = new HashMap<String, SceneObject>();
	scene_objects.put(Constants.BG_OBJ_STR, new BackgroundGraphic());
	BasicPaper current_paper;
	if (GameState.current_paper == GameState.latest_paper)
	    current_paper = new DynamicPaper( GameState.working_mask);
	else
	    current_paper = new StaticPaper( GameState.current_paper);
	scene_objects.put(Constants.PPR_OBJ_STR, current_paper);
	scene_objects.put(Constants.BTN_OBJ_STR, new Buttons());

	drawing_manager = new DrawingManager( scene_objects);
	update_manager = new UpdateManager( scene_objects);
	touch_manager = new TouchManager( scene_objects);
    }

    @Override
    public void resize(int width, int height) {
	// TODO Auto-generated method stub

    }

    @Override
    public void render() {
	touch_manager.update();
	update_manager.update();
	drawing_manager.update();
    }

    @Override
    public void pause() {
	// TODO Auto-generated method stub

    }

    @Override
    public void resume() {
	// TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
	// TODO Auto-generated method stub

    }
}