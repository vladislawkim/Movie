package com.vladislawfox.base.presentation.mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.vladislawfox.base.presentation.di.BaseComponent
import com.vladislawfox.base.presentation.di.HasComponent
import com.vladislawfox.base.presentation.extension.AndroidJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlin.coroutines.CoroutineContext

abstract class BaseMviFragment<I : MviIntent, S : MviViewState, C : BaseComponent>(@LayoutRes val layoutRes: Int) :
    Fragment(), CoroutineScope, MviView<I, S>, HasComponent<C> {

    protected lateinit var viewComponent: C
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
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

    override fun getComponent(): C = viewComponent
}