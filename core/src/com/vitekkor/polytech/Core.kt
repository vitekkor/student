package com.vitekkor.polytech

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.vitekkor.polytech.sreens.LoadScreen
import com.vitekkor.polytech.supportFiles.AssetsLoader



class Core : Game() {

    //create default font
    var font: BitmapFont? = null

    //create level font
    var levels: BitmapFont? = null

    //settings file
    var settings = null
    private val characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>"

    override fun create() {
        //generate fonts
        val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"))
        //parameters for fonts
        val param = FreeTypeFontGenerator.FreeTypeFontParameter()
        param.characters = characters
        param.size = Gdx.graphics.width / 72
        //set for default
        font = generator.generateFont(param)
        param.size = Gdx.graphics.width / 24
        //set for level
        levels = generator.generateFont(param)
        font!!.color = Color.WHITE
        levels!!.color = Color.WHITE
        //destroying the generator as unnecessary
        generator.dispose()
        AssetsLoader.load()
        setScreen(LoadScreen(this))
    }

    override fun dispose() {
        super.dispose()
        AssetsLoader.dispose()
    }
}