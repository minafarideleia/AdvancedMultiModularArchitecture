package com.minafarid.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun renderLoadingPopup(loadingMessageLInt: Int) {
  Dialog(
    onDismissRequest = { },
    properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
    content = {
      Box(
        modifier = Modifier
          .padding(16.dp)
          .size(200.dp)
          .background(
            color = Color.White,
            RoundedCornerShape(8.dp),
          ),
        contentAlignment = Alignment.Center,
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          CircularProgressIndicator(modifier = Modifier.size(80.dp))
          Spacer(modifier = Modifier.height(16.dp))
          Text(
            text = stringResource(id = loadingMessageLInt),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
          )
        }
      }
    },
  )
}
