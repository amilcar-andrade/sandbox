package a2.sandbox.kotlin_action

// Defines a Person data class in just a file, no need to be inside of a class file
data class PersonNoClazz(val name : String, val age : Int? = null ) // Default value set to null

/**
 * Extension function of person no class object
 */
fun findOldestPersonNoClazz(persons : List<PersonNoClazz>) : PersonNoClazz {
    val maxBy = persons.maxBy { it.age ?: 0 }
    // Because maxBy returns a null if we do not find a person, I am deciding to return a dummy one
    return maxBy ?: PersonNoClazz("Ghost")
}

/**
 * Summary
 *
 * Provide more concise, productive, safer alternative to Java.
 * Kotlin is statically typed, the type of every expression in a program is know at compile time, so the compiler
 * can validate that methods and fields. Kotlin, does not require you to specify the type of every variable explicitly in a lot of cases
 * it can be automatically determine from the context.
 *
 * e.g val x = 1
 *
 * This ability of the compiler to determine the type from context is called type inference
 *
 * * Benefits of static typing languages are: *
 *  - Performance (calling methods is faster, no need to figure it out what to call at runtime)
 *  - Reliability (compiler verifies correctness of the program, less crashes at runtime)
 *  - Maintainability (know types of objects when debugging or learning a new code base)
 *  - Tool support (static typing enables reliable refactoring)
 *
 *  Functional advantages
 *  - First class functions - work with functions as values, you can store them in variables, pass them as parameters or return them from other functions
 *  - Immutability - work with immutable objects, which guarantees that their state cant change after their creation
 *  - No side effects (pure functions) - Use pure functions that return the same result given the same inputs and do not modify the state of other objects
 *  - Safe multithreading - because you are using immutable data structures and pure functions you can be sure that unsafe modifications wont happen
 *  - Easier testing - functions without side effects can be tested in isolation
 *
 *  * Kotlin features for functional programming are: *
 *  - Function types, allowing functions to receive other functions as parameters or return other functions
 *  - Lambda expressions
 *  - Data classes, providing concise syntax for creating immutable value objects
 *  - Standard library for objects and collections in a functional style
 *
 *
 *
 *
 * **/