package deps

object Dependencies {
    const val ANDROIDX_CORE = "androidx.core:core-ktx:${DependenciesVersions.CORE_KTX}"
    const val ANDROIDX_LIFECYCLE_RUNTIME_KTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.LIFE_CYCLE_RUNTIME_KTX}"
    const val ANDROIDX_ACTIVITY_COMPOSE =
        "androidx.activity:activity-compose:${DependenciesVersions.ACTIVITY_COMPOSE}"
    const val ANDROIDX_UI = "androidx.compose.ui:ui:${DependenciesVersions.COMPOSE_UI}"
    const val ANDROIDX_UI_GRAPHICS =
        "androidx.compose.ui:ui-graphics:${DependenciesVersions.COMPOSE_UI}"
    const val ANDROIDX_UI_TOOLING_PREVIEW =
        "androidx.compose.ui:ui-tooling-preview:${DependenciesVersions.COMPOSE_UI}"
    const val ANDROIDX_MATERIAL3 =
        "androidx.compose.material3:material3:${DependenciesVersions.MATERIAL_3}"
    const val WORK_RUNTIME = "androidx.work:work-runtime-ktx:${DependenciesVersions.RUN_TIME}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${DependenciesVersions.APP_COMPAT}"
    const val MATERIAL = "com.google.android.material:material:${DependenciesVersions.MATERIAL}"
    const val ANDROIDX_ACTIVITY = "androidx.activity:activity-ktx:${DependenciesVersions.ANDROIDX_ACTIVITY}"


    const val hiltAndroid = "com.google.dagger:hilt-android:${DependenciesVersions.HILT}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${DependenciesVersions.HILT}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${DependenciesVersions.HILT}"
    const val hiltCompose = "androidx.hilt:hilt-work:${DependenciesVersions.HILT_COMPOSE}"
    const val hiltCompilerKapt = "androidx.hilt:hilt-compiler:${DependenciesVersions.HILT_COMPOSE}"
    const val hiltNavigation =
        "androidx.hilt:hilt-navigation-compose:${DependenciesVersions.HILT_COMPOSE}"


    const val retrofit = "com.squareup.retrofit2:retrofit:${DependenciesVersions.RETROFIT}"
    const val retrofitConverterGson =
        "com.squareup.retrofit2:converter-gson:${DependenciesVersions.RETROFIT}"
    const val retrofitKotlinCoroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${DependenciesVersions.RETROFIT_COROUTINE_ADAPTER_VERSION}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${DependenciesVersions.OKHTTP}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${DependenciesVersions.OKHTTP}"

    const val roomRuntime = "androidx.room:room-runtime:${DependenciesVersions.ROOM}"
    const val roomCompiler = "androidx.room:room-compiler:${DependenciesVersions.ROOM}"
    const val roomKtx = "androidx.room:room-ktx:${DependenciesVersions.ROOM}"

    const val datastore = "androidx.datastore:datastore:${DependenciesVersions.DATA_STORE}"
    const val kotlinCollections = "org.jetbrains.kotlinx:kotlinx-collections-immutable:${DependenciesVersions.KOTLIN_COLLECTIONS}"
    const val kotlinSerilaizations = "org.jetbrains.kotlinx:kotlinx-serialization-json:${DependenciesVersions.KOTLIN_SERIALIZATIONS}"

    const val protoBufJavaLite = "com.google.protobuf:protobuf-javalite:${DependenciesVersions.PROTO_BUF_JAVA}"
    const val protoBufKotlinLite = "com.google.protobuf:protobuf-kotlin-lite:${DependenciesVersions.PROTO_BUF_KOTLIN}"
    const val protoBufArtifact = "com.google.protobuf:protoc:${DependenciesVersions.PROTO_BUF_KOTLIN}"

    const val chuckerDebug = "com.github.chuckerteam.chucker:library:${DependenciesVersions.CHUCKER}"
    const val chuckerRelease = "com.github.chuckerteam.chucker:library-no-op:${DependenciesVersions.CHUCKER}"

}