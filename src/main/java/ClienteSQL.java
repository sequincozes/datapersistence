// faz as importações de classes necessárias para o funcionamento do programa
import java.sql.Connection;
// conexão SQL para Java
import java.sql.DriverManager;
// driver de conexão SQL para Java
import java.sql.SQLException;
// classe para tratamento de exceções
public class ClienteSQL {

    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        System.out.println("Conexão aberta!");
        connection.close();
    }

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost/persistenciadedados";
            String user = "ifrs";
            String password = "IF2021R$";
            return DriverManager.getConnection(url,user,password);
        }
        catch(SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }
}
