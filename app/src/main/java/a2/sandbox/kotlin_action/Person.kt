package a2.sandbox.kotlin_action

// Default value set to null
data class Person internal constructor(val name : String, val age : Int? = null) {

    // Regular function for just the Person object
    fun findOldestPerson(persons: List<Person>): Person {
        val maxBy = persons.maxBy { it.age ?: 0 }
        // Because maxBy returns a null if we do not find a person, I am deciding to return a dummy one
        return maxBy ?: Person("Ghost")
    }
}
