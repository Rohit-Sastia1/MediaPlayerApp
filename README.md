# MediaPlayerApp

*COMPANY*: CODTECH IT SOLUTIONS

*NAME*: ROHIT SASTIA

*INTERN ID*: CT04DH928

*DOMAIN*: ANDROID DEVELOPMENT

*DURATION*: 4 WEEKS

*MENTOR*: NEELA SANTHOSH

# MediaPlayerApp

##  Overview:
**MediaPlayerApp** is an Android application developed in **Java** using Android Studio. The app allows users to play audio files from their device’s local storage. It offers standard media player functionalities such as **play, pause, next, previous**, seek functionality, **looping**, and **shuffle** mode. It provides real-time playback updates and displays the duration of the track being played.

---

## ✅ Features:
- Pick and play audio from device storage
- Play, Pause, Next, and Previous buttons
- SeekBar with real-time progress updates
- Display of elapsed time and total duration
- Loop and Shuffle playback modes
- User-friendly and responsive UI
- Supports major audio formats like MP3, WAV

---

## ⚙️ Tech Stack:
- **Language:** Java  
- **UI Design:** XML (ConstraintLayout, Material Components)  
- **Android SDK:** MediaPlayer, Storage Access Framework  
- **Min SDK:** 27  
- **Target SDK:** 34

---
## 🏗️ Architecture

The app follows a straightforward single-activity architecture:

- **MainActivity.java**: Handles UI interactions and controls MediaPlayer lifecycle.
- **MediaPlayer**: Android’s native API used for audio playback.
- **Storage Access Framework**: Allows users to pick audio files from device storage.
- **UI Components**: Buttons, SeekBar, and TextViews update playback state in real-time.

---

##  Minimum Requirements

- Android Studio Dolphin or later  
- Android device or emulator running **Android 9 (API 28)** or higher  
- Active internet for fetching dependencies  
- Storage permission to access local audio files

---


## 🚀 How It Works:

The MediaPlayerApp is designed to offer a smooth and intuitive music playback experience using Android’s native MediaPlayer API and Storage Access Framework. Here's how the application works internally:

### 1. **Audio File Selection**
- When the user launches the app, they can pick an audio file from their device storage using an **Intent with `ACTION_OPEN_DOCUMENT`**.
- The file picker restricts the selection to only audio types (`audio/*`).
- Once a file is selected, its URI is captured and passed to the MediaPlayer instance for playback.

### 2. **Initializing MediaPlayer**
- The app uses Android’s **MediaPlayer class** to handle playback.
- The URI of the selected audio file is set as the data source.
- `MediaPlayer.prepare()` is called asynchronously, and once ready, it begins playback.

### 3. **User Controls**
- The app provides **Play**, **Pause**  buttons.
- If a playlist is implemented, the "Next" and "Previous" buttons can navigate through tracks.
- A **SeekBar** reflects the progress of the currently playing audio in real time.
- The SeekBar allows users to jump to any point in the track using `seekTo()`.

### 4. **Duration and Time Display**
- The total duration of the song and the elapsed time are displayed and updated in real time.
- A `Handler` and `Runnable` are used to update the UI every second with the current playback time.

### 5. **Looping and Shuffle**
- **Loop Mode**: The current song plays repeatedly when loop is enabled using `setLooping(true)`.
- **Shuffle Mode**: When enabled, it randomly selects and plays tracks from the list using a random number generator.

### 6. **Permissions**
- The app requests `READ_EXTERNAL_STORAGE` permission to access local media files (for Android versions below 10).
- For Android 10 and above, it uses `ACTION_OPEN_DOCUMENT` without needing storage permission.

### 7. **Lifecycle Management**
- The app gracefully handles lifecycle changes like pause or stop. It pauses playback during `onPause()` and resumes it if needed.
- Resources are released using `mediaPlayer.release()` during `onDestroy()` to avoid memory leaks.

---

## 🚀 Future Enhancements

- Add support for background playback and media notifications  
- Implement playlist management with drag-and-drop reordering  
- Integrate equalizer and audio visualizations  
- Support additional audio formats and cloud storage access

---

## Author

Developed as part of Task 02 – Android Media Player Project for educational purposes.

---

## Output

![Image](https://github.com/user-attachments/assets/f80f1122-d4fe-437d-9505-d685a4e37486)
![Image](https://github.com/user-attachments/assets/a8a5cea4-9651-4921-b7ec-62f28657b57c)
![Image](https://github.com/user-attachments/assets/ea07f458-9486-4e01-a095-524e81fbb83c)

