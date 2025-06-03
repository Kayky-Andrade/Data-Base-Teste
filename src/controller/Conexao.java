package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    public static Connection getConnection(){

        try {            
            return DriverManager.getConnection("jdbc:mysql://localhost/king", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão com o DataBase", "ERRO", JOptionPane.PLAIN_MESSAGE);
        }
        return null;
    }


    public static void main(String[] args) {
        
        if(getConnection() != null){
            JOptionPane.showMessageDialog(null, "Conexão Obtida com Sucesso", "Sucess", JOptionPane.PLAIN_MESSAGE);
        }


    }

}