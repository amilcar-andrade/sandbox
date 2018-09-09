package a2.sandbox.kotlin_action

import java.io.BufferedReader
import java.util.*

/**
 * Summary
 * - The fun keyword is used to declare a function
 * - Parameter type is written after its name
 * - Functions can be declared at the top level of a file, no need to create a class
 * - Arrays are classes, no special syntax for arrays
 *
 * In Kotlin, if is an expression with a result value, similar to the ternary operator in JAVA
 * If a function is written with its body in curly braces, we say that this function has a block body
 * If a function returns an expression directly, it has an expression body
 *
 * FUNCTIONS
 * -------------
 *
 * Kotlin supports type inference on functions, the compiler can infer the type even when it is not spelled out explicitly
 * NOTE: Omitting the type is only allow for functions with an expression body syntax. Functions with a block body that return a value
 * you have to add the return type explicitly
 *
 *
 * VARIABLES
 * -------------
 *
 * You can declare or not the type of a variable
 * If a variable does not have an initializer, you need to specify its type explicitly
 * A best practice is to try to always use val instead of var
 *
 * val (from value) - Immutable reference, similar to final in Java
 * var (from variable) - Mutable reference, regular non final variable in Java
 *
 * A val variable must be initialized exactly once during the execution of the block where it is defined
 *
 * NOTE: Even though a val reference is it self immutable and cant be changed, the object that it points to may be mutable.
 *
 * Even though the var keyword allows a variable to change its value, its type is fixed.
 *
 *
 *
 * PROPERTIES
 * ------------
 *
 * In Java, the combination of the field and its accessors is often referred to as a property, and many frameworks make heave use of the concept.
 * In Kotlin, properties are a first-class language feature, which entirely replaces fields and accessors method. You declare a property in a class
 * the same way you declare a variable with val and var keywords
 *
 * When you declare a val property you get a getter
 * When you declare a var property you get both a setter and getter
 *
 * The getter and setter rule has an exception: if the property name starts with is, no additional prefix for the getter is added and in the setter name, is is replaced with. Thus,
 * from Java, you call isMarried() for our Persona example.
 *
 * You can create a custom accessors to compute a value/property on the fly from other properties
 *
 *
 * PACKAGES
 * ------------
 *
 * Declarations defined in other files can be used directly if they are in the same package, they need to be imported if they are in a different package.
 * Kotlin does not make a distinction between importing classes and functions
 *
 * For Kotlin you should not hesitate to pull multiple classes into the same file, specially if the classes are small
 *
 *
 * ENUMS and WHEN
 * ---------------
 *
 * when (statement) it is similar to a switch statement in Java
 * enum is so-called a soft keyword, it has a special meaning when it comes before a class, but you can use it on other places
 * class is still a keyword and you need to still use clazz or aClass for variables
 *
 * When you declare each enum constant, you need to provide the property values for that constant.
 * The semicolon separated the enum constant list from the method definitions
 *
 * In Java, you can use a switch statement for dealing with enums but in Kotlin you use the enum keyword
 * Like if, when is an expression that returns a value, so you can write a function with an expression body, returning the when expression directly
 *
 * Unlike in Java, you do not need to write a break statement in each branch
 * You can also combine multiple values in the same branch if you separate with them commas
 *
 * The when construct allows you to use any object not just constants, strings or number literals like switch
 *
 *
 * Smart casts
 * ---------------
 *
 * For doing smart casts in Java you need to use the instanceof and then do an explicit cast
 * The is check is similar to instaceof in Java, but in Kotlin the compiler does the casting for you,
 * if you check a variable for a certain type you do not need to do the cast afterwards. The compiler performs the
 * cast for you, and it is call a smart cast
 * An explicit cast to the specific type is expressed via the 'as' keyword
 * e.g. val n = e as Num
 *
 * In Kotlin, there is no ternary operator, because unlike Java, the if expression returns a value.
 *
 *
 * Branches
 * --------------
 *
 * Both if and when can have blocks as branches, the last expression in the block is the result of it.
 * The rule "the last expression in a block is the result" holds in all cases where a block can be used and a result is expected
 *
 *
 * Iterating
 * ---------------
 *
 * The while loops is identical to the one in Java
 * The for loops exists in only one form, which is equivalent to Java's for-each loop. Its written for <item> in <elements>, as in C#
 * Kotlin uses the concept of ranges. A range is essentially just an interval between two values, usually numbers: a start and end. You write it
 * using the '..' operator
 * e.g. val oneToToen = 1..10
 * NOTE: Rages in Kotlin are closed or inclusive, meaning the second value is always part of the range
 *
 * You can use the step keyword to skip some numbers (iterations)
 * In many cases is more convenient to iterate over half-closed ranges, which do not include the specific end point. To create such a range, use the
 * 'until' functions. For example, the loop `for (x in 0 until size)` is the same as `for (x in 0..size-1)`
 *
 * Using 'in' to check collection and range membership
 *
 * The 'in' and '!in' operators also work in when expressions
 *
 * Ranges are not restricted to characters, either. If you have an class that supports comparing instances (by implementing the java.lang.Comparable interface)
 *
 * Exceptions
 * -------------
 *
 * The basic form for exception handling statements in Kotlin is similar to Java.
 * In Kotlin the throw construct is an expression and can be used as a part of other expressions
 * In Kotlin does not differentiate between checked and unchecked exceptions. You dont need to specify
 * the exceptions thrown by a function and you may or may not handle any exceptions.
 *
 * The try keyword in Kotlin, just like if and when is also an expression
 *
 **/

