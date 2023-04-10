package com.example.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.screens.ImagesGrid
import com.example.finalproject.screens.Profile
import com.example.finalproject.screens.PublishScreen
import com.example.finalproject.ui.theme.FinalProjectTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class HomeActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			FinalProjectTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					val windowSize = calculateWindowSizeClass(activity = this)
//					NavigationBar(windowSize.widthSizeClass)
					HomeActivityCompose()
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeActivityCompose() {
	val screens = listOf(Screen.Home, Screen.Publish, Screen.Profile)
	val selectedTab = remember { mutableStateOf(0) }
	val navHost = rememberNavController()

	Scaffold(
		bottomBar = {
			NavigationBar(
				content = {
					val navBackStackEntry = navHost.currentBackStackEntryAsState()
					val currentRoute = navBackStackEntry.value?.destination?.route

					screens.forEachIndexed { index, screen ->
						NavigationBarItem(
							icon = {
								Icon(
									painter = if (selectedTab.value == index) painterResource(id = screen.icon)
									else painterResource(id = screen.outlinedIcon),
									contentDescription = screen.name,
									modifier = Modifier.size(24.dp)
								)
							},
							label = { Text(screen.name) },
							selected = selectedTab.value == index,
							onClick = {
								selectedTab.value = index
								navHost.navigate(screen.route) {
									popUpTo(navHost.graph.startDestinationId) {
										inclusive = true
									}
									launchSingleTop = true
								}
							},
						)
					}
				},
			)
		},
	) {
		NavHost(
			navController = navHost,
			startDestination = Screen.Home.route,
			modifier = Modifier
				.fillMaxSize()
				.padding(it)
		) {
			composable(Screen.Home.route) { ImagesGrid() }
			composable(Screen.Publish.route) { PublishScreen() }
			composable(Screen.Profile.route) { Profile() }
		}

	}
}

sealed class Screen(
	val route: String,
	val name: String,
	@DrawableRes val icon: Int,
	@DrawableRes val outlinedIcon: Int,
) {
	object Home : Screen(
		"home",
		"Home",
		R.drawable.home_fill1_wght400_grad0_opsz48,
		R.drawable.home_fill0_wght400_grad0_opsz48
	)

	object Publish : Screen(
		"publish",
		"Publish",
		R.drawable.publish_fill1_wght400_grad0_opsz48,
		R.drawable.publish_fill0_wght400_grad0_opsz48
	)

	object Profile : Screen(
		"profile",
		"Profile",
		R.drawable.account_circle_fill1_wght400_grad0_opsz48,
		R.drawable.account_circle_fill0_wght400_grad0_opsz48
	)
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
	FinalProjectTheme {
//		NavigationBar(WindowWidthSizeClass.Compact)
		HomeActivityCompose()
	}
}

//@Preview(widthDp = 700)
//@Composable
//fun ProfilePreviewTablet() {
//	FinalProjectTheme {
//		NavigationBar(WindowWidthSizeClass.Expanded)
//	}
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun NavigationBar(windowSize: WindowWidthSizeClass) {
//	val items = listOf("Home", "Publish", "Profile")
//	val unfilledIcons = listOf(
//		painterResource(id = R.drawable.home_fill0_wght400_grad0_opsz48),
//		painterResource(id = R.drawable.publish_fill0_wght400_grad0_opsz48),
//		painterResource(id = R.drawable.account_circle_fill0_wght400_grad0_opsz48),
//	)
//	val filledIcons = listOf(
//		painterResource(id = R.drawable.home_fill1_wght400_grad0_opsz48),
//		painterResource(id = R.drawable.publish_fill1_wght400_grad0_opsz48),
//		painterResource(id = R.drawable.account_circle_fill1_wght400_grad0_opsz48),
//	)
//	val selectedTab = remember { mutableStateOf(0) }
//
//	val navHost = rememberNavController()
//	val navBackStackEntry = navHost.currentBackStackEntryAsState()
//	val currentRoute = navBackStackEntry.value?.destination?.route
//
//	if (windowSize == WindowWidthSizeClass.Compact)
//		NavigationBar(
//			content = {
//				items.forEachIndexed { index, item ->
//					NavigationBarItem(
//						icon = {
//							Icon(
//								painter = if (selectedTab.value == index) filledIcons[index]
//								else unfilledIcons[index],
//								contentDescription = item,
//								modifier = Modifier.size(24.dp)
//							)
//						},
//						label = { Text(item) },
//						selected = selectedTab.value == index,
//						onClick = {
//							selectedTab.value = index
//							navHost.navigate(screen.route) {
//								popUpTo(navHost.graph.startDestinationId) { inclusive = true }
//								launchSingleTop = true
//							}
//						},
//					)
//				}
//			},
//		)
//	else
//		PermanentNavigationDrawer(
//			drawerContent = {
//				PermanentDrawerSheet(Modifier.width(240.dp)) {
//					Spacer(modifier = Modifier.height(24.dp))
//					items.forEachIndexed { index, item ->
//						run {
//							NavigationDrawerItem(
//								icon = {
//									Icon(
//										painter = if (selectedTab.value == index) filledIcons[index]
//										else unfilledIcons[index],
//										contentDescription = item,
//										modifier = Modifier.size(24.dp)
//									)
//								},
//								label = { Text(item) },
//								selected = selectedTab.value == index,
//								onClick = { selectedTab.value = index },
//								modifier = Modifier.padding(
//									horizontal = 16.dp,
//									vertical = 8.dp
//								)
//							)
//						}
//					}
//				}
//			},
//			content = {
//				Column(
//					modifier = Modifier
//						.fillMaxSize()
//						.padding(16.dp),
//					horizontalAlignment = Alignment.CenterHorizontally
//				) {
//					Text(text = "Application content")
//				}
//			}
//		)
//}