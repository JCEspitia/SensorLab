# SensorLab — Base de interfaz y navegación

## Preparación

1. Crear en Android Studio un proyecto **Empty Activity** con Kotlin, Jetpack Compose y package `edu.utp.sensorlab` (minSdk 26 recomendado).
2. Copiar los tres archivos `.kt` de esta carpeta a las rutas equivalentes en `app/src/main/java/edu/utp/sensorlab/`.
3. Agregar a `app/build.gradle.kts` las dependencias de Navigation Compose y Material Icons Extended (ver `DEPENDENCIAS.md`).
4. Sincronizar Gradle y ejecutar en un teléfono Android.

## Alcance

La interfaz y la navegación están implementadas. Las lecturas de sensores aparecen como `—` hasta que se conecten las APIs de Android en clase. La cámara muestra un placeholder y su botón de captura está deshabilitado. No se han añadido permisos ni integraciones de hardware.

## Pantallas

- Inicio: cuatro módulos y acceso a Acerca de.
- Acelerómetro: lecturas X/Y/Z y experimento.
- Luz: lectura en lux y experimento.
- Proximidad: distancia y experimento.
- Cámara: área de vista previa y botón pendiente.
- Acerca de: objetivo y tecnologías.

La pantalla de inicio tiene navegación inferior. Los módulos usan flecha de regreso y el botón Atrás del sistema funciona mediante Navigation Compose.
