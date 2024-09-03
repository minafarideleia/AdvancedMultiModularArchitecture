package com.minafarid.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.minafarid.domain.model.ErrorMessage
import com.minafarid.presentation.R

@Composable
fun renderErrorPopup(errorMessage: ErrorMessage, retryAction: () -> Unit) {
  var showDialog by remember { mutableStateOf(true) }

  fun dismissDialog() {
    showDialog = false
  }
  if (showDialog) {
    Dialog(
      onDismissRequest = { },
      properties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false,
      ),
      content = {
        Box(
          modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
          contentAlignment = Alignment.Center,
        ) {
          Box(
            modifier = Modifier
              .aspectRatio(1f)
              .fillMaxSize(0.8f)
              .background(Color.White)
              .padding(16.dp),
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

              Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(),
              ) {
                Button(
                  onClick = { retryAction() },
                  modifier = Modifier
                    .padding(
                      8.dp,
                    )
                    .weight(1f),
                ) {
                  Text(text = stringResource(id = R.string.retry_again))
                }

                Button(
                  onClick = {
                    dismissDialog()
                  },
                  modifier = Modifier
                    .padding(
                      8.dp,
                    )
                    .weight(1f),
                ) {
                  Text(text = stringResource(id = R.string.dimiss))
                }
              }
            }
          }
        }
      },
    )
  }
}
