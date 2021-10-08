package br.com.zup;

public class Morador extends Pessoa{
    private String telefone;

    public Morador(String nome, String cpf, String telefone) {
        super(nome, cpf);
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder();
        retorno.append("Por favor, digite o telefone do morador: ");
        return retorno.toString();
    }
}
