package com.mendhie.instantbuy.presentation.adapters

import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter(val layouts: IntArray) : PagerAdapter() {
    override fun instantiateItem(myContainer: ViewGroup, mPosition: Int): Any {
        val layoutInflater = LayoutInflater.from(myContainer.rootView.context)
        val mInflater = layoutInflater.inflate(layouts[mPosition], myContainer, false)
        myContainer.addView(mInflater)
        return mInflater
    }

    override fun getCount(): Int {
        return layouts.size
    }

    override fun isViewFromObject(mView: View, mObject: Any): Boolean {
        return mView === mObject
    }

    override fun destroyItem(mContainer: ViewGroup, mPosition: Int, mObject: Any) {
        val v = mObject as View
        mContainer.removeView(v)
    }
}