package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
import model.Funcionarios;

public class Autenticacao {

Connection con;

public ResultSet autenticarFun (Funcionarios pessoa) throws SQLException{

    con = new Conexao().getConnection();
        
    try{

        String sql = "select * from funcionarios WHERE nomeFun=?";
        //faz a string SQL rodar
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,pessoa.getNomeFun());

        ResultSet rs = pstm.executeQuery();
        return rs;

    }catch(SQLException ex){
        System.out.println("Erro no retorno da classe Autenticacao do ResultSet");
        return null;
    }
}
}