package com.vladislawfox.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.vladislawfox.base.presentation.di.HasComponent
import com.vladislawfox.base.presentation.di.component.BaseAppComponent
import com.vladislawfox.movie.dashboard.DaggerDashboardComponent_DashboardDependenciesComponent
import com.vladislawfox.movie.dashboard.DashboardComponent
import com.vladislawfox.movie.dashboard.activity.DaggerDashboardActivityComponent
import com.vladislawfox.movie.dashboard.activity.DaggerDashboardActivityComponent_DashboardActivityDependenciesComponent
import com.vladislawfox.movie.dashboard.activity.DashboardActivityComponent
import com.vladislawfox.movie.dashboard.activity.module.ActivityContextModule
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HasComponent<DashboardActivityComponent> {

    private lateinit var dashboardActivityComponent: DashboardActivityComponent
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeInjector()
        getComponent().inject(this)
        navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    override fun initializeInjector() {
        val dashboardActivityDependencies = DaggerDashboardActivityComponent_DashboardActivityDependenciesComponent
            .builder()
            .dashboardComponentApi(DashboardComponent
                .get(DaggerDashboardComponent_DashboardDependenciesComponent
                    .builder()
                    .baseAppComponentApi((application as HasComponent<*>).getComponent() as BaseAppComponent)
                    .build()))
            .build()
        dashboardActivityComponent = DaggerDashboardActivityComponent
            .builder()
            .dashboardActivityDependencies(dashboardActivityDependencies)
            .activityContextModule(ActivityContextModule(this))
            .build()
    }

    override fun getComponent(): DashboardActivityComponent = dashboardActivityComponent
}
