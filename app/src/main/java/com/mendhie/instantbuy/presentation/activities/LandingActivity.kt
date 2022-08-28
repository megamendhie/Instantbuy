package com.mendhie.instantbuy.presentation.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.mendhie.instantbuy.R
import com.mendhie.instantbuy.databinding.ActivityLandingBinding
import com.mendhie.instantbuy.presentation.adapters.ViewPagerAdapter
import com.mendhie.instantbuy.presentation.manager.IntroductionManager

class LandingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandingBinding
    private lateinit var layouts: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val introManager = IntroductionManager(this)
        introManager.setFirst(false)

        layouts = intArrayOf(R.layout.slide_1, R.layout.slide_2, R.layout.slide_3)
        addBottomDots(0)
        val viewPagerAdapter = ViewPagerAdapter(layouts)
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.addOnPageChangeListener(viewListener)
        binding.btnNext.setOnClickListener {
            val current = getItem(+1)
            if (current < layouts.size) {
                binding.viewPager.currentItem = current
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        @SuppressLint("SetTextI18n")
        override fun onPageSelected(position: Int) {
            addBottomDots(position)
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    //Giving the dots functionality
    private fun addBottomDots(position: Int) {
        val dots = arrayOfNulls<TextView>(layouts.size)
        val colorActive = resources.getColor(R.color.purple_500)
        val colorInactive = resources.getColor(R.color.inactive);
        binding.lnrDots.removeAllViews()
        for (mdots in dots.indices) {
            dots[mdots] = TextView(this)
            dots[mdots]!!.text  = Html.fromHtml("&#8226;")
            dots[mdots]!!.textSize = 45f
            dots[mdots]!!.setTextColor(colorInactive)
            binding.lnrDots.addView(dots[mdots])
        }
        if (dots.size > 0) {
            dots[position]!!.setTextColor(colorActive)
        }
    }

    private fun getItem(viewpageritem: Int): Int {
        return binding.viewPager.currentItem + viewpageritem
    }

}