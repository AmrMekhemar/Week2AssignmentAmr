package model.caffe

import helper.*
import model.animals.Cat
import model.people.Employee
import model.people.Patron
import model.people.Person

class Cafe {
    // cafe related stuff
    /**
     * To simplify it, let's imitate a week-long cafe turnaround.
     *
     * Make sure to add your receipts to each set, with your data.
     * */
    private val receiptsByDay = mutableMapOf(
            "Monday" to mutableSetOf(receipt1),
            "Tuesday" to mutableSetOf(receipt2),
            "Wednesday" to mutableSetOf(receipt3),
            "Thursday" to mutableSetOf(receipt4),
            "Friday" to mutableSetOf(receipt5),
            "Saturday" to mutableSetOf(receipt6),
            "Sunday" to mutableSetOf(receipt7)
    )

    private val employees = mutableSetOf(Employee("Yaseen", "Ahmed", "yaseen@ahmed.com", "+53562145", 5000.0
            , "245455645546", "15-5-2011"),
            Employee("John", "Mike", "John@Mike.com", "+535656445", 4000.0
                    , "2454515645546", "15-5-2011"))
    private val customers = mutableSetOf<Person>(ahmed, sarah
    )

    // make sure to add sponsorships and tie them to people!
    private val sponsorships = mutableSetOf<Sponsorship>()

    /**
     * a function to sign a check in for  employees and adding them to the list of working employees
     * @param employee : Employee
     */
    fun checkInEmployee(employee: Employee) {
        employees.add(employee)
    }


    /**
     * a function to sign a check out for  employees and remove them from the list of working employees
     * @param employee : Employee
     */
    fun checkOutEmployee(employee: Employee) {
        employees.remove(employee)
    }

    /**
     * a function to print out the number of receipts for a specific day
     * @param employee : Employee
     */
    fun showNumberOfReceiptsForDay(day: String) {
        val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!
        println("On $day you made ${receiptsByDay.size} transactions!")
    }


    /**
     * a function to  prints out the receipts details and fees
     * @param items : List<Product>
     * @param customerId : String
     * @param cat : String?
     */
    fun getReceipt(items: List<Product>, customerId: String, cat: Cat?): Receipt {
        var customerFlag = false
        var employeeFlag = false
        customers.forEach {
            if (it.id == customerId)
                customerFlag = true
        }
        if (!customerFlag)
            employees.forEach {
                if (it.id == customerId)
                    employeeFlag = true
            }
        var whatCustomerShouldPay = 0.0
        if (employeeFlag) {
            if (cat == null)
                items.forEach {
                    it.price = it.price * 0.85
                    whatCustomerShouldPay += it.price
                }
            else items.forEach {
                whatCustomerShouldPay += it.price
            }
        }

        if (customerFlag)
            items.forEach {
                whatCustomerShouldPay += it.price
            }
        println("The Pawfee Cat Cafe Receipt:\n" +
                "The list of products: $items\n" +
                "Fees: $whatCustomerShouldPay$")
        return Receipt(listOfProducts = items, adoptedOrSponsoredCat = cat)
    }

    /**
     * a function to add a sponsorship
     * @param customerId : String
     * @param catName : String
     */
    fun addSponsorship(customerId: String, catName: String) {
        var flag = false
        CafeController.shelterCats.forEach {
            if (catName == it.name) {
                it.sponsorships.add(Sponsorship(customerId, it.id))
                CafeController.sponsorships.add(Sponsorship(customerId, it.id))
                flag = true
                println("sponsorship has been added")
            }
        }
        if (!flag)
            print("No cat with this name in the shelters")

    }

    /**
     * a function to remove a sponsorship
     * @param catName : String
     */

    fun removeSponsorship(catName: String) {
        CafeController.shelterCats.forEach { cat ->
            if (catName == cat.name) {
                CafeController.sponsorships.removeIf {
                    it.catId == cat.id
                }
            }
        }
        println("sponsorship has been removed")
    }

    fun getWorkingEmployees(): Set<Employee> = employees

    fun getAdoptedCats(): Set<Cat> {
        val adoptedCats = mutableSetOf<Cat>()
        getAdopters().forEach {
            it.cats.forEach { cat ->
                adoptedCats.add(cat)
            }
        }
        return adoptedCats
    }

    fun getSponsoredCats(): Set<Cat> {
        val sponsoredCats: MutableSet<Cat> = mutableSetOf()
        CafeController.shelterCats.forEach { cat ->
            sponsorships.forEach {
                if (it.catId == cat.id)
                    sponsoredCats.add(cat)
            }
        }
        return sponsoredCats
    }

    fun getMostPopularCats(): Set<Cat> {
        return CafeController.shelterCats.filter {
            it.sponsorships.size >= 2
        }.toMutableSet()
    }


    fun getTopSellingItems(): Set<Product> {
        val setOfProducts = mutableSetOf<Product>()
        receiptsByDay.forEach {
            it.value.flatMap { receipt ->
                receipt.listOfProducts
            }.asSequence()
                    .groupingBy { product ->
                        product
                    }
                    .eachCount()
                    .forEach {
                        if (it.value >= 2)
                            setOfProducts.add(it.key)
                    }

        }
        return setOfProducts
    }

    fun getAdopters(): List<Person> {
        return (employees + customers).filter { it.cats.isNotEmpty() }
    }
}