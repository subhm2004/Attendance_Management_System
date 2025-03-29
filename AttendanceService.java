import java.io.*;
import java.util.*;

public class AttendanceService {
    private static final String FILE_PATH = "attendance_data.csv"; // Ensure this file is in the right directory

    public void saveAttendance(List<Attendance> attendances) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Attendance attendance : attendances) {
                writer.write(attendance.getDate() + "," + attendance.getTotalClasses() + ","
                        + attendance.getAttendedClasses());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Attendance> loadAttendance() {
        List<Attendance> attendances = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String date = data[0];
                int totalClasses = Integer.parseInt(data[1]);
                int attendedClasses = Integer.parseInt(data[2]);
                attendances.add(new Attendance(date, totalClasses, attendedClasses));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attendances;
    }
}
