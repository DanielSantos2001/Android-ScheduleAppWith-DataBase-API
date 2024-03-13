package tam.trabalho2DanielSantos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class Task {

    @SerializedName("id")
    private int id;
    @SerializedName("descricao")
    private String descricao;
    @SerializedName("data")
    private String data;
    @SerializedName("data_limite")
    private Calendar data_limite;
    @SerializedName("list")
    private String list;
    @SerializedName("limite")
    private int passouLimite;
    @SerializedName("concluido")
    private int concluido;

    public Task(int id, String descricao, String data, Calendar data_limite, String list, int passouLimite, int concluido) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.data_limite = data_limite;
        this.list = list;
        this.passouLimite = passouLimite;
        this.concluido = concluido;
    }

    public Task(String descricao, Calendar data_limite, String list, int passouLimite, int concluido) {
        this.descricao = descricao;
        this.data_limite = data_limite;
        this.list = list;
        this.passouLimite = passouLimite;
        this.concluido = concluido;
    }

    public Task(String descricao, String data, String list, int passouLimite, int concluido) {
        this.descricao = descricao;
        this.data = data;
        this.list = list;
        this.passouLimite = passouLimite;
        this.concluido = concluido;
    }

    public Task(int id, String descricao, Calendar data_limite, String list, int passouLimite, int concluido) {
        this.id = id;
        this.descricao = descricao;
        this.data_limite = data_limite;
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

    public Calendar getData_limite() {
        return data_limite;
    }

    public void setData_limite(Calendar data_limite) {
        this.data_limite = data_limite;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public int getConcluido() {
        return concluido;
    }

    public void setConcluido(int concluido) {
        this.concluido = concluido;
    }

    public int getPassouLimite() {
        return passouLimite;
    }

    public void setPassouLimite(int passouLimite) {
        this.passouLimite = passouLimite;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Nome= " + descricao + '\n' +
                "Data limite= " + data_limite.getTime() + '\n' +
                "Atrasado= " + passouLimite + '\n' +
                "Concluído= " + concluido + '\n';
    }

    public String toString2() {
        return "Task{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                ", data_limite=" + data_limite +
                ", list='" + list + '\'' +
                ", passouLimite=" + passouLimite +
                ", concluido=" + concluido +
                '}';
    }
}
