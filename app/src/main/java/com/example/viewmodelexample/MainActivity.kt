package com.example.viewmodelexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.viewmodelexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : RestaurantViewModel
    lateinit var bindMain : ActivityMainBinding
    var testAlive : String? = null
    // lifecycle display test 용 변수
    lateinit var viewLifecyle : TextView

    override fun onResume() {
        super.onResume()
        Log.i("#OnResume MainActivity", "resume is called")
        // viewModel.getRestaurantListFromServer() ==> data가 변하면 UI도 update
        // 만약 orientation change나 다른 activity에서 돌아올 때 유지가 안되는 UI가 있든지
        // data를 서버에서 다시 불러 오고 싶으면
        // or UI만 개선

        viewLifecyle.text = viewLifecyle.text.toString() + "On Resume \n"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindMain.root)

        viewLifecyle = bindMain.tvLifecycle
        viewLifecyle.text = "On Create \n"

        // viewModel 변수 선언 및 초기화
        Log.i("#OnCreate >>", "OnCreate MainActivity")
        viewModel = ViewModelProvider(this)[RestaurantViewModel::class.java]

        // viewModel observer 설정
        viewModel.getRestaurantList().observe(this, Observer {
            Log.i("#observer >> ", "restaurant list value changed")

            // 여기서 ui를 변화시킨다
            viewModel.getRestaurantList().value?.get(0).let{
                bindMain.textView.text = it?.name
            }

        })

        // local variable test
        // create가 되면 재 설정 됨
        testAlive = "OnCreate"
        bindMain.textChange.text = testAlive.toString()

        bindMain.buttonChange.setOnClickListener {
            // 이값은 onCreate가 되면 없어짐  위에서 새로 생성됨
            testAlive = "새로운 값"
            bindMain.textChange.text = testAlive.toString()

            // 이값은 onCreate가 되어도 없어지지 않음
            // observer가 설정되어 있어 UI는 바꿀 필요가 없다.
            val newList = mutableListOf<RestaurantInfoDTO>().also{
                it.add(RestaurantInfoDTO("새로운 맛집", 123.22, 12.234))
                viewModel.setRestaurantList(it)
            }
        }

        // global variable 사용
        // 사용하려면 RestApplication 및 AndroidManifest.xml 변화 참고
        (application as RestApplication).globalUsername = "홍길동"

        bindMain.buttonActivity.setOnClickListener{
            Intent(applicationContext, OtherActivity::class.java).also{ startActivity(it)}
        }
    }
}