package com.enesselcuk.loginprotodatastore.common

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding>(
    private val inflater: (LayoutInflater) -> VB
) : AppCompatActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflater(layoutInflater)
        define()
        setContentView(binding.root)

    }

    open fun define() {}
}