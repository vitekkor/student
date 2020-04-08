package com.vitekkor.polytech.sreens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.vitekkor.polytech.Core
import com.vitekkor.polytech.supportFiles.InputHandler
import com.vitekkor.polytech.supportFiles.Renderer
import com.vitekkor.polytech.objects.World

class PlayScreen(core: Core) : Screen {

    private var world: World = World((Gdx.graphics.height / (Gdx.graphics.width / 136)) / 2)
    private var renderer: Renderer = Renderer(world)
    private var runTime = 0F

    init {
        Gdx.input.inputProcessor = InputHandler(world.getStudent())
    }

    override fun hide() {

    }

    override fun show() {
        //println("GameScreen - show called")
    }

    override fun render(delta: Float) {
        runTime += delta
        world.update(delta)
        renderer.render(runTime)
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {

    }
}