package a2.sandbox.kotlin_action

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Needed to be able to run simple snippets due to https://issuetracker.google.com/issues/68021152#comment12
 */
class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
    }
}