package com.vitekkor.polytech.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.vitekkor.polytech.supportFiles.AssetsLoader
import com.vitekkor.polytech.supportFiles.Move


class Student(x: Float, y: Float, private var width: Int, private var height: Int) : Actor() {

    private var targetPosition = Vector2(x, y)
    private var runTime = 0f
    private var position = Vector2(x, y)
    private var jumpUp = false
    private var jumped = true
    private var jumpDown = false
    private var myMove = Move(1 / 60f, Interpolation.linear)

    init {
        myMove.duration = 1 / 60f
        myMove.interpolation = Interpolation.linear
        myMove.setActor(this)
    }

    fun update(delta: Float) {
        if (!jumpDown && !jumpUp && !jumped) {
            myMove.duration = 1 / 60f
            jumped = true
        }
        myMove.setPosition(targetPosition.x, targetPosition.y)
        if (myMove.act(delta)) {
            if (!jumpUp && jumpDown) {
                jumpDown = false
            }
            if (jumpUp) {
                jumpUp = false
                targetPosition = Vector2(targetPosition.x, targetPosition.y - 120f)
            }
            myMove.restart()
        }
    }

    fun jump() {
        if (jumped) {
            jumpUp = true
            jumpDown = true
            jumped = false
            targetPosition = Vector2(targetPosition.x, targetPosition.y + 120f)
            myMove.duration = 1 / 7f
            myMove.restart()
        }
    }

    fun go(right: Boolean) {
        targetPosition = if (right) Vector2(targetPosition.x + 5f, targetPosition.y) else Vector2(targetPosition.x - 5f, targetPosition.y)
    }

    override fun getX(): Float {
        return position.x
    }

    override fun getY(): Float {
        return position.y
    }

    override fun getX(alignment: Int): Float {
        return position.x
    }

    override fun getY(alignment: Int): Float {
        return position.y
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        runTime += Gdx.graphics.deltaTime
        //update(runTime)
        //targetPosition = position
        batch.draw(AssetsLoader.studentAnimation!!.getKeyFrame(runTime, true) as TextureRegion, x, y, getWidth(), getHeight())
    }

    override fun getWidth(): Float {
        return width.toFloat()
    }

    override fun getHeight(): Float {
        return height.toFloat()
    }

    override fun setPosition(x: Float, y: Float, alignment: Int) {
        position.x = x
        position.y = y
    }

    override fun setPosition(x: Float, y: Float) {
        position.x = x
        position.y = y
    }
}