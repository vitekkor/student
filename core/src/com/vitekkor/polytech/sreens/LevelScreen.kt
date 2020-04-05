package com.vitekkor.polytech.sreens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.vitekkor.polytech.Core
import com.vitekkor.polytech.objects.PlayStage
import com.vitekkor.polytech.objects.Buttons

class LevelScreen(game: Core) : Screen {
    private val buttons = Buttons(game)
    private var stage: PlayStage = PlayStage(ScreenViewport())
    private var f1st: TextButton
    private var s2nd: TextButton
    private var table: Table
    private var labelStyle: Label.LabelStyle

    init {
        labelStyle = buttons.labelStyle
        val levelStyle = buttons.levelStyle
        table = Table()
        table.center()
        table.setFillParent(true)
        f1st = TextButton("1", levelStyle)
        f1st.addListener(object : ClickListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {

            }
        })
        s2nd = TextButton("2", levelStyle)
        s2nd.addListener(object : ClickListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.screen = TestScreen(game)
                dispose()
            }
        })
        table.add(f1st)//.expandY().fillY()
        table.row()
        table.add(s2nd)//.expandY().fillY()
        stage.addActor(table)
        Gdx.input.inputProcessor = stage
        Gdx.input.setCatchKey(Input.Keys.BACK, true)
        stage.setHardKeyListener(object : PlayStage.OnHardKeyListener {
            override fun onHardKey(keyCode: Int, state: Int) {
                if (keyCode == Input.Keys.BACK && state == 1) {
                    game.screen = MainMenuScreen(game)
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
    }

}