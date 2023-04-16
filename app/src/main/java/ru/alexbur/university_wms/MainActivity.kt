package ru.alexbur.university_wms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import ru.alexbur.core.di.navigation.NavigationScreenFactory
import ru.alexbur.feature.authorization.presentation.AuthorizationScreen
import ru.alexbur.uikit.theme.PrimaryFirst
import ru.alexbur.uikit.theme.UniversityWMSTheme
import ru.alexbur.university_wms.di.MainComponent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationScreenFactorySet: @JvmSuppressWildcards Set<NavigationScreenFactory>

    // @Inject
    //lateinit var navigationHostFactorySet: @JvmSuppressWildcards Set<NavigationHostFactory>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.getComponent().inject(this)
        setContent {
            UniversityWMSTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = PrimaryFirst,
                ) {
                    AuthorizationScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UniversityWMSTheme {
        Greeting("Android")
    }
}