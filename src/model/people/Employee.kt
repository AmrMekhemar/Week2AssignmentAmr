package model.people

import java.text.SimpleDateFormat

class Employee(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        val salary: Double,
        val socialSecurityNumber: String,
        val hireDate: String
) : Person(firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {

    override fun toString(): String {
        return "name: $firstName $lastName \n " +
                "email: $email \n " +
                "name: $phoneNumber \n "
    }

    /**
     * Prints a time of clocking in, in a nice format.
     *
     * Hint: to get time, you can create a `Date` object. Use SimpleDateFormatter to format the time!
     * */
    fun clockIn() {
        val currentTime = SimpleDateFormat.getTimeInstance()
        println("time for clocking im $currentTime")
    }

    // TODO same as above, but times for clocking out!
    fun clockOut() {
        val currentTime = SimpleDateFormat.getTimeInstance()
        println("time for clocking out $currentTime")
    }
}