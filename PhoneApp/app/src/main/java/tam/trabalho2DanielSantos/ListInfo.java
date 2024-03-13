package tam.trabalho2DanielSantos;

import com.google.gson.annotations.SerializedName;

public class ListInfo {
    @SerializedName("id")
    int id;
    @SerializedName("nome")
    String nome;
    @SerializedName("owner")
    String owner;

    public ListInfo(int id, String nome, String owner) {
        this.id = id;
        this.nome = nome;
        this.owner = owner;
    }

    public ListInfo(){

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return nome;
    }
}
