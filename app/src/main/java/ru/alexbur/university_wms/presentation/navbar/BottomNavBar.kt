package ru.alexbur.university_wms.presentation.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.alexbur.core.presentation.ViewEvent
import ru.alexbur.uikit.theme.BottomNavigationHeight
import ru.alexbur.uikit.theme.IconTint
import ru.alexbur.uikit.theme.PlaceHolderColor
import ru.alexbur.university_wms.di.MainComponent

@Composable
fun BottomNavBar(
    navController: NavController
) {

    val navControllerBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navControllerBackStackEntry?.destination?.route

    Row(
        Modifier
            .fillMaxWidth()
            .height(BottomNavigationHeight)
            .selectableGroup(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        NavBarItems.values().forEach { tab ->
            val isSelected = tab.router.route == route
            if (tab == NavBarItems.SCANNER) {
                NavigationBarItem(
                    modifier = Modifier
                        .weight(1f)
                        .height(BottomNavigationHeight),
                    selected = isSelected,
                    onClick = {
                        if (tab.router.route != route) {
                            with(navController) {
                                navigate(tab.router.route) {
                                    popUpTo(graph.startDestinationId) {
                                        saveState = true
                                    }
                                    restoreState = true
                                }
                            }
                        }
                    },
                    icon = {
                        Image(
                            modifier = Modifier.align(Alignment.Top),
                            painter = painterResource(id = tab.icon),
                            contentDescription = "Image in bottom navigation image"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(indicatorColor = Color.White)
                )
            } else {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.Bottom)
                        .height(48.dp)
                        .background(Color.White)
                        .clickable {
                            if (tab.router.route != route) {
                                with(navController) {
                                    navigate(tab.router.route) {
                                        popUpTo(graph.startDestinationId) {
                                            saveState = true
                                        }
                                        restoreState = true
                                    }
                                }
                            }
                        }
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = tab.icon),
                        contentDescription = "Image in bottom navigation image",
                        tint = if (isSelected) IconTint else PlaceHolderColor
                    )
                }
            }
        }
    }
}