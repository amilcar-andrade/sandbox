package a2.sandbox.kotlin_action

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Needed to be able to run simple snippets due to https://issuetracker.google.com/issues/68021152#comment12
 */
class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Chapter 1
        val matis = Person("Matis", 5)
        val persons = listOf<Person>(
                matis,
                Person("Amilcar", 29),
                Person("Unknown"))

        val findOldestPerson = matis.findOldestPerson(persons)
        println(findOldestPerson);

        val personsNoClazz = listOf<PersonNoClazz>(
                PersonNoClazz("Matis", 5),
                PersonNoClazz("Amilcar", 29),
                PersonNoClazz("Unknown"))

        val findOldestPersonNoClazz: PersonNoClazz = findOldestPersonNoClazz(personsNoClazz)
        println(findOldestPersonNoClazz);

        // Chapter 2
        val value : Int = max_blockBody(1, 4)
        println("--------------------")
        System.out.println(value)

        // Enum
        val color: Color = Color.RED
        println("--------------------")
        println(color.getMnemonic(Color.BLUE))

        // Branches
        println("--------------------")
        println(evalWithLogging(Sum(Sum(Num(1), Num(2)), Num(4))))

        // Iterating
        println("--------------------")
        for (i in 1..100) {
            println(fizzBuzz(i))
        }

        println("--------------------")
        // Iterating over a ranger with a step
        for (i in 100 downTo 1 step 2) {
            println(fizzBuzz(i))
        }

        println("--------------------")
        iterateSomeMap()

        println("--------------------")
        val list = listOf<String>("A", "B", "C")
        for ((index, element) in list.withIndex()) {
            println("$index: $element")
        }

        println("--------------------")
        println("Kotlin" in "Java".."Scala")
        println("Kotlin" in setOf("Java", "Scala"))

        // Exceptions
        // Exception as a part of an expression
        val number = 99
        val message: String = if (number in 0..100) number.toString() else throw IllegalArgumentException("Not valid percentage")
    }
}