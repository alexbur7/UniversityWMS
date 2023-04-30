package ru.alexbur.university_wms.presentation.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.alexbur.core.domain.navigation.Router
import ru.alexbur.uikit.theme.BottomNavigationHeight
import ru.alexbur.uikit.theme.IconTint
import ru.alexbur.uikit.theme.NavBarBackgroundColor
import ru.alexbur.uikit.theme.SecondaryTextColor

@Composable
fun WmsNavigationBarItem(modifier: Modifier, isSelected: Boolean, navController: NavController, tab: NavBarItems) {
    Box(
        modifier = modifier
            .height(BottomNavigationHeight)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(if (tab.router is Router.ScannerRouter) BottomNavigationHeight else 48.dp)
                .clip(CircleShape)
                .background(NavBarBackgroundColor)
                .clickable {
                    if (isSelected.not()) {
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
                tint = if (isSelected) IconTint else SecondaryTextColor
            )
        }
    }
}