package com.example.viewmodelexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.viewmodelexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : RestaurantViewModel
    lateinit var bindMain : ActivityMainBinding

    override fun onResume() {
        super.onResume()
        Log.i("#OnResume MainActivity", "viewModel 서버 갱신 호출")
        viewModel.getRestaurantListFromServer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindMain.root)

        // viewModel 변수 선언 및 초기화
        Log.i("#OnCreate >>", "OnCreate MainActivity")
        viewModel = ViewModelProvider(this)[RestaurantViewModel::class.java]

        // viewModel observer 설정
        viewModel.restaurantList.observe(this, Observer {
            Log.i("#observer >> ", "restaurant list value changed")

            // 여기서 ui를 변화시킨다
            bindMain.textView.text = viewModel.restaurantList.value.toString()

        })



        bindMain.buttonActivity.setOnClickListener{
            Intent(applicationContext, OtherActivity::class.java).also{ startActivity(it)}
        }
    }
}