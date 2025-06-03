package dao;

import controller.Conexao;
import controller.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.Tabela;

public class CRUD {

    //=========================================CRUD C (Create)============================================================
public static void cadastrarClientes(){
//Clientes cli = new Clientes();

try (Connection con = new Conexao().getConnection()){

    String sql = "Insert into clientes (nomeCli, telefoneCli, enderecoCli) Values (?,?,?)";
    PreparedStatement stmt = con.prepareStatement(sql);

    //A classe main tem a instaancia Clientes como static entt posso chamar ela aqui dessa forma
    stmt.setString(1, Main.cli.getNomeCli());
 
    stmt.setString(2, Main.cli.getTelefoneCli());
    
    stmt.setString(3, Main.cli.getEnderecoCli());

    stmt.executeUpdate();
    JOptionPane.showMessageDialog(null, "Contato Inserido", "Inserido", JOptionPane.PLAIN_MESSAGE);

}catch(Exception e){
System.out.println("Erro ao gravar o cadastro de cliente!\nMetodo Cadastrar");
}
}

//===================================== CRUD R (Read) ==========================================

    public static void listar(){

        //Início para acessar o BataBase
        //Implementação de modelo para a tabela livros
        //Necessário Importação 

        DefaultTableModel modelo = (DefaultTableModel)Tabela.tabelaLivros.getModel();
        //setRowCount(0) deixa a tabela sem valores, para iniciar com nada
        modelo.setRowCount(0);

        Connection con = new Conexao().getConnection();

        String sql = "Select * From livros";
        try {

            PreparedStatement stmt = con.prepareStatement(sql);
            //executar o comando de pesquisa
            ResultSet rs = stmt.executeQuery();

            //estrutura para percorrer a tabela e colocar os registros na listagem

            while (rs.next()) {
                //A String tem que estar com o msm nome da coluna no DataBase
                String codigo = rs.getString("codLivro");
                String nome = rs.getString("nomeLivro");
                String autor = rs.getString("autorLivro");
                String quantidade = rs.getString("quantLivro");
                String genero = rs.getString("generoLivro");
                modelo.addRow(new Object[]{codigo, nome,autor,quantidade,genero});
                }

            //fecha o DataBase e sua Conexão
            rs.close();
            stmt.close();
            con.close();
                if (rs.isClosed() && stmt.isClosed() && con.isClosed()){
                    System.out.println("Todas as conexões fechadas com o DataBase");
                }else{
                    System.out.println("Conexões com Servidor ainda abertas - ERRO!!");
                }

        } catch (SQLException e) {
            System.out.println("Erro ao Listar no CRUD");
        }

    }