// Functions
fun max_blockBody(a: Int, b: Int) : Int {
    return if (a > b) a else b
}

fun max_expressionBody(a: Int, b : Int) : Int = if (a > b) a else b

fun max_NoReturnType(a: Int, b: Int) = if (a > b) a else b

// Variables

fun var_NoTypeChange() {
    var answer = 42
    // Compile error!
    //answer = "papi"
}

fun val_mutablePoint() {
    val myList: ArrayList<String> = arrayListOf<String>("Java")
    myList.add("Kotlin")
}

fun variable_no_initializer() {
    val aNumber: Int
    aNumber = 40;
}

fun val_with_condition() : String {
    val message: String
    if (alwaysTrue()) {
        message = "Do this"
    } else {
        message = "Or do that"
    }
    return message
}


fun alwaysTrue() = true

val anInt = 4;

val stringii = "Is this val a string?"

val anIntWithType: Int = 5;

val doble: Double = 7.6e6


// String templates

fun string_templates(args: Array<String>) {
    val nameToPrint = if (args.isEmpty()) "Kotlin" else args[0]
    println("Hello, $nameToPrint!")
}

fun string_templates_complex_expression(args: Array<String>) {
    if (!(args.isEmpty())) {
        println("Hello, ${args[0]}!") // Use the ${} for a complex expression
    }
}

fun string_templates_nested_quotes(args: Array<String>) {
    println("Hello, ${if (args.isEmpty()) "Empty argument" else args[0]}!")
}

// Properties

class Persona(val name: String, var isMarried: Boolean)

fun divorce() {
    val persona = Persona("amilcar", true)
    // Not anymore :P
    persona.isMarried = false
}

class Rectangle(val height: Int, val width: Int) {
    // Custom getter (Property getter declaration)
    // The property isSquare does not need a field to store its value, it only has a custom getter with the implementation provided
    //The value is computed every time the property is accessed
    val isSquare: Boolean
        get() {
            return height == width
        }

    // No need to have curly braces if you do not want to add them
    val isSquareInline: Boolean
        get() = height == width
}

// ENUM

enum class SimpleEumColor {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

enum class Color(val r: Int, val g: Int, val b: Int) { // Properties of the color

    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238); // Semicolon is required (to separate from functions)

    // Method of the enum
    fun rgb(): Int = (r * 256 + g) * 256 + b

