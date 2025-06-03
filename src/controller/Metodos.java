package controller;

import javax.swing.JOptionPane;

public class Metodos {

//===================================================== Método de Formatar Telefone =======================================================
public static String formatarTelefone(String telefoneNovo) {
    telefoneNovo = telefoneNovo.replaceAll("\\D", ""); // Remove tudo que não for número

    do {
        if (telefoneNovo.length() == 11) { 
            // Formata como '(xx) xxxxx-xxxx'
            return String.format("(%s) %s-%s", 
                telefoneNovo.substring(0, 2), 
                telefoneNovo.substring(2, 7), 
                telefoneNovo.substring(7));
        } else {
            // Se não tiver 11 dígitos, pede de novo
            telefoneNovo = JOptionPane.showInputDialog(null, 
                "O telefone deve conter DDD brasileiro (2 dígitos) e o número (9 dígitos).\nExemplo: (11) 91234-5678", 
                "Telefone", JOptionPane.INFORMATION_MESSAGE);
            // Remove novamente qualquer coisa que não seja número
            telefoneNovo = telefoneNovo.replaceAll("\\D", "");
        }
    } while (telefoneNovo.length() != 11); // Continua pedindo até ter 11 dígitos

    return telefoneNovo; // Retorna o telefone formatado
}
//================================================================================================================================
}
