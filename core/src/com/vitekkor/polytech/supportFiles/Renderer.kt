package com.vitekkor.polytech.supportFiles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.vitekkor.polytech.objects.World

class Renderer(world: World) {
    private val batch = SpriteBatch()
    private val world = world
    private val camera = OrthographicCamera()
    private val shapeRenderer = ShapeRenderer()

    init {
        camera.setToOrtho(true, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch.projectionMatrix = camera.combined
        shapeRenderer.projectionMatrix = camera.combined
    }

    fun render(runTime: Float) {
        Gdx.gl.glClearColor(0F, 0F, 0F, 1F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        //background
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1f)
        shapeRenderer.rect(0f, 0f, Gdx.graphics.width + 0f, Gdx.graphics.height + 0f)
        //grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1f)
        shapeRenderer.rect(0f, Gdx.graphics.height - 63f, Gdx.graphics.width + 0f, 11f)
        //dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1f)
        shapeRenderer.rect(0f, Gdx.graphics.height - 52f, Gdx.graphics.width + 0f, 52f)
        shapeRenderer.end()
    }
}