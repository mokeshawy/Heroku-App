package com.heroku_app.features.common.viewmodel

import androidx.lifecycle.ViewModel
import com.heroku_app.features.launches.domain.modle.ui.LaunchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private var launchUiModel: LaunchUiModel? = null

    fun setLaunchUiModel(launchUiModel: LaunchUiModel) {
        this.launchUiModel = launchUiModel
    }

    val launchModel get() = this.launchUiModel

    override fun onCleared() {
        super.onCleared()
        launchUiModel = null
    }
}