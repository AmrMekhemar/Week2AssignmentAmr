package model.shelter

import model.animals.Cat
import java.util.*

data class Shelter(
    val name: String,
    val ID : String = UUID.randomUUID().toString(),
    val Address: String,
    val phone: String)
