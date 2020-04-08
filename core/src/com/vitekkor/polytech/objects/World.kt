package com.vitekkor.polytech.objects

import com.badlogic.gdx.Gdx

class World(middle: Int) {
    val middle = middle
    private val student = Student(33F, Gdx.graphics.height - 182f, 70, 120)
    fun update(delta: Float) {
        student.update(delta)
    }

    fun getStudent(): Student = student
}