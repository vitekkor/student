package com.vitekkor.polytech.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.badlogic.gdx.utils.Pool
import com.vitekkor.polytech.supportFiles.AssetsLoader


class Student(x: Float, y: Float, private var width: Int, private var height: Int) : Actor() {

    private var targetPosition = Vector2(x, y)
    private var runTime = 0f
    private var position = Vector2(x, y)
    private var jumped = true
    private var move: MoveToAction
    private var jumpDown: MoveToAction
    private val pool: Pool<MoveToAction> = object : Pool<MoveToAction>() {
        override fun newObject(): MoveToAction {
            return MoveToAction()
        }
    }

    init {
        move = pool.obtain()
        move.pool = pool
        move.duration = 1 / 7f
        move.interpolation = Interpolation.linear
        move.actor = this
        jumpDown = pool.obtain()
        jumpDown.pool = pool
        jumpDown.interpolation = Interpolation.linear
        jumpDown.duration = 1 / 7f
        jumpDown.actor = this
    }

    fun update(delta: Float) {}

    fun jump() {
        if (jumped) {
            jumped = false
            targetPosition = Vector2(targetPosition.x, targetPosition.y + 120f)
            move.setPosition(targetPosition.x, targetPosition.y)
            val sequence: SequenceAction = sequence(move, object : RunnableAction() {
                override fun run() {
                    if (!jumped) {
                        targetPosition = Vector2(targetPosition.x, targetPosition.y - 120f)
                        jumpDown.setPosition(targetPosition.x, targetPosition.y)
                        addAction(sequence(jumpDown, object : RunnableAction() {
                            override fun run() {
                                jumped = true
                            }
                        }))
                    }
                }
            })
            addAction(sequence)
        }
    }

    fun go(right: Boolean) {
        position = if (right) Vector2(position.x + 10f, position.y) else Vector2(position.x - 10f, position.y)
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