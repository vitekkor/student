package com.vitekkor.polytech.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.vitekkor.polytech.Core

class Buttons(core: Core) {
    val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    var textButtonStyle = TextButton.TextButtonStyle()
    var levelStyle = TextButton.TextButtonStyle()
    val labelStyle = Label.LabelStyle()
    //private val buttonAtlas = TextureAtlas(Gdx.files.internal("images/buttons.pack"))

    init {
        //skin.addRegions(buttonAtlas)
        textButtonStyle = skin.get(TextButton.TextButtonStyle::class.java)
        textButtonStyle.font = core.font
        levelStyle = skin.get(TextButton.TextButtonStyle::class.java)
        levelStyle.font = core.levels
        labelStyle.font = core.font
    }

}