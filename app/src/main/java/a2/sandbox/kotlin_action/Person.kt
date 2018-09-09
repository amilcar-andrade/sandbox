package a2.sandbox.kotlin_action

/**
 * Classes containing only data but no code are ofter called value objects, and many languages offer a concise syntax
 * for declaring them. In Kotlin, public is the default visbility, so you can omit it.
 */
data class Person internal constructor(val name : String, val age : Int? = null) { // Default value set to null


    // Regular function for just the Person object
    fun findOldestPerson(persons: List<Person>): Person {
        val maxBy = persons.maxBy { it.age ?: 0 }
        // Because maxBy returns a null if we do not find a person, I am deciding to return a dummy one
        return maxBy ?: Person("Ghost")
    }
}

/**
 * JAVA
 * public class Person {
 *   private final String name;
 *
 *   public Person(String name) {
 *      this.name = name;
 *   }
 *
 *   public String getName() {
 *      return this.name;
 *   }
 * }
 */
