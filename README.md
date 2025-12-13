# Proyecto Marketplace App - Programación Dirigida por Eventos

## Resumen del Proyecto

Este proyecto es una aplicación de marketplace para Android, desarrollada como parte de la asignatura "Programación Dirigida por Eventos". La aplicación pone en práctica conceptos fundamentales del desarrollo moderno en Android, abarcando desde la construcción de interfaces de usuario interactivas hasta la gestión de la persistencia de datos y la autenticación de usuarios.

El objetivo principal es construir una aplicación robusta, eficiente y centrada en la experiencia de usuario, aplicando el paradigma de la programación dirigida por eventos. La app permite a los usuarios registrarse, iniciar sesión, explorar un catálogo de productos y gestionar un carrito de compras. Para ello, se apoya en componentes modernos como **ViewBinding**, **Navigation Component**, **LiveData** y **Firebase Authentication**.

## Arquitectura y Conceptos Aplicados

La aplicación sigue una arquitectura de una sola actividad (`Single-Activity`) con múltiples fragmentos y se adhiere al patrón de diseño **MVVM (Model-View-ViewModel)**.

*   **Arquitectura General (MVVM)**: Se separa la lógica de la interfaz de usuario (Vistas) de la lógica de negocio (ViewModels) y de los datos (Repositorios). Esto da como resultado una aplicación más organizada, escalable y fácil de testear.
    *   **Vistas (Fragments & Activity)**: Responsables únicamente de mostrar los datos y capturar los eventos del usuario. No contienen lógica de negocio.
    *   **ViewModels**: Almacenan y gestionan los datos relacionados con la UI. Sobreviven a cambios de configuración (como rotaciones) y exponen los datos a las Vistas a través de **LiveData**.
    *   **Repositorios**: Actúan como la única fuente de verdad (`Single Source of Truth`). Abstraen el origen de los datos (base de datos local, servicios web, etc.) del resto de la aplicación.

*   **Programación Dirigida por Eventos**: La interacción del usuario se gestiona a través de *listeners* (ej. `setOnClickListener`). Las acciones del usuario, como hacer clic en un botón, desencadenan eventos que la aplicación procesa para actualizar el estado o navegar entre pantallas. Esta es la base de la interactividad en la app.

*   **Componente de Navegación (Navigation Component)**: Gestiona todo el flujo de navegación entre los fragmentos de la aplicación. Define las acciones de navegación y maneja la pila de retroceso de forma visual y centralizada en el gráfico de navegación (`navigation/nav_graph.xml`).

*   **Persistencia de Datos**: 
    *   **Firebase Authentication**: Se utiliza como servicio de backend para gestionar la autenticación de usuarios (registro e inicio de sesión con correo y contraseña).
    *   **Room**: Para la persistencia de datos locales (carrito de compras y historial de pedidos), se utiliza la biblioteca Room, una capa de abstracción sobre SQLite que simplifica las operaciones con la base de datos.

*   **Tareas Asíncronas y Corrutinas**: Las operaciones que no deben bloquear el hilo principal, como las llamadas a la base de datos (Room) o a servicios de red (Firebase), se ejecutan de forma asíncrona. Los repositorios y ViewModels gestionan esto para mantener la UI siempre fluida.

## Objetivos de Desarrollo Sostenible (ODS)

Aunque la aplicación es un marketplace genérico, su base tecnológica podría adaptarse para abordar ciertos ODS. Por ejemplo:

*   **ODS 8 (Trabajo Decente y Crecimiento Económico)**: Podría servir como plataforma para que pequeños productores locales vendan sus productos, promoviendo el comercio justo.
*   **ODS 12 (Producción y Consumo Responsables)**: Podría especializarse en la venta de productos de segunda mano, reciclados o de origen sostenible.

## Cómo Ejecutar el Proyecto

Para compilar y ejecutar este proyecto en tu entorno local, sigue estos pasos:

1.  **Clonar el repositorio**.
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    ```
2.  **Abrir en Android Studio**.
    *   Selecciona "Open an existing Android Studio project" y elige la carpeta del proyecto que acabas de clonar.
3.  **Configurar Firebase**.
    *   Este proyecto necesita un archivo de configuración de Firebase para que la autenticación funcione.
    *   Ve a la [Consola de Firebase](https://console.firebase.google.com/) y crea un nuevo proyecto.
    *   Dentro del proyecto, añade una aplicación Android. Utiliza `com.example.marketplace` como nombre de paquete.
    *   Descarga el archivo `google-services.json` que se genera y cópialo en el directorio `app/` de tu proyecto en Android Studio.
    *   En la sección "Authentication" de la consola, ve a la pestaña "Sign-in method" y habilita el proveedor "Correo electrónico/Contraseña".
4.  **Construir y Ejecutar**.
    *   Espera a que Gradle sincronice las dependencias.
    *   Elige un dispositivo o emulador y pulsa el botón "Run" (▶).

¡Listo! La aplicación se instalará y podrás ver la pantalla de inicio de sesión.
