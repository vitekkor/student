package com.vitekkor.polytech.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction
import com.badlogic.gdx.utils.Pool
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
    private val pool: Pool<MoveToAction> = object : Pool<MoveToAction>() {
        override fun newObject(): MoveToAction? {
            return MoveToAction()
        }
    }
    private var jumpUp = true
    private var jumpDown = false
    private var jumped = true

    init {
        currentState = State.STANDING
        previousState = currentState
    }

    fun update(delta: Float) {}

    fun jump() {
        if (jumped) {
            jumped = false
            targetPosition = Vector2(position.x, position.y + 120f)
            addAction(sequence(moveTo(targetPosition.x, targetPosition.y, 1 / 7f, Interpolation.linear),
                    object : RunnableAction() {
                        override fun run() {
                            addAction(sequence(moveTo(targetPosition.x, targetPosition.y - 120f, 1 / 7f, Interpolation.linear),
                                    object : RunnableAction() {
                                        override fun run() {
                                            jumped = true
                                        }
                                    })
                            )
                        }
                    })
            )
        }
    }

    fun onClick() {
        velocity.y = -140f
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