package com.vladislawfox.base.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.vladislawfox.base.presentation.extension.AndroidJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlin.coroutines.CoroutineContext
import com.vladislawfox.base.presentation.mvi.MviIntent
import com.vladislawfox.base.presentation.mvi.MviView
import com.vladislawfox.base.presentation.mvi.MviViewState

abstract class BaseFragment<I : MviIntent, S : MviViewState>(@LayoutRes val layoutRes: Int) : Fragment(), CoroutineScope, MviView<I, S> {
    protected val job = AndroidJob(lifecycle)

    override val intents = Channel<I>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    protected abstract fun initViews()

    protected abstract fun setupIntents()

    /**
     *  Start the stream by passing [MviIntent] to [MviViewModel]
     */
    protected abstract fun startStream(): Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startStream()
        setupIntents()
    }

    protected fun toast(message: String) =
        activity?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
}