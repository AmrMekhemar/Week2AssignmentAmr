package model.people

class Patron(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String
) : Person(firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {

    override fun toString(): String {
        return "name: $firstName $lastName \n " +
                "email: $email \n " +
                "name: $phoneNumber \n "
    }


}