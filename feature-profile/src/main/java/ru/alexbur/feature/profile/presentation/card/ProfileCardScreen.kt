package ru.alexbur.feature.profile.presentation.card

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.feature.profile.domain.models.Profile
import ru.alexbur.uikit.R
import ru.alexbur.uikit.extentions.backgroundItem
import ru.alexbur.uikit.theme.ListColor
import ru.alexbur.uikit.theme.ShimmerPlaceHolderColor
import ru.alexbur.uikit.theme.Typography

@Composable
fun ProfileCardScreen(modifier: Modifier, profile: Profile?) {
    Row(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
            .backgroundItem()
            .placeholder(
                visible = profile == null,
                color = ListColor,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = ShimmerPlaceHolderColor
                )
            )
    ) {

        AsyncImage(
            modifier = Modifier
                .padding(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .width(112.dp)
                .height(145.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(profile?.avatar)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
        )

        Column {
            Text(
                modifier = Modifier.padding(start = 12.dp, top = 24.dp, end = 12.dp),
                text = profile?.name.orEmpty(),
                style = Typography.displayLarge
            )

            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp),
                text = profile?.jobTitle.orEmpty(),
                style = Typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
internal fun ProfileCardScreen() {
    ProfileCardScreen(
        Modifier.fillMaxWidth(),
        Profile(
            avatar = "https://www.re-sourcepartners.com/wp-content/uploads/2020/04/physical-inventory-banner.jpg",
            name = "Азиз Суроев",
            jobTitle = "Разнорабочий"
        )
    )
}