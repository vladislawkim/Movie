package com.vladislawfox.movie.cinema.presentation.screen.adapter.holder

import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.vladislawfox.base.presentation.adapter.holder.BaseHolder
import com.vladislawfox.movie.R

/**
 * Created by vladislawfox on 1/30/19.
 */
@EpoxyModelClass
abstract class HeaderViewModel : EpoxyModelWithHolder<HeaderHolder>() {

  @EpoxyAttribute(DoNotHash) var titleRes: Int = 0
  override fun getDefaultLayout(): Int = R.layout.view_header_model

  override fun bind(holder: HeaderHolder) {
    holder.titleView.run { setText(titleRes) }
  }
}

class HeaderHolder : BaseHolder() {
  val titleView by bind<AppCompatTextView>(R.id.viewHeaderModelTitle)
}
