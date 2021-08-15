import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.agaperra.professionaldevelopment"
    const val compile_sdk = 30
    const val min_sdk = 21
    const val target_sdk = 30
    const val build_tools_version = "30.0.3"
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
//    const val repository = ":repository"
//    const val utils = ":utils"
}

object Versions {
    // Tools
    const val multidex = "1.0.3"

    // ViewModel lifecycle
    const val viewModel_lifecycle = "2.3.1"

    // Design
    const val appcompat = "1.3.1"
    const val material = "1.4.0"
    const val constraint = "2.1.0"

    // Kotlin
    const val core = "1.6.0"
    const val stdlib = "1.5.10"
    const val coroutinesCore = "1.5.0"
    const val coroutinesAndroid = "1.5.0"

    // Retrofit
    const val retrofit = "2.6.0"
    const val converterGson = "2.6.0"
    const val interceptor = "3.12.1"
    const val adapterCoroutines = "0.9.2"

    // Koin
    const val koinCore = "3.1.2"
    const val koinAndroid = "3.1.2"

    // Picasso
    const val picasso = "2.5.2"

    // Room
    const val roomKtx = "2.3.0"
    const val runtime = "2.3.0"
    const val roomCompiler = "2.3.0"

    // Test
    const val jUnit = "4.12"
    const val runner = "1.2.0"
    const val espressoCore = "3.2.0"

}


object Tools {
    const val multidex = "com.android.support:multidex:${Versions.multidex}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Lifecycle {
    const val view_model = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel_lifecycle}"
}

object Koin {
    const val koin_core = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val koin_android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
}

object Picasso {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}
