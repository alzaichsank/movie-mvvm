package id.alik.movie_mvvm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.alik.movie_mvvm.R
import id.alik.movie_mvvm.common.extension.disableShiftMode
import id.alik.movie_mvvm.common.utils.Logger
import id.alik.movie_mvvm.ui.main.home.FragmentHome
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    private var tabTag = ""

    private val bottomNavigationListener by lazy {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_menu_movie -> {
                    setToMovie()
                    true
                }
                R.id.main_menu_tv -> {
                    setToTv()
                    true
                }
                R.id.main_menu_favorite -> {
                    setToFavorite()
                    true
                }
                else -> {
                    setToMovie()
                    true
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigationContainer()
        setToMovie()
    }

    private fun initBottomNavigationContainer() {
        navigation.setOnNavigationItemSelectedListener(bottomNavigationListener)
        navigation.disableShiftMode()

    }

    private fun setToMovie() {
        loadFragment(FragmentHome(), "")
    }

    private fun setToTv() {
//        loadFragment(OrderFragment(), "")
    }

    private fun setToFavorite() {
//        loadFragment(CartFragment(), CartFragment.TAG)
    }

    private fun setSelectedTabToPosition(position: Int) {
        Logger.debug("cek tag $position")
        val moveTo = when (position) {
            0 -> R.id.main_menu_movie
            1 -> R.id.main_menu_tv
            2 -> R.id.main_menu_favorite
            else -> R.id.main_menu_movie
        }
        navigation.selectedItemId = moveTo
    }

    private fun loadFragment(fragment: Fragment, tag: String?) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.pager_main_menu, fragment, tag)
            addToBackStack(null)
        }.commit()
    }
}
