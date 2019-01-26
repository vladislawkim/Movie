package com.vladislawfox.base.presentation.extension

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach

class AndroidJob(lifecycle: Lifecycle) : Job by Job(), LifecycleObserver {
    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() = cancel()
}

@UseExperimental(ObsoleteCoroutinesApi::class)
@ObsoleteCoroutinesApi
fun View.setOnClick(scope: CoroutineScope, action: suspend () -> Unit) {
    // launch one actor as a parent of the context job
    val eventActor = scope.actor<Unit> {
        for (event in channel) action()
    }
    // install a listener to activate this actor
    setOnClickListener { eventActor.offer(Unit) }
}

@UseExperimental(ObsoleteCoroutinesApi::class)
@ObsoleteCoroutinesApi
suspend fun <T> ReceiveChannel<T>.consumeEachOnUI(action: (T) -> Unit) = onUI {
    consumeEach(action)
}

suspend fun onUI(block: suspend CoroutineScope.() -> Unit) =
    withContext(Dispatchers.Main, block)