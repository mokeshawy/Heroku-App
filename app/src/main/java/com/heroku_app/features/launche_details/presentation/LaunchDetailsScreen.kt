package com.heroku_app.features.launche_details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.ui_component.main_top_bar.MainTopBar
import com.core.ui_component.subcompose_async.SubcomposeAsyncImageComponent
import com.heroku_app.R
import com.heroku_app.features.common.viewmodel.MainViewModel
import com.heroku_app.ui.theme.medium
import com.heroku_app.ui.theme.semiBold


@Composable
fun LaunchDetailsScreen(
    mainViewModel: MainViewModel,
    onBackClicked: () -> Unit
) {

    val uiModel = mainViewModel.launchModel

    MainTopBar(
        title = R.string.app_name,
        titleText = uiModel?.missionUiModel?.name,
        isPullRefresh = false,
        leftIcon = R.drawable.ic_arrow_back,
        onLeftIconClicked = onBackClicked,
        content = {
            LaunchContent(
                launchImage = uiModel?.missionUiModel?.missionPatch,
                rocketName = uiModel?.rocketUiModel?.name,
                rocketType = uiModel?.rocketUiModel?.type,
                id = uiModel?.id,
                missionName = uiModel?.missionUiModel?.name,
                site = uiModel?.site
            )
        })
}

@Composable
fun LaunchContent(
    modifier: Modifier = Modifier,
    launchImage: String?,
    rocketName: String?,
    rocketType: String?,
    id: String?,
    missionName: String?,
    site: String?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .verticalScroll(state = rememberScrollState())
    ) {

        SubcomposeAsyncImageComponent(
            modifier = modifier
                .size(400.dp)
                .fillMaxWidth()
                .padding(all = 10.dp),
            imageUrl = launchImage,
            errorPlaceholder = R.drawable.ic_vector_placeholder
        )

        Spacer(modifier = modifier.height(10.dp))

        RocketContent(
            rocketName = rocketName,
            rocketType = rocketType,
            id = id
        )

        Spacer(modifier = modifier.height(5.dp))

        MissionContent(missionName = missionName)

        Spacer(modifier = modifier.height(5.dp))

        SiteContent(site = site)
    }

}

@Composable
private fun RocketContent(
    rocketName: String?,
    rocketType: String?,
    id: String?,
) {

    Text(
        text = stringResource(id = R.string.rocket),
        fontSize = 20.sp,
        style = MaterialTheme.typography.titleLarge.semiBold
    )

    CommonText(
        title = R.string.name,
        value = rocketName
    )

    CommonText(
        title = R.string.type,
        value = rocketType
    )

    CommonText(
        title = R.string.id,
        value = id
    )
}

@Composable
private fun MissionContent(missionName: String?) {
    Text(
        text = stringResource(id = R.string.mission),
        fontSize = 20.sp,
        style = MaterialTheme.typography.titleLarge.semiBold
    )

    CommonText(
        title = R.string.name,
        value = missionName
    )
}


@Composable
private fun SiteContent(site: String?) {
    Text(
        text = stringResource(id = R.string.site),
        fontSize = 20.sp,
        style = MaterialTheme.typography.titleLarge.semiBold
    )

    CommonText(
        title = R.string.name,
        value = site
    )
}

@Composable
fun CommonText(
    modifier: Modifier = Modifier,
    title: Int,
    value: String?
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = title),
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium.medium
        )

        Spacer(modifier = modifier.width(3.dp))

        Text(
            text = value ?: "-",
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium.medium
        )
    }
}


@Composable
@Preview(showBackground = true)
fun LaunchContentPreview() {
    LaunchContent(
        launchImage = "https://imgur.com/jHNFSY6.png",
        rocketName = "Falcon 9",
        rocketType = "FT",
        id = "110",
        missionName = "CRS-21",
        site = "KSC LC 39A"
    )
}