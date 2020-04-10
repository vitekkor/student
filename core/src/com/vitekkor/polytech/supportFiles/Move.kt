package com.vitekkor.polytech.supportFiles

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor

class Move(x: Float, y: Float, var duration: Float, var interpolation: Interpolation?) {
    private var endX = x
    private var endY = y
    private var startX = 0f
    private var startY = 0f
    private var complete = false
    private var began = false
    var reverse = false
    private var time = 0f
    private lateinit var target: Actor

    constructor(duration: Float, interpolation: Interpolation?) : this(0f, 0f, duration, interpolation)

    fun act(delta: Float): Boolean {
        if (complete) return true
        return try {
            if (!began) {
                begin()
                began = true
            }
            time += delta
            complete = time >= duration
            var percent = (if (complete) 1f else time / duration)
            if (interpolation != null) percent = interpolation!!.apply(percent)
            update(if (reverse) 1 - percent else percent)
            complete
        } finally {
        }
    }

    private fun update(percent: Float) {
        val x: Float
        val y: Float
        when (percent) {
            0f -> {
                x = startX
                y = startY
            }
            1f -> {
                x = endX
                y = endY
            }
            else -> {
                x = startX + (endX - startX) * percent
                y = startY + (endY - startY) * percent
            }
        }
        target.setPosition(x, y)
    }

    fun setActor(actor: Actor) {
        target = actor
    }

    fun setPosition(x: Float, y: Float) {
        endX = x
        endY = y
    }

    private fun begin() {
        startX = target.x
        startY = target.y
    }

    fun restart() {
        time = 0f
        began = false
        complete = false
    }

    fun isComplete(): Boolean = complete
}