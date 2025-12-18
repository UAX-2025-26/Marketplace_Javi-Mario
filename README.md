# Marketplace App

## 1. Introducción

**Marketplace App** es una aplicación para Android desarrollada como un proyecto integral para demostrar la aplicación de conceptos clave del desarrollo de software móvil. El resultado es una aplicación de comercio electrónico funcional que permite a los usuarios registrarse, explorar productos, gestionar un carrito de compras y realizar pedidos.

El proyecto no solo se enfoca en la funcionalidad, sino también en la implementación de una arquitectura de software robusta, escalable y mantenible, utilizando patrones y herramientas modernas del ecosistema de Android.

## 2. Características

*   **Autenticación de Usuarios**: Sistema completo de registro e inicio de sesión utilizando correo electrónico y contraseña, respaldado por **Firebase Authentication**.
*   **Catálogo de Productos**: Presentación de una lista de productos en una interfaz limpia y eficiente, utilizando `RecyclerView` para un rendimiento óptimo.
*   **Vista de Detalle**: Cada producto tiene su propia pantalla de detalle que muestra información ampliada.
*   **Carrito de la Compra**: Los usuarios pueden añadir y eliminar productos de su carrito. El estado del carrito se guarda localmente en el dispositivo, persistiendo entre sesiones gracias a **Room**.
*   **Gestión de Pedidos**: Funcionalidad para simular el proceso de pago y guardar un registro de los pedidos realizados por el usuario.
*   **Navegación Intuitiva**: Flujo de navegación coherente y predecible gestionado por el **Navigation Component** de Jetpack.

## 3. Arquitectura y Stack Tecnológico

La aplicación se ha construido siguiendo el patrón de diseño **MVVM (Model-View-ViewModel)**, que promueve una clara separación de responsabilidades entre la interfaz de usuario, la lógica de presentación y la lógica de negocio.

### Stack Tecnológico

*   **Lenguaje**: Java
*   **Arquitectura**: MVVM + Patrón Repositorio
*   **Componentes de Android Jetpack**:
    *   **UI**: `ViewBinding` para la vinculación de vistas y `Navigation Component` para la navegación.
    *   **Datos**: `Room` para la base de datos local y `ViewModel` para gestionar los datos de la UI de forma consciente del ciclo de vida.
    *   **Observabilidad**: `LiveData` para crear flujos de datos observables que la UI puede consumir de forma reactiva.
*   **Backend como Servicio (BaaS)**: **Firebase Authentication** para la gestión de usuarios.
*   **Diseño**: Material Components para una apariencia moderna y consistente.

### Flujo de Datos

1.  **Vista (Fragment)**: Captura una acción del usuario (ej. un clic) y la comunica al `ViewModel`.
2.  **ViewModel**: Procesa la acción. Si necesita datos, solicita al `Repositorio` la información requerida.
3.  **Repositorio**: Es la única fuente de verdad. Decide si obtener los datos de la base de datos local (`Room`) o de un servicio remoto (Firebase). Abstrae el origen de los datos.
4.  **Datos**: Los datos fluyen de vuelta desde el `Repositorio` al `ViewModel`.
5.  **LiveData**: El `ViewModel` actualiza un `LiveData`, y la `Vista`, que está observando este `LiveData`, recibe la notificación y actualiza la interfaz de usuario automáticamente.

## 4. Estructura del Proyecto

El código fuente está organizado en paquetes que reflejan la arquitectura MVVM:

*   `com.example.marketplace.ui`: Contiene las `Activity` y `Fragment` (la capa de la Vista).
    *   `auth`: Fragmentos relacionados con la autenticación (Login, Register).
    *   `products`: Fragmentos para el listado y detalle de productos.
    *   `cart`: Fragmento para el carrito de la compra.
    *   `orders`: Fragmento para el historial de pedidos.
*   `com.example.marketplace.viewmodels`: Contiene los `ViewModel` para cada fragmento que necesita gestionar estado o lógica de UI.
*   `com.example.marketplace.repository`: Contiene la clase `Repository`, que gestiona la lógica de acceso a los datos.
*   `com.example.marketplace.data`: Contiene las entidades de `Room` y el `DAO` (Data Access Object).

## 5. Guía de Inicio

Sigue estos pasos para compilar y ejecutar el proyecto en tu entorno local.

### Prerrequisitos

*   Android Studio (versión Arctic Fox o superior)
*   Git

### Instalación y Configuración

1.  **Clona el repositorio**:
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    ```

2.  **Abre el proyecto en Android Studio**:
    *   Ve a `File > Open` y selecciona la carpeta del proyecto que acabas de clonar.

3.  **Configura Firebase**:
    *   Este proyecto requiere una configuración de Firebase para que la autenticación funcione.
    *   Ve a la [Consola de Firebase](https://console.firebase.google.com/) y crea un nuevo proyecto.
    *   Dentro del proyecto, añade una nueva aplicación Android. Es crucial que uses `com.example.marketplace` como nombre de paquete durante el registro.
    *   Descarga el archivo de configuración `google-services.json` que Firebase genera.
    *   Copia este archivo en el directorio `app/` de tu proyecto en Android Studio.
    *   Finalmente, en la sección de **Authentication** de la consola de Firebase, ve a la pestaña **Sign-in method** y habilita el proveedor **"Correo electrónico/Contraseña"**.

4.  **Construye y Ejecuta**:
    *   Espera a que Gradle sincronice todas las dependencias del proyecto.
    *   Elige un dispositivo virtual o físico y pulsa el botón "Run" (▶).

## 6. Decisiones de Diseño

*   **Single-Activity Architecture**: Se optó por una única `Activity` para simplificar la gestión del ciclo de vida, la comunicación entre pantallas y la implementación de una barra de navegación consistente.
*   **Inmutabilidad de LiveData**: El `ViewModel` expone los datos a la Vista usando `LiveData`, mientras que internamente utiliza `MutableLiveData`. Esto asegura que solo el `ViewModel` pueda modificar el estado, siguiendo un flujo de datos unidireccional.
*   **Manejo de Asincronía**: Las operaciones de base de datos con Room se diseñaron para ser llamadas desde funciones `suspend` de Kotlin, asegurando que las operaciones de I/O se ejecuten fuera del hilo principal para no afectar la fluidez de la UI.

