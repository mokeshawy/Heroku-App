package com.core.extensions


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.core.error.AppError
import com.core.state.State
import com.core.state.State.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


fun Exception.getValidationError() = AppError.I(exception = this)


fun Context.showToast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


fun <T : Any> LazyPagingItems<T>.loadToFirstItem(onError: () -> Unit, onLoading: () -> Unit) =
    when (this.loadState.refresh) {
        is LoadState.Error -> onError()

        is LoadState.Loading -> onLoading()
        is LoadState.NotLoading -> {}
    }


fun <T : Any> LazyPagingItems<T>.loadToMoreItem(onError: () -> Unit, onLoading: () -> Unit) =
    when (this.loadState.append) {
        is LoadState.Error -> onError()

        is LoadState.Loading -> onLoading()
        is LoadState.NotLoading -> {}
    }


fun Context.restartActivity(activity: Activity) {
    val intent = Intent(this, activity::class.java)
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(intent)
}


fun ViewModel.viewModelScope(block: suspend () -> Unit) = this.viewModelScope.launch {
    block()
}


suspend inline fun <T> Flow<State<T>>.collectOnFlowState(
    crossinline onLoading: () -> Unit = {},
    crossinline onError: (AppError) -> Unit,
    crossinline onSuccess: suspend (T) -> Unit,
) = collect {
    onLoading()
    when (it) {
        is Error -> onError(it.error)
        is State.Loading -> {}
        is State.Success -> onSuccess(it.data ?: return@collect)
    }
}
