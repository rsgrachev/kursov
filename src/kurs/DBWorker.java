package kurs;

import kurs.model.Fond;
import kurs.model.Group;
import kurs.model.Student;
import kurs.model.SummaFond;

import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBWorker {
    public static final String PATH_TO_DB_FILE = "database.db";
    public static final String URL = "jdbc:sqlite:" + PATH_TO_DB_FILE;
    public static Connection conn;


    public static void initDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Драйвер: " + meta.getDriverName());
                //createDB();
            }
        } catch (SQLException ex) {
            System.out.println("Ошибка подключения к БД: " + ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
    public static Group getGroup(int groupId) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM 'Group' WHERE 'Group'.ID ="+groupId);
        //System.out.println(resultSet.getInt("ID"));
        Group group = new Group(resultSet.getInt("ID"),resultSet.getString("Name"));
        resultSet.close();
        statement.close();
        return group;
    }
    public static SummaFond getSummaFond() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM 'SummaFond' WHERE 'SummaFond'.ID = 1");
        SummaFond summaFond = new SummaFond(resultSet.getInt("ID"),resultSet.getInt("Summa"));
        resultSet.close();
        statement.close();
        return summaFond;
    }
    public static void updateSummaFond(SummaFond summaFond) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("UPDATE SummaFond SET Summa = ? WHERE ID = ?");
        statement.setObject(1, summaFond.getSumma());
        statement.setInt(2, summaFond.getID());
        statement.executeUpdate();
        statement.close();
    }
    public static void deleteStudent(Student student) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM 'Students' WHERE 'Students'.ID ="+student.getId());
        System.out.println("Студент удалён!");
        statement.close();
    }
    public static void addStudent(Student student) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO Students('Surname','Name','Patronymic','GroupID','Budget','Dolgi') " +
                        "VALUES(?,?,?,?,?,?)");
        statement.setObject(1, student.getFirstName());
        statement.setObject(2, student.getSecondName());
        statement.setObject(3, student.getPatronymic());
        statement.setObject(4, student.getGroup().getId());
        statement.setObject(5, student.isBudget());
        statement.setObject(6, student.isStudyDebts());

        statement.execute();
        statement.close();

        System.out.println("Добавление студента!");
    }

    public static void deleteFondData() throws SQLException {
//        PreparedStatement statement = conn.prepareStatement("DELETE FROM Fond");
//
//        statement.executeUpdate();
//        statement.close();

        Statement statement = conn.createStatement();
        statement.executeUpdate("DELETE FROM 'Fond'");
        System.out.println(statement.executeUpdate("DELETE FROM 'Fond'"));
        statement.close();
    }

    public static void addLowScholarship(Student student) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO Fond('StudentId','sum') " +
                        "VALUES(?,?)");
        statement.setObject(1, student.getId());
        statement.setObject(2, 4000);

        statement.executeUpdate();
        statement.close();

    }

    public static void addScholarship(Student student) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO Fond('StudentId','sum') " +
                        "VALUES(?,?)");
        statement.setObject(1, student.getId());
        statement.setObject(2, 5000);

        statement.executeUpdate();
        statement.close();

    }
    public static void addHighScholarship(Student student) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO Fond('StudentId','sum') " +
                        "VALUES(?,?)");
        statement.setObject(1, student.getId());
        statement.setObject(2, 6000);

        statement.executeUpdate();
        statement.close();

    }
    public static Student getStudent(int studentId) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM 'Students' WHERE 'Students'.ID =" + studentId);
        Student student = new Student(resultSet.getInt("ID"), resultSet.getString("Surname"),
                resultSet.getString("Name"),
                resultSet.getString("Patronymic"),
                getGroup(resultSet.getInt("GroupID")), resultSet.getBoolean("Budget"),
                resultSet.getBoolean("Dolgi"),
                resultSet.getBoolean("Zachet"), resultSet.getInt("Marks"));
        resultSet.close();
        statement.close();
        return student;
    }

    public static List<Fond> getAllFonds() throws SQLException {

        Statement statement = conn.createStatement();
        List<Fond> list = new ArrayList<Fond>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Fond");
        while (resultSet.next()) {
            list.add(new Fond(resultSet.getInt("ID"),getStudent(resultSet.getInt("StudentId")),
                    resultSet.getInt("Sum")));
        }
        resultSet.close();
        statement.close();
        return list;

    }

    public static List<Student> getAllStudents() throws SQLException {
        Statement statement = conn.createStatement();
        List<Student> list = new ArrayList<Student>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Students");
        while (resultSet.next()) {
            list.add(new Student(resultSet.getInt("ID"),resultSet.getString("Surname"),
                    resultSet.getString("Name"),
                    resultSet.getString("Patronymic"),
                    getGroup(resultSet.getInt("GroupID")),resultSet.getBoolean("Budget"),
                    resultSet.getBoolean("Dolgi"),
                    resultSet.getBoolean("Zachet"),resultSet.getInt("Marks")));
        }
        resultSet.close();
        statement.close();
        return list;
    }
    public  static  List<Group> getAllGroups() throws SQLException {
        Statement statement = conn.createStatement();
        List<Group> list = new ArrayList<Group>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM 'Group'");
        while (resultSet.next()) {
            list.add(new Group(resultSet.getInt("ID"),resultSet.getString("Name")));
        }
        resultSet.close();
        statement.close();
        return list;
    }

    public static void updateDataMarks(Student student) throws SQLException {
        PreparedStatement statementZachet = conn.prepareStatement("UPDATE Students SET Zachet = ? WHERE ID = ?");
        statementZachet.setObject(1, student.getOffset());
        statementZachet.setInt(2, student.getId());

        PreparedStatement statementMarks = conn.prepareStatement("UPDATE Students SET Marks = ? WHERE ID = ?");
        statementMarks.setInt(1, student.getMarks());
        statementMarks.setInt(2, student.getId());

        statementZachet.executeUpdate();
        statementZachet.close();

        statementMarks.executeUpdate();
        statementMarks.close();
    }

    public static void updateStudent(Student student) throws SQLException {
        PreparedStatement statementSurname = conn.prepareStatement("UPDATE Students SET Surname = ? WHERE ID = ?");
        statementSurname.setString(1, student.getFirstName());
        statementSurname.setInt(2, student.getId());

        PreparedStatement statementName = conn.prepareStatement("UPDATE Students SET Name = ? WHERE ID = ?");
        statementName.setString(1, student.getSecondName());
        statementName.setInt(2, student.getId());

        PreparedStatement statementPatronymic = conn.prepareStatement("UPDATE Students SET Patronymic = ? WHERE ID = ?");
        statementPatronymic.setString(1, student.getPatronymic());
        statementPatronymic.setInt(2, student.getId());

        PreparedStatement statementGroup = conn.prepareStatement("UPDATE Students SET GroupID = ? WHERE ID = ?");
        statementGroup.setInt(1, student.getGroup().getId());
        statementGroup.setInt(2, student.getId());

        statementSurname.executeUpdate();
        statementSurname.close();

        statementName.executeUpdate();
        statementName.close();

        statementPatronymic.executeUpdate();
        statementPatronymic.close();

        statementGroup.executeUpdate();
        statementGroup.close();
    }


}
