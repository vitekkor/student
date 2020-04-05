package com.vitekkor.polytech

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.vitekkor.polytech.objects.Buttons
import com.vitekkor.polytech.sreens.MainMenuScreen


class Core : Game() {
    var buttons: Buttons? = null
    var font: BitmapFont? = null
    var levels: BitmapFont? = null
    private val characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>"

    override fun create() {
        buttons = Buttons(this)
        val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"))
        val param = FreeTypeFontGenerator.FreeTypeFontParameter()
        param.characters = characters
        param.size = Gdx.graphics.width / 72
        font = generator.generateFont(param)
        param.size = Gdx.graphics.width / 24
        levels = generator.generateFont(param)
        font!!.color = Color.WHITE
        levels!!.color = Color.WHITE
        generator.dispose()
        this.setScreen(MainMenuScreen(this))
    }
}