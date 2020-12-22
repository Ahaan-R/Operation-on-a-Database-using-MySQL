import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHandler {

    public static void main(String[] args) {
        String path = "data/DEPENDENT.txt";
        String table = "dependent";
        String dbname = "COMPANY";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            insertIntoDB(path, table, dbname);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private static void insertIntoDB(String path, String table, String dbname)
            throws SQLException, FileNotFoundException, IOException {
        Connection conn;
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname, "root", "root");
        System.out.println("connected");
        Statement query = (Statement) conn.createStatement();
        FileReader file = new FileReader(path);
        BufferedReader buff = new BufferedReader(file);
        String line = new String();

        while ((line = buff.readLine()) != null) {
            if (line.equals("NEW TABLE")) {
                continue;
            } else {
                query.execute("SET FOREIGN_KEY_CHECKS=0");
                query.execute("Insert into " + table + " values(" + line + ")");

            }
        }
        conn.close();
        buff.close();
        file.close();
        System.out.println("Inserted into database");
    }

}

//----------------

//    static Connection connection=null;
//    static String dbName="COMPANY";
//    static String url = "jdbc:mysql://localhost:3306/"+dbName;
//    static String username="root";
//    static String password ="root";
//
//    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
//
//        Class.forName("com.mysql.jdbc.Driver").newInstance();
//        connection= DriverManager.getConnection(url,username,password);
//        PreparedStatement ps=connection.prepareStatement("INSERT INTO 'company'.'works_on' ('Essn'),('Pno'),('Hours') VALUES ('123'),('1'),('12'); ");
//        int status=ps.executeUpdate();
//
//        if(status!=0) {
//            System.out.println("db connected");
//            System.out.println("record inserted");
//        }
//
//    }
//
//}
