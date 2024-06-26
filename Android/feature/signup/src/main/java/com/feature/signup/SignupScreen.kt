package com.feature.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.feature.signup.components.Agreement
import com.feature.signup.components.Check
import com.feature.signup.components.Joining

@Composable
fun SignupScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
        ) {
            IconButton(
                modifier = modifier
                    .padding(start = 4.dp)
                    .align(Alignment.CenterStart),
                onClick = {
                    navController.navigateUp()
                },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                )
            }
        }

        var step by remember {
            mutableIntStateOf(0)
        }
        Box(
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            when (step) {
                0 -> Check(
                    onClick = {
                        step++
                    }
                )

                1 -> Agreement(
                    onClick = {
                        step++
                    }
                )

                else -> Joining(
                    onClick = { /*TODO*/ }
                )
            }
        }
    }
}

@Composable
@Preview(name = "Signup")
private fun SignupScreenPreview() {
    SignupScreen(rememberNavController())
}
