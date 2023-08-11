# project-notes-2-text

### HOW TO RUN THE PROGRAM:
The program is dependent on the various components of Android components, thus requiring running the program in an Android system environment. This can be achieved in IntelliJ by using AVD manager or in Android Studio by simply selecting devices to choose an emulator or connect to a physical device. As our app has a target SDK of API level of 29, and a minimal SDK of API level of 23, the device should be configured with API 29, or Android version 10.0. Then the app can be run by using the "run" function in the IDE of choice and should open in the emulator/physical device connected. 


### FileMenuFactory method:
Used the factory method design pattern to implement the creation of popupmenus used when files are long-clicked. This allows the option of quickly extending the list of available file options in the future if necessary. The factory design pattern is particularly useful here since it prevents the need for repeated creations of the same popupmenu view object.


### Observer:
The observer design pattern is used to accommodate the Android platform workflow for the File Sharing feature, as shown in ShareObserver. Because Android work by Activity instead of main.java, the classes for file sharing require the detection of the "share" button pressed, which is facilitated by the FileMenuInteractor. Since it is poor code style to initiate these classes in the FileMenuInteractor as it shouldn't know which classes are used for sharing, the ShareObserver is used instead to monitor when FileMenuInteractor detects the "share" button being pressed and initiate these classes for sharing in the ShareObserver instead. 


### Factory:
The text editing sections of the app utilize a factory design pattern. The text editing sections work closely with the IO.File class and our own created TextFile class. The text editing sections use classes such as OpenTextEditorInteractor, SaveFile, JoinFile, EditTextInteractor, to do various tasks with the Textfile and IO.File entities. These include creating new instances of TextFile to extract the text from TextFile and to also create new instances of IO.File when saving files. 


### Unit Testing Involving Context:
Upon researching testing involving context, it is determined that unit testing is not viable for any classes involving context. As any Directory-related classes and method requires interaction with the Android System and thus requires the usage of Context throughout the actions. As Context is a part of the Android Environment, it cannot be accurately mocked and testing can only be done through Instrumented tests, which require running the app and will not be a unit test. 
![image](https://github.com/CSC207-2023Y-UofT/project-notes-2-text/assets/133291994/3159dc9b-92f2-4db1-bc8c-5c72cbdc58b8) (Image taken from https://developer.android.com/training/testing/fundamentals)
Due to this limitation, we opted to instead use Android's Log feature to record critical values used throughout these classes, and thus can monitor the action of the classes at runtime. 
