package com.example.chowmein_order.model


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*


const val FULL_PLATE = "Full Plate"
const val HALF_PlATE = "Half Plate"

//Pickup Constant
private const val PRICE_SAME_DAY_PICKUP = 20

/**
 * OrderViewModel holds data and modifies data in terms of Item, Plates, Plate's Quantity and
 * pickup date.
 */
class OrderViewModel : ViewModel() {

    //Choice of the topping
    private val _item = MutableLiveData<String>()
    val item: LiveData<String> = _item

    //Set topping price
    private val _itemPrice = MutableLiveData<Int>()
    private val itemPrice: LiveData<Int> = _itemPrice


    //Number of Plates
    private val _plates = MutableLiveData<Int>()
    val plates: LiveData<Int> = _plates

    //Plate's Quantity
    private val _platesQuantity = MutableLiveData<String>()
    val platesQuantity: LiveData<String> = _platesQuantity

    // Possible date options
    val dateOptions: List<String> = getPickupOptions()

    //Pickup Date
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    //Price of the order so far.
    private val _price = MutableLiveData<Int>()
    val price: LiveData<Int> = _price

    init {
        resetOrder()
    }

    fun setTopping(itemType: String) {
        _item.value = itemType
        updatePrice()
    }

    fun setToppingPrice(itemPrice: Int) {
        _itemPrice.value = itemPrice
        updatePrice()
    }

    fun setPlates(noOfPlates: Int) {
        _plates.value = noOfPlates
        updatePrice()
    }

    fun setPlatesQuantity(platesQuantity: String) {
        _platesQuantity.value = platesQuantity
    }

    fun setDate(pickUpDate: String) {
        _date.value = pickUpDate
        updatePrice()
    }


    /**
     * Update price based on order details.
     */
    private fun updatePrice() {
        var calculatedPrice = (itemPrice.value ?: 0) * (plates.value ?: 0)
        if ((platesQuantity.value ?: "") == HALF_PlATE) {
            calculatedPrice = ((itemPrice.value ?: 0) / 2) * (plates.value ?: 0)
        }
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }


    /**
     * Returns a list of date options starting with the current date and the following 3 dates.
     */
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    /**
     * Reset order of all the order details to initial default values for topping, plates,
     * plate's quantity, pickUpDate.
     */
    fun resetOrder() {
        _item.value = ""
        _itemPrice.value = 0
        _date.value = dateOptions[0]
        _plates.value = 0
        _platesQuantity.value = ""
        _price.value = 0
    }


}