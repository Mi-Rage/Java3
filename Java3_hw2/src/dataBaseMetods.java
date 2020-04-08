
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class dataBaseMetods {
    private static final String URL = "jdbc:sqlite:testDB.db";
    private static final String TABLE = "students";
    private static final String FILE_NAME = "DZ_update.txt";
    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) throws SQLException {

        // подключимся к базе
        try {
            connect(URL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // создадим таблицу и заполним её значениями
        createNewTable(TABLE);
        for (int i = 1; i < 10; i++) {
            insert(TABLE,"Bob" + i, 100 + i);
        }

        // вставим в таблицу строку и выведем содержимое
        insert(TABLE,"Bobby", 100500);
        selectAll(TABLE);

        //обновим строку в таблице и выведем содержимое
        updateScore(TABLE,"Bobby", 10);
        selectAll(TABLE);

        // обновим данные таблицы из файла и выведем сожержимое
        connection.setAutoCommit(false);  //ускорим работу
        updateFromFile(FILE_NAME);
        connection.setAutoCommit(true);   //вернём как было
        selectAll(TABLE);

        // удалим строку в таблице и выведем содержимое
        deleteString(2);
        selectAll(TABLE);

        // отключимся от базы
        disconnect();

    }


    public static void connect(String url) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(url);
        stmt = connection.createStatement();
        System.out.println("DataBase connected.");
    }

    public static void disconnect() {
        try {
            connection.close();
            System.out.println("DataBase disconnected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Создадим если ещё не создали таблицу с тремя полями
    public static void createNewTable(String table) {

        String sql = "CREATE TABLE IF NOT EXISTS " + table + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "score INTEGER)";

        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Create Table query complete.");
    }

    // Создаем запрос на добавление имени и очков в таблицу
    public static void insert(String table, String name, int score) {

        String sql = "INSERT INTO " + table + " (name,score) VALUES(?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            int result = pstmt.executeUpdate();
            // Можно не выводить результат, сделано для удобства тестирования
            if (result == 1) {
                System.out.println("INSERT with " + name + " and " + score + " is complete.");
            } else {
                System.out.println("Something is wrong....");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Просмотр содержимого базы
    public static void selectAll(String table) {
        String sql = "SELECT id, name, score FROM " + table;
        System.out.println("Read ALL data from table " + table);

        try {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getInt("score"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // вставим новые значения в таблицу. Обновим score по name
    public static void updateScore(String table, String name, int newScore) {

        String sql = "UPDATE " + table + "\n" +
                "   SET score = '" + newScore + "'\n" +
                " WHERE name = '" + name + "';";

        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Update Table query complete.");
    }

    // удалим строку по id
    public static void deleteString(int id) {

        String sql = "DELETE FROM students\n" +
                "      WHERE id = '" + id + "'";

        try (Statement stmt = connection.createStatement()) {
            int result = stmt.executeUpdate(sql);
            if (result == 1) {
                System.out.println("Delete String " + id + " is complete.");
            } else {
                System.out.println("Delete String " + id + " is NOT complete.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Обновление содержимого базы по данным из файла file.
     * Для удобства из файда удалим лишние строки и оставим только данные в формате "1  Bob1  55" и тд.
     * Считывем из файла пока есть что, передаем строки массив и берем из массива только name, по которому обновляем
     * значения score с помощью предварительно написанного метода updateScore();
     *
     * @param file - имя файла с новыми данными
     */
    public static void updateFromFile(String file) {
        String[] newData;
        try {
            File input = new File(file);
            Scanner fileScanner = new Scanner(input);
            while (fileScanner.hasNextLine()) {
                newData = fileScanner.nextLine().split(" {2}");
                updateScore(TABLE, newData[1], Integer.parseInt(newData[2]));
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error Reading File");
            e.printStackTrace();
        }
    }
}
