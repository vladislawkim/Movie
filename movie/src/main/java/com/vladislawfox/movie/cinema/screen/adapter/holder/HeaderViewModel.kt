package com.vladislawfox.movie.cinema.screen.adapter.holder

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.vladislawfox.base.presentation.adapter.holder.BaseHolder
import com.vladislawfox.movie.R
import kotlinx.android.synthetic.main.view_header_model.view.*

/**
 * Created by vladislawfox on 1/30/19.
 */
@EpoxyModelClass(layout = R.layout.view_header_model)
abstract class HeaderViewModel : EpoxyModelWithHolder<HeaderViewModel.Holder>() {

    @EpoxyAttribute var titleResource: Int = 0

    override fun bind(holder: Holder) {
        holder.view.run { viewHeaderModelTitle.text = context.getString(titleResource) }
    }

    class Holder : BaseHolder()
}