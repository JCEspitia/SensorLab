package edu.utp.sensorlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.utp.sensorlab.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SensorLabApp() }
    }
}

private object Routes {
    const val HOME = "home"
    const val ACCELEROMETER = "accelerometer"
    const val LIGHT = "light"
    const val PROXIMITY = "proximity"
    const val CAMERA = "camera"
    const val ABOUT = "about"
}

@Composable
fun SensorLabApp() {
    SensorLabTheme {
        val navController = rememberNavController()
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(navController = navController, startDestination = Routes.HOME) {
                composable(Routes.HOME) {
                    HomeScreen(
                        onAccelerometer = { navController.navigate(Routes.ACCELEROMETER) },
                        onLight = { navController.navigate(Routes.LIGHT) },
                        onProximity = { navController.navigate(Routes.PROXIMITY) },
                        onCamera = { navController.navigate(Routes.CAMERA) },
                        onAbout = { navController.navigate(Routes.ABOUT) }
                    )
                }
                composable(Routes.ACCELEROMETER) { AccelerometerScreen { navController.popBackStack() } }
                composable(Routes.LIGHT) { LightScreen { navController.popBackStack() } }
                composable(Routes.PROXIMITY) { ProximityScreen { navController.popBackStack() } }
                composable(Routes.CAMERA) { CameraScreen { navController.popBackStack() } }
                composable(Routes.ABOUT) { AboutScreen { navController.popBackStack() } }
            }
        }
    }
}
