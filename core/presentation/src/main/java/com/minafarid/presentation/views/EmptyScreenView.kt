package com.minafarid.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.minafarid.presentation.R

@Composable
fun renderEmptyScreen(emptyMessageId: Int) {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Image(
        painter = painterResource(id = R.drawable.empty_ic),
        contentDescription = "EmptyIcon",
        modifier = Modifier.size(100.dp),
      )

      Spacer(modifier = Modifier.height(16.dp))
      Text(
        text = stringResource(id = R.string.no_result),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
      )
      Spacer(modifier = Modifier.height(16.dp))
      Text(
        text = stringResource(emptyMessageId),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
      )
    }
  }
}
