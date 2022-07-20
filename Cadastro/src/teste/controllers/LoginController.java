package teste.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import teste.App;
import teste.dao.Conexao;
import teste.dao.UsuarioDao;
import teste.modelos.Usuario;

public class LoginController {

    @FXML
    private Button cadastroUsuario;

    @FXML
    private Button fazerLogin;

    @FXML
    private TextField textNome;

    @FXML
    private PasswordField textSenhaCadastro;

    @FXML
    void mudarTela(ActionEvent event) {
        App.trocarTela(2);
    }

    @FXML
    void entrarLogin(ActionEvent event) {
        String nomeLogin = textNome.getText();
        String password = textSenhaCadastro.getText();

        Usuario usuarioAutenticar = new Usuario(nomeLogin, password);
        Connection conexao;
        try {
            conexao = new Conexao().getConnection();
            UsuarioDao usuarioDaoAutenticar = new UsuarioDao(conexao);
            boolean existe = usuarioDaoAutenticar.autenticarUsuario(usuarioAutenticar);

            if (existe) {
                JOptionPane.showMessageDialog(null, "Login feito com Sucesso.", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                textNome.setText("");
                textSenhaCadastro.setText("");
                App.trocarTela(3);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario e Senha não encontrados", "Falhou",
                        JOptionPane.ERROR_MESSAGE);

                textNome.setText("");
                textSenhaCadastro.setText("");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}