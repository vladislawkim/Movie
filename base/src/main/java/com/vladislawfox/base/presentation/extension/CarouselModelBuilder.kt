package com.vladislawfox.base.presentation.extension

import com.airbnb.epoxy.EpoxyModel
import com.vladislawfox.base.presentation.adapter.CarouselViewModelBuilder

/**
 * Created by vladislawfox on 2/3/19.
 */
/** Add models to a CarouselModel_ by transforming a list of items into EpoxyModels.
 *
 * @param items The items to transform to models
 * @param modelBuilder A function that take an item and returns a new EpoxyModel for that item.
 */
inline fun <T> CarouselViewModelBuilder.withModelsFrom(
    items: List<T>,
    modelBuilder: (T) -> EpoxyModel<*>
) {
  models(items.map { modelBuilder(it) })
}