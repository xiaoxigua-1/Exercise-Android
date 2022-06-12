package com.example.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.exercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.abc, A()).commit()
        val a = FragmentAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.abc, true)
        binding.abc.adapter = a
    }
}

class FragmentAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    private val fragments = listOf(A(), B())

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
}