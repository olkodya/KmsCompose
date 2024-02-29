package com.example.keymanagement.ui.features.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import com.example.keymanagement.R

@Composable
fun ErrorImageAuth(
    modifier: Modifier = Modifier,
    isImageValidate: Boolean
) {
    if (isImageValidate) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_error_outline_24),
                contentDescription = "Image Error",
                modifier = modifier.size(250.dp)
            )
        }
    }
}