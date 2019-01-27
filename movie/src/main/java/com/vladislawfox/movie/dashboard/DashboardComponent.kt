package com.vladislawfox.movie.dashboard

import com.vladislawfox.base.presentation.di.component.BaseAppComponentApi
import com.vladislawfox.base.presentation.di.scope.PerFeature
import dagger.Component
import java.lang.ref.WeakReference

/**
 * Created by vladislawfox on 1/27/19.
 */
@PerFeature
@Component(dependencies = [DashboardDependencies::class])
interface DashboardComponent {

  companion object {
    @Volatile
    private lateinit var dashComponentWeak: WeakReference<DashboardComponent>
    fun get(dashDependencies: DashboardDependencies): DashboardComponent {
      if (!this::dashComponentWeak.isInitialized || dashComponentWeak.get() == null) {
        synchronized(DashboardComponent::class) {
          if (!this::dashComponentWeak.isInitialized || dashComponentWeak.get() == null) {
            val component = DaggerDashboardComponent.builder()
                .dashboardDependencies(dashDependencies)
                .build()
            dashComponentWeak = WeakReference(component)
          }
        }
      }
      return dashComponentWeak.get()!!
    }
  }

  @PerFeature
  @Component(dependencies = [BaseAppComponentApi::class])
  interface DashboardDependenciesComponent : DashboardDependencies
}