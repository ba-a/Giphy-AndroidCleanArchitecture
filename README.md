# Giphy-AndroidCleanArchitecture
  
# Android Clean Architecture
  
This Repo contains some best practices for current state of the art Android development  
(This was written on 03.06.2020)  
  
It uses the giphy developer API to show the latest trending giphys and also contains   
a giphy search that will show first 25 results with the feature to share them.  
    
It is built with Kotlin using:  
    - Single activity multiple fragments pattern  
    - MVVM with Repository Pattern(Fragment -> ViewModel -> UseCase -> Repository)  
    - Uniflow for UI State updates (great library btw: https://github.com/uniflow-kt/uniflow-kt)  
    - Coroutines for Background work  
    - NavGraph for navigation  
    - BottomNav for the BottomBar  
    - ViewBinding for accessing views  
    - Koin for dependency injection  
    - Glide for image/gif loading  
    - Retrofit + Gson for API calls  
    - ViewPager2 with custom PageTransformer  
    - RecyclerView  
    - mockk for Unit Testing  
    - Chucker for analysing network traffic  
    
