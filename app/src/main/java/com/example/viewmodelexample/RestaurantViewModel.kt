package com.example.viewmodelexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData

class RestaurantViewModel : ViewModel(){

    // 수정은 외부에서 직접하지는 못하게, viewModel을 통해서 수정 가능
    private val restaurantList = MutableLiveData<MutableList<RestaurantInfoDTO>>()

    // 수정할 수 없는 값으로 변경해서
    fun getRestaurantList() : LiveData<MutableList<RestaurantInfoDTO>> {
        return restaurantList
    }

    // 초기화
    init{
        restaurantList.value = mutableListOf<RestaurantInfoDTO>()
        getRestaurantListFromServer()
    }

    fun getRestaurantListFromServer(){
        // observer가 호출되려면 value라는 자체가 변해야 하므로 새로 만들어 대입
        val newList = mutableListOf<RestaurantInfoDTO>()
        // AJAX 이용 RestaureantInfoList를 받아온 후 setting
        // test용으로 하나만 넣어서 새 list를 만듬
        newList.add(RestaurantInfoDTO("BusanIT", 123.22, 36.22))
        restaurantList.value = newList
    }

    fun setRestaurantList( newList : MutableList<RestaurantInfoDTO>){
        restaurantList.value= newList
    }
}