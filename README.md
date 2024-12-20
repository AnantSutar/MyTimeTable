# MyTimetable: Java Application  

**GitHub**  

MyTimetable is a Java-based course management application developed using **Java SE 8** and **JavaFX** to provide a robust and user-friendly graphical interface.

## Key Features
- **Object-Oriented Programming (OOP):** Ensures modular and reusable code.
- **Model-View-Controller (MVC):** Maintains a clear separation of concerns for better maintainability.
- **Java Collections Framework:** Efficient data management with generics.
- **Exception Handling:** Comprehensive error management for a reliable user experience.

## Requirements
- **Java Development Kit (JDK):** Version 8 or higher
- **Eclipse IDE:** Preferably the latest version with JavaFX support
- **JavaFX SDK:** Ensure JavaFX is set up in your environment

## Steps to Run in Eclipse
1. **Clone the Repository:**
   - Open your terminal and run:
     ```bash
     git clone <repository-url>
     ```
   - Replace `<repository-url>` with the GitHub link to the MyTimetable repository.

2. **Open Eclipse:**
   - Launch Eclipse IDE and select a workspace.

3. **Import the Project:**
   - Go to `File` > `Import...`.
   - Select `Existing Projects into Workspace` under the `General` category.
   - Click `Next` and browse to the directory where you cloned the repository.
   - Select the project and click `Finish`.

4. **Set Up JavaFX:**
   - Right-click on the project in the `Package Explorer` and select `Properties`.
   - Go to `Java Build Path` > `Libraries`.
   - Click `Add External JARs...` and select the JavaFX SDK JARs from your installation.
   - Add the JavaFX runtime VM arguments:
     - Go to `Run` > `Run Configurations...`.
     - Under the `Arguments` tab, add the following to the `VM arguments` field:
       ```text
       --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
       ```
     - Replace `/path/to/javafx-sdk/lib` with the actual path to your JavaFX SDK library.

5. **Run the Application:**
   - Right-click on the main class file in the `Package Explorer`.
   - Select `Run As` > `Java Application`.

6. **Explore the Application:**
   - Interact with the user interface to manage courses effectively!


