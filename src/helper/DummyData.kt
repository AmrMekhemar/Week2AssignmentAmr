package helper

import model.caffe.Product
import model.caffe.Receipt
import model.people.Person

val vanilla = Product(price = 5.5, name = "Vanilla")
val hotChocolate = Product(price = 5.5, name = "HotChocolate")
val Tee = Product(price = 3.0, name = "Tee")
val IceCream = Product(price = 6.5, name = "Ice Cream")
val isbresso = Product(price = 10.5, name = "Isbresso")
val cappuccino = Product(price = 11.5, name = "Cappuccino")

val products1 = arrayListOf(vanilla, cappuccino, Tee)
val products2 = arrayListOf(vanilla, hotChocolate, Tee)
val products3 = arrayListOf(vanilla, IceCream, Tee, isbresso, isbresso)
val products4 = arrayListOf(vanilla, hotChocolate, Tee, Tee, isbresso)
val products5 = arrayListOf(IceCream, hotChocolate, cappuccino, cappuccino)

val receipt1 = Receipt(listOfProducts = products1, adoptedOrSponsoredCat = null)
val receipt2 = Receipt(listOfProducts = products2, adoptedOrSponsoredCat = null)
val receipt3 = Receipt(listOfProducts = products3, adoptedOrSponsoredCat = null)
val receipt4 = Receipt(listOfProducts = products4, adoptedOrSponsoredCat = null)
val receipt5 = Receipt(listOfProducts = products5, adoptedOrSponsoredCat = null)
val receipt6 = Receipt(listOfProducts = products4, adoptedOrSponsoredCat = null)
val receipt7 = Receipt(listOfProducts = products5, adoptedOrSponsoredCat = null)

val ahmed = Person(firstName = "Ahmed", lastName = "Mahmoud", email = "ahmad@someDomain.com", cats = mutableSetOf(), phoneNumber = "+2554504")
val amr = Person(firstName = "Ahmed", lastName = "Mahmoud", email = "amr@someDomain.com", cats = mutableSetOf(), phoneNumber = "+5481")
val sarah = Person(firstName = "Ahmed", lastName = "Mahmoud", email = "sarah@someDomain.com", cats = mutableSetOf(), phoneNumber = "+4456")
val john = Person(firstName = "Ahmed", lastName = "Mahmoud", email = "john@someDomain.com", cats = mutableSetOf(), phoneNumber = "+1254504")