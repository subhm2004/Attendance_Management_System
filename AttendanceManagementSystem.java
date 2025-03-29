import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class AttendanceManagementSystem extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private AttendanceService attendanceService;
    private JLabel percentageLabel;

    public AttendanceManagementSystem() {
        setTitle("Attendance Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        attendanceService = new AttendanceService();

        JButton addButton = new JButton("Add Attendance");
        JButton refreshButton = new JButton("Refresh");
        JButton editButton = new JButton("Edit Attendance");
        JButton deleteButton = new JButton("Delete Attendance");
        percentageLabel = new JLabel("Attendance Percentage: 0.0%");
        percentageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        percentageLabel.setForeground(Color.RED); // Set percentage label color to red

        tableModel = new DefaultTableModel(new String[] { "Date", "Total Classes", "Attended Classes" }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Customize table appearance
        table.setSelectionBackground(new Color(0, 0, 139)); // Dark blue using RGB values
        table.setSelectionForeground(Color.WHITE); // Set selected text color to white

        // Using default light theme colors
        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.setForeground(Color.BLACK);
        refreshButton.setBackground(Color.LIGHT_GRAY);
        refreshButton.setForeground(Color.BLACK);
        editButton.setBackground(Color.LIGHT_GRAY);
        editButton.setForeground(Color.BLACK);
        deleteButton.setBackground(Color.LIGHT_GRAY);
        deleteButton.setForeground(Color.BLACK);
        percentageLabel.setForeground(Color.RED); // Red color for percentage label
        table.setBackground(Color.WHITE); // Light table background
        table.setForeground(Color.BLACK); // Table text color
        table.setSelectionBackground(Color.CYAN); // Table row selection color

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAttendance();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
                updateAttendancePercentage();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAttendance();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAttendance();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(percentageLabel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        refreshTable();
        updateAttendancePercentage();
    }

    private void addAttendance() {
        String date = JOptionPane.showInputDialog(this, "Enter Date (dd/mm/yyyy):");
        String totalClassesStr = JOptionPane.showInputDialog(this, "Enter Total Classes:");
        String attendedClassesStr = JOptionPane.showInputDialog(this, "Enter Attended Classes:");

        if (date != null && totalClassesStr != null && attendedClassesStr != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                sdf.parse(date); // Validate date format

                int totalClasses = Integer.parseInt(totalClassesStr);
                int attendedClasses = Integer.parseInt(attendedClassesStr);

                // Check if Attended Classes is greater than Total Classes
                if (attendedClasses > totalClasses) {
                    JOptionPane.showMessageDialog(this, "Attended Classes cannot be greater than Total Classes.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Return early to prevent adding invalid attendance
                }

                Attendance newAttendance = new Attendance(date, totalClasses, attendedClasses);
                List<Attendance> attendances = attendanceService.loadAttendance();
                attendances.add(newAttendance);
                attendanceService.saveAttendance(attendances);

                JOptionPane.showMessageDialog(this, "Attendance added successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
                updateAttendancePercentage();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use dd/mm/yyyy.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshTable() {
        List<Attendance> allAttendances = attendanceService.loadAttendance();
        tableModel.setRowCount(0);
        for (Attendance attendance : allAttendances) {
            tableModel.addRow(new Object[] { attendance.getDate(), attendance.getTotalClasses(),
                    attendance.getAttendedClasses() });
        }
    }

    private void updateAttendancePercentage() {
        List<Attendance> allAttendances = attendanceService.loadAttendance();
        int totalClasses = 0;
        int attendedClasses = 0;

        for (Attendance attendance : allAttendances) {
            totalClasses += attendance.getTotalClasses();
            attendedClasses += attendance.getAttendedClasses();
        }

        double percentage = totalClasses == 0 ? 0.0 : (double) attendedClasses / totalClasses * 100;
        percentageLabel.setText("Attendance Percentage: " + String.format("%.2f", percentage) + "%");
    }

    private void editAttendance() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String date = (String) tableModel.getValueAt(selectedRow, 0);
            String totalClassesStr = (String) tableModel.getValueAt(selectedRow, 1);
            String attendedClassesStr = (String) tableModel.getValueAt(selectedRow, 2);

            String newTotalClassesStr = JOptionPane.showInputDialog(this, "Edit Total Classes:", totalClassesStr);
            String newAttendedClassesStr = JOptionPane.showInputDialog(this, "Edit Attended Classes:",
                    attendedClassesStr);

            try {
                int newTotalClasses = Integer.parseInt(newTotalClassesStr);
                int newAttendedClasses = Integer.parseInt(newAttendedClassesStr);

                if (newAttendedClasses > newTotalClasses) {
                    JOptionPane.showMessageDialog(this, "Attended Classes cannot be greater than Total Classes.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<Attendance> attendances = attendanceService.loadAttendance();
                for (Attendance attendance : attendances) {
                    if (attendance.getDate().equals(date)) {
                        attendance.setTotalClasses(newTotalClasses);
                        attendance.setAttendedClasses(newAttendedClasses);
                        break;
                    }
                }
                attendanceService.saveAttendance(attendances);
                JOptionPane.showMessageDialog(this, "Attendance edited successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
                updateAttendancePercentage();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an attendance record to edit.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAttendance() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String date = (String) tableModel.getValueAt(selectedRow, 0);

            List<Attendance> attendances = attendanceService.loadAttendance();
            attendances.removeIf(attendance -> attendance.getDate().equals(date));

            attendanceService.saveAttendance(attendances);
            JOptionPane.showMessageDialog(this, "Attendance deleted successfully.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
            updateAttendancePercentage();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an attendance record to delete.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AttendanceManagementSystem().setVisible(true);
            }
        });
    }
}
