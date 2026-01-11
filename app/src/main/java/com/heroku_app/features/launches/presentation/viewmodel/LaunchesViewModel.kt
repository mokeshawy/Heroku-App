package com.heroku_app.features.launches.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.core.bases.base_viewmodel.BaseViewModel
import com.core.extensions.viewModelScope
import com.heroku_app.features.common.domain.model.ui.launches_ui.LaunchUiModel
import com.heroku_app.features.launches.domain.event.LaunchesIntent
import com.heroku_app.features.launches.domain.modle.state.LaunchesPagingState
import com.heroku_app.features.launches.domain.repository.LaunchesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val launchesRepository: LaunchesRepository
) : BaseViewModel<LaunchesIntent, LaunchesPagingState>(initialState = LaunchesPagingState()) {


    val favorites get() = launchesRepository.getFavorites()

    init {
        sendLaunchesPagingIntent()
    }

    fun refresh() = sendLaunchesPagingIntent()


    private fun sendLaunchesPagingIntent() = sendIntent(LaunchesIntent.LaunchesPagingIntent)


    override fun processIntent(intent: LaunchesIntent) {
        when (intent) {
            LaunchesIntent.LaunchesPagingIntent -> reduceLaunchesPagingData()
        }
    }

    private fun reduceLaunchesPagingData() = viewModelScope {
        val launchesPagingData = launchesRepository.getLaunches().cachedIn(viewModelScope)
        launchesPagingData.collect {
            updateStateFlow { copy(launchPagingUiModel = launchesPagingData) }
        }
    }


    fun toggleFavorite(launch: LaunchUiModel) = launchesRepository.toggleFavorite(launch = launch)

}