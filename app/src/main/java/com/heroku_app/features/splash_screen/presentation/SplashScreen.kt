package com.heroku_app.features.splash_screen.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.ui_component.subcompose_async.SubcomposeAsyncImageComponent
import com.heroku_app.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToOrderList: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        SubcomposeAsyncImageComponent(
            modifier = modifier.size(250.dp),
            imageUrl = R.drawable.ic_heroku_app,
            errorPlaceholder = R.drawable.ic_vector_placeholder
        )
    }

    LaunchedEffect(Unit) {
        delay(1000)
        onNavigateToOrderList()
    }
}

@Composable
@Preview(showBackground = true)
fun SplashScreenPreview() {
    SplashScreen(onNavigateToOrderList = {})
}
