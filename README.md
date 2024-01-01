# KMP TaskApp
It is a Kotlin Multi-Platform(KMP) Task app using SqlDelight, Kotlin, MVVM architecture , Coroutines and Jetpack compose.

# Features: 
- Colorful task
- Important task
- Delete Task
- Separate Completed Tasks section
- Swipe to Important and delete task
- Edit Task

# Tech stack:
- Kotlin
- Jetpack compose
- Kotlin Multi platform
- SqlDelight
- MVVM architecture
- Coroutine
- Kotlin Date Time  

# Type of App supports:
- Android
- IOS
- Desktop

# Video

https://github.com/KaushalVasava/KMP_TaskApp/assets/49050597/b915fe84-7348-4a1f-86f5-6a2f5a1a4855

# Support Me
If you like my work you can support me via :

[![buymecoffee1 (2)](https://github.com/KaushalVasava/Tasks/assets/49050597/327844b7-b9a4-4c5d-beb7-e9e177c82880)](https://www.buymeacoffee.com/kaushal.developer)

# Contribution
You can contribute this project. Just Solve issue or update code and raise PR. I'll do code review and merge your changes into main branch. See Commit message guidelines https://initialcommit.com/blog/git-commit-messages-best-practices

# Licence
Copyright 2023 Kaushal Vasava

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

# Author
 Kaushal Vasava

Steps:

1. Add Required dependencies and plugins like Retrofit for API Calling, Coroutines for background work or fetch data in background not on the Main thread, Dagger-Hilt for Dependecy Injection, Glide for Image loading.

2. Add Internet Permission in AndroidManifest.xml
3. Create AndroidGifApp class and Add @HiltAndroidApp annotation to use dependency injection in our project and add this class in AndroidManifest application name tag
4. Define all the required models from JSON repsonse of the API.
5. Define ApiService.kt interface and add getGif method for fetching data. It is get method so add @GET annotation above this method. and pass two query parameters. 
6. Add AppModule.kt in di folder for our dependencies like Retrofit, Api, Repository
7. Define TestRepo.kt and Inject ApiService in TestRepo constructor
8. Define TestViewModel.kt and Inject TestRepo in TestViewmodel constructor
9. Define variables to store Api data and search query and add getData() function to fetch data.
10. Design UI using Jetpack Compose, Add MainActivity.kt and HomeScreen.kt file design UI and connect with TestViewmodel.
11. Add UnitTest in test folder. Add ApiTest.kt 
