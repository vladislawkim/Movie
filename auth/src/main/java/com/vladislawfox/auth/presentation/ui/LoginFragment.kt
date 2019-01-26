package com.vladislawfox.auth.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladislawfox.auth.R
import com.vladislawfox.auth.presentation.di.AuthComponent
import com.vladislawfox.auth.presentation.di.DaggerAuthComponent_AuthDependenciesComponent
import com.vladislawfox.base.presentation.di.component.BaseAppComponent
import com.vladislawfox.base.presentation.ui.BaseFragment
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginIntent, LoginViewState, AuthComponent>(R.layout.fragment_login) {

    @Inject lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initializeInjector()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initViews() {
    }

    override fun setupIntents() {
    }

    override fun startStream(): Job = launch { viewModel.run{ processIntents(intents) } }

    override fun render(state: LoginViewState) {
    }

    override fun initializeInjector() {
        this.viewComponent = AuthComponent
            .get(DaggerAuthComponent_AuthDependenciesComponent.builder()
                .baseAppComponentApi(BaseAppComponent.get())
                .build()).also { it.inject(this) }
    }
}
