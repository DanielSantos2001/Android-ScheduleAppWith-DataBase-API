package tam.trabalho2DanielSantos;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class TaskAPI {

    @SerializedName("id")
    private int id;
    @SerializedName("descricao")
    private String descricao;
    @SerializedName("data")
    private String data;
    @SerializedName("list")
    private String list;
    @SerializedName("limite")
    private int passouLimite;
    @SerializedName("concluido")
    private int concluido;

    public TaskAPI(int id, String descricao, String data, String list, int passouLimite, int concluido) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.list = list;
        this.passouLimite = passouLimite;
        this.concluido = concluido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public int getPassouLimite() {
        return passouLimite;
    }

    public void setPassouLimite(int passouLimite) {
        this.passouLimite = passouLimite;
    }

    public int getConcluido() {
        return concluido;
    }

    public void setConcluido(int concluido) {
        this.concluido = concluido;
    }

    @Override
    public String toString() {
        return "descricao:'" + descricao + '\'' +
                "\ndata='" + data + '\'' +
                "\npassouLimite=" + passouLimite +
                "\nconcluido=" + concluido;
    }
}
