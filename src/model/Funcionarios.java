package model;

public class Funcionarios {

private String nomeFun, telefoneFun;
private int codFun;
public Funcionarios() {
}

public Funcionarios(int codFun, String nomeFun, String telefoneFun) {
    this.codFun = codFun;
    this.nomeFun = nomeFun;
    this.telefoneFun = telefoneFun;
}



public String getNomeFun() {
    return nomeFun;
}

public void setNomeFun(String nomeFun) {
    this.nomeFun = nomeFun;
}

public String getTelefoneFun() {
    return telefoneFun;
}

public void setTelefoneFun(String telefoneFun) {
    this.telefoneFun = telefoneFun;
}

public int getCodFun() {
    return codFun;
}

public void setCodFun(int codFun) {
    this.codFun = codFun;
}



}
