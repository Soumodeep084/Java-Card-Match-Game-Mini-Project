package Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import Logic.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConnectionProvider {

    /**
     *
     * @return Connection Object ( User Defined )
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "");
        return con;
    }

    public static ResultSet getAllUserData() throws IOException, ClassNotFoundException, SQLException {
        Connection con = getConnection();
        Statement st = con.createStatement();
        String sql = "SELECT * from User Order By score DESC";
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public static boolean addUserData(User u) throws IOException, ClassNotFoundException, SQLException {
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateTime = dt.format(format);
        Connection con = getConnection();
        String sql = "INSERT INTO User (name , score , level , dateTime) values (?,?,?,?)";
        PreparedStatement ps = con.prepareCall(sql);
        ps.setString(1, u.getName());
        ps.setInt(2, u.getScore());
        ps.setInt(3, u.getLevel());
        ps.setString(4, dateTime);
        int x = ps.executeUpdate();
        return x > 0;
    }

//    public static ResultSet getParticularUser(String name) throws IOException , ClassNotFoundException , SQLException{
//        Connection con = getConnection();
//        String sql = "SELECT * from User where name = ?";
//        PreparedStatement ps = con.prepareCall(sql);
//        ps.setString(0, name);
//        ResultSet rs = ps.executeQuery();
//        return rs;
//    }
    private ConnectionProvider() {
    }
}
