package com.vitekkor.polytech.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Student extends Actor {
    private Array<TextureAtlas.AtlasRegion> regions;
    private Animation animation;
    private float stateTime;
    private float positionX = 0f;
    private float positionY = 0f;
    private float myWidth;
    private float myHeight;
    private float lastTime = 0;
    private boolean jump;

    public Student(TextureAtlas textureAtlas) {
        regions = textureAtlas.getRegions();
        setPosition(20f, 480f);
        setSize(regions.get(0).getRegionWidth(), regions.get(0).getRegionHeight());
        setScale(2f);
        animation = new Animation(1 / 8f, regions);
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stateTime += Gdx.graphics.getDeltaTime();
        batch.draw((TextureRegion) animation.getKeyFrame(stateTime, true), positionX, positionY, myWidth, myHeight);

    }

    @Override
    public void setPosition(float x, float y) {
        positionX = x;
        positionY = y;
        positionChanged();
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        setPosition(x, y);
    }

    @Override
    public void setScale(float scaleXY) {
        setSize(myWidth * scaleXY, myHeight * scaleXY);
    }

    @Override
    public void setSize(float width, float height) {
        myHeight = height;
        myWidth = width;
    }

    @Override
    public float getX(int alignment) {
        return positionX;
    }

    @Override
    public float getY(int alignment) {
        return positionY;
    }

    private void setRegions(TextureAtlas newTextureAtlas) {
        regions = newTextureAtlas.getRegions();
    }

    public void jump() {
        if (positionY == 480)
            addAction(sequence(moveTo(positionX, positionY + 160f, 2 / 7f), moveTo(positionX, positionY, 2 / 7f)));
    }

    public void go(boolean right) {
        float x = positionX;
        if (right) x += 20;
        else x -= 20;
        addAction(moveTo(x, positionY, 1 / 7f));
    }
}
