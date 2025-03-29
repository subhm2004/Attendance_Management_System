# Attendance Management System

## 📌 Overview
The **Attendance Management System** is a Java-based GUI application designed to streamline attendance tracking efficiently. The application provides a user-friendly interface to **add, edit, delete, and refresh** attendance records while also calculating and displaying the **attendance percentage** dynamically.

## ✨ Features
- 📅 **Add Attendance Records** – Enter new attendance details easily.
- ✏️ **Edit Existing Records** – Modify records as needed.
- ❌ **Delete Attendance Entries** – Remove outdated or incorrect records.
- 🔄 **Refresh Data** – Update displayed attendance records.
- 📊 **Automatic Attendance Percentage Calculation** – Displays the overall attendance percentage.
- 💾 **Data Persistence** – Save and load attendance records seamlessly.

## 📂 Project Structure
```
Attendance_Management_System/
│── src/
│   ├── AttendanceManagementSystem.java   # Main GUI class
│   ├── Attendance.java                    # Attendance record class
│   ├── AttendanceService.java             # Data handling and persistence
│── .gitignore                              # Files to be ignored by Git
│── README.md                               # Project documentation
```

### 🔍 File Descriptions
- **`src/AttendanceManagementSystem.java`**  
  Implements the main **GUI-based** attendance management system using `JFrame`. It provides methods for adding, editing, deleting, and refreshing records while computing attendance percentages.

- **`src/Attendance.java`**  
  Defines the `Attendance` class with attributes:
  - `date` (Date of record)
  - `totalClasses` (Total classes held)
  - `attendedClasses` (Classes attended)
  
  Includes getter and setter methods for these properties.

- **`src/AttendanceService.java`**  
  Manages the **storage and retrieval** of attendance data. Reads and writes attendance records from a data source (e.g., a file or database) to ensure persistence.

## 🚀 Setup Instructions
### Prerequisites
Ensure you have the following installed on your system:
- **Java Development Kit (JDK)** (Version 8 or later)
- **An IDE** (e.g., IntelliJ IDEA, Eclipse, or VS Code) or a terminal with `javac`

### 🔧 Installation Steps
1. **Clone the Repository**
   ```sh
   git clone https://github.com/subhm2004/Attendance_Management_System.git
   cd Attendance_Management_System
   ```
2. **Compile the Java Files**
   ```sh
   javac -d bin src/*.java
   ```
3. **Run the Application**
   ```sh
   java -cp bin AttendanceManagementSystem
   ```

## 📖 Usage Guidelines
1. **Add Attendance:** Click on "Add Attendance" and fill in the details.
2. **Edit Record:** Select a record, click "Edit Attendance," modify, and save.
3. **Delete Record:** Select a record and click "Delete Attendance."
4. **Refresh Data:** Click "Refresh" to update the table and percentage.

## 🛠️ Future Enhancements
- 🔄 **Database Integration** (MySQL/PostgreSQL)
- 📈 **Graphical Analysis** of Attendance Trends
- 📅 **Export Attendance Reports** (CSV/Excel)

## 📜 License
This project is licensed under the **MIT License**. Feel free to use, modify, and distribute it.

---

💡 **Developed by [Shubham Malik](https://github.com/subhm2004)**  
📧 Contact: subhu04012003@gmail.com

