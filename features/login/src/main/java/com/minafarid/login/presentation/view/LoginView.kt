package com.minafarid.login.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minafarid.login.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Button(modifier = Modifier.fillMaxWidth(),
                onClick = { loginViewModel.login(username, password) }) {
                Text(text = "Login")
            }

        }

    }
}