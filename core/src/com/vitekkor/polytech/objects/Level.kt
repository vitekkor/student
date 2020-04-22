package com.vitekkor.polytech.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.JsonValue
import com.vitekkor.polytech.supportFiles.AssetsLoader

class Level(lvl: JsonValue) : Actor() {
    private val level = lvl
    private val position: Pair<Float, Float>
    private val texture: Texture
    private val collisions = mutableListOf<Float>()

    init {
        AssetsLoader.manager.load(level[0].asString(), Texture::class.java)
        texture = AssetsLoader.manager.get(level[0].asString())
        val p = level[1].asFloatArray()
        position = p[0] to p[1]
        level[2].forEach {
            collisions.addAll(it.asFloatArray().asList())
        }
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        batch.draw(texture, position.first, position.second, width, height)
    }

    fun isCollision(camPosition: Pair<Float, Float>, playerPosition: Pair<Float, Float>): Boolean {
        val realPosition = camPosition.first - position.first to camPosition.second - position.second
        for (collision in collisions.windowed(4, 4)) {
            if (realPosition.first in collision[0]..collision[3] && realPosition.second in collision[1]..collision[2]) return true
        }
        return false
    }

    fun dispose() {
        collisions.clear()
        AssetsLoader.manager.unload(level[0].asString())
    }
}