package com.vitekkor.polytech.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import com.badlogic.gdx.utils.Pool


class Studen(textureAtlas: TextureAtlas) : Actor() {

    private var moveTo = MoveToAction()
    private var regions = textureAtlas.regions
    private var animation: Animation<Any>? = null
    var stateTime = 0f
    private var positionX = 0f
    private var positionY = 0f
    private var myWidth = 0f
    private var myHeight = 0f

    init {
        setPosition(20f, 480f)
        setScale(1.5f)
        animation = Animation(1 / 8f, regions)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        setSize(240f, 160f)
        super.draw(batch, parentAlpha)
        stateTime += Gdx.graphics.deltaTime
        batch.draw(animation!!.getKeyFrame(stateTime, true) as TextureRegion, positionX, positionY, myWidth, myHeight)
    }

    override fun setPosition(x: Float, y: Float) {
        positionX = x
        positionY = y
    }

    override fun setSize(width: Float, height: Float) {
        myHeight = height
        myWidth = width
    }

    override fun setScale(scaleXY: Float) {
        setSize(width * scaleXY, height * scaleXY)
    }

    fun setRegion(newTextureAtlas: TextureAtlas) {
        regions = newTextureAtlas.regions
    }

    fun jump() {
        moveTo.setPosition(positionX, positionY + 160f)
        moveTo.duration = 2f
        addAction(moveTo)

    }
}