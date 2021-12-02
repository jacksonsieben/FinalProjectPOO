import java.sql.*;

public class ConnectSQL {
    public Connection con;
    
    public ConnectSQL() {
    }

    public ConnectSQL(Connection con) {
        this.con = con;
    }

    public Connection mySqlConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb","root","");  
            return con;
        }catch(Exception e){ System.out.println(e); return con;}        
    }

    public void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
