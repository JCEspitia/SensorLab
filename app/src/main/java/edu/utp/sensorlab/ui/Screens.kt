package edu.utp.sensorlab.ui

import android.hardware.Sensor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.utp.sensorlab.sensors.rememberSensorReading
import java.util.Locale

private val Shape = RoundedCornerShape(24.dp)

@Composable
fun HomeScreen(
    onAccelerometer: () -> Unit,
    onLight: () -> Unit,
    onProximity: () -> Unit,
    onCamera: () -> Unit,
    onAbout: () -> Unit
) {
    Scaffold(bottomBar = {
        NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
            NavigationBarItem(
                selected = true,
                onClick = {},
                icon = { Icon(Icons.Default.Home, null) },
                label = { Text("Inicio") })
            NavigationBarItem(
                selected = false,
                onClick = onAbout,
                icon = { Icon(Icons.Default.Info, null) },
                label = { Text("Acerca de") })
        }
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 22.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(
                        "LABORATORIO ANDROID",
                        style = MaterialTheme.typography.labelMedium,
                        color = Indigo,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "SensorLab",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        "Explora el hardware de tu teléfono",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Surface(shape = RoundedCornerShape(18.dp), color = Indigo.copy(alpha = 0.12f)) {
                    Icon(
                        Icons.Default.Sensors,
                        null,
                        tint = Indigo,
                        modifier = Modifier
                            .padding(15.dp)
                            .size(30.dp)
                    )
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(Brush.linearGradient(listOf(Indigo, Violet)), Shape)
                    .padding(22.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                    Text(
                        "Tu dispositivo es un laboratorio",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Descubre cómo Android interpreta el movimiento, la luz, la proximidad y la imagen.",
                        color = Color.White.copy(alpha = 0.9f)
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        HeroStat("03", "Sensores", Modifier.weight(1f))
                        HeroStat("01", "Cámara", Modifier.weight(1f))
                        HeroStat("04", "Módulos", Modifier.weight(1f))
                    }
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    "Laboratorio de hardware",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Selecciona un módulo para comenzar",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ModuleCard(
                    "Acelerómetro",
                    "Movimiento en 3 ejes",
                    Icons.Default.ScreenRotation,
                    Indigo,
                    onAccelerometer,
                    Modifier.weight(1f)
                )
                ModuleCard(
                    "Luz ambiental",
                    "Iluminación en lux",
                    Icons.Default.WbSunny,
                    Amber,
                    onLight,
                    Modifier.weight(1f)
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ModuleCard(
                    "Proximidad",
                    "Detecta objetos cercanos",
                    Icons.Default.Sensors,
                    Emerald,
                    onProximity,
                    Modifier.weight(1f)
                )
                ModuleCard(
                    "Cámara",
                    "Captura fotografías",
                    Icons.Default.PhotoCamera,
                    Pink,
                    onCamera,
                    Modifier.weight(1f)
                )
            }
            InfoCard(
                "Aprendizaje práctico",
                "Los módulos están listos para conectar las APIs reales de Android durante las próximas clases.",
                Icons.Default.School
            )
            Spacer(Modifier.height(4.dp))
        }
    }
}

@Composable
private fun HeroStat(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier
            .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(15.dp))
            .padding(vertical = 12.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            value,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            label,
            color = Color.White.copy(alpha = 0.88f),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun ModuleCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accent: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(190.dp)
            .clickable(onClick = onClick),
        shape = Shape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(shape = RoundedCornerShape(15.dp), color = accent.copy(alpha = 0.13f)) {
                Icon(icon, null, tint = accent, modifier = Modifier
                    .padding(11.dp)
                    .size(25.dp))
            }
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                null,
                tint = accent,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Composable
private fun InfoCard(title: String, description: String, icon: ImageVector) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.07f)
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, null, tint = MaterialTheme.colorScheme.primary)
            Column {
                Text(title, fontWeight = FontWeight.SemiBold)
                Text(
                    description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LabScaffold(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accent: Color,
    onBack: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(title, fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver") }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
        )
    }) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(accent.copy(alpha = 0.10f), Shape)
                    .padding(22.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Surface(shape = RoundedCornerShape(20.dp), color = accent.copy(alpha = 0.15f)) {
                        Icon(
                            icon,
                            null,
                            tint = accent,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(36.dp)
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(
                            title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            subtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            content()
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun SectionTitle(title: String, description: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Text(
            description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ReadingCard(
    label: String,
    value: String,
    unit: String,
    accent: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(17.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                label,
                style = MaterialTheme.typography.labelLarge,
                color = accent,
                fontWeight = FontWeight.Bold
            )
            Text(
                value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                unit,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun WaitingPanel(message: String, icon: ImageVector = Icons.Default.HourglassEmpty) {
    Card(
        shape = Shape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                icon,
                null,
                modifier = Modifier.size(42.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                "Esperando integración",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                message,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun AccelerometerScreen(onBack: () -> Unit) {

    val reading = rememberSensorReading(
        Sensor.TYPE_ACCELEROMETER
    )

    val x = reading.values.getOrNull(0)
    val y = reading.values.getOrNull(1)
    val z = reading.values.getOrNull(2)

    fun formatValue(value: Float?): String {
        return value?.let { String.format(Locale.US, "%.2f", it) } ?: "-"
    }


    LabScaffold(
        "Acelerómetro",
        "Movimiento y aceleración en tres ejes",
        Icons.Default.ScreenRotation,
        Indigo,
        onBack
    ) {
        SectionTitle(
            "Lecturas en tiempo real",
            "Aceleración medida en metros por segundo al cuadrado"
        )
        if (!reading.available) {

            InfoCard(
                "Sensor no disponible",
                "Este dispositivo no cuenta con un acelerómetro compatible.",
                Icons.Default.Warning
            )

        } else {

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                ReadingCard(
                    "EJE X",
                    formatValue(x),
                    "m/s²",
                    Indigo,
                    Modifier.weight(1f)
                )

                ReadingCard(
                    "EJE Y",
                    formatValue(y),
                    "m/s²",
                    Violet,
                    Modifier.weight(1f)
                )

                ReadingCard(
                    "EJE Z",
                    formatValue(z),
                    "m/s²",
                    Emerald,
                    Modifier.weight(1f)
                )
            }

            InfoCard(
                "Sensor conectado",
                if (reading.values.isEmpty())
                    "Esperando la primera medición..."
                else
                    "Los valores se actualizan al mover el teléfono.",
                Icons.Default.CheckCircle
            )
        }
        InfoCard(
            "Experimento",
            "Inclina y gira el teléfono. Observa cómo cambia cada eje cuando conectemos el sensor.",
            Icons.Default.Science
        )
    }
}

@Composable
fun LightScreen(onBack: () -> Unit) {

    val reading = rememberSensorReading(
        Sensor.TYPE_LIGHT
    )

    val lux = reading.values.getOrNull(0)

    val description = when {
        lux == null -> "Esperando medición"
        lux < 10f -> "Ambiente muy oscuro"
        lux < 100f -> "Iluminación baja"
        lux < 1000f -> "Ambiente iluminado"
        else -> "Iluminación intensa"
    }

    LabScaffold(
        "Luz ambiental",
        "Mide la iluminación del entorno",
        Icons.Default.WbSunny,
        Amber,
        onBack
    ) {
        SectionTitle("Iluminación actual", "El sensor reportará la intensidad de luz en lux")

        if (!reading.available){
            InfoCard(
                "Sensor no disponible",
                "Este teléfono no cuenta con un sensor de luz ambiental accesible mediante Android.",
                Icons.Default.Lightbulb
            )
        } else {
            ReadingCard("ILUMINACIÓN", lux.toString(), "lux", Amber)
            InfoCard(
                "Interpretación",
                description,
                Icons.Default.Lightbulb
            )
        }

        InfoCard(
            "Experimento",
            "Acerca el teléfono a una ventana y después cubre el sensor con la mano.",
            Icons.Default.Science
        )
    }
}

@Composable
fun ProximityScreen(onBack: () -> Unit) {
    val reading = rememberSensorReading(
        Sensor.TYPE_PROXIMITY
    )

    val distance = reading.values.getOrNull(0)

    LabScaffold(
        "Proximidad",
        "Detecta objetos cercanos al teléfono",
        Icons.Default.Sensors,
        Emerald,
        onBack
    ) {
        SectionTitle(
            "Estado del sensor",
            "Algunos dispositivos informan distancia y otros solo cerca/lejos"
        )
        ReadingCard("DISTANCIA", distance.toString(), "cm (si está disponible)", Emerald)
        InfoCard(
            "Rango máximo",
            reading.maxRange.toString(),
            Icons.Default.Science
        )
        InfoCard(
            "Experimento",
            "Acerca y aleja la mano de la parte superior del teléfono.",
            Icons.Default.Science
        )
    }
}

@Composable
fun CameraScreen(onBack: () -> Unit) {
    LabScaffold(
        "Cámara",
        "Vista previa y captura de fotografías",
        Icons.Default.PhotoCamera,
        Pink,
        onBack
    ) {
        SectionTitle("Vista previa", "Este módulo se completará durante la clase de CameraX")
        Card(
            shape = Shape,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.CameraAlt, null, modifier = Modifier.size(62.dp), tint = Pink)
                Spacer(Modifier.height(14.dp))
                Text("Vista previa no conectada", fontWeight = FontWeight.Bold)
                Text(
                    "Aquí mostraremos la cámara del dispositivo",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
        Button(
            onClick = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(Icons.Default.PhotoCamera, null)
            Spacer(Modifier.width(8.dp))
            Text("Capturar fotografía")
        }
        InfoCard(
            "Próxima clase",
            "Implementaremos CameraX, permisos de cámara y captura de imágenes.",
            Icons.Default.School
        )
    }
}

@Composable
fun AboutScreen(onBack: () -> Unit) {
    LabScaffold(
        "Acerca de",
        "Aprende Android explorando hardware real",
        Icons.Default.Info,
        Indigo,
        onBack
    ) {
        SectionTitle("SensorLab", "Aplicación educativa desarrollada con Kotlin y Jetpack Compose")
        InfoCard(
            "Objetivo",
            "Explorar acelerómetro, sensor de luz, proximidad y cámara en teléfonos Android.",
            Icons.Default.School
        )
        InfoCard(
            "Tecnologías",
            "Jetpack Compose, Navigation Compose, SensorManager y CameraX.",
            Icons.Default.Code
        )
        InfoCard(
            "Versión",
            "Base visual y navegación. Las integraciones de hardware se implementarán en clase.",
            Icons.Default.Info
        )
    }
}