    public static void selecionarClienteExistente() {
        try (Connection con = new Conexao().getConnection()) {
            String sql = "SELECT codCli, nomeCli FROM clientes";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
    
            java.util.List<String> nomes = new java.util.ArrayList<>();
            java.util.Map<String, Integer> mapaCodigos = new java.util.HashMap<>();
    
            while (rs.next()) {
                int cod = rs.getInt("codCli");
                String nome = rs.getString("nomeCli");
                String item = cod + " - " + nome;
                nomes.add(item);
                mapaCodigos.put(item, cod);
            }
    
            if (nomes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há clientes cadastrados.");
                return;
            }
    
            String escolhido = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione um cliente:",
                    "Clientes Existentes",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    nomes.toArray(),
                    nomes.get(0)
            );
    
            if (escolhido != null) {
                int codEscolhido = mapaCodigos.get(escolhido);
                Main.cli.setCodCli(codEscolhido);
                JOptionPane.showMessageDialog(null, "Cliente selecionado: " + escolhido);
            }
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar clientes: " + e.getMessage());
        }
    }
    


    
    //===================================== CRUD D (Delete) ==========================================

    
    public static void Alugar() {
        int linhaSelecionada = Tabela.tabelaLivros.getSelectedRow();
    
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um livro para alugar", "Alugar", JOptionPane.PLAIN_MESSAGE);
            return;
        }
    
        int codLivro = Integer.parseInt(Tabela.tabelaLivros.getValueAt(linhaSelecionada, 0).toString());
        int quantLivro = Integer.parseInt(Tabela.tabelaLivros.getValueAt(linhaSelecionada, 3).toString());
    
        if (quantLivro <= 0) {
            JOptionPane.showMessageDialog(null, "Livro indisponível para aluguel", "Indisponibilidade", JOptionPane.PLAIN_MESSAGE);
            return;
        }
    
        int codCli = Main.cli.getCodCli();
        int codFun = Main.fun.getCodFun(); // Você precisa ter isso preenchido no login
        System.out.println("Usando codFun = " + Main.fun.getCodFun());
        try (Connection con = new Conexao().getConnection()) {
            // Inserir na tabela venda
            String insertVenda = "INSERT INTO venda (dataVenda, codLivro, codCli, codFun) VALUES (CURDATE(), ?, ?, ?)";
            PreparedStatement psInsert = con.prepareStatement(insertVenda);
            psInsert.setInt(1, codLivro);
            psInsert.setInt(2, codCli);
            psInsert.setInt(3, codFun);
            psInsert.executeUpdate();
    
            // Atualizar livros
            String updateLivro = "UPDATE livros SET quantLivro = quantLivro - 1 WHERE codLivro = ?";
            PreparedStatement psUpdate = con.prepareStatement(updateLivro);
            psUpdate.setInt(1, codLivro);
            psUpdate.executeUpdate();
    
            JOptionPane.showMessageDialog(null, "Livro alugado com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
            Tabela.tabelaLivros.setValueAt(quantLivro - 1, linhaSelecionada, 3);
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alugar livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    
    }


    //===================================== CRUD U (Update) ==========================================

    public static void Devolver() {
        int linhaSelecionada = Tabela.tabelaLivros.getSelectedRow();
    
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um livro para devolver", "Devolver", JOptionPane.PLAIN_MESSAGE);
            return;
        }
    
        int codLivro = Integer.parseInt(Tabela.tabelaLivros.getValueAt(linhaSelecionada, 0).toString());
        int qtdAtual = Integer.parseInt(Tabela.tabelaLivros.getValueAt(linhaSelecionada, 3).toString());
        int codCli = Main.cli.getCodCli();
    
        try (Connection con = new Conexao().getConnection()) {
            // Verifica se o cliente realmente alugou o livro
            String verificarVenda = "SELECT codVenda FROM venda WHERE codLivro = ? AND codCli = ? LIMIT 1";
            PreparedStatement psVerifica = con.prepareStatement(verificarVenda);
            psVerifica.setInt(1, codLivro);
            psVerifica.setInt(2, codCli);
    
            ResultSet rs = psVerifica.executeQuery();
    
            if (rs.next()) {
                int codVenda = rs.getInt("codVenda");
    
                // Remove da tabela venda
                String deletarVenda = "DELETE FROM venda WHERE codVenda = ?";
                PreparedStatement psDelete = con.prepareStatement(deletarVenda);
                psDelete.setInt(1, codVenda);
                psDelete.executeUpdate();
    
                // Atualiza livros
                String atualizarLivro = "UPDATE livros SET quantLivro = quantLivro + 1 WHERE codLivro = ?";
                PreparedStatement psUpdate = con.prepareStatement(atualizarLivro);
                psUpdate.setInt(1, codLivro);
                psUpdate.executeUpdate();
    
                JOptionPane.showMessageDialog(null, "Livro devolvido com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
                Tabela.tabelaLivros.setValueAt(qtdAtual + 1, linhaSelecionada, 3);
    
            } else {
                JOptionPane.showMessageDialog(null, "Este cliente não possui este livro alugado.", "Erro", JOptionPane.PLAIN_MESSAGE);
            }
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao devolver livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
