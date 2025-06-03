package model;

public class Livros {

private String nomeLivro, autorLivro, quantLivro,generoLivro;

public Livros() {
}

public Livros(String nomeLivro, String autorLivro, String quantLivro, String generoLivro) {
    this.nomeLivro = nomeLivro;
    this.autorLivro = autorLivro;
    this.quantLivro = quantLivro;
    this.generoLivro = generoLivro;
}

public String getNomeLivro() {
    return nomeLivro;
}

public void setNomeLivro(String nomeLivro) {
    this.nomeLivro = nomeLivro;
}

public String getAutorLivro() {
    return autorLivro;
}

public void setAutorLivro(String autorLivro) {
    this.autorLivro = autorLivro;
}

public String getQuantLivro() {
    return quantLivro;
}

public void setQuantLivro(String quantLivro) {
    this.quantLivro = quantLivro;
}

public String getGeneroLivro() {
    return generoLivro;
}

public void setGeneroLivro(String generoLivro) {
    this.generoLivro = generoLivro;
}

}
