# GPS_Tracker

GeoTrack is an Android app built in Android Studio using Java that detects the user's current location in the form of latitude, longitude, pincode, house number, area, state, and country. The app stores the location data along with the corresponding date in a database for future reference.

## App Features

- Detects precise location using GPS and Network Providers.

- Provides detailed location information:
  - Latitude
  - Longitude
  - Pincode
  - House Number
  - Area
  - State
  - Country

- Stores the location data with the current date in a local database.

- Works both with GPS and Network Providers for improved accuracy.

## 🛠️ Technical Details

### 1. Location Detection Logic

- The app uses the LocationManager class to get location updates:
- man.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
- This ensures that location updates are requested every 6000 milliseconds (6 seconds) with a minimum distance change of 10 meters.

### 2. The app also checks if both GPS and Network Providers are enabled:

- man = (LocationManager) getSystemService(LOCATION_SERVICE);
- isNet = man.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
- isGPS = man.isProviderEnabled(LocationManager.GPS_PROVIDER);

### 3. Database Integration

- The app stores the detected location details in a local database along with the current date. 
- This helps users keep track of their location history.

## 🧩 Dependencies

- androidx.appcompat:appcompat:1.6.1
- com.google.android.material:material:1.11.0
- androidx.constraintlayout:constraintlayout:2.1.4
- junit:junit:4.13.2
- androidx.test.ext:junit:1.1.5
- androidx.test.espresso:espresso-core:3.5.1

## 🚀 Getting Started

- Prerequisites
- Android Studio
- Java Development Kit (JDK)
- A physical or virtual Android device with location services enabled.

## Installation Steps

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Sync the project to download all required dependencies.
4. Build and run the app on your connected Android device or emulator.

