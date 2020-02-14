package id.alik.movie_mvvm.common.extension

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.image
import id.alik.movie_mvvm.R
import id.alik.movie_mvvm.common.utils.Logger

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun ImageView.filter(color: Int) {
    this.image?.let {
        this.context
        it.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}

fun ImageView.filterDefault() {
    this.image?.let {
        val color = ContextCompat.getColor(this.context, R.color.colorPrimary)
        this.context
        this.filter(color)
    }
}

fun View.makeEnable() {
    this.isEnabled = true
}

fun View.makeDisable() {
    this.isEnabled = false
}


@SuppressLint("RestrictedApi")
fun BottomNavigationView.disableShiftMode() {
    val menuView = this.getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0 until menuView.childCount) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShifting(false)
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: NoSuchFieldException) {
        Logger.debug("Unable to get shift mode field $e")
    } catch (e: IllegalAccessException) {
        Logger.debug("Unable to change value of shift mode $e")
    }
}

fun EditText.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            cb(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}
