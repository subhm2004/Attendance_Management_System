# Attendance Management System

## Overview
The Attendance Management System is a Java-based GUI application designed to help users manage attendance records efficiently. It allows users to add, edit, delete, and refresh attendance records while also calculating and displaying the overall attendance percentage.

### File Descriptions
- **src/AttendanceManagementSystem.java**: Contains the main class `AttendanceManagementSystem`, which extends `JFrame` to create the GUI for managing attendance. It includes methods for adding, editing, deleting, and refreshing attendance records, as well as calculating and displaying the attendance percentage.

- **src/Attendance.java**: Defines the `Attendance` class, representing an attendance record with properties for `date`, `totalClasses`, and `attendedClasses`, along with their respective getters and setters.

- **src/AttendanceService.java**: Contains the `AttendanceService` class, which handles the loading and saving of attendance records. It provides methods to read from and write to a data source (e.g., a file or database) to persist attendance data.

- **.gitignore**: Specifies files and directories that should be ignored by Git, such as compiled class files and IDE-specific files.

## Setup Instructions
1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Compile the Java files using a Java compiler.
4. Run the `AttendanceManagementSystem` class to start the application.

## Usage Guidelines
- To add a new attendance record, click on the "Add Attendance" button and fill in the required fields.
- To edit an existing record, select the record from the table and click on the "Edit Attendance" button.
- To delete a record, select it from the table and click on the "Delete Attendance" button.
- Click on the "Refresh" button to update the displayed attendance records and the attendance percentage.

## License
This project is licensed under the MIT License.
