package com.vladislawfox.movie.cinema.screen.adapter.holder

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.vladislawfox.movie.R

/**
 * Created by vladislawfox on 1/30/19.
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HeaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val titleView: AppCompatTextView

    init {
        View.inflate(context, R.layout.view_header_model, this)
        titleView = (findViewById(R.id.viewHeaderModelTitle))
    }

    @ModelProp
    fun title(@StringRes stringRes: Int) {
        titleView.setText(stringRes)
    }
}