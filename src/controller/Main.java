package controller;

import dao.CRUD;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Clientes;
import model.Funcionarios;
import view.Tabela;

public class Main {

    //instancias
    public static Funcionarios fun = new Funcionarios();
    static Autenticacao objFun = new Autenticacao();
    public static Clientes cli = new Clientes();
    //o getClass não pode ser colocado no main e tambem não pode ser chamado no psvm
    static ImageIcon sk = new ImageIcon(Main.class.getResource("/img/SK.png"));
    

    public static void main(String[] args) {
        
        //declaração de variaveis
        ResultSet rsFun = null;
        String nome = null, tel = null, ende = null;
        sk.setImage(sk.getImage().getScaledInstance(125,125,Image.SCALE_SMOOTH));
        int prosseguir = 1;


        
        String nomeFun = JOptionPane.showInputDialog(null, "Funcionario:");
        try { 
            fun.setNomeFun(nomeFun);
          //vailida o funcionario
        rsFun = objFun.autenticarFun(fun);

        

        } catch (Exception e) {
        System.out.println("Erro ao pegar o nome do funcionario");
        }
        try{
//vailida o funcionario e da continuidade ao codigo
        if(rsFun.next()){
            //captura o codFun
        fun.setCodFun(rsFun.getInt("codFun"));
        System.out.println("Funcionário autenticado: codFun = " + fun.getCodFun());
//============================================================================================================================================
        //Escolhendo a ação que o funcionario deseja prosseguir
        Object acao[] = {"Cadastrar Cliente", "Consultar Livros", "Alugar Livro", "Devolver Livro"};
        String msg = ("Como Deseja Proseguir " + nomeFun); 
    do{
        int opcao = JOptionPane.showOptionDialog(null, msg, "Opções", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, sk, acao, acao); 
        
        if (opcao == 0) { //Cadastrar Cliente ===================================================

            nome = JOptionPane.showInputDialog(null, "Bem vindo a Biblioteca S.Kings" +
            "\n\nDigite seu nome: ");
        System.out.println(nome);
        cli.setNomeCli(nome);

        tel = JOptionPane.showInputDialog(null, "Digite seu Telefone: ");

        String telNovo = Metodos.formatarTelefone(tel);
        System.out.println(telNovo);
        cli.setTelefoneCli(telNovo);

        ende = JOptionPane.showInputDialog(null, "Digite seu endereço:");
        System.out.println(ende);
        cli.setEnderecoCli(ende);

        //metodo para cadastrar clientes
        CRUD.cadastrarClientes();

            //Capturar corretamente o codCli depois do cadastro
            try (Connection con = new Conexao().getConnection()) {
            String sql = "SELECT codCli FROM clientes WHERE telefoneCli = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, telNovo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Main.cli.setCodCli(rs.getInt("codCli"));
            }
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar codCli do cliente.");
            }

        }else if (opcao == 1){ //Listar livros ====================================================

            //Tabela.main(null);  Serve, porem o codigo vai executar e logo em seguida continuar a estrutura normalmente, atropelando a tabela 
            Tabela.mostrarTabela();  

        }else if(opcao == 2){
            
            Tabela.mostrarTabela(); 

        }else if(opcao == 3){

            Tabela.mostrarTabela(); 
        }



        //Verificações para voltar ao inicio e fechar  o codigo
        if(opcao == JOptionPane.CLOSED_OPTION){
            JOptionPane.showMessageDialog(null, "Encerrando aplicação\n Tenha um bom dia!", "Encerrando", JOptionPane.PLAIN_MESSAGE, sk);
            System.exit(0);
        }

            Object escolha[] = {"Sim", "Não"};
            int resultado = JOptionPane.showOptionDialog(null, "Deseja retornar a seleção de OPÇÕES?", "Retornar", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, sk, escolha, escolha[0]);
            if(resultado == 0){
                prosseguir = 0;
            }else{
                JOptionPane.showMessageDialog(null, "Encerrando aplicação\n Tenha um bom dia!", "Encerrando", JOptionPane.PLAIN_MESSAGE, sk);
                System.exit(0);
            }
          
          
    }while(prosseguir == 0);   

        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro inesperado" + "\n" + ex);
        }finally{
        if (rsFun != null)
            try {
                rsFun.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o ResultSet");
            }
        }

    }
}

