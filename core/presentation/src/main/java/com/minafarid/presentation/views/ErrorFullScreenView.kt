package com.minafarid.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.minafarid.domain.model.ErrorMessage
import com.minafarid.presentation.R

@Composable
fun renderErrorFullScreen(errorMessage: ErrorMessage, retryAction: () -> Unit) {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Image(
        painter = painterResource(id = R.drawable.error_ic),
        contentDescription = "ErrorIcon",
        modifier = Modifier.size(100.dp),
      )

      Spacer(modifier = Modifier.height(16.dp))
      Text(
        text = stringResource(id = R.string.cannot_proceed),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
      )
      Spacer(modifier = Modifier.height(16.dp))
      Text(
        text = errorMessage.message,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
      )
      Spacer(modifier = Modifier.height(16.dp))
      Button(
        onClick = { retryAction() },
        modifier = Modifier
          .fillMaxWidth()
          .padding(
            start = 16.dp,
            end = 16.dp,
          ),
      ) {
        Text(text = stringResource(id = R.string.retry_again))
      }
    }
  }
}
