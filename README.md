1.App Features:

.Authentication: Users can register and log in using their email and password via Firebase Authentication.
.Home Screen: Displays a list of dummy items fetched from a remote API using Retrofit.
.Detail Screen: Shows details for a selected item. Users can add or remove items from their favorites, which are stored in Firebase Firestore.
.Favorites Screen: Displays all items the logged-in user has marked as favorites. The list updates in real-time.
.Offline Support: The app uses Firestore's local caching to provide offline functionality.
.Image Loading: Images are loaded efficiently using the Coil library.
.Logout: Users can log out from their account.


2.Simple Setup Steps
To get this project running, you'll need to configure Firebase and a few other dependencies.

1.Clone the repository.

2.Add Firebase:
.Create a new Firebase project in the Firebase Console.
.Enable Email/Password authentication.
.Add an Android app to your project and download the google-services.json file.
.Place this file in your project's app/ directory.
.Configure API Services:
.The app uses Retrofit to handle network requests for fetching the dummy item list.
.Coil is used for loading and displaying images from the network.

3.Run in Android Studio:
.Open the project in Android Studio.
.Sync Gradle to download all dependencies.
.Run the app on an emulator or a physical device.
