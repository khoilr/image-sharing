@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalproject.ui.theme.FinalProjectTheme

class LoginActivity : ComponentActivity() {
	@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			FinalProjectTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					val windowSizeClass = calculateWindowSizeClass(this)
					LoginCompose(windowSizeClass.widthSizeClass)
				}
			}
		}
	}
}

@ExperimentalMaterial3WindowSizeClassApi
@Composable
fun LoginCompose(windowSize: WindowWidthSizeClass) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.widthIn(min = 240.dp, max = 320.dp)
			.padding(horizontal = 32.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		content = {
			if (windowSize == WindowWidthSizeClass.Compact)
				Column(
					modifier = Modifier.fillMaxWidth(),
					content = {
						Content(windowSize)
					},
				)
			else
				Row(
					modifier = Modifier.fillMaxHeight(),
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceBetween,
					content = {
						Content(windowSize)
					},
				)
		},
	)
}

@Composable
fun Content(windowSize: WindowWidthSizeClass) {
	val email = remember { mutableStateOf("") }
	val password = remember { mutableStateOf("") }
	val passwordVisible = remember { mutableStateOf(false) }
	val localFocusManager = LocalFocusManager.current

	val localModifier: Modifier
	val arrangement: Arrangement.Vertical
	if (windowSize == WindowWidthSizeClass.Compact) {
		localModifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 16.dp)
		arrangement = Arrangement.spacedBy(8.dp)

	} else {
		localModifier = Modifier.fillMaxHeight()
		arrangement = Arrangement.Center
	}

	val launcher =
		rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
	val context = LocalContext.current

	Column(
		modifier = localModifier
			.pointerInput(Unit) {
				detectTapGestures(
					onTap = { localFocusManager.clearFocus() }
				)
			},
		verticalArrangement = arrangement,
		horizontalAlignment = Alignment.CenterHorizontally,
		content = {
			Image(
				painter = painterResource(id = R.drawable.camera),
				contentDescription = "Logo",
				modifier = Modifier
					.size(256.dp)
			)
			Text(
				"Imsha",
				style = MaterialTheme.typography.titleLarge,
				color = MaterialTheme.colorScheme.primary
			)
			Text(
				"Share your memories with the world!",
				style = MaterialTheme.typography.titleMedium,
				color = MaterialTheme.colorScheme.secondary
			)
		},
	)

	// User interaction
	Column(
		modifier = localModifier,
		verticalArrangement = arrangement,
		horizontalAlignment = Alignment.CenterHorizontally,
		content = {
			// input fields
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 16.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp),
				content = {
					OutlinedTextField(
						modifier = Modifier.fillMaxWidth(),
						value = email.value,
						onValueChange = { email.value = it },
						label = { Text("Email") },
						keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
						singleLine = true,
						keyboardActions = KeyboardActions(
							onDone = { localFocusManager.clearFocus() }
						)
					)
					// password hide/show button
					OutlinedTextField(
						modifier = Modifier.fillMaxWidth(),
						value = password.value,
						onValueChange = { password.value = it },
						label = { Text("Password") },
						visualTransformation = PasswordVisualTransformation(),
						keyboardOptions = KeyboardOptions(keyboardType = if (passwordVisible.value) KeyboardType.Text else KeyboardType.Password),
						trailingIcon = {
							IconButton(
								onClick = {
									passwordVisible.value = !passwordVisible.value
								},
							) {
								Icon(
									painter = painterResource(id = if (passwordVisible.value) R.drawable.visibility_fill0_wght400_grad0_opsz48 else R.drawable.visibility_off_fill0_wght400_grad0_opsz48),
									contentDescription = "Show password",
									modifier = Modifier.size(24.dp)
								)
							}
						},
						singleLine = true,
						keyboardActions = KeyboardActions(
							onDone = { localFocusManager.clearFocus() }
						)
					)
				},
			)
			// auth buttons
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 16.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp),
				content = {
					Button(
						onClick = {
							val intent = Intent(context, HomeActivity::class.java)
							launcher.launch(intent)
						},
						modifier = Modifier
							.fillMaxWidth(),
						content = {
							Text("Login / Register")
						},
					)
					OutlinedButton(
						onClick = { /*TODO*/ },
						modifier = Modifier.fillMaxWidth(),
						content = {
							Row(
								modifier = Modifier.fillMaxWidth(),
								horizontalArrangement = Arrangement.Center,
								verticalAlignment = Alignment.CenterVertically,
								content = {
									Image(
										painter = painterResource(id = R.drawable.google),
										contentDescription = "Login with Google",
										modifier = Modifier
											.padding(end = 8.dp)
											.size(24.dp)
									)
									Text("Login with Google")
								},
							)
						},
					)
				},
			)
		},
	)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
	FinalProjectTheme {
		LoginCompose(
			windowSize = WindowWidthSizeClass.Compact
		)
	}
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 700)
@Composable
fun LoginPreviewTablet() {
	FinalProjectTheme {
		LoginCompose(
			windowSize = WindowWidthSizeClass.Medium
		)
	}
}
