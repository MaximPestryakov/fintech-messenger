package me.maximpestryakov.fintechmessenger.navigation

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.layout_header_navigation.view.*
import kotlinx.android.synthetic.main.layout_main_content.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.about.AboutFragment
import me.maximpestryakov.fintechmessenger.dialog_list.DialogListFragment
import me.maximpestryakov.fintechmessenger.settings.SettingsFragment


class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        val EXTRA_USER_ID = "me.maximpestryakov.fintechmessenger.navigation.EXTRA_USER_ID"
        val EXTRA_EMAIL = "me.maximpestryakov.fintechmessenger.navigation.EXTRA_EMAIL"
        private val MENU_DIALOGS = 0
    }

    private var userId: Int = 0

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        userId = intent.getIntExtra(EXTRA_USER_ID, 0)
        val email = intent.getStringExtra(EXTRA_EMAIL)
        navigationView.getHeaderView(0).textView.text = email

        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.addDrawerListener(toggle)

        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            navigationView.menu.getItem(MENU_DIALOGS).isChecked = true
            onNavigationItemSelected(navigationView.menu.getItem(MENU_DIALOGS))
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dialogs -> {
                val dialogsFragment = DialogListFragment.newInstance(userId)
                addFragment(dialogsFragment)
                supportActionBar?.setTitle(R.string.menu_dialogs)
            }
            R.id.nav_settings -> {
                val settingsFragment = SettingsFragment()
                addFragment(settingsFragment)
                supportActionBar?.setTitle(R.string.menu_settings)
            }
            R.id.nav_about -> {
                val aboutFragment = AboutFragment()
                addFragment(aboutFragment)
                supportActionBar?.setTitle(R.string.menu_about)
            }
            R.id.nav_exit -> finish()
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.popup_in, R.anim.popup_out)
            replace(R.id.contentNavigation, fragment)
        }.commit()
    }
}
