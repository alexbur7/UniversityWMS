package ru.alexbur.university_wms.presentation.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.alexbur.uikit.theme.BottomNavigationHeight
import ru.alexbur.uikit.theme.IconTint
import ru.alexbur.uikit.theme.PlaceHolderColor

@Composable
fun BottomNavBar(
    items: Array<NavItem.NavBarItems>,
    navController: NavController,
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
        items.forEach { tab ->
            val isSelected = tab.route == route
            if (tab == NavItem.NavBarItems.SCANNER) {
                NavigationBarItem(
                    modifier = Modifier.height(BottomNavigationHeight),
                    selected = isSelected,
                    onClick = {
                        if (tab.route != route) {
                            with(navController) {
                                navigate(tab.route) {
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
                    }
                )
            } else {
                NavigationBarItem(
                    modifier = Modifier
                        .height(48.dp)
                        .align(Alignment.Bottom)
                        .background(Color.White),
                    selected = isSelected,
                    onClick = {
                        if (tab.route != route) {
                            with(navController) {
                                navigate(tab.route) {
                                    popUpTo(graph.startDestinationId) {
                                        saveState = true
                                    }
                                    restoreState = true
                                }
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = tab.icon),
                            contentDescription = "Image in bottom navigation image",
                            tint = if (isSelected) IconTint else PlaceHolderColor
                        )
                    }
                )
            }
        }
    }
}