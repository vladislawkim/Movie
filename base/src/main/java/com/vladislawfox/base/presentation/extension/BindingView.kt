package com.vladislawfox.base.presentation.extension

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment

/**
 * Created by vladislawfox on 2/5/19.
 */

fun <T> lazyUnsychronized(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)

fun <V : View> Activity.bindView(@IdRes idRes: Int): Lazy<V> {
  return lazyUnsychronized {
    findViewById<V>(idRes)
  }
}

fun <V : View> View.bindView(@IdRes idRes: Int): Lazy<V> {
  return lazyUnsychronized {
    findViewById<V>(idRes)
  }
}

fun <V : View> Fragment.bindView(@IdRes idRes: Int): Lazy<V> {
  return lazyUnsychronized {
    view?.findViewById<V>(idRes) ?: throw NullPointerException(
        "View in fragment cannot be null! You must fix this!")
  }
}

fun View.bindString(@StringRes stringRes: Int): Lazy<String> {
  return lazyUnsychronized {
    context.getString(stringRes)
  }
}

fun Fragment.bindString(@StringRes stringRes: Int): Lazy<String> {
  return lazyUnsychronized {
    context?.getString(stringRes) ?: throw NullPointerException(
        "View in fragment cannot be null! You must fix this!")
  }
}

fun Activity.bindString(@StringRes stringRes: Int): Lazy<String> {
  return lazyUnsychronized {
    getString(stringRes)
  }
}

fun View.bindDrawable(@DrawableRes drawableRes: Int): Lazy<Drawable?> {
  return lazyUnsychronized {
    ResourcesCompat.getDrawable(resources, drawableRes, null)
  }
}

fun Fragment.bindDrawable(@DrawableRes drawableRes: Int): Lazy<Drawable?> {
  return lazyUnsychronized {
    ResourcesCompat.getDrawable(resources, drawableRes, null)
  }
}

fun Activity.bindDrawable(@DrawableRes drawableRes: Int): Lazy<Drawable?> {
  return lazyUnsychronized {
    ResourcesCompat.getDrawable(resources, drawableRes, null)
  }
}

fun View.bindDimen(@DimenRes dimenRes: Int): Lazy<Int> {
  return lazyUnsychronized {
    resources.getDimensionPixelSize(dimenRes)
  }
}

fun Fragment.bindDimen(@DimenRes dimenRes: Int): Lazy<Int> {
  return lazyUnsychronized {
    resources.getDimensionPixelSize(dimenRes)
  }
}

fun Activity.bindDimen(@DimenRes dimenRes: Int): Lazy<Int> {
  return lazyUnsychronized {
    resources.getDimensionPixelSize(dimenRes)
  }
}



