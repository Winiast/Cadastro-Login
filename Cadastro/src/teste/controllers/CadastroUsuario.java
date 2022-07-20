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

public class CadastroUsuario {

    @FXML
    private Button salvarCadastro;

    @FXML
    private TextField textNomeCadastro;

    @FXML
    private PasswordField textConfirmacao;

    @FXML
    private PasswordField textSenhaCadastro;

    @FXML
    void fazerCadastro(ActionEvent event) throws SQLException {
        String nome = textNomeCadastro.getText();
        String senha = textSenhaCadastro.getText();
        String confirmacaoSenha = textConfirmacao.getText();

        if (senha.equals(confirmacaoSenha)) {
            Usuario pessoa = new Usuario(nome, senha);
            Connection conexao = new Conexao().getConnection();
            UsuarioDao pessoaDao = new UsuarioDao(conexao);
            pessoaDao.insert(pessoa);
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            App.trocarTela(1);
            textNomeCadastro.setText("");
            textSenhaCadastro.setText("");
            textConfirmacao.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Senha diferente da confirmação de senha", "Falha",
                    JOptionPane.ERROR_MESSAGE);
            textSenhaCadastro.setText("");
            textConfirmacao.setText("");
        }

    }

}
