package com.vitekkor.polytech.objects

import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport


class PlayStage(screenViewport: ScreenViewport?) : Stage(screenViewport) {
    // Прослушивает события нажатия клавиш пользователем
    override fun keyDown(keyCode: Int): Boolean {
        if (keyCode == Input.Keys.BACK) {
            if (hardKeyListener != null) hardKeyListener!!.onHardKey(keyCode, 1)
        }
        return super.keyDown(keyCode)
    }

    override fun keyUp(keyCode: Int): Boolean {
        if (keyCode == Input.Keys.BACK) {
            if (hardKeyListener != null) hardKeyListener!!.onHardKey(keyCode, 0)
        }
        return super.keyUp(keyCode)
    }

    interface OnHardKeyListener {
        // Теперь, при нажатии пользователем кнопки Назад, будет выполняться код из этого метода (который мы пишем в теле экрана)
        fun onHardKey(keyCode: Int, state: Int)
    }

    private var hardKeyListener: OnHardKeyListener? = null

    fun setHardKeyListener(newHardKeyListener: OnHardKeyListener?) {
        hardKeyListener = newHardKeyListener
    }

    fun getHardKeyListener(): OnHardKeyListener? {
        return hardKeyListener
    }

}