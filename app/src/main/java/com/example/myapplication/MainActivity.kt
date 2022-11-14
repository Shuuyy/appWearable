package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment

import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity(){




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewpager: ViewPager2 = findViewById(R.id.view_pager);

        val fragments : ArrayList<Fragment> = arrayListOf(
            BlankFragment2(),
            listOfCityFragment(),



        )

        val adapter = ViewAdapterPage(fragments,this)
        viewpager.adapter=adapter

    }



}