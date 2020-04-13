package com.vitekkor.polytech.sreens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.vitekkor.polytech.Core
import com.vitekkor.polytech.objects.PlayStage
import com.vitekkor.polytech.supportFiles.AssetsLoader

class LoadScreen(core: Core) : Screen {
    private val game = core
    private var load = 0f
    private val stage = PlayStage(ScreenViewport())
    private val progressBar = ProgressBar(0f, 100f, 10f, false, AssetsLoader.progressBar)

    init {
        progressBar.setPosition(Gdx.graphics.width /4f, Gdx.graphics.height /2f)
        stage.addActor(progressBar)
    }

    override fun hide() {}

    override fun show() {}

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0F, 0F, 0F, 1F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        if (AssetsLoader.manager.update()) {
            AssetsLoader.setLoadedAssets()
            progressBar.value = 100f
            game.screen = MainMenuScreen(game)
        }
        progressBar.value = AssetsLoader.manager.progress * 100f
        stage.draw()
        stage.act(delta)

    }

    override fun pause() {}

    override fun resume() {}

    override fun resize(width: Int, height: Int) {}

    override fun dispose() {}

}