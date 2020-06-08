package helper

import model.animals.Cat
import model.caffe.Cafe
import model.caffe.Product
import model.caffe.Sponsorship
import model.people.Person
import model.shelter.Shelter

class CafeController {
    // instantiating a Cafe object
    val cafe = Cafe()

    companion object {
        val shelters = mutableSetOf<Shelter>(Shelter(name = "harry Shelter", Address = "cairo egypt", phone = "+201145814")
                , Shelter(name = "Amr Shelter", Address = "Giza egypt", phone = "+2010145814"))
        var shelterCats = mutableSetOf<Cat>(Cat(name = "katie", gender = "female", shelterId = shelters.first().ID)
                , Cat(name = "Susie", gender = "female", shelterId = shelters.first().ID)
                , Cat(name = "Mero", gender = "male", shelterId = shelters.first().ID)
                , Cat(name = "Lafa", gender = "female", shelterId = shelters.last().ID)
                , Cat(name = "DoaDoa", gender = "female", shelterId = shelters.last().ID)
                , Cat(name = "So", gender = "male", shelterId = shelters.last().ID))

        val sponsorships = mutableListOf<Sponsorship>()

    }

    private val shelterToCat = mutableMapOf(
            shelters.first() to getShelterCats(shelters.first().ID),
            shelters.last() to getShelterCats(shelters.last().ID)
    )


    /**
     * a function to get a specific shelter's cats
     * @param shelterId : String
     * @return shelter's cats
     */
    fun getShelterCats(shelterId: String): MutableSet<Cat> {
        val catSet = mutableSetOf<Cat>()
        shelterCats.filter { it.shelterId == shelterId }.forEach {
            catSet.add(it)
        }
        return catSet
    }

    /**
     * a function to adopt a cat to a specific person
     * @param catId : String
     * @param person : Person
     */
    fun adoptCat(catId: String, person: Person) {
        // check if cats exist, and retrieve its entry!

        val catInShelter = shelterToCat.entries.firstOrNull { (_, catsInShelter) ->
            catsInShelter.any { it.id == catId }
        }

        // you can adopt that cat!
        if (catInShelter != null) {
            val cat = catInShelter.value.first { cat -> cat.id == catId } // find the cat for that ID again

            /*
            remove sponsorships from the cat
             */
            sponsorships.forEach {
                if (it.catId == catId)
                    sponsorships.remove(it)
            }
            // remove the cat from the shelter
            catInShelter.value.remove(cat)

            // add the cat to the person
            person.cats.add(cat)
            shelterCats.remove(cat)
            println("${cat.name} has been removed from its shelter and adopted by ${person.firstName}")
        }
    }


    /**
     * a function to sell Menu Items and prints out the receipts details and fees
     * @param items : List<Product>
     * @param customerId : String
     * @param cat : String?
     */
    fun sellItems(items: List<Product>, customerId: String, cat: Cat?) {
        val receipt = cafe.getReceipt(items, customerId, cat)
    }

    /**
     * a function to get the number of all adoptions per shelter
     * @param shelterId : String
     * @return number of all adoptions per shelter
     */
    fun getNumberOfAdoptionsPerShelter(shelterId: String): Int {
        val allAdopters = cafe.getAdopters()
        var shelterFirstAdoptions = 0
        var shelterLastAdoptions = 0
        allAdopters.forEach {
            it.cats.forEach { cat ->
                when (cat.shelterId) {
                    shelters.first().ID -> shelterFirstAdoptions += 1
                    shelters.last().ID -> shelterLastAdoptions += 1
                }
            }
        }
        return when (shelterId) {
            shelters.first().ID -> shelterFirstAdoptions
            shelters.last().ID -> shelterLastAdoptions
            else -> 0
        }

    }


    /**
     * a function to get a set of Unadopted Cats
     * @return a set of unAdoptedCats
     */
    fun getUnadoptedCats(): Set<Cat> {
        val allAdopters = cafe.getAdopters()
        val unAdoptedCats: MutableSet<Cat> = mutableSetOf()
        var isAdoptedCat = false
        shelterCats.forEach { shelterCat ->
            allAdopters.forEach { person ->
                person.cats.forEach { cat ->
                    if (shelterCat == cat)
                        isAdoptedCat = true
                }
            }
            if (!isAdoptedCat)
                unAdoptedCats.add(shelterCat)
        }
        return unAdoptedCats
    }


}
