package tam.trabalho2DanielSantos;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    int id;
    @SerializedName("nome")
    String nome;
    @SerializedName("senha")
    String senha;
    @SerializedName("Token")
    String Token;

    public User(int id, String nome, String senha, String Token) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.Token = Token;
    }

    public User(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public User(){

    }
    public User(String Token){
        this.Token = Token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return senha;
    }

    public void setPassword(String password) {
        this.senha = senha;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }
}
