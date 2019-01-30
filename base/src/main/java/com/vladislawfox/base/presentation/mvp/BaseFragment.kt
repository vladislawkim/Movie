package com.vladislawfox.base.presentation.mvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.vladislawfox.base.presentation.di.BaseComponent
import com.vladislawfox.base.presentation.di.HasComponent
import dagger.Lazy
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/27/19.
 */
abstract class BaseFragment<V : MvpView, P : MvpPresenter<V>, C : BaseComponent>(@LayoutRes val layoutRes: Int) : MvpFragment<V, P>(), HasComponent<C>, MvpView {

  @Inject protected lateinit var viewPresenter: Lazy<P>
  protected lateinit var viewComponent: C
  private var imm: InputMethodManager? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    initializeInjector()
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(layoutRes, container, false)
  }

  override fun createPresenter(): P = viewPresenter.get()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    imm = view.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    super.onViewCreated(view, savedInstanceState)
  }

  protected fun hideKeyboard() {
    imm?.hideSoftInputFromWindow(view?.windowToken, 0)
  }

  protected fun showKeyBoard() {
    imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
  }

  protected fun changeInputMode(mode: Int) {
    activity?.window?.run {
      if (attributes.softInputMode != mode) {
        setSoftInputMode(mode)
      }
    }
  }

  protected fun showToast(stringId: Int, duration: Int) = Toast.makeText(context, stringId,
      duration).show()

  override fun getComponent(): C = viewComponent
}