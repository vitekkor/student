package com.vitekkor.polytech.sreens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.vitekkor.polytech.Core
import com.vitekkor.polytech.objects.Buttons
import com.vitekkor.polytech.objects.PlayStage


class MainMenuScreen(core: Core) : Screen {
    private var game: Core = core
    private val buttons = Buttons(core)
    private var stage: PlayStage = PlayStage(ScreenViewport())
    private var play: TextButton
    private var exit: TextButton
    private var settings: TextButton
    private var table: Table

    init {
        val textButtonStyle = buttons.textButtonStyle
        table = Table()
        table.row().pad(20F)
        table.center()
        table.setFillParent(true)

        //create button "play" and add listener to it
        play = TextButton("Play", textButtonStyle)
        play.addListener(object : ClickListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.screen = LevelScreen(game)
                dispose()
            }
        })

        //create button "exit" and add listener to it
        exit = TextButton("Exit", textButtonStyle)
        exit.addListener(object : ClickListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.screen = ExitScreen(game)
                dispose()
            }
        })

        settings = TextButton("Settings", textButtonStyle)
        settings.addListener(object : ClickListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                //game.screen = SettingsScreen(game)
                dispose()
            }
        })
        table.add(play)
        table.row()
        table.add(settings)
        table.row()
        table.add(exit)
        stage.addActor(table)
        Gdx.input.inputProcessor = stage
        Gdx.input.setCatchKey(Input.Keys.BACK, true)

        //listener for the Back key
        stage.setHardKeyListener(object : PlayStage.OnHardKeyListener {
            override fun onHardKey(keyCode: Int, state: Int) {
                if (keyCode == Input.Keys.BACK && state == 1) {
                    game.screen = ExitScreen(game)
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
        stage.act(delta)
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