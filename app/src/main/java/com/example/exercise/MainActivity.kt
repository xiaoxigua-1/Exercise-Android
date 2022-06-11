package com.example.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.exercise.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.abc, A()).commit()
        val a = FragmentAdapter(supportFragmentManager)
//        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                binding.abc.currentItem = tab?.position ?: 0
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//        })
        binding.tabLayout.setupWithViewPager(binding.abc)
        binding.abc.adapter = a
        val c = listOf("a", "b")
        for(i in 0..c.size) {
            binding.tabLayout.getTabAt(i)?.text = c[i]
        }
    }
}

class FragmentAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    private val fragments = listOf(A(), B())

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
}