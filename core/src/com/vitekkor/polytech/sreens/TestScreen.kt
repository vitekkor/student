package com.vitekkor.polytech.sreens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.widget.VisTextButton
import com.vitekkor.polytech.Core
import com.vitekkor.polytech.objects.PlayStage
import com.vitekkor.polytech.objects.Student

class TestScreen(core: Core) : Screen {
    private val game = core

    private var stage: PlayStage = PlayStage(ScreenViewport())
    private var table: Table = Table()
    private var studentStyle = TextureAtlas(Gdx.files.internal("images/student.atlas"))
    private var space: VisTextButton
    private var actor1: Student

    init {
        VisUI.load()
        actor1 = Student(20f, 280f, 70, 120)
        table.center()
        table.setFillParent(true)
        stage.addActor(actor1)
        space = VisTextButton("")
        space.setPosition(Gdx.graphics.width - 120F, 240f)
        stage.addActor(space)
        space.addListener(object : ClickListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                //actor1.jump()
            }
        })
        Gdx.input.inputProcessor = stage
        Gdx.input.setCatchKey(Input.Keys.BACK, true)
        stage.setHardKeyListener(object : PlayStage.OnHardKeyListener {
            override fun onHardKey(keyCode: Int, state: Int) {
                if (keyCode == Input.Keys.BACK && state == 1) {
                    core.screen = MainMenuScreen(core)
                    dispose()
                }
                if (keyCode == Input.Keys.SPACE && state == 1) {
                    //actor1.jump()
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

     private fun handleInput(dt: Float) {
         //control our player using immediate impulses

         //control our player using immediate impulses
         if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) actor1.go(true)
         if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) actor1.go(false)
         if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
             actor1.jump()
         //if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) player.fire()
     }

    override fun hide() {}

    override fun show() {}

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0F, 0F, 0F, 1F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        handleInput(delta)
        stage.draw()
        stage.act(delta)
    }

    override fun pause() {}

    override fun resume() {}

    override fun resize(width: Int, height: Int) {}

    override fun dispose() {
        stage.dispose()
        game.dispose()
    }

}