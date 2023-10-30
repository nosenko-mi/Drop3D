package com.nosenkomi.drop3d.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nosenkomi.Drop3DGame;

public class GameScreen implements Screen {

    public PerspectiveCamera cam;

    // A model contains everything on what to render and it keeps track of the resources.
    // It doesn't contain information like where to render the model.
    // Therefor we need to create a ModelInstance.
    public ModelBatch modelBatch;
    public AssetManager assets;
    public Array<ModelInstance> instances = new Array<ModelInstance>();
    public Environment environment;

    public CameraInputController camController;
    public boolean loading;

    Drop3DGame game;

    String modelPath = "bucket/bucketg3db.g3db";

    public GameScreen(Drop3DGame game) {
        this.game = game;

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 30f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        assets = new AssetManager();
        assets.load(modelPath, Model.class);
        loading = true;

    }

    private void doneLoading() {
        Model model = assets.get(modelPath, Model.class);
        ModelInstance modelInstance = new ModelInstance(model);
        instances.add(modelInstance);
        loading = false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (loading && assets.update()) {
            doneLoading();
        }
        camController.update();

        ScreenUtils.clear(0f, 0f, 0f, 1, true);

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        instances.clear();
        assets.dispose();
    }
}
