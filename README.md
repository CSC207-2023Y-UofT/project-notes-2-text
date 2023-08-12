# project-notes-2-text

## HOW TO RUN THE PROGRAM:
The program is dependent on the various components of Android platform, thus requiring running the program in an Android system environment. This can be achieved in IntelliJ by using AVD manager or in Android Studio by simply selecting devices to choose an emulator or connect to a physical device. As our app has a target SDK of API level of 29, and a minimal SDK of API level of 26, the device should be configured with API 29, or Android version 10.0. The Gradle JDK is JetBrains Runtime version 17.0.6. Then the app can be run by using the "run" function in the IDE of choice and should open in the emulator/physical device connected. 

Following the android ["Build your first app"](https://developer.android.com/codelabs/build-your-first-android-app#0) guide to the 4th step is a good way to set up the emulator as required in android studio. Otherwise, feature demonstrations can be quickly viewed from this [gdrive](https://drive.google.com/drive/folders/1-Xp3OHFaEYvpY8-pLJ4wdnEGRybp_TFn?usp=sharing) folder.

## Design Patterns

### FileMenuFactory method:
Used the factory method design pattern to implement the creation of popupmenus used when files are long-clicked. This allows the option of quickly extending the list of available file options in the future if necessary. The factory design pattern is particularly useful here since it prevents the need for repeated creations of the same popupmenu view object.

### Abstract Factory:
The abstract factory design pattern was used to implement the creation of viewholders for the various recyclerview adapters used in this program. This design pattern was appropriate since multiple different forms of view holders are needed for the directory system and the selection system, and it thus makes sense to have an abstract factory that is implemented by factories that produce each of the view holder types. This also introduces an abstraction layer in the usecase layer between viewholders, which are entities, and the recyclerview adapters, which are adaptors, better maintaining Clean Architecture in the implementation.


### Observer:
The observer design pattern is used to accommodate the Android platform workflow for the File Sharing feature, as shown in ShareObserver. Because Android work by Activity instead of main.java, the classes for file sharing require the detection of the "share" button pressed, which is facilitated by the FileMenuInteractor. Since it is poor code style to initiate these classes in the FileMenuInteractor as it shouldn't know which classes are used for sharing, the ShareObserver is used instead to monitor when FileMenuInteractor detects the "share" button being pressed and initiate these classes for sharing in the ShareObserver instead. 


### Factory:
The text editing sections of the app utilize a factory design pattern. The text editing sections work closely with the IO.File class and our own created TextFile class. The text editing sections use classes such as OpenTextEditorInteractor, SaveFile, JoinFile, EditTextInteractor, to do various tasks with the Textfile and IO.File entities. These include creating new instances of TextFile to extract the text from TextFile and to also create new instances of IO.File when saving files. 

### Facade:
While file opening, sharing and moving were implemented as separate functionality through separate classes (due to the single responsiility principle), they were brought together under the facade of the filemenu interactor to simplify accessing them for file menus.

### Dependency Injection:
When calling the UserUpdateInfo use case class, the controller and presenter classes for the pop-up pages for account management injects the user database into the use case class through constructor injection. This makes sure the use case class doesn't directly depend on the database.

### Singleton:
The CurrentUser class only has one instance of itself and it can be accessed and changed by any other use case and adaptor classes through a getter and a setter.

### Strategy:
The RegisterUseCase and UserUpdateInfo use case classes both use the UserRequirement class to check if the input the user entered meets the requirements the user info is meant to have.


## Unit Testing Involving Context:
Upon researching testing involving context, it is determined that unit testing is not viable for any classes involving context. As any Directory-related classes and method requires interaction with the Android System and thus requires the usage of Context throughout the actions. As Context is a part of the Android Environment, it cannot be accurately mocked and testing can only be done through Instrumented tests, which require running the app and will not be a unit test. 
![image](https://github.com/CSC207-2023Y-UofT/project-notes-2-text/assets/133291994/3159dc9b-92f2-4db1-bc8c-5c72cbdc58b8)  
(Image taken from https://developer.android.com/training/testing/fundamentals) Due to this limitation, we opted to instead use Android's Log feature to record critical values used throughout these classes, and thus can monitor the action of the classes at runtime. 


## SOLID
The entire application was designed while keeping SOLID principles in mind. While there are many examples of this, here are a few prominent ones:

### Single Responsibility Principle
The entire app, particularly any classes dealing with directory features, sought to especially maintain the single-responsibility principle: we maintain strict assurance that classes would only deal with tasks that met their overall purpose, and to separate any unrelated functionality into separate classes. For instance, file opening, and file sharing, were separated into separate classes due to their large code components and individual behavior. In this case, we also implemented a facade for these functions under file menu controller since that accesses the separate functionality for the same file.

### Open-Closed Principle
This principle is applied in the program through the usage of input boundaries. Although it is not necessary, the interface helps hide information so that higher-level classes are closed for modification but open to an extension of function. This is evident in the usage of FileMenuInputBoundary. This way while it is closed for modification, we were able to allow extension of functionality through inheritance into underlying FileMenuController, JoinMenuController, and SelectionMenuController. 

### Liskov Substitution Principle
Use of this principle is evident in FileMenuController, with JoinMenuController and SelectionMenuController which inherited it. This allows the usage of the popup menu function in other modes of the directory, such as 'open', which is critical to allow the user to navigate the directory in selection mode. 

### Interface Segregation Principle
While there is no direct usage of this principle, we kept the principle in mind and designed the program in a way there are no large interfaces present to force unneeded dependencies. 

### Dependency Inversion Principle
This principle is applied throughout the program with the use of various Output Boundaries. One example of such is the ThirdPartyOutputBoundary, this allows the sharing use case to not depend on a lower level class, ThirdPartySender. This way changes in how Android ShareSheet needs to be implemented in ThirdPartySender have no effect on the logic present in the high-level use case. 


## Other notes:

### Remaining warnings: There may be a few warnings left that were left unchanged for certain reasons - we have these explained in the code next to the lines that threw the warnings.
