package ru.alexbur.university_wms.presentation.navbar

import androidx.annotation.DrawableRes
import ru.alexbur.core.domain.navigation.Router
import ru.alexbur.uikit.R

enum class NavBarItems(
    @DrawableRes
    val icon: Int,
    val router: Router
) {
    DATA(
        R.drawable.ic_data,
        Router.ScannerDataRouter,
    ),

    SCANNER(
        R.drawable.ic_scanner,
        Router.ScannerRouter,
    ),

    PROFILE(
        R.drawable.ic_profile,
        Router.ProfileRouter
    ),
}