    // Expression body
    fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Richard";
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }

    fun getWarmth(color: Color) = when (color) {
            Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
            Color.GREEN -> "neutral"
            Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
    }

    fun getWarmth_blockBody(color: Color) : String {
        val warmth = when (color) {
            RED, ORANGE, YELLOW -> "warm"
            GREEN -> "neutral"
            BLUE, INDIGO, VIOLET -> "cold"
        }
        return warmth
    }

    // Creates a set and compare if both objects are in the Set
    // This is inefficient because it creates a couple of sets just to check if the items are in the set
    fun mix(c1: Color ,c2: Color) =
        when (setOf(c1, c2)) {
            setOf(RED, YELLOW) -> ORANGE
            setOf(YELLOW, BLUE) -> GREEN
            setOf(BLUE, VIOLET) -> INDIGO
            else -> {
                throw Exception("Dirty color")
            }
        }

    // This is an example of the when expression without an argument but its better for performance
    // mixOptimized does the same thing as mix, the advantage is that it does not create object.
    fun mixOptimized(c1: Color ,c2: Color) =
            when {
                (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
                (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
                (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO
                else -> throw Exception("Dirty color")
            }
}


// Smart casts
interface Expr
// Both Num and Sum are implementing the marker interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

// implementing eval in Java style
fun evalJavaStyle(e: Expr) : Int {
    if (e is Num) {
        // do a cost
        val n = e as Num
        return n.value
    }

    if (e is Sum) {
        // recursion
        val sum = e as Sum
        return evalJavaStyle(sum.right) + evalJavaStyle(sum.left)
    }

    throw IllegalArgumentException("Unknown expression")
}

// I prefer curly braces!
fun eval(e: Expr) : Int =
        if (e is Num) {
            e.value
        } else if (e is Sum) {
            eval(e.right) + eval(e.left)
        } else {
            throw IllegalArgumentException("Unkwnown expression")
        }

// The curly braces are optional if there is only one expression in an if branch!
fun evalNoBraces(e: Expr) : Int =
        if (e is Num) e.value
        else if (e is Sum) eval(e.right) + eval(e.left)
        else throw IllegalArgumentException("Unkwnown expression")

fun evalWithWhen(e: Expr) : Int =
    when (e) {
        is Num -> e.value
        is Sum -> evalWithWhen(e.right) + evalWithWhen(e.left)
        else -> throw IllegalArgumentException("Unkwnown expression")
    }

fun evalWithLogging(e: Expr) : Int =
        when (e) {
            is Num -> {
                val value = e.value
                println("num: $value")
                value
            }
            is Sum -> {
                val left = evalWithLogging(e.left)
                val right = evalWithLogging(e.right)
                println("sum: $left + $right")
                left + right
            }
            else -> {
                throw IllegalArgumentException("Unkwnown expression")
            }
        }

// Iterating

/**
 * The body is executed while the condition is true
 */
fun whilee() {
    while (alwaysTrue()) {
        println("infinite loop")
    }
}

/**
 * The body is executed for the first time unconditionally. After that, its executed while the conditions is true
 */
fun doWhilee() {
    do {
        println("infinite loop")
    } while (alwaysTrue())
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i"
}

fun iterateSomeMap() {
    val treeMap = TreeMap<Char, String>() // Keys are sorted
    for (c in 'A'..'F') { // Iterate over the characters from A to F using a range of characters
        val binary = Integer.toBinaryString(c.toInt())
        treeMap[c] = binary // Store the value in a map by the c key
    }

    for ((key, value) in treeMap) {
        println("$key = $value")
    }
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNoDigit(c: Char) = c !in '0'..'9'

fun recognize(c: Char) = when(c) {
    in '0'..'9' -> "Its a digit"
    in 'a'..'z', in 'A'..'Z' -> "Its a letter"
    else -> "Unknown"
}

fun checkPercentage(percentage: Int): Int =
    if (percentage !in 0..100) {
        throw IllegalArgumentException("Percetange is not between 0 and 100")
    } else {
        percentage
    }

fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine();
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        return null
    } finally {
        reader.close()
    }
}

fun readNumberExpression(read: BufferedReader) {
    val number = try {
        Integer.parseInt(read.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}















