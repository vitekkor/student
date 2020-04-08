package com.vitekkor.polytech.supportFiles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.graphics.g2d.TextureRegion


class AssetsLoader {
    private val assetsManager = AssetManager()

    companion object {
        var texture: Texture? = null
        var bg: TextureRegion? = null
        var grass: TextureRegion? = null

        private lateinit var regions:
                com.badlogic.gdx.utils.Array<AtlasRegion>
        var studentAnimation: Animation<Any>? = null
        var student: TextureRegion? = null
        var birdDown: TextureRegion? = null
        var birdUp: TextureRegion? = null

        var skullUp: TextureRegion? = null
        var skullDown: TextureRegion? = null
        var bar: TextureRegion? = null

        fun load() {
            val textureAtlas = TextureAtlas(Gdx.files.internal("images/student.atlas"))
            regions = textureAtlas.regions
            studentAnimation = Animation(1/ 10F, regions)
            studentAnimation!!.playMode = Animation.PlayMode.LOOP_PINGPONG
        }

        fun dispose() {}
    }
}