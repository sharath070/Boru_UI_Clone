package com.sharath070.boruuiclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import com.sharath070.boruuiclone.adapters.FragmentAdapter
import com.sharath070.boruuiclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var heartPressed = false

    private lateinit var fragmentAdapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        binding.toolBar.setNavigationOnClickListener {
            onBackPressed()
        }

        fragmentAdapter = FragmentAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = fragmentAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Marathi Newspaper"
                1 -> tab.text = "Hindi Newspaper"
                2 -> tab.text = "Hindi Newspaper"
                3 -> tab.text = "Hindi"
            }
        }.attach()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_icons, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.tbHeart) {
            heartPressed = !heartPressed

            if (heartPressed) {
                item.setIcon(R.drawable.heart_red)
            } else {
                item.setIcon(R.drawable.heart)
            }

        }

        if (item.itemId == R.id.tbShare) {
            val msg =
                "Hey! ,\nI have been using Buro Ui Clone for quite a time now. It is now seamless to order groceries."
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, msg)
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent, "Share about us in..."))
        }

        return super.onOptionsItemSelected(item)
    }
}