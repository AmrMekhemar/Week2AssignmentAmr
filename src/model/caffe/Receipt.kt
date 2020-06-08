package model.caffe

import model.animals.Cat
import java.util.*
import kotlin.collections.ArrayList

class Receipt(
        val ID:String = UUID.randomUUID().toString(),
        val listOfProducts: List<Product>,
        val adoptedOrSponsoredCat: Cat?) {

}