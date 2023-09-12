package com.sharath070.boruuiclone.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sharath070.boruuiclone.fragments.Fragment1
import com.sharath070.boruuiclone.fragments.Fragment2
import com.sharath070.boruuiclone.fragments.Fragment3
import com.sharath070.boruuiclone.fragments.Fragment4

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> Fragment1()
            1 -> Fragment2()
            2 -> Fragment3()
            3 -> Fragment4()
            else -> Fragment1()
        }
    }
}