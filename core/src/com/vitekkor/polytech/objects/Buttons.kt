package com.vitekkor.polytech.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.vitekkor.polytech.Core

class Buttons(core: Core) {
    val skin = Skin()
    val textButtonStyle = TextButton.TextButtonStyle()
    val levelStyle = TextButton.TextButtonStyle()
    val labelStyle = Label.LabelStyle()
    private val buttonAtlas = TextureAtlas(Gdx.files.internal("images/buttons.pack"))

    init {
        skin.addRegions(buttonAtlas)
        textButtonStyle.font = core.font
        textButtonStyle.up = skin.getDrawable("button-up")
        textButtonStyle.down = skin.getDrawable("button-down")
        textButtonStyle.checked = skin.getDrawable("button-up")
        levelStyle.font = core.levels
        levelStyle.up = skin.getDrawable("level-up")
        levelStyle.down = skin.getDrawable("level-down")
        levelStyle.checked = skin.getDrawable("level-up")
        labelStyle.font = core.font
    }

}