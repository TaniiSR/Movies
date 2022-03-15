package com.task.movies.utils.base.interfaces

import com.task.movies.utils.base.SingleClickEvent

interface IBaseViewModel {
    val clickEvent: SingleClickEvent
    fun onClick(view: android.view.View)
}