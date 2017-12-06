## Instructions

For following the Workshop switch to the `workshop` branch.

Download the [slides in this link](https://www.dropbox.com/s/pcpmcubj65j2prr/Pokemon%20Go.pdf?dl=0)

Follow the comments in the code and the slides.
Once you have download the repo, you will have to integrate Firebase and Google Maps.

### Integrating Firebase
The best way using the assistant included in Android Studio, go to Tools/Firebase
The repo will be missing the google-service.json that is all you need, the dependencies are set.

### Integrating Google Maps
The repo will be missing the Google Maps key, the simpliest way to get one is to create a MapActivity, maybe you will have to do it in other Android project.
Once the MapActivity is created follow the instructions in the created .xml files so you can get a key

Inside the raw folder you can find a file that can be loaded by the emulator to simulate the location changes

### Functions
This the [functions repo](https://github.com/cutiko/pokemongo-firebase). Read more about setting up Functions there.

#### Warnings
This is a Functions and Android demo, so it is assume you are familiar with some of the concepts used.

 - Location on Android
 - Google Maps on Android
 - Asynctasks on Android
 - Retrofit for Android
 - Using an interface ass callback in Android
 - HTTP Requests (this is used in Functions)
