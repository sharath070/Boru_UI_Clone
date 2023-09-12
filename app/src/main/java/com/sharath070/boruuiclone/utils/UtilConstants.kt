package com.sharath070.boruuiclone.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class UtilConstants : ViewModel() {

    private val _itemCount = MutableLiveData(0)
    val itemCount: LiveData<Int> = _itemCount

    private val _totalPrice =
        MutableLiveData(BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN).toDouble())
    val totalPrice: LiveData<Double> = _totalPrice


    fun increaseItemCount() {
        _itemCount.value = itemCount.value?.plus(1)
    }

    fun decreaseItemCount() {
        _itemCount.value = itemCount.value?.minus(1)
    }

    fun increasePrice(price: Double) {
        _totalPrice.value =
            totalPrice.value?.plus(price)?.let {
                BigDecimal(it).setScale(2, RoundingMode.HALF_EVEN).toDouble()
            }
    }

    fun decreasePrice(price: Double) {
        _totalPrice.value =
            totalPrice.value?.minus(price)?.let {
                BigDecimal(it).setScale(2, RoundingMode.HALF_EVEN).toDouble()
            }
    }
}
