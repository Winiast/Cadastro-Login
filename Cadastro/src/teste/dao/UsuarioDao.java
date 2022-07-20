package teste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import teste.modelos.Usuario;

public class UsuarioDao {

    private final Connection connection;

    public UsuarioDao(Connection connection) {
        this.connection = connection;
    }

    public void insert(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario(usuario, senha) VALUES (?, ? )";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getSenha());
        statement.execute();

    }

    public void insertOrUptade(Usuario usuario) throws SQLException {
        if (usuario.getId() > 0) {
            update(usuario);
        } else {
            insert(usuario);
        }
    }

    public void delete(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        statement.execute();
    }

    public ArrayList<Usuario> selectAll() throws SQLException {
        String sql = "SELECT * FROM usuario";
        PreparedStatement statement = connection.prepareStatement(sql);
        return pesquisa(statement);

    }

    private ArrayList<Usuario> pesquisa(PreparedStatement statement) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        statement.execute();
        ResultSet resultset = statement.getResultSet();

        while (resultset.next()) {
            int id = resultset.getInt("id");
            String usuario = resultset.getString("usuario");
            String senha = resultset.getString("senha");

            Usuario novoUsuario = new Usuario(id, usuario, senha);
            usuarios.add(novoUsuario);
        }

        return usuarios;
    }

    public Usuario buscaUsuario(Usuario usuario) throws SQLException {
        String sql = "SELECT FROM usuario WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId());

        return pesquisa(statement).get(0);

    }

    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario  set usuario = ? , senha = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getSenha());
        statement.setInt(3, usuario.getId());
        statement.execute();
    }

    public boolean autenticarUsuario(Usuario usuarioAutenticar) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND  senha = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuarioAutenticar.getUsuario());
        statement.setString(2, usuarioAutenticar.getSenha());
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        connection.close();

        return resultSet.next();
    }
}
