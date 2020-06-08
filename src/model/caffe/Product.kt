package model.caffe

import java.util.*

data class Product(
        val id: String = UUID.randomUUID().toString(),
        var price: Double,
        val name: String
)