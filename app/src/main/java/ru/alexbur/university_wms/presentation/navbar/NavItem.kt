package ru.alexbur.university_wms.presentation.navbar

import androidx.annotation.DrawableRes
import ru.alexbur.feature.authorization.presentation.AuthorizationScreenFactory
import ru.alexbur.feature.profile.presentation.ProfileScreenFactory
import ru.alexbur.feature.scanned_data.presentation.ScannedDataScreenFactory
import ru.alexbur.feature.scanner.presentation.ScannerScreenFactory
import ru.alexbur.uikit.R
import ru.alexbur.university_wms.presentation.MainNavHostScreenFactory

sealed class NavItem(val route: String) {

    object Authorization : NavItem(AuthorizationScreenFactory.route)

    object MainScreen : NavItem(MainNavHostScreenFactory.route)

    enum class NavBarItems(
        @DrawableRes
        val icon: Int,
        val route: String
    ) {
        DATA(
            R.drawable.ic_data,
            ScannedDataScreenFactory.route,
        ),

        SCANNER(
            R.drawable.ic_scanner,
            ScannerScreenFactory.route,
        ),

        PROFILE(
            R.drawable.ic_profile,
            ProfileScreenFactory.route
        ),
    }
}