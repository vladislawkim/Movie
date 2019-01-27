package com.vladislawfox.auth.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladislawfox.auth.R
import com.vladislawfox.auth.presentation.di.AuthComponent
import com.vladislawfox.auth.presentation.di.DaggerAuthComponent_AuthDependenciesComponent
import com.vladislawfox.base.presentation.di.component.BaseAppComponent
import com.vladislawfox.base.presentation.mvi.BaseMviFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment : BaseMviFragment<LoginIntent, LoginViewState, AuthComponent>(R.layout.fragment_login) {

    @Inject lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initializeInjector()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initViews() {
    }

    @ExperimentalCoroutinesApi
    override fun setupIntents() {
        viewAuthGuestSession.setOnClickListener {
            launch {
                viewModel.state.value.let {
                    intents.send(LoginIntent.GuestSessionIntent)
                }
            }
        }
    }

    override fun startStream(): Job = launch { viewModel.run{ processIntents(intents) } }

    override fun render(state: LoginViewState) {
        state.guestSession?.let {
            toast(it.sessionId)
        }
    }

    override fun initializeInjector() {
        this.viewComponent = AuthComponent
            .get(DaggerAuthComponent_AuthDependenciesComponent.builder()
                .baseAppComponentApi(BaseAppComponent.get())
                .build()).also { it.inject(this) }
    }
}
