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
import com.vladislawfox.base.presentation.di.BaseComponent
import com.vladislawfox.base.presentation.di.HasComponent
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/27/19.
 */
abstract class BaseFragment<V : MvpView, P : Presenter<V>, C : BaseComponent>(@LayoutRes val layoutRes: Int) : Fragment(), HasComponent<C>, MvpView {

  @Inject protected lateinit var presenter: P
  protected lateinit var viewComponent: C
  private var imm: InputMethodManager? = null

  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(layoutRes, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    presenter.attachView(this as V)
    imm = view.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onResume() {
    presenter.onResume()
    super.onResume()
  }

  override fun onPause() {
    presenter.onPause()
    super.onPause()
  }

  override fun onDestroyView() {
    presenter.detachView()
    super.onDestroyView()
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