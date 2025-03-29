public class Attendance {
    private String date;
    private int totalClasses;
    private int attendedClasses;

    public Attendance(String date, int totalClasses, int attendedClasses) {
        this.date = date;
        this.totalClasses = totalClasses;
        this.attendedClasses = attendedClasses;
    }

    public String getDate() {
        return date;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public int getAttendedClasses() {
        return attendedClasses;
    }

    // Setter methods
    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public void setAttendedClasses(int attendedClasses) {
        this.attendedClasses = attendedClasses;
    }
}
