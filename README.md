# Upcoming Movies
This repository contains a sample android app which shows the upcoming movies and details.
It uses the [TMDB](https://www.themoviedb.org/documentation/api)'s API implementation.

- Download apk from [here](https://github.com/Ravi879/Upcoming-Movies/raw/master/Upcoming%20Movies.apk).

### Screenshot
1. Movie List <br>
![Screenshot 1](https://github.com/Ravi879/Upcoming-Movies/raw/master/Screenshot/movie_list.png "")
2. Movie Details <br>
![Screenshot 2](https://github.com/Ravi879/Upcoming-Movies/raw/master/Screenshot/movie_detail.png "")

 #### Quick explanation of repository directory :
1. [screenshot](https://github.com/Ravi879/Upcoming-Movies/tree/master/Screenshot): Sample screenshot from mobile emulator, tablet and real mobile device. 
2. [code](https://github.com/Ravi879/Upcoming-Movies/tree/master/code): The android app code.

### Prerequisites

- TMDB's API key, click here for [sign up](https://www.themoviedb.org/account/signup)
- Android SDK v28
- Android Support Repository

### Open and Run Project
1. open android studio, select File -> Import -> "Existing Projects into your workspace".
2. go to the path where you cloned the Repo: (repoFolder)\code
3. add your registered TMDB api-key into [string.xml](https://github.com/Ravi879/Upcoming-Movies/blob/master/code/app/src/main/res/values/strings.xml).
4. rebuild the project and run.

### Built With

- [MVVM architecture](https://developer.android.com/jetpack/docs/guide)
- Dependency Injection : [Dagger2](https://github.com/google/dagger)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) : Live  data, View Model
- [Retrofit](https://github.com/square/retrofit)
- [RXJava2](https://github.com/ReactiveX/RxJava)
- [Data Binding](https://developer.android.com/topic/libraries/data-binding/)
- Language:  [Kotlin](https://kotlinlang.org/)


### Author

- **Ravi Gadhiya**

### License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- This project is licensed under the **[MIT license](http://opensource.org/licenses/mit-license.php)**. 

### Support

Please feel free to submit [issues](https://github.com/Ravi879/Upcoming-Movies/issues) with any bugs or other unforeseen issues you experience.