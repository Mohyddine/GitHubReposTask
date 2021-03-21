GitHubReposTask
Idea of the App The task is to implement a small App that will list the most starred Github repos that were created in the last 30 days. Fetching the sorted JSON data directly from the Github API.

Features

● As a User I should be able to list the most starred Github repos that were created in the last 30 days.

● As a User I should see the results as a list. One repository per row.

● As a User I should be able to see for each repo/row the following details :

○ Repository name

○ Repository description

○ Number of stars for the repo.

○ Number of issues for the repo.

○ Username and avatar of the owner.

● As a User I should be able to keep scrolling and new results should appear (pagination).

libraries:
    // Lifecycle components for "viewModel and livedata"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.3.0"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.3.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0"
    
    //Google Gson for model annotation
    implementation "com.google.code.gson:gson:2.8.6"
    
    //retrofit library for http network requests and Gson converter
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    //The Paging Library helps you load and display small chunks of data at a time
    implementation "androidx.paging:paging-runtime-ktx:2.1.2"

    //kotlin Coroutines for lightweight threading
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3'

    //coil url image loading library using kotlin coroutines
    implementation "io.coil-kt:coil:1.1.1"