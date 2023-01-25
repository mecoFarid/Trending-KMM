package com.mecofarid.trending.common.ui.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.replaceFragment(fragment: Fragment, containerId: Int){
    supportFragmentManager
        .beginTransaction()
        .replace(containerId, fragment)
        .addToBackStack(null)
        .commit()
}