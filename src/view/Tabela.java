package view;

    import dao.CRUD;
    import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
    
    public class Tabela extends JFrame {
        //public static no JTable é uma má prática!!!!!!!
        //Corrigir dps
        public static JTable tabelaLivros;
        private JButton btnListar;
        private JButton btnExcluir;
        private JButton btnAtualizar;
    
        public Tabela() {
            setTitle("Biblioteca - Livros");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null); // Centraliza a tela
            
            // Layout principal
            setLayout(new BorderLayout());
    
            // Criar a tabela
            String[] colunas = {"Código","Nome", "Autor", "Quantidade", "Gênero"};
            DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
            tabelaLivros = new JTable(modeloTabela);
            JScrollPane scrollPane = new JScrollPane(tabelaLivros);
            add(scrollPane, BorderLayout.CENTER);
    
            // Painel dos botões inferiores
            JPanel painelBotoes = new JPanel();
            painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centralizado e espaçado
    
            btnListar = new JButton("Listar");
            btnExcluir = new JButton("Devolver");
            btnAtualizar = new JButton("Alugar");
    
            painelBotoes.add(btnListar);
            painelBotoes.add(btnAtualizar);
            painelBotoes.add(btnExcluir);
    
            add(painelBotoes, BorderLayout.SOUTH);
    
            // Deixar visível
            setVisible(true);

            
             
            btnListar.addActionListener(e -> CRUD.listar());

            btnAtualizar.addActionListener(e -> {
                int linhaSelecionada = tabelaLivros.getSelectedRow();
                if (linhaSelecionada != -1) {
                    CRUD.selecionarClienteExistente(); 
                    CRUD.Alugar();  
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione algum livro", "Selecione", JOptionPane.PLAIN_MESSAGE);
                }
                
            });
            btnExcluir.addActionListener(e -> {
                int linhaSelecionada = tabelaLivros.getSelectedRow();
                if (linhaSelecionada != -1) {
                CRUD.selecionarClienteExistente(); 
                CRUD.Devolver();
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione algum livro", "Selecione", JOptionPane.PLAIN_MESSAGE);

                }
        });
    
        }

            //Mostra a janela e espera ela ser fechada=====================================================
            public static void mostrarTabela() {
                Tabela janela = new Tabela();
                janela.setVisible(true);
        
                // Aguarda até a janela ser fechada
                while (janela.isVisible()) {
                    try {
                        Thread.sleep(100); // Pausa para não travar o processador
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            
        }
    
        public static void main(String[] args) {
            SwingUtilities.invokeLater(Tabela::new);
        }
    }




