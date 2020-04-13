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
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.vitekkor.polytech.Core
import com.vitekkor.polytech.objects.PlayStage
import com.vitekkor.polytech.supportFiles.AssetsLoader


class ExitScreen(core: Core) : Screen {
    private var game: Core = core
    private var stage: PlayStage = PlayStage(ScreenViewport())
    private var yes: TextButton
    private var no: TextButton
    private var table: Table
    private var labelStyle: Label.LabelStyle = AssetsLoader.labelStyle
    private val text = "Are you sure you want to quit?"

    init {
        val label = Label(text, labelStyle)
        label.setAlignment(Align.center)
        val textButtonStyle = AssetsLoader.textButtonStyle
        table = Table()
        table.center()
        table.setFillParent(true)
        yes = TextButton("Yes", textButtonStyle)
        yes.addListener(object : ClickListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                AssetsLoader.dispose()
                Gdx.app.exit()
                dispose()
            }
        })
        no = TextButton("No", textButtonStyle)
        no.setSize(100F, 100F)
        no.addListener(object : ClickListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.screen = MainMenuScreen(game)
                dispose()
            }
        })
        table.row().colspan(3).expandX().fillX()
        table.add(label).fillX()
        table.row().padTop(20F)
        table.row().expandX().fillX()
        table.add(yes).fillX()
        //table.padRight(20F)
        table.add(no).fillX()
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
        stage.act(0F)
        stage.draw()
    }

    override fun pause() {}

    override fun resume() {}

    override fun resize(width: Int, height: Int) {}

    override fun dispose() {
        stage.dispose()
    }

}