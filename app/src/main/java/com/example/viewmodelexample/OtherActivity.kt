package com.example.viewmodelexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewmodelexample.databinding.ActivityOtherBinding

class OtherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOtherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView2.text = (application as RestApplication).globalUsername

    }
}