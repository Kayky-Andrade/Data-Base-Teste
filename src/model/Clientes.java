package model;

public class Clientes {

private String nomeCli, telefoneCli, enderecoCli;
private int codCli;
public Clientes() {
}

public Clientes(int codCli, String nomeCli, String telefoneCli, String enderecoCli) {
    this.codCli = codCli;
    this.nomeCli = nomeCli;
    this.telefoneCli = telefoneCli;
    this.enderecoCli = enderecoCli;
}



public void setCodCli(int codCli) {
    this.codCli = codCli;
}

public int getCodCli() {
    return codCli;
}

public String getNomeCli() {
    return nomeCli;
}

public void setNomeCli(String nomeCli) {
    this.nomeCli = nomeCli;
}

public String getTelefoneCli() {
    return telefoneCli;
}

public void setTelefoneCli(String telefoneCli) {
    this.telefoneCli = telefoneCli;
}

public String getEnderecoCli() {
    return enderecoCli;
}

public void setEnderecoCli(String enderecoCli) {
    this.enderecoCli = enderecoCli;
}



}
