package io.github.mayachen350.libchenviews.activity.navdrawer

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

interface HasDrawer {
    var drawerLayout: DrawerLayout

    var navigationView: NavigationView
}