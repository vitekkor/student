package test

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.JsonReader
import com.vitekkor.polytech.objects.Level
import org.junit.jupiter.api.Test

internal class LevelTest {

    @Test
    fun isCollision() {
        val json = Gdx.files.internal("levels/levels.json").file().readText()
        val jsonReader = JsonReader()
        val levels = jsonReader.parse(json)
        val lvl = Level(levels)
    }
}