package a2.sandbox.kotlin_action.geometry

import java.util.Random

class Rectangulo(val height: Int, val width: Int) {

    val isSquare : Boolean
        get() = height == width
}

fun createRectangulo() : Rectangulo {
    val random = Random();
    return Rectangulo(random.nextInt(), random.nextInt())
}
