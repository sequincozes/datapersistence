// faz as importações de classes necessárias para o funcionamento do programa

import modelo.Usuario;

import java.sql.*;
// conexão SQL para Java
// driver de conexão SQL para Java
import java.util.ArrayList;

// classe para tratamento de exceções
public class ClienteSQL {
    Connection connection;

    public static void main(String[] args) throws SQLException {
        ClienteSQL c = new ClienteSQL();
        c.connection = getConnection();
        Usuario usuario = new Usuario();
        usuario.setNome("Silvio");
        usuario.setCpf("000.000.000-00");
        usuario.setEmail("sequincozes@gmail.com");
        usuario.setTelefone("999999999");
        c.inserirUsuario(usuario);
        c.listarUsuarios();
        c.connection.close();
    }

    public void inserirUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario(nome,cpf,email,telefone) VALUES(?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getCpf());
        stmt.setString(3, usuario.getEmail());
        stmt.setString(4, usuario.getTelefone());
        stmt.execute();
        stmt.close();
    }

    public ArrayList<Usuario> listarUsuarios() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("SELECT id, nome,cpf,email,telefone FROM usuario");
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong(1));
            usuario.setNome(rs.getString(2));
            usuario.setCpf(rs.getString(3));
            usuario.setEmail(rs.getString(4));
            usuario.setTelefone(rs.getString(5));
            usuarios.add(usuario);
            exibeInformacoes(usuario);
        }
        return usuarios;
    }

    private void exibeInformacoes(Usuario usuario) {
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Telefone: " + usuario.getTelefone());
        System.out.println("CPF: " + usuario.getCpf());
        System.out.println("E-mail: " + usuario.getEmail());
    }

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost/persistenciadedados";
            String user = "ifrs";
            String password = "IF2021R$";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }
}
