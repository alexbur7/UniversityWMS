package ru.alexbur.university_wms.presentation.navbar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.alexbur.uikit.theme.BottomNavigationHeight

@Composable
fun BottomNavBar(
    navController: NavController
) {

    val navControllerBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navControllerBackStackEntry?.destination?.route

    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .height(BottomNavigationHeight)
            .selectableGroup(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        NavBarItems.values().forEach { tab ->
            val isSelected = tab.router.route == route
            WmsNavigationBarItem(
                modifier = Modifier.weight(1f),
                isSelected = isSelected,
                navController = navController,
                tab = tab
            )
        }
    }
}