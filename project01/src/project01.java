
import javax.management.relation.RoleList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class project01 extends JFrame {
    private String currentTableName;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton SearchButton;
    private JTable dataTable;
    private JButton showTableButton;
    private JComboBox<String> tableComboBox;
    private JComboBox<String> courseComboBox;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<String> streamComboBox;
    private JComboBox<String> subjectComboBox;
    int rollNo;

    private Connection connection;

    public project01() {

        setTitle("StudentReportCard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        courseComboBox = new JComboBox<>(new String[] { "iteg", "meg", "beg" });
        streamComboBox = new JComboBox<>(new String[] { "bca", "bba", "bcom", "bsc" });
        yearComboBox = new JComboBox<>(new Integer[] { 1, 2, 3 });

        subjectComboBox = new JComboBox<>();

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Course:"));
        inputPanel.add(courseComboBox);
        inputPanel.add(new JLabel("Stream:"));
        inputPanel.add(streamComboBox);
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(yearComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        insertButton = new JButton("Insert Data");
        buttonPanel.add(insertButton);
        updateButton = new JButton("Update Data");
        deleteButton = new JButton("Delete Data");
        SearchButton = new JButton("Search");
        showTableButton = new JButton("show table");

        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(SearchButton);
        buttonPanel.add(showTableButton);

        buttonPanel.add(courseComboBox);
        buttonPanel.add(streamComboBox);
        buttonPanel.add(yearComboBox);

        dataTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(dataTable);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        insertButton.addActionListener(e -> insertData());
        updateButton.addActionListener(e -> updateData());
        deleteButton.addActionListener(e -> deleteData());
        showTableButton.addActionListener(e -> showTableButton());
        SearchButton.addActionListener(e -> SearchData());
        courseComboBox.addActionListener(e -> updateSubjectComboBox());

        streamComboBox.addActionListener(e -> updateSubjectComboBox());

        yearComboBox.addActionListener(e -> updateSubjectComboBox());

        tableComboBox = new JComboBox<>(new String[] { "iteg", "beg", "meg" });
        courseComboBox.addActionListener(e -> updateStreamAndYearComboBoxes());
        tableComboBox.addActionListener(e -> switchTable());

        pack();
        setLocationRelativeTo(null);
    }

    private void switchTable() {
        currentTableName = (String) tableComboBox.getSelectedItem();
        showTableButton();
    }

    private void updateSubjectComboBox() {
        String selectedCourse = (String) courseComboBox.getSelectedItem();
        int selectedYear = (int) yearComboBox.getSelectedItem();

        if ("bca".equals(selectedCourse)) {
            if (selectedYear == 1) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { " Math", "Programming",
                        "Data_Structures", "Opreting_System", "Computer_Fondamental" }));
            } else if (selectedYear == 2) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Database_Management",
                        "Web_Development", "Algorithms", "Web_Designing", "Data_Structures" }));
            } else if (selectedYear == 3) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Artificial_Intelligence",
                        "Mobile_App_Development", "Cloud_Computing", "Python_Language", "AI" }));
            }
        } else if ("bba".equals(selectedCourse)) {
            if (selectedYear == 1) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Business_Communication",
                        "Accounting", "Economics", "Communication", "Financial" }));
            } else if (selectedYear == 2) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(
                        new String[] { "Marketing", "Organizational_Behavior", "Financial_Management", "HRM", "MR" }));
            } else if (selectedYear == 3) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Strategic_Management",
                        "International_Business", "Entrepreneurship", "Internatinal_Finance", "Invesment_Banking" }));
            }
        } else if ("bba".equals(selectedCourse)) {
            if (selectedYear == 1) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Business_Communication",
                        "Accounting", "Economics", "Communication", "Financial" }));
            } else if (selectedYear == 2) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(
                        new String[] { "Marketing", "Organizational_Behavior", "Financial_Management", "HRM", "MR" }));
            } else if (selectedYear == 3) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Strategic_Management",
                        "International_Business", "Entrepreneurship", "Internatinal_Finance", "Invesment_Banking" }));
            }
        } else if ("bsc".equals(selectedCourse)) {
            if (selectedYear == 1) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(
                        new String[] { "Cell_Biology", "Genetics", "Biochemistry", "Chemistry", "Biotechnology" }));
            } else if (selectedYear == 2) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Microbiology", "Immunology",
                        "Biotechnology", "Medical_Micro", "Molecular_Diagnostics" }));
            } else if (selectedYear == 3) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Molecular_Biology",
                        "Bioinformatics", "Research_Methodology", "Biological_Chemistry", "bioinformaics" }));
            }
        } else if ("bcom".equals(selectedCourse)) {
            if (selectedYear == 1) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Environmental_Studies",
                        "Financial_Accounting", "Data_Structures", "Opreting_System", "Computer_Fondamental" }));
            } else if (selectedYear == 2) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Database_Management",
                        "Web_Development", "Algorithms", "Web_Designing", "Data_Structures" }));
            } else if (selectedYear == 3) {
                subjectComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Artificial_Intelligence",
                        "Mobile_App_Development", "Cloud_computing", "Python_Language", "AI" }));
            }
        }
    }

    private void updateStreamAndYearComboBoxes() {
        String selectedCourse = (String) courseComboBox.getSelectedItem();

        if ("iteg".equals(selectedCourse)) {
            streamComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "bca", "bba" }));
        } else if ("meg".equals(selectedCourse)) {
            streamComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "bba", "bcom" }));
        } else if ("beg".equals(selectedCourse)) {
            streamComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "bsc" }));
        }
        yearComboBox.setModel(new DefaultComboBoxModel<>(new Integer[] { 1, 2, 3 }));
    }

    public void showTableButton() {

        String course = (String) courseComboBox.getSelectedItem();
        String stream = (String) streamComboBox.getSelectedItem();
        int year = (int) yearComboBox.getSelectedItem();
        String tableName = course + "_" + stream + "_" + year;
        getSubjects(course, stream, year);
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                model.addColumn(columnName);
            }

            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                model.addRow(rowData);
            }

            dataTable.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to fetch data.");
        }
    }

    private String[] getSubjects(String course, String stream, int year) {

        String[] subjects = null;

        if ("iteg".equals(course) && "bca".equals(stream) && year == 1) {
            subjects = new String[] { "Math", "Programming", "Data_Structures", "Opreting_System",
                    "Computer_Fondamental" };
        } else if ("iteg".equals(course) && "bca".equals(stream) && year == 2) {
            subjects = new String[] { "Database_Management", "Web_Development", "Algorithms", "Web_Designing",
                    "Data_Structures" };
        } else if ("iteg".equals(course) && "bca".equals(stream) && year == 3) {
            subjects = new String[] { "Artificial_Intelligence", "Mobile_App_Development", "Cloud_Computing",
                    "Python_Language", "AI" };
        } else if ("iteg".equals(course) && "bba".equals(stream) && year == 1) {
            subjects = new String[] { "Business_Communication", "Accounting", "Economics", "Communication",
                    "Financial" };
        } else if ("iteg".equals(course) && "bba".equals(stream) && year == 2) {
            subjects = new String[] { "Marketing", "Organizational_Behavior", "Financial_Management", "HRM", "MR" };
        } else if ("iteg".equals(course) && "bba".equals(stream) && year == 3) {
            subjects = new String[] { "Strategic_Management", "International_Business", "Entrepreneurship",
                    "Internatinal_Finance", "Invesment_Banking" };
        } else if ("meg".equals(course) && "bba".equals(stream) && year == 1) {
            subjects = new String[] { "Business_Communication", "Accounting", "Economics", "Communication",
                    "Financial" };
        } else if ("meg".equals(course) && "bba".equals(stream) && year == 2) {
            subjects = new String[] { "Marketing", "Organizational_Behavior", "Financial_Management", "HRM", "MR" };
        } else if ("meg".equals(course) && "bba".equals(stream) && year == 3) {
            subjects = new String[] { "Strategic_Management", "International_Business", "Entrepreneurship",
                    "Internatinal_Finance", "Invesment_Banking" };
        } else if ("meg".equals(course) && "bcom".equals(stream) && year == 1) {
            subjects = new String[] { "Environmental_Studies", "Financial_Accounting", "Data_Structures",
                    "Opreting_System", "Computer_Fondamental" };
        } else if ("meg".equals(course) && "bcom".equals(stream) && year == 2) {
            subjects = new String[] { "Database_Management", "Web_Development", "Algorithms", "Web_Designing",
                    "Data_Structures" };
        } else if ("meg".equals(course) && "bcom".equals(stream) && year == 3) {
            subjects = new String[] { "Artificial_Intelligence", "Mobile_App_Development", "Cloud_computing",
                    "Python_Language",
                    "AI" };
        } else if ("beg".equals(course) && "bsc".equals(stream) && year == 1) {
            subjects = new String[] { "Cell_Biology", "Genetics", "Biochemistry", "Chemistry", "Biotechnology" };
        } else if ("beg".equals(course) && "bsc".equals(stream) && year == 2) {
            subjects = new String[] { "Microbiology", "Immunology", "Biotechnology", "Medical_Micro",
                    "Molecular_Diagnostics" };
        } else if ("beg".equals(course) && "bsc".equals(stream) && year == 3) {
            subjects = new String[] { "Molecular_Biology", "Bioinformatics", "Research_Methodology",
                    "Biological_Chemistry", "Bioinformaics" };
        }

        else {

            subjects = new String[] { "DefaultSubject1", "DefaultSubject2", "DefaultSubject3" };
        }

        subjectComboBox.setModel(new DefaultComboBoxModel<>(subjects));
        return subjects;
    }

    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/project";
        String username = "root";
        String password = "9109";
        connection = DriverManager.getConnection(url, username, password);
    }

    public void insertData() {
        String selectedSubject = (String) subjectComboBox.getSelectedItem();
        String course = (String) courseComboBox.getSelectedItem();
        String stream = (String) streamComboBox.getSelectedItem();
        int year = (int) yearComboBox.getSelectedItem();
        String tableName = course + "_" + stream + "_" + year;

        String[] subjects = getSubjects(course, stream, year);

        StringBuilder queryBuilder = new StringBuilder(
                "INSERT INTO " + tableName + " (RollNo, Student_Name, Student_FatherName");

        for (String subject : subjects) {
            queryBuilder.append(", ").append(subject);
        }

        queryBuilder.append(", Percentage, Grade) VALUES (?, ?, ?");

        for (int i = 0; i < subjects.length + 2; i++) {
            queryBuilder.append(", ?");
        }

        queryBuilder.append(")");

        double RollNo = 0;
        boolean validRollNo = false;

        do {
            validRollNo = true;
            String input = JOptionPane.showInputDialog(this, "Enter Roll Number :");
            try {
                RollNo = Double.parseDouble(input);
                if (RollNo >= 0) {

                    boolean rollNoExists = checkRollNoExists(tableName, (int) RollNo);
                    if (rollNoExists) {
                        JOptionPane.showMessageDialog(this,
                                "Roll Number already exists:");
                        validRollNo = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid roll number.");
                    validRollNo = false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid roll number.");
                validRollNo = false;
            }
        } while (!validRollNo);

        String Student_Name = "";
        boolean validStudentName = false;

        do {
            Student_Name = JOptionPane.showInputDialog(this, "Student_Name:");
            if (Pattern.matches(".*\\d+.*", Student_Name)) {
                JOptionPane.showMessageDialog(this, "Invalid student name.");
            } else {
                validStudentName = true;
            }
        } while (!validStudentName);

        String Student_FatherName = "";

        boolean validFatherName = false;
        do {
            Student_FatherName = JOptionPane.showInputDialog(this, "Enter Student_FatherName:");
            if (Pattern.matches(".*\\d+.*", Student_FatherName)) {
                JOptionPane.showMessageDialog(this,
                        "Invalid Father's name.");
            } else {
                validFatherName = true;
            }
        } while (!validFatherName);

        double[] subjectMarks = new double[subjects.length];

        for (int i = 0; i < subjects.length; i++) {
            boolean validSubjectMark = false;
            do {
                String subjectMarkInput = JOptionPane.showInputDialog(this, subjects[i] + " Number:");
                try {
                    double subjectMark = Double.parseDouble(subjectMarkInput);

                    if (subjectMark < 0) {
                        JOptionPane.showMessageDialog(this,
                                "Subject marks cannot be negative. Please enter a non-negative value.");
                    } else if (subjectMark > 100) {
                        JOptionPane.showMessageDialog(this,
                                "Subject marks above 100 number. Please enter a less then 100 .");
                    } else {
                        subjectMarks[i] = subjectMark;
                        validSubjectMark = true;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this,
                            "Invalid " + subjects[i] + " score. Please enter a numeric value.");
                }
            } while (!validSubjectMark);
        }

        double Percentage = calculatePercentage(subjectMarks);
        String Grade = calculateGrade(Percentage);

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());

            statement.setDouble(1, RollNo);
            statement.setString(2, Student_Name);
            statement.setString(3, Student_FatherName);

            for (int i = 0; i < subjects.length; i++) {
                statement.setDouble(4 + i, subjectMarks[i]);
            }

            statement.setDouble(subjects.length + 4, Percentage);
            statement.setString(subjects.length + 5, Grade);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data inserted successfully!");
            showTableButton();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to insert data.");
        }
    }

    private double calculatePercentage(double[] subjectMarks) {
        double totalMarks = 0;
        for (double mark : subjectMarks) {
            totalMarks += mark;
        }
        return totalMarks / subjectMarks.length;
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else if (percentage >= 33) {
            return "E";
        } else {
            return "Fail";
        }
    }

    public void updateData() {
        String course = (String) courseComboBox.getSelectedItem();
        String stream = (String) streamComboBox.getSelectedItem();
        int year = (int) yearComboBox.getSelectedItem();
        String tableName = course + "_" + stream + "_" + year;

        boolean validRollNo = false;

        do {
            validRollNo = true;
            String rollNoInput = JOptionPane.showInputDialog(this, "Enter student RollNo:");
            try {
                rollNo = Integer.parseInt(rollNoInput);

                boolean rollNoExists = checkRollNoExists(tableName, rollNo);

                if (!rollNoExists) {
                    JOptionPane.showMessageDialog(this,
                            "Student with RollNo " + rollNo + " does not exist in the database.");
                    validRollNo = false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid RollNo. Please enter a valid SWRollNo.");
                validRollNo = false;
            }
        } while (!validRollNo);

        boolean rollNoExists = checkRollNoExists(tableName, rollNo);
        if (!rollNoExists) {
            JOptionPane.showMessageDialog(this, "Student with RollNo " + rollNo + " does not exist in the database.");
            return;
        }

        String[] subjects = getSubjects(course, stream, year);

        Object selectedValue = JOptionPane.showInputDialog(
                this,
                "Select the field to update:",
                "Update Data",
                JOptionPane.PLAIN_MESSAGE,
                null,
                subjects,
                subjects[0]);

        if (selectedValue != null) {
            String updateColumn = (String) selectedValue;

            String newValue = "";

            if ("RollNo".equals(updateColumn)) {
                JOptionPane.showMessageDialog(this, "RollNo cannot be updated directly.");
                return;
            } else if ("Student_Name".equals(updateColumn)) {
                // Handle updating student name
            } else {

                boolean validSubjectMark = false;
                do {
                    String subjectMarkInput = JOptionPane.showInputDialog(this,
                            "Enter the new " + updateColumn + " Marks:");
                    try {
                        double newSubjectMark = Double.parseDouble(subjectMarkInput);

                        if (newSubjectMark < 0) {
                            JOptionPane.showMessageDialog(this,
                                    "Subject marks cannot be negative. Please enter a non-negative value.");
                        } else if (newSubjectMark > 100) {
                            JOptionPane.showMessageDialog(this,
                                    "Subject marks above 100 number. Please enter a less then 100 .");
                        } else {
                            newValue = String.valueOf(newSubjectMark);
                            validSubjectMark = true;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this,
                                "Invalid " + updateColumn + " score. Please enter a numeric value.");
                    }
                } while (!validSubjectMark);

                try {
                    connect();
                    String updateQuery = "UPDATE " + tableName + " SET " + updateColumn + " = ? WHERE RollNo = ?";
                    PreparedStatement statement = connection.prepareStatement(updateQuery);

                    statement.setString(1, newValue);
                    statement.setInt(2, (int) rollNo);

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {

                        updatePercentageAndGrade(tableName, rollNo, subjects);
                        JOptionPane.showMessageDialog(this, "Data updated successfully!");
                        showTableButton();
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "No student found with the provided RollNo or invalid table name.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to update data.");
                }
            }
        }
    }

    private void updatePercentageAndGrade(String tableName, int rollNo, String[] subjects) {
        try {

            connect();
            PreparedStatement selectStatement = connection
                    .prepareStatement("SELECT * FROM " + tableName + " WHERE RollNo = ?");
            selectStatement.setInt(1, rollNo);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                double[] subjectMarks = new double[subjects.length];

                for (int i = 0; i < subjects.length; i++) {
                    subjectMarks[i] = resultSet.getDouble(subjects[i]);
                }

                double newPercentage = calculatePercentage(subjectMarks);
                String newGrade = calculateGrade(newPercentage);

                PreparedStatement updateStatement = connection.prepareStatement(
                        "UPDATE " + tableName + " SET Percentage = ?, Grade = ? WHERE RollNo = ?");
                updateStatement.setDouble(1, newPercentage);
                updateStatement.setString(2, newGrade);
                updateStatement.setInt(3, rollNo);

                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected > 0) {
                    // Data updated successfully
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update percentage and grade.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to update percentage and grade.");
        }
    }

    private boolean checkRollNoExists(String tableName, int rollNo) {
        try {
            connect();
            PreparedStatement statement = connection
                    .prepareStatement("SELECT RollNo FROM " + tableName + " WHERE RollNo = ?");
            statement.setInt(1, rollNo);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void deleteData() {
        String course = (String) courseComboBox.getSelectedItem();
        String stream = (String) streamComboBox.getSelectedItem();
        int year = (int) yearComboBox.getSelectedItem();
        String tableName = course + "_" + stream + "_" + year;

        int RollNo = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter student RollNo:"));
        showTableButton();

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE RollNo = ?");
            statement.setInt(1, RollNo);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Data deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No student found with the provided RollNo or invalid table name.");
            }
            showTableButton();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to delete data.");
        }
    }

    public void SearchData() {
        String course = (String) courseComboBox.getSelectedItem();
        String stream = (String) streamComboBox.getSelectedItem();
        int year = (int) yearComboBox.getSelectedItem();
        String yearText = convertYearToText(year);
        String tableName = course + "_" + stream + "_" + year;
        String searchCriteria = JOptionPane.showInputDialog(this, "Enter roll number or name to search:");

        try {
            connect();
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM " + tableName + " WHERE RollNo = ? OR Student_Name = ?");
            statement.setString(1, searchCriteria);
            statement.setString(2, searchCriteria);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                JDialog marksheetDialog = new JDialog();
                marksheetDialog.setTitle("Student Marksheet");
                marksheetDialog.setLayout(new BorderLayout());

                JPanel marksheetPanel = new JPanel(new GridLayout(0, 2));

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                marksheetPanel.add(new JLabel("Course:"));
                marksheetPanel.add(new JLabel(course));
                marksheetPanel.add(new JLabel("Stream:"));
                marksheetPanel.add(new JLabel(stream));
                marksheetPanel.add(new JLabel("Year:"));
                marksheetPanel.add(new JLabel(yearText));

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = resultSet.getString(i);
                    marksheetPanel.add(new JLabel(columnName + ":"));
                    marksheetPanel.add(new JLabel(columnValue));
                }

                marksheetDialog.add(marksheetPanel, BorderLayout.CENTER);
                marksheetDialog.pack();
                marksheetDialog.setLocationRelativeTo(this);
                marksheetDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No matching data found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to search data.");
        }
    }

    private String convertYearToText(int numericYear) {
        switch (numericYear) {
            case 1:
                return "1st Year";
            case 2:
                return "2nd Year";
            case 3:
                return "3rd Year";
            default:
                return numericYear + "th Year";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            project01 frame = new project01();
            frame.setVisible(true);
            frame.updateStreamAndYearComboBoxes();

        });
    }
}
