package tam.trabalho2DanielSantos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class listAll extends AppCompatActivity {

    private ArrayList<TaskAPI> listTask = new ArrayList<TaskAPI>();
    TaskAPI taskAPI;
    ArrayAdapter arrayAdapter;
    private DAO dao = new ClientRest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        FloatingActionButton returnButton = (FloatingActionButton) findViewById(R.id.retur);

        getTasks();

        ListView list = findViewById(R.id.listTask);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listTask);

        list.setAdapter(arrayAdapter);

        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getTasks() {
        listTask.clear();

        taskAPI = new TaskAPI(0,"","","",0,0);

        dao.getAllTask(taskAPI, new DAO.getAllTaskListener() {
            @Override
            public void onSuccess(List<TaskAPI> lists) {
                listTask.addAll(lists);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(listAll.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}