# project-notes-2-text

### HOW TO RUN THE PROGRAM:
The program is dependent on the various components of Android components, thus requiring running the program in an Android system environment. This can be achieved in IntelliJ by using AVD manager or in Android Studio by simply selecting devices to choose an emulator or connect to a physical device. As our app has a target SDK of API level of 29, and a minimal SDK of API level of 23, the device should be configured with API 29, or Android version 10.0. Then the app can be run by using the "run" function in the IDE of choice and should open in the emulator/physical device connected. 


### FileMenuFactory method:
Used the factory method design pattern to implement the creation of popupmenus used when files are long-clicked. This allows the option of quickly extending the list of available file options in the future if necessary. The factory design pattern is particularly useful here since it prevents the need for repeated creations of the same popupmenu view object.


### Observer:
The observer design pattern is used to accommodate the Android platform workflow for the File Sharing feature, as shown in ShareObserver. Because Android work by Activity instead of main.java, the classes for file sharing require the detection of the "share" button pressed, which is facilitated by the FileMenuInteractor. Since it is poor code style to initiate these classes in the FileMenuInteractor as it shouldn't know which classes are used for sharing, the ShareObserver is used instead to monitor when FileMenuInteractor detects the "share" button being pressed and initiate these classes for sharing in the ShareObserver instead. 

