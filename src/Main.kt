import helper.*

fun main() {

    val cafeController = CafeController() // print out the data here using CafeController functions
    val topSellingItems = cafeController.cafe.getTopSellingItems()
    // printing out the topSelling items
    topSellingItems.forEach {
        println(it.name)
    }
    // cat's owned buy every shelter
    println(cafeController.getShelterCats(CafeController.shelters.first().ID))
    println(cafeController.getShelterCats(CafeController.shelters.first().ID))

    // let's try to adopt a cat
    val katie = CafeController.shelterCats.filter {
        it.name == "katie"
    }[0]
    cafeController.adoptCat(katie.id, ahmed)




    // Selling Items and printing out a receipt
    cafeController.sellItems(products1, ahmed.id, null)


    // getting number of adoption per shelter
    val numberOfAdoptionPerShelter = cafeController.getNumberOfAdoptionsPerShelter(CafeController.shelters.first().ID)
    println("Number of adoptions in ${CafeController.shelters.first().name} : $numberOfAdoptionPerShelter  ")

    // printing out the anAdopted cats
    val unAdoptedCats = cafeController.getUnadoptedCats()
    print("unAdopted Cats:\n")
    unAdoptedCats.forEach {
        println(it.name)
    }


    //add a sponsorship
    cafeController.cafe.addSponsorship(sarah.id, "DoaDoa")
    cafeController.cafe.addSponsorship(ahmed.id, "DoaDoa")
    cafeController.cafe.addSponsorship(amr.id, "DoaDoa")

    //get the most popular cats
    println("the most popular cats: ${cafeController.cafe.getMostPopularCats()} ")

    //remove a sponsorship
    cafeController.cafe.removeSponsorship("DoaDoa")





}