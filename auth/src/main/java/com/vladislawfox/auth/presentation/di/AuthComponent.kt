package com.vladislawfox.auth.presentation.di

import com.vladislawfox.base.presentation.di.api.BaseAppComponentApi
import com.vladislawfox.base.presentation.di.scope.PerFeature
import dagger.Component
import java.lang.ref.WeakReference

/**
 * Created by vladislawfox on 1/20/19.
 */
@PerFeature
@Component(dependencies = [AuthDependencies::class], modules = [AuthDataModule::class])
interface AuthComponent : AuthComponentApi {

    companion object {
        @Volatile
        private lateinit var authComponentWeak: WeakReference<AuthComponent>

        fun get(authDependencies: AuthDependencies): AuthComponent {
            if (!this::authComponentWeak.isInitialized || authComponentWeak.get() == null) {
                synchronized(AuthComponent::class) {
                    if (!this::authComponentWeak.isInitialized || authComponentWeak.get() == null) {
                        val component = DaggerAuthComponent.builder()
                            .authDependencies(authDependencies)
                            .build()
                        authComponentWeak = WeakReference(component)
                    }
                }
            }
            return authComponentWeak.get()!!
        }
    }

    @PerFeature
    @Component(dependencies = [BaseAppComponentApi::class])
    interface AuthDependenciesComponent : AuthDependencies
}