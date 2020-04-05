package com.vitekkor.polytech.sreens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.vitekkor.polytech.Core
import com.vitekkor.polytech.objects.PlayStage
import com.vitekkor.polytech.objects.Student

class TestScreen(core: Core) : Screen {
    private val game = core

    //private val buttons = core.buttons
    private var stage: PlayStage = PlayStage(ScreenViewport())
    private var table: Table = Table()
    private var studentStyle = TextureAtlas(Gdx.files.internal("images/student.atlas"))

    init {
        //val ss = studentStyle.regions
        val actor1 = Student(studentStyle)
        table.center()
        table.setFillParent(true)
        stage.addActor(actor1)
        //actor1.setSize(240F, 160F)
        Gdx.input.inputProcessor = stage
        Gdx.input.setCatchKey(Input.Keys.BACK, true)
        stage.setHardKeyListener(object : PlayStage.OnHardKeyListener {
            override fun onHardKey(keyCode: Int, state: Int) {
                if (keyCode == Input.Keys.BACK && state == 1) {
                    core.screen = MainMenuScreen(core)
                    dispose()
                }
            }
        })
    }

    override fun hide() {}

    override fun show() {}

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0F, 0F, 0F, 1F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
    }

    override fun pause() {}

    override fun resume() {}

    override fun resize(width: Int, height: Int) {}

    override fun dispose() {
        stage.dispose()
        game.dispose()
    }

}