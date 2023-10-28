package com.example.viewmodelexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData

class RestaurantViewModel : ViewModel(){

    private val _restaurantList = MutableLiveData<MutableList<RestaurantInfoDTO>>()

    val restaurantList : LiveData<MutableList<RestaurantInfoDTO>> get() = _restaurantList
    // 이렇게 하면 실제 java로 compile되면 restaurantList라는 변수는 생기지 않음
    // get() =   restauranList라는 변수가 생기지 않고, getRestaurantList() 가 _restauranList를
    // return하게 됨
    // 따라서 viewModel Instance.getRestaurantList()는 LiveData<MutableList<RestaurantInfoDTO>>()를 return
    // viewModelInstance.getRestaurantList().value는 MutableList<RestaurantInfoDTO> 를 return 하게 되며
    // 결국 private값이므로 외부에서는 수정 불가능함.

    // 초기화
    init{
        _restaurantList.value = mutableListOf<RestaurantInfoDTO>()
    }

    // server에서 읽어 올거니 이 method는 필요 없겠다.
//    fun setRestaurantList(restaurantList : List<RestaurantInfoDTO>){
//        // observer를 위해  새로운 list로 바꾸어 주어야 함  새로운 객체를 생성 대입함
//        val newList = mutableListOf<RestaurantInfoDTO>()
//        for(info : RestaurantInfoDTO in restaurantList) newList.add(info)
//        _restaurantList.value = newList
//    }

    fun getRestaurantListFromServer(){
        // observer가 호출되려면 value라는 자체가 변해야 하므로
        val newList = mutableListOf<RestaurantInfoDTO>()
        // Retrofit을 이용 RestaureantInfoList를 받아온 후 setting
        // test용으로 하나만 넣어서 새 list를 만듬
        newList.add(RestaurantInfoDTO("BusanIT", 123.22, 36.22))
        _restaurantList.value = newList
    }

}