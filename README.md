# 🛒 Marketplace App - Android E-Commerce Application

<div align="center">

**Aplicación de Marketplace para Android desarrollada con arquitectura MVVM y tecnologías modernas**

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Firebase](https://img.shields.io/badge/Firebase-Authentication-orange.svg)](https://firebase.google.com/)

</div>

---

## 📋 Tabla de Contenidos

- [Descripción General](#-descripción-general)
- [Características Principales](#-características-principales)
- [Arquitectura y Tecnologías](#-arquitectura-y-tecnologías)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación y Configuración](#-instalación-y-configuración)
- [Guía de Uso](#-guía-de-uso)
- [Componentes del Sistema](#-componentes-del-sistema)
- [Flujo de Navegación](#-flujo-de-navegación)
- [Base de Datos](#-base-de-datos)
- [Compilación y Ejecución](#-compilación-y-ejecución)
- [Pruebas](#-pruebas)
- [Objetivos de Desarrollo Sostenible](#-objetivos-de-desarrollo-sostenible)
- [Solución de Problemas](#-solución-de-problemas)
- [Contribuir](#-contribuir)
- [Autores](#-autores)
- [Licencia](#-licencia)

---

## 🌟 Descripción General

**Marketplace App** es una aplicación Android completa de comercio electrónico desarrollada como proyecto educativo para la asignatura **"Programación Dirigida por Eventos"**. 

### ¿Qué es este proyecto?

Esta aplicación es un **marketplace navideño funcional** que permite a los usuarios realizar todas las operaciones típicas de una tienda online:

- 🔐 **Registrarse y autenticarse** de forma segura usando Firebase Authentication
- 📱 **Explorar un catálogo** de productos navideños con información detallada
- 🛍️ **Agregar productos al carrito** con cantidades personalizables
- 💳 **Realizar pedidos** que se guardan en un historial persistente
- 👤 **Gestionar su sesión** con login, logout y persistencia de datos

### ¿Por qué es importante este proyecto?

Este proyecto NO es solo una app funcional, es una **demostración práctica de arquitectura de software profesional**:

1. **Aplicación Real de Conceptos Teóricos**: Los patrones de diseño y principios de programación que se estudian en clase están implementados aquí de forma práctica.

2. **Código Escalable y Mantenible**: La arquitectura MVVM permite que la app crezca sin volverse un "código espagueti". Puedes agregar nuevas funcionalidades sin romper las existentes.

3. **Preparación Profesional**: Las técnicas usadas aquí (MVVM, Repository Pattern, Dependency Injection manual) son las mismas que se usan en empresas como Google, Amazon o startups tecnológicas.

4. **Fundamentos Transferibles**: Aunque está en Android, los conceptos (separación de capas, programación reactiva, persistencia) aplican a iOS, Web, Backend.

### Conceptos Fundamentales Demostrados

- **Arquitectura MVVM** (Model-View-ViewModel): Separación de responsabilidades en 3 capas
- **Patrón Single-Activity**: Una sola Activity que hospeda múltiples Fragments, siguiendo las mejores prácticas modernas de Android
- **Programación Dirigida por Eventos**: La app reacciona a acciones del usuario (clics, entradas de texto) en lugar de ejecutar código secuencial
- **Persistencia Híbrida**: 
  - Local (Room Database) para datos del usuario (carrito, pedidos)
  - Remota (Firebase) para autenticación centralizada
- **Navegación declarativa**: Flujos de navegación definidos visualmente en XML, no en código disperso
- **Programación Asíncrona**: Operaciones pesadas en hilos background para mantener la UI fluida
- **Observables Lifecycle-Aware**: LiveData que respeta el ciclo de vida de Android, evitando memory leaks

---

## ✨ Características Principales

### 🔒 Autenticación de Usuarios
- **Registro de nuevos usuarios** con validación de email y contraseña
- **Inicio de sesión** seguro mediante Firebase Authentication
- **Cierre de sesión** con limpieza de estado
- Validación de formularios en tiempo real

### 🛍️ Catálogo de Productos
- **Listado de productos** con temática navideña
- **Vista de detalle** con información completa de cada producto
- Diseño responsive con RecyclerView
- Interfaz intuitiva y atractiva

### 🛒 Gestión del Carrito
- **Agregar productos al carrito** con cantidades personalizables
- **Modificar cantidades** de productos existentes
- **Ver resumen del carrito** con cálculo automático de totales
- **Persistencia local** mediante Room Database
- **Vaciar carrito** al finalizar compra

### 📦 Historial de Pedidos
- **Registro automático** de pedidos realizados
- **Consulta del historial** con fecha y detalles
- Almacenamiento persistente en base de datos local
- Visualización ordenada por fecha

### 🎨 Interfaz de Usuario
- Diseño Material Design 3
- Tema adaptable (Light/Dark mode)
- ViewBinding para acceso seguro a las vistas
- Animaciones y transiciones fluidas

---

## 🏗️ Arquitectura y Tecnologías

### Patrón Arquitectónico: MVVM

La aplicación sigue el patrón **MVVM (Model-View-ViewModel)**, que proporciona una clara separación de responsabilidades.

#### ¿Por qué MVVM?

MVVM es el patrón recomendado por Google para aplicaciones Android modernas porque:

1. **Separación de Responsabilidades**: Cada capa tiene un propósito específico y bien definido, lo que hace el código más organizado y fácil de mantener.

2. **Testabilidad**: La lógica de negocio en ViewModels y Repositories puede probarse sin necesidad de componentes Android (Activities/Fragments), permitiendo tests unitarios más rápidos y confiables.

3. **Supervivencia a Cambios de Configuración**: Los ViewModels sobreviven a rotaciones de pantalla y otros cambios de configuración, evitando pérdida de datos y mejorando la experiencia del usuario.

4. **Código Reutilizable**: Los Repositories y ViewModels pueden compartirse entre diferentes vistas, reduciendo duplicación de código.

5. **Mantenibilidad**: Cuando necesitas modificar la interfaz, solo cambias la Vista. Si cambias la fuente de datos (de local a remota), solo modificas el Repository, sin tocar las demás capas.

#### Flujo de Datos en MVVM

```
Usuario interactúa con UI (View)
         ↓
View notifica evento al ViewModel
         ↓
ViewModel procesa la lógica de negocio
         ↓
ViewModel solicita datos al Repository
         ↓
Repository obtiene datos (Room/Firebase)
         ↓
Repository devuelve datos al ViewModel
         ↓
ViewModel expone datos como LiveData
         ↓
View observa LiveData y actualiza UI
```

#### Diagrama de Capas

```
┌─────────────────────────────────────────────────────────┐
│                        VIEW LAYER                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │  Fragments   │  │   Activity   │  │   Adapters   │  │
│  │   (UI/UX)    │  │  (Host)      │  │ (RecyclerV.) │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
└─────────────────────────────────────────────────────────┘
                           ↕ (LiveData/Observers)
┌─────────────────────────────────────────────────────────┐
│                     VIEWMODEL LAYER                      │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │CartViewModel │  │OrderViewModel│  │   (Logic)    │  │
│  │ (UI State)   │  │  (UI State)  │  │              │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
└─────────────────────────────────────────────────────────┘
                           ↕ (Data Requests)
┌─────────────────────────────────────────────────────────┐
│                    REPOSITORY LAYER                      │
│  ┌──────────────┐  ┌──────────────┐                    │
│  │CartRepository│  │OrderReposit. │  (Single Source    │
│  │ (Data Logic) │  │ (Data Logic) │   of Truth)        │
│  └──────────────┘  └──────────────┘                    │
└─────────────────────────────────────────────────────────┘
                           ↕ (Data Operations)
┌─────────────────────────────────────────────────────────┐
│                      DATA SOURCES                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │Room Database │  │  Firebase    │  │   Models     │  │
│  │  (Local)     │  │   (Auth)     │  │   (Entities) │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
└─────────────────────────────────────────────────────────┘
```

#### **View (Vista)** - La Interfaz con el Usuario

- **Responsabilidad**: Mostrar datos y capturar eventos del usuario
- **Componentes**: Fragments, Activity, Adapters
- **Característica**: Sin lógica de negocio, solo renderizado y eventos

**¿Qué hace?** La Vista es lo que el usuario ve y con lo que interactúa. En esta app, cada pantalla (login, catálogo, carrito) es un Fragment. Cuando el usuario hace clic en un botón o escribe en un campo, la Vista captura ese evento y lo comunica al ViewModel.

**Ejemplo práctico**: Cuando haces clic en "Agregar al Carrito", el Fragment (`ProductDetailFragment`) captura ese evento y llama a `cartViewModel.addToCart(item, quantity)`. La Vista NO calcula el total ni guarda en la base de datos, solo comunica la acción.

#### **ViewModel** - El Cerebro de la Pantalla

- **Responsabilidad**: Gestionar estado de la UI y lógica de presentación
- **Características**:
  - Sobrevive a cambios de configuración (rotaciones)
  - Expone datos mediante LiveData
  - Procesa eventos del usuario
  - Se comunica con Repositories

**¿Qué hace?** El ViewModel es el intermediario entre la Vista y los datos. Contiene toda la lógica de presentación: qué mostrar, cuándo mostrar, cómo procesar las acciones del usuario. Cuando rotas el teléfono, el Fragment se destruye y recrea, pero el ViewModel permanece, manteniendo el estado.

**Ejemplo práctico**: El `CartViewModel` mantiene la lista de items del carrito como `LiveData<List<CartItem>>`. Cuando agregas un producto, el ViewModel le dice al Repository que lo guarde, y automáticamente todos los Fragments que observan ese LiveData reciben la actualización.

**¿Por qué LiveData?** LiveData es "consciente del ciclo de vida" (lifecycle-aware), lo que significa que solo notifica cambios cuando el Fragment está visible. Esto previene crashes y memory leaks.

#### **Repository** - El Gestor de Datos

- **Responsabilidad**: Única fuente de verdad para los datos
- **Funciones**:
  - Abstrae el origen de datos
  - Coordina entre fuentes locales y remotas
  - Ejecuta operaciones de forma asíncrona

**¿Qué hace?** El Repository decide DE DÓNDE vienen los datos. ¿Los tengo guardados localmente? ¿Necesito descargarlos de internet? ¿Están desactualizados? El ViewModel no necesita saber estos detalles, solo pide "dame los productos del carrito" y el Repository se encarga.

**Ejemplo práctico**: `CartRepository` gestiona los datos del carrito. Cuando pides todos los items, el Repository consulta la base de datos Room. Cuando agregas un item, verifica si ya existe (para actualizar la cantidad) o si es nuevo (para insertarlo). Todo esto ocurre en un hilo separado para no bloquear la UI.

**Principio Single Source of Truth**: Siempre hay UNA sola fuente autoritativa de datos. En nuestra app, el carrito "vive" en Room, no en múltiples variables dispersas. Esto evita inconsistencias.

#### **Data Sources** - Las Fuentes de Información

- **Room Database**: Persistencia local (SQLite)
- **Firebase Authentication**: Autenticación remota
- **Models**: Definición de estructuras de datos

**¿Qué hace cada fuente?**

**Room Database**: Es una base de datos SQLite local en el dispositivo. Usamos Room (no SQLite directamente) porque:
- Convierte automáticamente entre objetos Java y tablas SQL
- Verifica las consultas en tiempo de compilación (detecta errores antes de ejecutar)
- Se integra perfectamente con LiveData

**Firebase Authentication**: Servicio en la nube de Google que gestiona usuarios. ¿Por qué usarlo?
- Seguridad robusta (hashing de contraseñas, protección contra ataques)
- No necesitamos crear nuestro propio sistema de autenticación (complejo y arriesgado)
- Escalable: funciona igual con 10 o 10 millones de usuarios

**Models (Entidades)**: Son clases Java que representan conceptos del mundo real: Producto, Item del Carrito, Pedido. Room las convierte en tablas, Firebase las serializa a JSON.

### 🔧 Stack Tecnológico

#### **Lenguaje y Framework**

| Tecnología | Versión | ¿Por qué se eligió? |
|-----------|---------|---------------------|
| **Java 11** | JDK 11+ | Lenguaje maduro y estable con excelente soporte en Android. Aunque Kotlin es el lenguaje oficial recomendado por Google, Java sigue siendo ampliamente usado y es fundamental para entender Android. |
| **Android SDK** | API 24+ | Android 7.0 Nougat (2016) cubre el 95%+ de dispositivos en uso, balanceando compatibilidad con acceso a APIs modernas. |
| **Target SDK** | API 36 | Objetivo a las últimas APIs de Android para aprovechar mejoras de seguridad y rendimiento más recientes. |

**Nota sobre Java vs Kotlin**: Este proyecto usa Java por razones pedagógicas (amplia documentación, sintaxis familiar para estudiantes de programación general). En producción, muchos proyectos nuevos prefieren Kotlin por su sintaxis concisa y null-safety.

#### **Jetpack Components** - La Suite Moderna de Android

Android Jetpack es una colección de bibliotecas que implementan mejores prácticas, reducen código boilerplate y funcionan consistentemente en diferentes versiones de Android.

| Componente | Versión | ¿Qué hace? | ¿Por qué NO usar la alternativa antigua? |
|-----------|---------|------------|------------------------------------------|
| **ViewBinding** | Incluido | Genera automáticamente clases que referencian vistas del XML. `binding.textView` en vez de `findViewById(R.id.textView)` | **vs findViewById()**: ViewBinding es type-safe (errores en compilación, no runtime) y null-safe (evita NullPointerExceptions). |
| **Navigation Component** | 2.7.6 | Gestiona navegación entre pantallas con un gráfico visual. Maneja backstack, argumentos tipados, deep links. | **vs Manual FragmentTransactions**: Reduce código en 70%, elimina errores comunes (backstack mal manejado), permite visualizar flujos. |
| **LiveData** | Incluido | Observable que respeta el ciclo de vida. Solo notifica cuando el Fragment/Activity está activo. | **vs EventBus/RxJava**: Más simple, integrado nativamente, previene memory leaks automáticamente. |
| **Room** | 2.6.1 | ORM (Object-Relational Mapping) sobre SQLite. Convierte objetos Java ↔ tablas SQL. | **vs SQLite directo**: Room verifica SQL en compilación, reduce bugs, 10x menos código boilerplate. |
| **ConstraintLayout** | Latest | Layout flexible que reemplaza jerarquías complejas de LinearLayout/RelativeLayout. | **vs Layouts anidados**: Mejor rendimiento (jerarquía plana), más expresivo, editor visual potente. |

#### **Firebase Services** - Backend as a Service

| Servicio | Versión | ¿Qué proporciona? |
|---------|---------|-------------------|
| **Firebase BoM** | 32.7.0 | Bill of Materials: Gestiona versiones compatibles de todas las bibliotecas Firebase automáticamente. |
| **Firebase Authentication** | (de BoM) | Sistema completo de autenticación: registro, login, recuperación de contraseña, tokens seguros. |

**¿Por qué Firebase y no un backend propio?**
1. **Desarrollo rápido**: Autenticación funcional en minutos, no semanas
2. **Seguridad profesional**: Google mantiene la seguridad actualizada
3. **Escalabilidad automática**: De 10 a 10 millones de usuarios sin cambios
4. **Gratuito para aprendizaje**: Tier gratuito generoso
5. **Enfoque en aprendizaje**: Permite concentrarse en arquitectura Android, no en backend

**Limitación importante**: Firebase es cerrado (vendor lock-in). En producción, proyectos grandes a veces prefieren backends propios para control total.

#### **Build System** - Cómo se Ensambla la App

| Herramienta | Versión | Función |
|-------------|---------|---------|
| **Gradle** | 8.x | Sistema de construcción que compila código, gestiona dependencias, ejecuta tests. |
| **Android Gradle Plugin** | 8.x | Plugin que extiende Gradle con capacidades Android (APK building, ProGuard, etc.). |
| **Minimum SDK** | 24 | Android 7.0+: versión mínima compatible |
| **Compile SDK** | 36 | Versión contra la que compilamos (acceso a últimas APIs) |
| **Target SDK** | 36 | Versión para la que optimizamos (Google Play lo usa para permisos y comportamiento) |

**¿Por qué Gradle y no otro sistema?**
- Es el estándar oficial de Android (Maven quedó obsoleto)
- Altamente configurable (builds diferentes para debug/release)
- Gestión declarativa de dependencias (versiones, repositorios)
- Incremental builds: solo recompila lo que cambió
- **Compile SDK**: 36

#### **Testing**
- JUnit para tests unitarios
- Espresso para tests de UI
- AndroidX Test para instrumentación

---

## 📁 Estructura del Proyecto

```
Marketplace_Javi-Mario/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/marketplace/
│   │   │   │   │
│   │   │   │   ├── MainActivity.java          # Activity principal (host)
│   │   │   │   │
│   │   │   │   ├── ui/                        # Capa de presentación
│   │   │   │   │   ├── auth/                  # Autenticación
│   │   │   │   │   │   ├── LoginFragment.java
│   │   │   │   │   │   └── RegisterFragment.java
│   │   │   │   │   │
│   │   │   │   │   ├── products/              # Productos
│   │   │   │   │   │   ├── ProductListFragment.java
│   │   │   │   │   │   ├── ProductDetailFragment.java
│   │   │   │   │   │   └── ProductAdapter.java
│   │   │   │   │   │
│   │   │   │   │   ├── cart/                  # Carrito
│   │   │   │   │   │   ├── CartFragment.java
│   │   │   │   │   │   └── CartAdapter.java
│   │   │   │   │   │
│   │   │   │   │   ├── orders/                # Pedidos
│   │   │   │   │   │   ├── OrderListFragment.java
│   │   │   │   │   │   └── OrderAdapter.java
│   │   │   │   │   │
│   │   │   │   │   └── viewmodels/            # ViewModels
│   │   │   │   │       ├── CartViewModel.java
│   │   │   │   │       └── OrderViewModel.java
│   │   │   │   │
│   │   │   │   ├── data/                      # Capa de datos
│   │   │   │   │   ├── CartRepository.java
│   │   │   │   │   ├── OrderRepository.java
│   │   │   │   │   │
│   │   │   │   │   └── local/                 # Base de datos local
│   │   │   │   │       ├── AppDatabase.java
│   │   │   │   │       ├── CartItem.java
│   │   │   │   │       ├── CartDao.java
│   │   │   │   │       ├── Order.java
│   │   │   │   │       └── OrderDao.java
│   │   │   │   │
│   │   │   │   └── models/                    # Modelos de datos
│   │   │   │       └── Product.java
│   │   │   │
│   │   │   ├── res/                           # Recursos
│   │   │   │   ├── layout/                    # Layouts XML
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── fragment_login.xml
│   │   │   │   │   ├── fragment_register.xml
│   │   │   │   │   ├── fragment_product_list.xml
│   │   │   │   │   ├── fragment_product_detail.xml
│   │   │   │   │   ├── fragment_cart.xml
│   │   │   │   │   ├── fragment_order_list.xml
│   │   │   │   │   ├── item_product.xml
│   │   │   │   │   ├── item_cart.xml
│   │   │   │   │   └── item_order.xml
│   │   │   │   │
│   │   │   │   ├── navigation/                # Gráfico de navegación
│   │   │   │   │   └── nav_graph.xml
│   │   │   │   │
│   │   │   │   ├── values/                    # Strings, colores, temas
│   │   │   │   ├── drawable/                  # Recursos gráficos
│   │   │   │   └── mipmap/                    # Iconos de app
│   │   │   │
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   ├── androidTest/                       # Tests instrumentados
│   │   └── test/                              # Tests unitarios
│   │
│   ├── build.gradle                           # Configuración del módulo
│   ├── proguard-rules.pro
│   └── google-services.json                   # Config Firebase (NO incluir en Git)
│
├── gradle/                                    # Wrapper de Gradle
├── build.gradle                               # Configuración raíz
├── settings.gradle                            # Configuración del proyecto
├── gradle.properties
├── gradlew                                    # Script Gradle (Unix)
├── gradlew.bat                                # Script Gradle (Windows)
├── .gitignore
├── LICENSE                                    # Licencia Apache 2.0
└── README.md                                  # Este archivo
```

### 📦 Organización por Capas

#### **Capa de Presentación (ui/)**
Contiene todos los componentes visuales y la lógica de presentación:
- **Fragments**: Pantallas de la aplicación
- **Adapters**: Renderizado de listas (RecyclerView)
- **ViewModels**: Gestión de estado de UI

#### **Capa de Datos (data/)**
Maneja el acceso y persistencia de datos:
- **Repositories**: Abstraen las fuentes de datos
- **Local**: Base de datos Room (DAOs y Entities)
- **Remote**: Integraciones con servicios externos (Firebase)

#### **Modelos (models/)**
Clases de dominio que representan entidades del negocio

---

## 📋 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

### Software Necesario

1. **Java Development Kit (JDK)**
   - Versión: JDK 11 o superior
   - Descarga: [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) o [OpenJDK](https://openjdk.org/)

2. **Android Studio**
   - Versión: Android Studio Hedgehog (2023.1.1) o superior
   - Descarga: [developer.android.com/studio](https://developer.android.com/studio)
   - Incluye:
     - Android SDK
     - Android SDK Platform-Tools
     - Android Emulator

3. **Git**
   - Para clonar el repositorio
   - Descarga: [git-scm.com](https://git-scm.com/)

### Cuentas Requeridas

4. **Cuenta de Google/Firebase**
   - Necesaria para configurar Firebase Authentication
   - Crear en: [firebase.google.com](https://firebase.google.com/)

### Configuración del Sistema

5. **Configuración de Variables de Entorno**
   ```bash
   # Agregar JAVA_HOME
   export JAVA_HOME=/path/to/jdk
   export PATH=$JAVA_HOME/bin:$PATH
   
   # Verificar instalación
   java -version
   javac -version
   ```

6. **Dispositivo Android o Emulador**
   - **Opción 1**: Dispositivo físico con:
     - Android 7.0 (API 24) o superior
     - USB Debugging habilitado
   
   - **Opción 2**: Emulador de Android:
     - Configurado desde Android Studio AVD Manager
     - Recomendado: Pixel 5 con Android 11 o superior

---

## 🚀 Instalación y Configuración

Sigue estos pasos detallados para configurar el proyecto en tu máquina local:

### 1️⃣ Clonar el Repositorio

```bash
# Clonar usando HTTPS
git clone https://github.com/UAX-2025-26/Marketplace_Javi-Mario.git

# O usando SSH
git clone git@github.com:UAX-2025-26/Marketplace_Javi-Mario.git

# Navegar al directorio del proyecto
cd Marketplace_Javi-Mario
```

### 2️⃣ Abrir en Android Studio

1. Abre **Android Studio**
2. Selecciona `File > Open`
3. Navega hasta la carpeta `Marketplace_Javi-Mario`
4. Haz clic en **OK**
5. Espera a que Android Studio indexe el proyecto y descargue las dependencias

### 3️⃣ Configurar Firebase Authentication

Este es el paso **más importante** para que la autenticación funcione:

#### A. Crear Proyecto en Firebase

1. Accede a [Firebase Console](https://console.firebase.google.com/)
2. Haz clic en **"Agregar proyecto"** o **"Add project"**
3. Nombre del proyecto: `marketplace-app` (o el que prefieras)
4. Desactiva Google Analytics (opcional para este proyecto)
5. Haz clic en **"Crear proyecto"**

#### B. Registrar App Android

1. En la página principal del proyecto, haz clic en el ícono de **Android**
2. Configura los siguientes campos:
   ```
   Package name: com.example.marketplace
   App nickname: Marketplace App (opcional)
   Debug signing certificate SHA-1: (opcional por ahora)
   ```
3. Haz clic en **"Registrar app"**

#### C. Descargar google-services.json

1. Descarga el archivo `google-services.json` generado
2. Copia el archivo a la ruta:
   ```
   Marketplace_Javi-Mario/app/google-services.json
   ```
3. **IMPORTANTE**: Este archivo NO debe subirse a Git (ya está en .gitignore)

#### D. Habilitar Email/Password Authentication

1. En Firebase Console, ve a **"Authentication"** en el menú lateral
2. Haz clic en **"Get started"** si es la primera vez
3. Ve a la pestaña **"Sign-in method"**
4. Haz clic en **"Email/Password"**
5. **Activa** el switch en "Email/Password"
6. Haz clic en **"Guardar"**

### 4️⃣ Sincronizar Dependencias de Gradle

1. En Android Studio, haz clic en **"Sync Now"** en la barra superior
2. O selecciona `File > Sync Project with Gradle Files`
3. Espera a que todas las dependencias se descarguen (puede tardar varios minutos la primera vez)

### 5️⃣ Verificar Configuración

Comprueba que todo esté correctamente configurado:

```bash
# Desde la raíz del proyecto, ejecuta:
./gradlew clean build

# En Windows:
gradlew.bat clean build
```

Si la compilación es exitosa, estás listo para ejecutar la app.

---

## 📖 Guía de Uso

### Flujo Completo de Usuario

#### 1. **Pantalla de Inicio de Sesión** (LoginFragment)

Al abrir la aplicación por primera vez:

```
┌─────────────────────────────────┐
│     🛒 Marketplace App          │
│                                 │
│  Email:  ___________________    │
│                                 │
│  Password:  ________________    │
│                                 │
│      [ Iniciar Sesión ]         │
│                                 │
│  ¿No tienes cuenta? Regístrate  │
└─────────────────────────────────┘
```

**Acciones disponibles:**
- Ingresar email y contraseña de usuario existente
- Clic en "Iniciar Sesión" para autenticarse
- Clic en "Regístrate" para crear una nueva cuenta

**Validaciones:**
- Email debe ser válido (formato correcto)
- Password no puede estar vacío
- Firebase valida las credenciales

#### 2. **Pantalla de Registro** (RegisterFragment)

Para nuevos usuarios:

```
┌─────────────────────────────────┐
│       Crear Nueva Cuenta        │
│                                 │
│  Email:  ___________________    │
│                                 │
│  Password:  ________________    │
│                                 │
│  Confirmar:  _______________    │
│                                 │
│        [ Registrarse ]          │
└─────────────────────────────────┘
```

**Proceso:**
1. Ingresar email válido
2. Crear contraseña (mínimo 6 caracteres)
3. Confirmar contraseña
4. Clic en "Registrarse"
5. Firebase crea la cuenta
6. Redirección automática al catálogo

#### 3. **Catálogo de Productos** (ProductListFragment)

Pantalla principal después del login:

```
┌─────────────────────────────────┐
│  Catálogo Navideño    🛒 ⚙️ 🚪  │
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │ 🎄 Árbol de Navidad         │ │
│ │ Hermoso árbol artificial    │ │
│ │ Precio: 49.99€              │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ ⭐ Estrella para Árbol      │ │
│ │ Estrella dorada brillante   │ │
│ │ Precio: 12.99€              │ │
│ └─────────────────────────────┘ │
│ ... más productos ...           │
└─────────────────────────────────┘
```

**Productos disponibles:**
1. Árbol de Navidad - 49.99€
2. Estrella para Árbol - 12.99€
3. Luces LED - 19.99€
4. Bolas de Navidad - 15.99€
5. Corona Navideña - 24.99€
6. Muñeco de Nieve - 29.99€
7. Calcetín Navideño - 8.99€
8. Velas Aromáticas - 9.99€

**Opciones del menú (toolbar):**
- 🛒 **Ver Carrito**: Acceso al carrito de compras
- 📦 **Mis Pedidos**: Historial de compras
- 🚪 **Cerrar Sesión**: Volver al login

#### 4. **Detalle del Producto** (ProductDetailFragment)

Al hacer clic en un producto:

```
┌─────────────────────────────────┐
│  ← Detalle del Producto         │
├─────────────────────────────────┤
│                                 │
│     🎄 Árbol de Navidad         │
│                                 │
│  Hermoso árbol de Navidad       │
│  artificial de 180cm de altura  │
│  con ramas densas y base        │
│  resistente. Perfecto para      │
│  decorar tu hogar.              │
│                                 │
│  Precio: 49.99€                 │
│                                 │
│  Cantidad: [ - ] 1 [ + ]        │
│                                 │
│    [ Agregar al Carrito ]       │
│                                 │
└─────────────────────────────────┘
```

**Funcionalidades:**
- Ver descripción completa del producto
- Ajustar cantidad con botones +/-
- Agregar al carrito (min: 1, max: 99)
- Confirmación visual al agregar
- Navegación hacia atrás

**¿Qué sucede internamente cuando haces clic en "Agregar al Carrito"?**

1. **Fragment captura el evento**: El `setOnClickListener` del botón se activa
2. **Fragment crea un CartItem**: Con el productId, nombre, precio y cantidad seleccionada
3. **Fragment llama al ViewModel**: `cartViewModel.addToCart(cartItem, quantity)`
4. **ViewModel delega al Repository**: `cartRepository.insertOrUpdate(cartItem, quantity)`
5. **Repository ejecuta en background thread**: Usa `ExecutorService` para no bloquear UI
6. **Repository consulta si el item existe**: `cartDao.getCartItemById(productId)`
   - Si existe: Actualiza quantity (suma la nueva cantidad)
   - Si no existe: Inserta nuevo CartItem
7. **Room notifica cambio**: Como `getAllCartItems()` retorna LiveData
8. **Todos los observadores reciben actualización**: CartFragment ve el cambio automáticamente
9. **UI se actualiza**: Sin necesidad de "refrescar" manualmente

Este flujo ejemplifica perfectamente **programación reactiva** y **separación de responsabilidades**.

#### 5. **Carrito de Compras** (CartFragment)

Visualización de productos seleccionados:

```
┌─────────────────────────────────┐
│  ← Mi Carrito                   │
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │ Árbol de Navidad            │ │
│ │ Cant: 1    49.99€           │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ Luces LED                   │ │
│ │ Cant: 2    39.98€           │ │
│ └─────────────────────────────┘ │
│                                 │
│ Total: 89.97€                   │
│                                 │
│     [ Realizar Pedido ]         │
│                                 │
└─────────────────────────────────┘
```

**Características:**
- Lista de todos los items en el carrito
- Muestra cantidad y subtotal por producto
- Cálculo automático del total
- Botón para confirmar pedido
- Persistencia local (datos se mantienen al cerrar app)

**Al realizar pedido:**
1. Se crea un registro en el historial
2. Se vacía el carrito automáticamente
3. Mensaje de confirmación
4. Opción de ver pedidos realizados

#### 6. **Historial de Pedidos** (OrderListFragment)

Consulta de compras realizadas:

```
┌─────────────────────────────────┐
│  ← Mis Pedidos                  │
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │ Pedido #1234                │ │
│ │ 17/12/2024 13:30            │ │
│ │ Árbol de Navidad (1),       │ │
│ │ Luces LED (2)               │ │
│ │ Total: 89.97€               │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ Pedido #1235                │ │
│ │ 15/12/2024 10:15            │ │
│ │ Corona Navideña (1)         │ │
│ │ Total: 24.99€               │ │
│ └─────────────────────────────┘ │
└─────────────────────────────────┘
```

**Información mostrada:**
- ID del pedido (autoincremental)
- Fecha y hora de la compra
- Resumen de productos incluidos
- Precio total del pedido
- Ordenado por fecha descendente

---

## 🧩 Componentes del Sistema

### Componentes de UI (Fragments)

#### **LoginFragment**
```java
// Responsabilidades:
// - Capturar credenciales del usuario
// - Validar formato de email y contraseña
// - Comunicarse con Firebase Auth
// - Navegar a ProductListFragment si login exitoso
// - Manejar errores de autenticación

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FragmentLoginBinding binding;
    
    // Listener del botón login
    binding.buttonLogin.setOnClickListener(v -> {
        String email = binding.editTextEmail.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString().trim();
        loginUser(email, password);
    });
}
```

#### **ProductListFragment**
```java
// Responsabilidades:
// - Mostrar lista de productos en RecyclerView
// - Implementar menú de opciones (carrito, pedidos, logout)
// - Navegar al detalle de producto al hacer clic
// - Gestionar estado de autenticación

public class ProductListFragment extends Fragment 
    implements ProductAdapter.OnProductClickListener {
    
    // Configuración del RecyclerView
    productAdapter = new ProductAdapter(products, this);
    binding.recyclerViewProducts.setAdapter(productAdapter);
    binding.recyclerViewProducts.setLayoutManager(
        new LinearLayoutManager(getContext())
    );
}
```

#### **CartFragment**
```java
// Responsabilidades:
// - Observar LiveData del carrito (CartViewModel)
// - Calcular total de la compra
// - Permitir realizar pedido
// - Mostrar lista de items con RecyclerView

public class CartFragment extends Fragment {
    private CartViewModel cartViewModel;
    
    // Observer del carrito
    cartViewModel.getAllCartItems().observe(getViewLifecycleOwner(), items -> {
        cartAdapter.setCartItems(items);
        updateTotal(items);
    });
}
```

### ViewModels

#### **CartViewModel**
```java
// Gestiona el estado del carrito de compras
// - Expone LiveData<List<CartItem>> para observar cambios
// - Interactúa con CartRepository para operaciones CRUD
// - Sobrevive a cambios de configuración

public class CartViewModel extends AndroidViewModel {
    private CartRepository repository;
    private LiveData<List<CartItem>> allCartItems;
    
    public void addToCart(CartItem item, int quantity) {
        repository.insertOrUpdate(item, quantity);
    }
    
    public void clearCart() {
        repository.clearCart();
    }
}
```

#### **OrderViewModel**
```java
// Gestiona el historial de pedidos
// - Expone LiveData<List<Order>> para listar pedidos
// - Crea nuevos pedidos desde el contenido del carrito
// - Calcula y almacena resúmenes de pedidos

public class OrderViewModel extends AndroidViewModel {
    private OrderRepository repository;
    
    public void createOrder(List<CartItem> items) {
        String summary = buildSummary(items);
        double total = calculateTotal(items);
        Order order = new Order(System.currentTimeMillis(), summary, total);
        repository.insert(order);
    }
}
```

### Repositories

#### **CartRepository**
```java
// Abstrae el acceso a la base de datos del carrito
// - Proporciona métodos para operaciones CRUD
// - Ejecuta operaciones en background thread
// - Retorna LiveData para observación reactiva

public class CartRepository {
    private CartDao cartDao;
    
    public void insertOrUpdate(CartItem item, int quantity) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            CartItem existing = cartDao.getCartItemById(item.getProductId());
            if (existing != null) {
                existing.setQuantity(existing.getQuantity() + quantity);
                cartDao.update(existing);
            } else {
                cartDao.insert(item);
            }
        });
    }
}
```

### Adapters

#### **ProductAdapter**
```java
// Renderiza la lista de productos en RecyclerView
// - Implementa patrón ViewHolder
// - Gestiona eventos de clic en items
// - Utiliza ViewBinding para acceso a vistas

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;
    private OnProductClickListener listener;
    
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }
}
```

---

## 🧭 Flujo de Navegación

El Navigation Component gestiona toda la navegación mediante un gráfico declarativo:

### Gráfico de Navegación (nav_graph.xml)

```
┌────────────────────────────────────────────────────────────┐
│                    Navigation Graph                         │
└────────────────────────────────────────────────────────────┘
                              
    START
      │
      ▼
┌─────────────────┐
│  LoginFragment  │ ◄─────────────┐ (Acción global)
└─────────────────┘                │
      │   │                        │
      │   └──────────────►┌─────────────────┐
      │                   │RegisterFragment │
      │                   └─────────────────┘
      │ (Si login exitoso)
      ▼
┌──────────────────────┐
│ProductListFragment   │
└──────────────────────┘
      │    │    │
      │    │    └──────►┌───────────────────┐
      │    │            │OrderListFragment  │
      │    │            └───────────────────┘
      │    │
      │    └────────────►┌─────────────────┐
      │                  │  CartFragment   │
      │                  └─────────────────┘
      │                           │
      │                           │ (Realizar pedido)
      │                           ▼
      │                  ┌─────────────────┐
      │                  │OrderListFragment│
      │                  └─────────────────┘
      ▼
┌────────────────────────┐
│ProductDetailFragment   │
└────────────────────────┘
      │ (Agregar)
      ▼
┌─────────────────┐
│  CartFragment   │
└─────────────────┘
```

### Acciones de Navegación

```xml
<!-- Desde Login a ProductList (limpia back stack) -->
<action
    android:id="@+id/action_loginFragment_to_productListFragment"
    app:destination="@id/productListFragment"
    app:popUpTo="@id/loginFragment"
    app:popUpToInclusive="true" />

<!-- Desde ProductList a ProductDetail (con argumentos) -->
<action
    android:id="@+id/action_productListFragment_to_productDetailFragment"
    app:destination="@id/productDetailFragment">
    <argument android:name="productId" app:argType="string" />
    <argument android:name="productName" app:argType="string" />
    <argument android:name="productDescription" app:argType="string" />
    <argument android:name="productPrice" app:argType="float" />
</action>

<!-- Acción global para volver al login (logout) -->
<action
    android:id="@+id/action_global_to_loginFragment"
    app:destination="@id/loginFragment"
    app:popUpTo="@id/nav_graph"
    app:popUpToInclusive="true" />
```

### Navegación Programática

```java
// Navegar con argumentos usando Safe Args
ProductDetailFragmentArgs args = new ProductDetailFragmentArgs.Builder()
    .setProductId(product.getId())
    .setProductName(product.getName())
    .setProductDescription(product.getDescription())
    .setProductPrice((float) product.getPrice())
    .build();
    
navController.navigate(
    R.id.action_productListFragment_to_productDetailFragment,
    args.toBundle()
);

// Navegar usando acción global
navController.navigate(R.id.action_global_to_loginFragment);

// Navegar hacia atrás
navController.navigateUp();
```

---

## 💾 Base de Datos

### ¿Por qué necesitamos una base de datos local?

En aplicaciones móviles, la base de datos local es **fundamental** por varias razones:

1. **Funcionamiento Offline**: El usuario puede usar la app sin conexión a internet. El carrito no desaparece si se cae la red.

2. **Rendimiento**: Leer de disco es ~100ms, leer de internet es ~500-2000ms. La diferencia se nota instantáneamente.

3. **Persistencia**: Los datos sobreviven al cierre de la app. No pierdes tu carrito al salir.

4. **Reducción de Costes**: Menos peticiones al servidor = menor factura de servicios cloud.

5. **Mejor UX**: Interacciones instantáneas, sin "spinners" de carga constantes.

### Esquema de Room Database

La aplicación utiliza **Room** para persistencia local con dos tablas principales.

#### ¿Por qué Room y no SQLite directamente?

Room es una capa de abstracción sobre SQLite que proporciona:

| Característica | SQLite Directo | Room |
|---------------|----------------|------|
| **Verificación de SQL** | Runtime (crashes si SQL incorrecto) | Compile-time (error antes de ejecutar) |
| **Conversión Objeto↔Tabla** | Manual (100+ líneas de código boilerplate) | Automática (anotaciones @Entity) |
| **Integración LiveData** | Compleja (manejar cursors manualmente) | Nativa (retornas `LiveData<List<T>>`) |
| **Migraciones** | Propensas a errores | Guiadas con helpers |
| **Threading** | Debes manejar manualmente | Advertencias automáticas si consultas en UI thread |

**Ejemplo real**: Sin Room, insertar un CartItem requiere ~30 líneas. Con Room, solo una anotación `@Insert` en el DAO.

#### **Tabla: cart_items** - El Carrito de Compras

**Propósito**: Almacenar los productos que el usuario ha agregado al carrito, con sus cantidades.

**Esquema SQL generado por Room:**
```sql
CREATE TABLE cart_items (
    productId TEXT PRIMARY KEY NOT NULL,  -- ID único del producto
    name TEXT NOT NULL,                    -- Nombre para mostrar
    price REAL NOT NULL,                   -- Precio unitario
    quantity INTEGER NOT NULL              -- Cantidad seleccionada
);
```

**¿Por qué estos campos?**
- `productId`: Clave primaria. Garantiza que cada producto aparece solo una vez. Si agregas el mismo producto dos veces, se actualiza la cantidad, no se duplica.
- `name` y `price`: Datos desnormalizados (copiados del modelo Product). ¿Por qué? Si el precio del producto cambia en el catálogo, el carrito mantiene el precio original (como un "congelamiento" del precio en el momento de agregarlo).
- `quantity`: Contador. Más eficiente que tener N filas del mismo producto.

**Entity en Java:**
```java
@Entity(tableName = "cart_items")  // Room crea la tabla con este nombre
public class CartItem {
    @PrimaryKey              // Marca la clave primaria
    @NonNull                 // No puede ser null (Room lo verifica)
    private String productId;
    
    private String name;
    private double price;
    private int quantity;
    // ... constructors, getters, setters
}
```

**DAO (Data Access Object)** - La Interfaz con la Base de Datos:
```java
@Dao  // Room genera la implementación automáticamente
public interface CartDao {
    // Retorna LiveData: se actualiza automáticamente cuando la DB cambia
    @Query("SELECT * FROM cart_items")
    LiveData<List<CartItem>> getAllCartItems();
    
    // Búsqueda por ID (retorna objeto simple, no LiveData, para operaciones síncronas)
    @Query("SELECT * FROM cart_items WHERE productId = :productId")
    CartItem getCartItemById(String productId);
    
    // Si el item existe (mismo productId), REEMPLAZA. Si no, INSERTA.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartItem cartItem);
    
    // Actualiza un item existente (basado en la primary key)
    @Update
    void update(CartItem cartItem);
    
    // Vacía todo el carrito (al finalizar compra)
    @Query("DELETE FROM cart_items")
    void clearCart();
}
```

**Nota sobre LiveData en el DAO**: `getAllCartItems()` retorna `LiveData<List<CartItem>>`. Esto significa que Room observa la tabla y notifica automáticamente cuando hay cambios (inserts, updates, deletes). El Fragment no necesita "refrescar" manualmente.

#### **Tabla: orders** - Historial de Pedidos

**Propósito**: Registrar cada pedido completado para que el usuario pueda consultar su historial.

**Esquema SQL:**
```sql
CREATE TABLE orders (
    id INTEGER PRIMARY KEY AUTOINCREMENT,  -- ID único autogenerado
    date INTEGER NOT NULL,                  -- Timestamp UNIX (milisegundos desde 1970)
    itemsSummary TEXT NOT NULL,            -- Resumen legible de items
    totalPrice REAL NOT NULL               -- Precio total del pedido
);
```

**Decisiones de diseño:**
- `id` con `AUTOINCREMENT`: Room genera IDs únicos automáticamente (1, 2, 3...). Útil para referencias futuras o actualizaciones.
- `date` como `INTEGER` (timestamp): Más eficiente que almacenar strings de fecha. Se puede convertir fácilmente a `Date`, `LocalDateTime`, o formatear como se necesite.
- `itemsSummary` como `TEXT`: Resumen desnormalizado. ¿Por qué no una tabla separada `order_items`? Para un historial de solo-lectura, es más simple y rápido consultar. En sistemas complejos sí usarías una relación.
- `totalPrice`: Pre-calculado. Evita recalcular sumando items cada vez que se muestra el historial.

**Entity en Java:**
```java
@Entity(tableName = "orders")
public class Order {
    @PrimaryKey(autoGenerate = true)  // Room incrementa automáticamente
    private int id;
    
    private long date;                 // Timestamp: System.currentTimeMillis()
    private String itemsSummary;       // "Árbol de Navidad (1), Luces LED (2)"
    private double totalPrice;         // 89.97
    // ... constructors, getters, setters
}
```

**DAO:**
```java
@Dao
public interface OrderDao {
    // Ordena por fecha descendente: pedidos más recientes primero
    @Query("SELECT * FROM orders ORDER BY date DESC")
    LiveData<List<Order>> getAllOrders();
    
    @Insert
    void insert(Order order);
    
    // Nota: No hay @Delete ni @Update porque los pedidos son inmutables
    // (no se editan ni eliminan una vez creados en esta versión de la app)
}
```

**¿Por qué no hay DELETE/UPDATE?** En e-commerce real, los pedidos son **inmutables** una vez creados (por auditoría, legal, contabilidad). Si el usuario quiere "cancelar", se crea un nuevo registro de cancelación, no se borra el pedido original.

### Configuración de Database - El Corazón de Room

```java
@Database(entities = {CartItem.class, Order.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CartDao cartDao();
    public abstract OrderDao orderDao();
    
    private static volatile AppDatabase INSTANCE;
    
    // Singleton pattern con Double-Check Locking
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        "marketplace_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return INSTANCE;
    }
    
    // ExecutorService para operaciones asíncronas
    public static final ExecutorService databaseWriteExecutor =
        Executors.newFixedThreadPool(4);
}
```

#### Explicación Detallada de la Configuración

**1. Anotación @Database:**
```java
@Database(
    entities = {CartItem.class, Order.class},  // Tablas a crear
    version = 2,                                // Versión del esquema
    exportSchema = false                        // No exportar esquema JSON
)
```
- `entities`: Lista de todas las clases `@Entity`. Room crea una tabla por cada una.
- `version`: Número de versión del esquema. Al cambiar el esquema (añadir columna, tabla, etc.), incrementas esto para triggear migración.
- `exportSchema = false`: Por defecto, Room exporta el esquema a JSON para tracking de cambios. Para desarrollo simple, lo desactivamos.

**2. Patrón Singleton con Double-Check Locking:**

**¿Por qué Singleton?** Crear una instancia de base de datos es **costoso** (abre archivos, carga esquema, etc.). Queremos UNA sola instancia compartida por toda la app.

**¿Por qué Double-Check Locking?**
```java
if (INSTANCE == null) {              // Primera verificación (sin lock, rápida)
    synchronized (AppDatabase.class) { // Solo si es null, adquiere lock
        if (INSTANCE == null) {        // Segunda verificación (con lock, segura)
            INSTANCE = Room.databaseBuilder(...).build();
        }
    }
}
```

- **Primer check**: Evita el costoso `synchronized` si la instancia ya existe (99.9% de las llamadas)
- **synchronized**: Asegura que solo un hilo cree la instancia (seguridad en multi-threading)
- **Segundo check**: Previene race condition (dos hilos pasan el primer check simultáneamente)
- **volatile**: Garantiza visibilidad de INSTANCE entre hilos (previene optimizaciones del compilador que podrían romper el patrón)

**3. fallbackToDestructiveMigration():**

**¿Qué hace?** Si cambias el esquema (version 1 → 2) y no proporcionas una migración manual, Room **borra toda la base de datos** y la recrea.

**¿Cuándo usarlo?**
- ✅ **Desarrollo/Prototipado**: Cambias esquema frecuentemente, no quieres escribir migraciones complejas
- ✅ **Apps con datos no críticos**: Perder datos es aceptable (caché, carrito recuperable)
- ❌ **Producción con datos valiosos**: Nunca uses esto si perder datos afecta al usuario

**Alternativa en producción:**
```java
.addMigrations(MIGRATION_1_2, MIGRATION_2_3)
```
Donde defines cómo transformar datos de versión antigua a nueva.

**4. ExecutorService - Pool de Hilos para DB:**

```java
public static final ExecutorService databaseWriteExecutor =
    Executors.newFixedThreadPool(4);
```

**¿Por qué un pool de hilos?**
- Room **prohíbe** operaciones de escritura en el hilo principal (lanzará excepción)
- Necesitamos hilos background para inserts, updates, deletes
- Un pool reutiliza hilos (más eficiente que crear/destruir hilos constantemente)

**¿Por qué 4 hilos?**
- Balance entre paralelismo y recursos
- 1 hilo: operaciones seriales, lento en múltiples escrituras
- 100 hilos: desperdicia memoria, context switching overhead
- 4 hilos: suficiente para esta app (raramente más de 2-3 operaciones DB simultáneas)

**Uso en Repositories:**
```java
AppDatabase.databaseWriteExecutor.execute(() -> {
    cartDao.insert(item);  // Este código corre en hilo background
});
```

### Ciclo de Vida de los Datos

```
┌─────────────────────────────────────────────────────────────┐
│                    Data Flow Diagram                         │
└─────────────────────────────────────────────────────────────┘

User Action (Add to Cart)
         │
         ▼
    Fragment ──────────► ViewModel.addToCart(item, qty)
                              │
                              ▼
                       Repository.insertOrUpdate()
                              │
                              ▼
                  ExecutorService.execute(() -> {
                      DAO.insert() or DAO.update()
                  })
                              │
                              ▼
                       Room Database
                              │
                              ▼
                       LiveData<List<CartItem>>
                              │
                              ▼
                       ViewModel (observes)
                              │
                              ▼
                       Fragment (observer callback)
                              │
                              ▼
                    Update UI (Adapter.notifyDataSetChanged())
```

---

## 🛠️ Compilación y Ejecución

### Compilar el Proyecto

#### Desde Android Studio (Recomendado)

1. Abre el proyecto en Android Studio
2. Selecciona `Build > Make Project` (Ctrl+F9 / Cmd+F9)
3. O selecciona `Build > Rebuild Project` para compilación completa

#### Desde Línea de Comandos

```bash
# En Linux/Mac
./gradlew assembleDebug

# En Windows
gradlew.bat assembleDebug

# Para build de release
./gradlew assembleRelease
```

**Outputs:**
- Debug APK: `app/build/outputs/apk/debug/app-debug.apk`
- Release APK: `app/build/outputs/apk/release/app-release.apk`

### Ejecutar en Dispositivo/Emulador

#### Opción 1: Desde Android Studio

1. Conecta tu dispositivo Android o inicia un emulador
2. Selecciona el dispositivo en el dropdown superior
3. Haz clic en el botón **Run** (▶) o presiona Shift+F10
4. La app se instalará y se iniciará automáticamente

#### Opción 2: Desde Línea de Comandos

```bash
# Listar dispositivos conectados
adb devices

# Instalar APK
adb install app/build/outputs/apk/debug/app-debug.apk

# Lanzar la aplicación
adb shell am start -n com.example.marketplace/.MainActivity

# Ver logs en tiempo real
adb logcat | grep -i marketplace
```

### Limpiar Build

```bash
# Limpiar artifacts de compilaciones previas
./gradlew clean

# Limpiar y compilar
./gradlew clean build
```

---

## 🧪 Pruebas

El proyecto incluye infraestructura para tests unitarios e instrumentados.

### Tests Unitarios

Ubicación: `app/src/test/java/`

```bash
# Ejecutar todos los tests unitarios
./gradlew test

# Ejecutar tests específicos
./gradlew test --tests com.example.marketplace.ExampleUnitTest
```

### Tests Instrumentados (UI)

Ubicación: `app/src/androidTest/java/`

Requieren un dispositivo o emulador en ejecución:

```bash
# Ejecutar todos los tests instrumentados
./gradlew connectedAndroidTest

# Ejecutar tests específicos
./gradlew connectedAndroidTest -P android.testInstrumentationRunnerArguments.class=\
com.example.marketplace.ExampleInstrumentedTest
```

### Cobertura de Código

```bash
# Generar reporte de cobertura
./gradlew jacocoTestReport

# Ver reporte en:
# app/build/reports/jacoco/jacocoTestReport/html/index.html
```

---

## 🌍 Objetivos de Desarrollo Sostenible

Aunque esta es una aplicación educativa de marketplace genérico, su arquitectura y funcionalidades pueden adaptarse para contribuir a varios **Objetivos de Desarrollo Sostenible (ODS)** de las Naciones Unidas:

### 🎯 ODS 8: Trabajo Decente y Crecimiento Económico

**Aplicación potencial:**
- Plataforma para pequeños productores locales y artesanos
- Comercio justo digital eliminando intermediarios
- Empoderamiento económico de comunidades rurales
- Marketplace para economía colaborativa

**Adaptaciones necesarias:**
- Sistema de verificación de vendedores
- Pasarela de pagos real
- Sistema de valoraciones y confianza

### ♻️ ODS 12: Producción y Consumo Responsables

**Aplicación potencial:**
- Marketplace de productos de segunda mano
- Venta de productos reciclados o upcycled
- Plataforma para intercambio sostenible
- Enfoque en productos eco-friendly

**Adaptaciones necesarias:**
- Etiquetas de sostenibilidad
- Calculadora de huella de carbono
- Categorías de productos sostenibles
- Información sobre ciclo de vida del producto

### 💡 Características Sostenibles Actuales

- **Eficiencia de Recursos**: App ligera y optimizada
- **Diseño Sostenible**: Arquitectura escalable y mantenible
- **Código Abierto**: Compartir conocimiento para replicación

---

## 🔧 Solución de Problemas

### Problemas Comunes y Soluciones

#### 1. Error: "google-services.json not found"

**Causa:** Archivo de configuración de Firebase no configurado.

**Solución:**
```bash
1. Ir a Firebase Console
2. Descargar google-services.json
3. Copiar a: app/google-services.json
4. Sync Gradle
```

#### 2. Error: "Firebase Authentication failed"

**Causa:** Email/Password no habilitado en Firebase Console.

**Solución:**
1. Ir a Firebase Console > Authentication
2. Sign-in method > Email/Password
3. Habilitar y guardar

#### 3. Error: "Room database migration failed"

**Causa:** Cambio en el esquema de base de datos.

**Solución:**
```java
// Opción 1: Desinstalar app (borra datos)
adb uninstall com.example.marketplace

// Opción 2: Ya implementado - fallbackToDestructiveMigration()
// La app recrea la BD automáticamente
```

#### 4. Error: "Gradle sync failed"

**Causa:** Problemas de red o versiones incompatibles.

**Solución:**
```bash
# Limpiar caché de Gradle
./gradlew clean --refresh-dependencies

# Invalidar cachés en Android Studio
File > Invalidate Caches / Restart
```

#### 5. App se cierra al iniciar

**Verificar logs:**
```bash
adb logcat | grep AndroidRuntime
```

**Causas comunes:**
- Firebase no configurado
- Permisos faltantes
- Dependencias no sincronizadas

#### 6. RecyclerView no muestra productos

**Solución:**
1. Verificar que `ProductListFragment` inicializa el adapter
2. Comprobar que la lista de productos no está vacía
3. Verificar logs: `Log.d("ProductList", "Products: " + products.size())`

### Logs Útiles

```bash
# Ver todos los logs de la app
adb logcat | grep marketplace

# Filtrar por nivel de error
adb logcat *:E

# Limpiar logs
adb logcat -c

# Guardar logs en archivo
adb logcat > marketplace_logs.txt
```

### Contacto para Soporte

Si encuentras un problema no listado aquí:
1. Revisa los [Issues existentes](https://github.com/UAX-2025-26/Marketplace_Javi-Mario/issues)
2. Crea un nuevo Issue con:
   - Descripción del problema
   - Pasos para reproducir
   - Logs relevantes
   - Versión de Android y Android Studio

---

## 🤝 Contribuir

¡Las contribuciones son bienvenidas! Este es un proyecto educativo open-source.

### Cómo Contribuir

1. **Fork el repositorio**
   ```bash
   # En GitHub, haz clic en "Fork"
   ```

2. **Clona tu fork**
   ```bash
   git clone https://github.com/TU_USUARIO/Marketplace_Javi-Mario.git
   cd Marketplace_Javi-Mario
   ```

3. **Crea una rama para tu feature**
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```

4. **Realiza tus cambios**
   - Sigue las convenciones de código existentes
   - Comenta código complejo
   - Actualiza documentación si es necesario

5. **Commit y Push**
   ```bash
   git add .
   git commit -m "feat: añadir funcionalidad X"
   git push origin feature/nueva-funcionalidad
   ```

6. **Crea un Pull Request**
   - Ve a tu fork en GitHub
   - Haz clic en "New Pull Request"
   - Describe tus cambios detalladamente

### Convenciones de Código

- **Java**: Seguir [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- **XML**: Indentación de 4 espacios
- **Nomenclatura**:
  - Clases: PascalCase (ej. `ProductListFragment`)
  - Variables: camelCase (ej. `productAdapter`)
  - Constantes: UPPER_SNAKE_CASE (ej. `MAX_QUANTITY`)

### Commits Semánticos

```
feat: nueva funcionalidad
fix: corrección de bug
docs: cambios en documentación
style: formato, punto y coma, etc
refactor: refactorización de código
test: añadir tests
chore: actualizar dependencias
```

### Ideas para Contribuir

- 🎨 Mejorar UI/UX
- 🔍 Implementar búsqueda de productos
- 📸 Añadir imágenes reales a productos
- 💳 Integrar pasarela de pagos (simulada)
- 🌐 Internacionalización (i18n)
- ♿ Mejorar accesibilidad
- 🧪 Aumentar cobertura de tests
- 📊 Añadir analytics

---

## 👥 Autores

### Desarrollador Principal

**Javier Vicente Yustres Rodríguez**
- GitHub: [@UAX-2025-26](https://github.com/UAX-2025-26)
- Universidad: Universidad Alfonso X el Sabio (UAX)
- Asignatura: Programación Dirigida por Eventos
- Año Académico: 2025-26

### Agradecimientos

- **Universidad Alfonso X el Sabio (UAX)** - Por la formación académica
- **Google Firebase** - Por los servicios de autenticación
- **Android Jetpack** - Por las bibliotecas modernas
- **Comunidad Android** - Por la documentación y recursos

---

## 📄 Licencia

Este proyecto está licenciado bajo la **Apache License 2.0** - ver el archivo [LICENSE](LICENSE) para más detalles.

```
Copyright 2024 Javier Vicente Yustres Rodríguez

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

### ¿Qué significa esta licencia?

✅ **Puedes:**
- Usar este código comercialmente
- Modificar el código
- Distribuir el código
- Usar este código en proyectos privados
- Sublicenciar

⚠️ **Debes:**
- Incluir una copia de la licencia
- Indicar cambios realizados
- Incluir el aviso de copyright

❌ **No puedes:**
- Usar las marcas registradas del proyecto sin permiso
- Responsabilizar a los autores por daños

---

## 📚 Recursos Adicionales

### Documentación Oficial

- [Android Developer](https://developer.android.com/)
- [Firebase Docs](https://firebase.google.com/docs)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Navigation Component](https://developer.android.com/guide/navigation)
- [MVVM Architecture](https://developer.android.com/topic/architecture)

### Tutoriales Recomendados

- [Android Kotlin Fundamentals](https://developer.android.com/courses/kotlin-android-fundamentals/overview)
- [Firebase Android Codelab](https://firebase.google.com/docs/android/setup)
- [Room with LiveData](https://developer.android.com/codelabs/android-room-with-a-view)

### Herramientas Útiles

- [Android Studio](https://developer.android.com/studio) - IDE oficial
- [Firebase Console](https://console.firebase.google.com/) - Gestión de servicios
- [Scrcpy](https://github.com/Genymobile/scrcpy) - Control de dispositivo desde PC
- [ADB](https://developer.android.com/studio/command-line/adb) - Android Debug Bridge

---

<div align="center">

**⭐ Si este proyecto te fue útil, considera darle una estrella en GitHub ⭐**

Hecho con ❤️ para el aprendizaje del desarrollo Android

[⬆ Volver al inicio](#-marketplace-app---android-e-commerce-application)

</div>
