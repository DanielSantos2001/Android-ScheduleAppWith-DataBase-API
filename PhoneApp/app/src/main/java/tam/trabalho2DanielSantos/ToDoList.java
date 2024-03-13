package tam.trabalho2DanielSantos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class ToDoList extends AppCompatActivity {

    private ArrayList<TaskAPI> listTask = new ArrayList<TaskAPI>();
    TaskAPI taskAPI;
    ArrayAdapter arrayAdapter;
    ListView list;
    private DAO dao = new ClientRest();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);

        ArrayList<Task> task = new ArrayList<Task>();
        FloatingActionButton returnButton = (FloatingActionButton) findViewById(R.id.retur);
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addTask);

        getTasks();

        list = findViewById(R.id.listTask);

        AdapterFilter();

        list.setAdapter(arrayAdapter);

        listener(list);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayAdapter.notifyDataSetChanged();
                finish();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(getApplicationContext(), addTask.class);
                switchActivityIntent.putExtra("sessionList",getIntent().getExtras().getInt("sessionList"));
                startActivityForResult(switchActivityIntent, 1);

                arrayAdapter.notifyDataSetChanged();
                listener(list);
            }
        });
    }

    public void listener(ListView list) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ToDoList.this, "Selecionado: " + listTask.get(i).toString(), Toast.LENGTH_SHORT).show();
                taskAPI = (TaskAPI) adapterView.getItemAtPosition(i);
                Intent switchActivityIntent = new Intent(getApplicationContext(), TaskInfo.class);
                switchActivityIntent.putExtra("id", listTask.get(i).getId());
                switchActivityIntent.putExtra("descricao", listTask.get(i).getDescricao());
                switchActivityIntent.putExtra("data", listTask.get(i).getData());
                switchActivityIntent.putExtra("list", listTask.get(i).getList());
                switchActivityIntent.putExtra("atrasado", listTask.get(i).getPassouLimite());
                switchActivityIntent.putExtra("concluido", listTask.get(i).getConcluido());
                startActivityForResult(switchActivityIntent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(listTask!=null) {
            for (int i = 0; i < listTask.size(); i++) {
                Calendar time = Calendar.getInstance();
                String d = listTask.get(i).getData();
                String da[] = d.split("-");
                time.set(Integer.parseInt(da[0]),Integer.parseInt(da[1]),Integer.parseInt(da[2]),Integer.parseInt(da[3]),Integer.parseInt(da[4]));
                System.out.println(time.getTime());
                if (time.before(Calendar.getInstance())) {
                    System.out.println("Aqui");
                    taskAPI.setPassouLimite(1);
                    atualizarLimite();
                }
            }
        }
        getTasks();
        arrayAdapter.notifyDataSetChanged();
    }

    public void atualizarLimite(){
        dao.atualizarLimiteTask(taskAPI, new DAO.atualizarLimiteTaskListener() {
            @Override
            public void onSuccess(TaskAPI task) {
            }

            @Override
            public void onError(String message) {
                //Toast.makeText(ToDoList.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AdapterFilter() {
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listTask);
        list.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    public void getTasks() {
        listTask.clear();

        taskAPI = new TaskAPI(0,"","",String.valueOf(getIntent().getExtras().getInt("sessionList")),0,0);

        dao.getTask(taskAPI, new DAO.getTaskListener() {
            @Override
            public void onSuccess(List<TaskAPI> lists) {
                listTask.addAll(lists);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(ToDoList.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
