package com.task.movies.ui.main

import com.task.movies.domain.IDataRepository
import com.task.movies.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val dataRepository: IDataRepository
) : BaseViewModel(), IMainVM {

}
