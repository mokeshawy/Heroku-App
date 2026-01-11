package com.heroku_app.features.launche_details.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.core.bases.base_viewmodel.BaseViewModel
import com.core.extensions.collectOnFlowState
import com.core.extensions.viewModelScope
import com.heroku_app.features.launche_details.domain.event.LaunchDetailsIntent
import com.heroku_app.features.launche_details.domain.mapper.toLaunchUiModel
import com.heroku_app.features.launche_details.domain.model.state.LaunchDetailsState
import com.heroku_app.features.launche_details.domain.repository.LaunchByIdRepository
import com.heroku_app.features.launche_details.presentation.graph.LaunchDetailsRouteScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LaunchDetailsViewModel @Inject constructor(
    private val launchByIdRepository: LaunchByIdRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<LaunchDetailsIntent, LaunchDetailsState>(initialState = LaunchDetailsState()) {

    val launchDetails = savedStateHandle.toRoute<LaunchDetailsRouteScreen>()

    init {
        sendLaunchByIdIntent()
    }

    fun refresh() {
        resetLaunchDetailsState()
        sendLaunchByIdIntent()
    }

    private fun sendLaunchByIdIntent() =
        sendIntent(LaunchDetailsIntent.LaunchById(id = launchDetails.id))


    override fun processIntent(intent: LaunchDetailsIntent) {
        when (intent) {
            is LaunchDetailsIntent.LaunchById -> reduceLaunchByIdState(id = intent.id)
        }
    }

    private fun reduceLaunchByIdState(id: String) = viewModelScope {
        updateStateFlow { copy(isLoading = true) }
        launchByIdRepository.getLaunchById(id = id).collectOnFlowState(
            onError = {
                handleError(it) { updateStateFlow { copy(isLoading = false, error = it) } }
            },
            onSuccess = { data ->
                val uiModel = data.toLaunchUiModel()
                updateStateFlow { copy(isLoading = false, launchUiModel = uiModel) }
            }
        )
    }

    private fun resetLaunchDetailsState() = viewModelScope {
        updateStateFlow { copy(isLoading = false, error = null, launchUiModel = null) }
    }
}