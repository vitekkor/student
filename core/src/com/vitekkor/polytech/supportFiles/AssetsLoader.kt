package com.vitekkor.polytech.supportFiles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.utils.Json
import com.badlogic.gdx.utils.JsonReader
import com.badlogic.gdx.utils.JsonValue
import java.io.File


class AssetsLoader {
    companion object {
        var bg: TextureRegion? = null
        private lateinit var regions:
                com.badlogic.gdx.utils.Array<AtlasRegion>
        var studentAnimation: Animation<Any>? = null
        private val resolver = InternalFileHandleResolver()
        val manager = AssetManager(resolver, true)
        lateinit var touchPadStyle: Touchpad.TouchpadStyle
        lateinit var textButtonStyle: TextButton.TextButtonStyle
        lateinit var levelStyle: TextButton.TextButtonStyle
        lateinit var labelStyle: Label.LabelStyle
        lateinit var skin: Skin
        var levels: JsonValue? = null
        lateinit var progressBar: ProgressBar.ProgressBarStyle
        private const val characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>"

        fun load() {
            manager.load("skin/uiskin.json", Skin::class.java)
            manager.finishLoading()
            skin = manager.get("skin/uiskin.json", Skin::class.java)
            progressBar = skin.get("default-horizontal", ProgressBar.ProgressBarStyle::class.java)
            manager.setLoader(BitmapFont::class.java, ".ttf", FreetypeFontLoader(resolver))
            manager.setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(resolver))
            manager.load("images/student.atlas", TextureAtlas::class.java)
            val smallFont = FreetypeFontLoader.FreeTypeFontLoaderParameter()
            smallFont.fontFileName = "fonts/font.ttf"
            smallFont.fontParameters.size = Gdx.graphics.width / 72
            smallFont.fontParameters.characters = characters
            manager.load("smallFont.ttf", BitmapFont::class.java, smallFont)
            val bigFont = FreetypeFontLoader.FreeTypeFontLoaderParameter()
            bigFont.fontFileName = "fonts/font.ttf"
            bigFont.fontParameters.size = Gdx.graphics.width / 24
            bigFont.fontParameters.characters = characters
            manager.load("bigFont.ttf", BitmapFont::class.java, bigFont)
        }

        fun setLoadedAssets() {
            regions = manager.get("images/student.atlas", TextureAtlas::class.java).regions
            studentAnimation = Animation(1 / 10F, regions)
            studentAnimation!!.playMode = Animation.PlayMode.LOOP_PINGPONG
            textButtonStyle = skin.get("round", TextButton.TextButtonStyle::class.java)
            textButtonStyle.font = manager.get("smallFont.ttf", BitmapFont::class.java)
            levelStyle = skin.get("round", TextButton.TextButtonStyle::class.java)
            levelStyle.font = manager.get("bigFont.ttf", BitmapFont::class.java)
            labelStyle = skin.get(Label.LabelStyle::class.java)
            labelStyle.font = manager.get("smallFont.ttf", BitmapFont::class.java)
            touchPadStyle = skin.get(Touchpad.TouchpadStyle::class.java)
            loadLevels()
        }

        private fun loadLevels() {
            val json = Gdx.files.internal("levels/levels.json").readString()
            val jsonReader = JsonReader()
            levels = jsonReader.parse(json)
        }

        fun dispose() {
            levels = null
            manager.dispose()
        }
    }
}