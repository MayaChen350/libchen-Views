package io.github.mayachen350.libchenviews.activity.navdrawer

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewbinding.ViewBinding
import ca.cegepmontpetit.cem6222816.tp3_4n6.AccueilActivity
import ca.cegepmontpetit.cem6222816.tp3_4n6.ConnexionActivity
import ca.cegepmontpetit.cem6222816.tp3_4n6.CreationActivity
import ca.cegepmontpetit.cem6222816.tp3_4n6.backend.PersistentSessionData
import ca.cegepmontpetit.cem6222816.tp3_4n6.backend.RetrofitUtil
import io.github.mayachen350.libchenviews.http.DefaultCallback
import com.google.android.material.navigation.NavigationView
import io.github.mayachen350.libchenviews.R
import io.github.mayachen350.libchenviews.activity.BaseActivity
import retrofit2.Call
import retrofit2.Response

abstract class DrawerActivity<BINDING : ViewBinding> : BaseActivity<BINDING>(), HasDrawer {

    abstract override var drawerLayout: DrawerLayout
    abstract override var navigationView: NavigationView

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    protected fun setupDrawer() {
        setupDrawerName()
        setupDrawerApplicationBar()
        setupDrawerItemSelected()
    }

    private fun setupDrawerName() {
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                findViewById<TextView>(R.id.drawer_title_tv).text =
                    PersistentSessionData.connectedUserUsername
            }

            override fun onDrawerOpened(drawerView: View) {}

            override fun onDrawerClosed(drawerView: View) {}

            override fun onDrawerStateChanged(newState: Int) {}

        })
    }

    private fun setupDrawerApplicationBar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.closed_drawer)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun setupDrawerItemSelected() {
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.accueil_item -> {
                    startActivity(
                        Intent(this, AccueilActivity::class.java)
                    )
                }

                R.id.ajouter_item -> {
                    startActivity(
                        Intent(this, CreationActivity::class.java)
                    )
                }

                R.id.deconnexion_item -> {
                    httpRequestWithLoading(findViewById(R.id.main)) { dismissSnackBar ->
                        RetrofitUtil.get().signOut().enqueue(
                            object : DefaultCallback<String>() {
                                override fun onSuccessfulResponse(
                                    call: Call<String>,
                                    response: Response<String>
                                ) {
                                    super.onSuccessfulResponse(call, response)
                                    dismissSnackBar()
                                }

                                override fun onFailure(call: Call<String>, t: Throwable) {
                                    super.onFailure(call, t)
                                    dismissSnackBar()
                                }
                            }
                        )
                    }
                    startActivity(
                        Intent(this, ConnexionActivity::class.java)
                    )
                }
            }
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
        super.onConfigurationChanged(newConfig)
    }
}