package com.vladislawfox.base.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.bluelinelabs.conductor.Controller
import com.vladislawfox.base.presentation.di.BaseComponent
import com.vladislawfox.base.presentation.di.HasComponent
import com.vladislawfox.base.presentation.extension.inputMethodManager
import com.vladislawfox.base.presentation.presenter.Presenter
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/19/19.
 */
abstract class BaseViewController<V : MvpView, P : Presenter<V>, C : BaseComponent>(bundle: Bundle? = null) : Controller(bundle),
    HasComponent<C>, MvpView {

    @Inject
    protected lateinit var presenter: P
    protected lateinit var viewComponent: C
    protected open val controllerRetainViewMode = RetainViewMode.RETAIN_DETACH
    protected var imm: InputMethodManager? = null
    private var lifecycleListener: ConductorLifecycleListener? = null
    protected val context by lazy { activity as Context }
    abstract val layoutId: Int

    init {
        retainViewMode = controllerRetainViewMode
    }

    abstract fun onViewCreated(view: View)

    override fun getComponent(): C = viewComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): View = inflater.inflate(layoutId, container, false)

    fun createPresenter() {
        presenter.setArgs(args)
        presenter.createView()
        view?.let { onViewCreated(it) }
        imm = view?.context?.inputMethodManager
    }

    override fun onDestroyView(view: View) {
        presenter.destroyView()
        super.onDestroyView(view)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.attachView(this as V)
    }

    override fun onDetach(view: View) {
        presenter.detachView()
        super.onDetach(view)
    }

    protected fun hideKeyboard() {
        imm?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    protected fun showKeyboard() {
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    protected fun changeInputMode(mode: Int) {
        activity?.window?.run {
            if (attributes.softInputMode != mode) {
                setSoftInputMode(mode)
            }
        }
    }

    protected fun showToast(stringId: Int, duration: Int) = Toast.makeText(
        activity, stringId,
        duration
    ).show()

//    abstract fun injectMe(component: C)

    override fun onContextAvailable(context: Context) {
        super.onContextAvailable(context)
        initializeInjector()
//        injectMe(viewComponent)
        lifecycleListener = ConductorLifecycleListener().apply { addLifecycleListener(this) }
    }

    override fun onContextUnavailable() {
        lifecycleListener?.let { removeLifecycleListener(it) }
        lifecycleListener = null
        super.onContextUnavailable()
    }
}