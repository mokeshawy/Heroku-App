package com.heroku_app.features.launches.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.core.extensions.loadToFirstItem
import com.core.extensions.loadToMoreItem
import com.core.ui_component.main_top_bar.MainTopBar
import com.core.ui_component.subcompose_async.SubcomposeAsyncImageComponent
import com.core.ui_component.ui_extensions.noRippleClickable
import com.heroku_app.R
import com.heroku_app.features.common.domain.model.ui.launches_ui.LaunchUiModel
import com.heroku_app.features.composable.BottomLoadingIndicator
import com.heroku_app.features.composable.PagingErrorItem
import com.heroku_app.features.launches.presentation.viewmodel.LaunchesViewModel
import com.heroku_app.ui.theme.semiBold


@Composable
fun LaunchesScreen(
    viewModel: LaunchesViewModel = hiltViewModel(),
    onNavigateToLaunchesDetailsScreen: (id: String, missionName: String?) -> Unit
) {

    val launchesState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    val pagingData = launchesState.launchPagingUiModel?.collectAsLazyPagingItems()

    val favorites by viewModel.favorites.collectAsStateWithLifecycle()

    val isRefreshing = pagingData?.loadState?.refresh is LoadState.Loading

    MainTopBar(
        title = R.string.launches,
        isRefreshing = isRefreshing,
        onRefresh = viewModel::refresh,
        content = {
            LaunchesContent(
                pagingData = pagingData,
                favorites = favorites,
                onRetry = viewModel::refresh,
                onItemClicked = { uiModel ->
                    onNavigateToLaunchesDetailsScreen(
                        uiModel.id, uiModel.missionUiModel?.name
                    )
                },
                onFavoriteToggle = { launch -> viewModel.toggleFavorite(launch) }
            )
        }
    )
}


@Composable
fun LaunchesContent(
    pagingData: LazyPagingItems<LaunchUiModel>?,
    favorites: List<LaunchUiModel>,
    onRetry: () -> Unit,
    onItemClicked: (LaunchUiModel) -> Unit,
    onFavoriteToggle: (LaunchUiModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(count = pagingData?.itemCount ?: 0) { index ->
            val item = pagingData?.get(index) ?: return@items

            val isFavorite = favorites.any { it.id == item.id }

            LaunchItem(
                launchImage = item.missionUiModel?.missionPatch,
                rocketName = item.rocketUiModel?.name,
                missionName = item.missionUiModel?.name,
                isFavorite = isFavorite,
                onItemClicked = { onItemClicked(item) },
                onFavoriteChange = { onFavoriteToggle(item) }
            )
        }

        pagingData?.loadToFirstItem(onError = {
            item {
                PagingErrorItem(
                    errorMessage = R.string.failed_to_load_more_data,
                    onRetry = onRetry
                )
            }
        }, onLoading = {})

        pagingData?.loadToMoreItem(
            onError = {
                item {
                    PagingErrorItem(
                        errorMessage = R.string.failed_to_load_more_data,
                        onRetry = onRetry
                    )
                }
            }, onLoading = {
                item {
                    BottomLoadingIndicator()
                }
            }
        )
    }
}

@Composable
fun LaunchItem(
    modifier: Modifier = Modifier,
    launchImage: String?,
    rocketName: String?,
    missionName: String?,
    isFavorite: Boolean,
    onItemClicked: () -> Unit,
    onFavoriteChange: (Boolean) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .noRippleClickable(onClick = onItemClicked),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SubcomposeAsyncImageComponent(
                modifier = modifier.size(50.dp),
                imageUrl = launchImage,
                errorPlaceholder = R.drawable.ic_vector_placeholder
            )
            Column(
                modifier = modifier
                    .weight(2f)
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = rocketName ?: "-",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLarge.semiBold
                )
                Text(
                    text = missionName ?: "-",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLarge.semiBold
                )

                FavoriteButton(
                    modifier = modifier.align(Alignment.End),
                    isFavorite = isFavorite,
                    onFavoriteChange = onFavoriteChange
                )
            }
        }
    }
}


@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onFavoriteChange: (Boolean) -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = { onFavoriteChange(!isFavorite) }
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (isFavorite) Color.Red else Color.Gray
        )
    }
}


