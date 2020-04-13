package com.vitekkor.polytech.sreens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Dialog
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.widget.VisTextButton
import com.vitekkor.polytech.Core
import com.vitekkor.polytech.objects.PlayStage
import com.vitekkor.polytech.objects.Student
import com.vitekkor.polytech.supportFiles.AssetsLoader

class TestScreen(core: Core) : Screen {
    private val game = core

    private var stage: PlayStage = PlayStage(ScreenViewport())
    private var table: Table = Table()
    private var space: TextButton
    private var actor1: Student = Student(20f, 280f, 70, 120)
    private var touchPad = Touchpad(20f, AssetsLoader.touchPadStyle)

    init {
        table.center()
        table.setFillParent(true)
        stage.addActor(actor1)
        space = TextButton("", AssetsLoader.textButtonStyle)
        space.setPosition(Gdx.graphics.width - Gdx.graphics.height / 3f, 0f)
        space.setSize(Gdx.graphics.height / 3f, Gdx.graphics.height / 3f)
        stage.addActor(space)
        touchPad.setPosition(120f, 0f)
        touchPad.setSize(Gdx.graphics.height / 3f, Gdx.graphics.height / 3f)
        stage.addActor(touchPad)
        Gdx.input.inputProcessor = stage
        Gdx.input.setCatchKey(Input.Keys.BACK, true)
        stage.setHardKeyListener(object : PlayStage.OnHardKeyListener {
            override fun onHardKey(keyCode: Int, state: Int) {
                if (keyCode == Input.Keys.BACK && state == 1) {
                    val dialog = Dialog("Are you sure you want to quit?", AssetsLoader.skin)
                    dialog.setPosition(Gdx.graphics.width /4f, Gdx.graphics.height /4f)
                    dialog.setSize(Gdx.graphics.width /2f, Gdx.graphics.height /2f)
                    dialog.text("All progress at this level made will be reset")
                    dialog.row()
                    val yes = TextButton("Yes", AssetsLoader.textButtonStyle)
                    yes.addListener(object : ClickListener() {
                        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                            game.screen = LevelScreen(game)
                            dispose()
                        }
                    })
                    dialog.button(yes)
                    dialog.padRight
                    val no = TextButton("No", AssetsLoader.textButtonStyle)
                    no.addListener(object : ClickListener() {
                        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                           dialog.addAction(Actions.fadeOut(0.4f, Interpolation.fade))
                        }
                    })
                    dialog.button(no)
                    stage.addActor(dialog)
                }
                if (keyCode == Input.Keys.SPACE && state == 1) {
                    actor1.jump()
                }
                /* if (keyCode == Input.Keys.LEFT && state == 1) {
                     actor1.go(false)
                 }
                 if (keyCode == Input.Keys.RIGHT && state == 1) {
                     actor1.go(true)
                 }*/
            }
        })
    }

    private fun handleInput() {
        //control our player using immediate impulses

        //control our player using immediate impulses
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || touchPad.knobPercentX > 0) actor1.go(true)
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || touchPad.knobPercentX < 0) actor1.go(false)
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || space.isPressed) actor1.jump()
        //if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) player.fire()
    }

    override fun hide() {}

    override fun show() {}

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0F, 0F, 0F, 1F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        handleInput()
        actor1.update(delta)
        stage.draw()
        stage.act(delta)
    }

    override fun pause() {}

    override fun resume() {}

    override fun resize(width: Int, height: Int) {}

    override fun dispose() {
        stage.dispose()
    }

}