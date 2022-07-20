package teste.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import teste.dao.Conexao;
import teste.dao.UsuarioDao;
import teste.modelos.Usuario;

public class MenuController {

    @FXML
    private Button teste;

    @FXML
    void executarTeste(ActionEvent event) throws SQLException {
        Connection conexao = new Conexao().getConnection();
        UsuarioDao usuarioDao = new UsuarioDao(conexao);
        Usuario usuario = new Usuario("Bartolomeu", "12345");

        try {
            usuarioDao.insert(usuario);
            JOptionPane.showMessageDialog(null, "Deu certo", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            System.out.println("ERRO" + ex.getMessage());
        }
        Usuario usuarionovo = new Usuario(17, "teste", "123");
        usuarioDao.update(usuarionovo);

        usuarioDao.delete(usuarionovo);
        usuarioDao.delete(usuario);
        usuarioDao.delete(usuario);
        usuarioDao.delete(usuario);
    }

}
