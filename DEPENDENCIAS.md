# Dependencias adicionales

Si tu proyecto usa un catálogo de versiones (`libs.versions.toml`), agrega las librerías equivalentes a estas coordenadas, eligiendo versiones compatibles con tu proyecto:

```kotlin
implementation("androidx.navigation:navigation-compose:2.9.6")
implementation("androidx.compose.material:material-icons-extended")
```

`material-icons-extended` puede obtener su versión del Compose BOM, si el proyecto incluye `implementation(platform(libs.androidx.compose.bom))`.

**Nota:** el código no utiliza CameraX todavía; se agregará en la sesión de cámara. Tampoco necesita permisos para mostrar esta interfaz.
