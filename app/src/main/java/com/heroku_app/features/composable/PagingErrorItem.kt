package com.heroku_app.features.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.heroku_app.R

@Composable
fun PagingErrorItem(
    @StringRes errorMessage: Int,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = errorMessage))
        Spacer(modifier = Modifier.height(8.dp))

        AppButton(
            onClick = onRetry,
            text = stringResource(id = R.string.retry),
        )
    }
}

@Composable
fun AppButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(onClick = onClick, modifier = modifier.heightIn(min = 45.dp), enabled = enabled) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}