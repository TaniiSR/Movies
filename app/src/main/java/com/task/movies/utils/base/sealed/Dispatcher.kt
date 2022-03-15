package com.task.movies.utils.base.sealed

sealed class Dispatcher {
    object Main : Dispatcher()
    object Background : Dispatcher()
    object LongOperation : Dispatcher()
}