package com.nosenkomi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nosenkomi.drop3d.screen.MainMenuScreen;

public class Drop3DGame extends Game {

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        this.setScreen(new MainMenuScreen(this));

    }

    @Override
    public void render() {
        //  Without this call, the Screen that you set in the create() method will not be rendered
        //  if you override the render method in your Game class!
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        this.getScreen().dispose();
    }

    /*

    public lateinit var batch: SpriteBatch
    public lateinit var font: BitmapFont

    override fun create() {
        batch = SpriteBatch()
        // use LibGDX's default Arial font
        font = BitmapFont()
        this.setScreen(MainMenuScreen(this))
    }

    override fun render() {
        //  Without this call, the Screen that you set in the create() method will not be rendered
        //  if you override the render method in your Game class!
        super.render()
    }

    override fun dispose() {
        this.getScreen().dispose()
        batch.dispose()
        font.dispose()
    }


     */
}
