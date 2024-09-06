package com.minafarid.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.minafarid.domain.model.toUser
import com.minafarid.navigator.desinations.USER_AGE
import com.minafarid.navigator.desinations.USER_FULLNAME
import com.minafarid.navigator.desinations.USER_PARAM

@Composable
fun homeScreen(navController: NavController) {
  val backStackEntry = navController.currentBackStackEntryAsState().value
  val user = backStackEntry?.arguments?.getString(USER_PARAM)
  val fullName = backStackEntry?.arguments?.getString(USER_FULLNAME)
  val age = backStackEntry?.arguments?.getInt(USER_AGE)
  val userObject = user?.toUser()

  Scaffold(
    content = { pad ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(pad),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(text = "Email:${userObject?.email}")
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "FullName:$fullName")
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Age:$age")
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = { }) {
          Text(text = "Load Home Info")
        }
      }
    },
  )
}
