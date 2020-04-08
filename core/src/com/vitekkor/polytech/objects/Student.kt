package com.vitekkor.polytech.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction
import com.vitekkor.polytech.supportFiles.AssetsLoader


class Student(x: Float, y: Float, private var width: Int, private var height: Int) : Actor() {
    enum class State {
        FALLING, JUMPING, STANDING, RUNNING, GROWING, DEAD
    }

    private var targetPosition = Vector2(x, y)
    private var runTime = 0f
    private var previousState: State
    private var currentState: State
    private var position = Vector2(x, y)
    private var velocity = Vector2(0F, 0F)
    private var jumped = true
    private val jumpUp = moveTo(0f, 0f, 1 / 7f, Interpolation.linear)
    private val jumpDown = moveTo(0f, 0f, 1 / 7f, Interpolation.linear)
    private var go = true

    init {
        currentState = State.STANDING
        previousState = currentState
    }

    fun update(delta: Float) {}

    fun jump() {
        if (jumped) {
            jumped = false
            targetPosition = Vector2(position.x, position.y + 120f)
            jumpUp.setPosition(targetPosition.x, targetPosition.y)
            addAction(sequence(jumpUp, object : RunnableAction() {
                override fun run() {
                    targetPosition = Vector2(position.x, position.y - 120f)
                    jumpDown.setPosition(targetPosition.x, targetPosition.y)
                    addAction(sequence(jumpDown, object : RunnableAction() {
                        override fun run() {
                            jumped = true
                        }
                    }))
                }
            })
            )
        }
    }
    private fun move(){

    }

    fun go(right: Boolean) {
        position = if (right) Vector2(position.x + 10f, position.y) else Vector2(position.x - 10f, position.y)
        go = true
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
        targetPosition = position
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
}