import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){


    Connection connection = null;

    try{

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examen","root","");
        System.out.println("Conectado a la base de datos");


        Statement st = connection.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS juegos(" +
                "id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "titulo varchar(255) NOT NULL," +
                "genero varchar(255) NOT NULL)" +
                "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");



    }catch(SQLException | ClassNotFoundException sqlE ){
        System.out.println("No se pudo conectar a la base de datos");
        sqlE.printStackTrace();
    }

    //Insertamos registro

        String sentenciaSql = "INSERT INTO juegos(titulo, genero) VALUES (?, ?)";
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(sentenciaSql);

            ps.setString(1, "Ghost & Goblins");
            ps.setString(2, "Arcade");
           // ps.executeUpdate();


        }catch(SQLException sqle){
             sqle.printStackTrace();
        }


    //Modificar registro

        sentenciaSql = "UPDATE juegos SET titulo = ?, genero = ? WHERE id = ?";
        ps = null;

        try{
            ps = connection.prepareStatement(sentenciaSql);
            ps.setString(1, "Encierros de Callasparra 2007");
            ps.setString(2, "Arcade");
            ps.setInt(3, 1);
           // ps.executeUpdate();
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }


    //Eliminar registros

        sentenciaSql = "DELETE FROM juegos WHERE id = ?";
        ps = null;

        try{
            ps = connection.prepareStatement(sentenciaSql);
            System.out.println("Que id quieres borrar");
            Scanner sc = new Scanner(System.in);
          /* int num = sc.nextInt();
           ps.setInt(1, num);
           ps.executeUpdate(); */
        }catch(SQLException e){
            e.printStackTrace();
        }

    //Consulta
/*
        sentenciaSql = "SELECT titulo, genero FROM juegos";
        ps = null;
        ResultSet rs = null;

        try{
            ps = connection.prepareStatement(sentenciaSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Titulo: " + rs.getString("titulo"));
                System.out.println("Genero; " + rs.getString("genero"));
                System.out.println("------------------------");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
*/
        //Consulta concreta

        sentenciaSql = "SELECT titulo, genero FROM juegos WHERE genero = ?";
        ps = null;
        ResultSet rs = null;

        try{
            ps = connection.prepareStatement(sentenciaSql);
            Scanner sc = new Scanner(System.in);
            System.out.println("Que genero quieres buscar?");
            String genero = sc.next();
            ps.setString(1, genero );


            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Titulo: " + rs.getString("titulo"));
                System.out.println("Genero; " + rs.getString("genero"));
                System.out.println("------------------------");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
