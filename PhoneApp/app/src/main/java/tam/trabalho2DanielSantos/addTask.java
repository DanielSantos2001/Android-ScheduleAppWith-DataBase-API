package tam.trabalho2DanielSantos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class addTask extends AppCompatActivity {

    Task task;
    private DAO dao = new ClientRest();
    TaskAPI taskAPI;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button addButton = (Button) findViewById(R.id.addTaskButton);

        SharedPreferences prefs = getSharedPreferences("PrefsAddTask", MODE_PRIVATE);

        TextView nameTask = findViewById(R.id.nameTask);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        if (prefs.getString("descricao", null) != null) {
            nameTask.setText(prefs.getString("descricao", null));
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String descricao = nameTask.getText().toString();

                String data = "" + datePicker.getYear() + "-" + datePicker.getMonth() + "-" + datePicker.getDayOfMonth() + "-" + timePicker.getHour() + "-" + timePicker.getMinute();

                String sessionList = String.valueOf(getIntent().getExtras().getInt("sessionList"));

                taskAPI = new TaskAPI(0,descricao,data,sessionList,0,0);
                System.out.println(taskAPI);

                addTask();

                finish();
            }
        });
    }

    public void addTask(){

        dao.criarTask(taskAPI, new DAO.criarTaskListener() {
            @Override
            public void onSuccess(TaskAPI task) {
                Toast.makeText(addTask.this, "Task adicionada com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(addTask.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();

        SharedPreferences prefs = getSharedPreferences("PrefsAddTask", MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        TextView nameTask = findViewById(R.id.nameTask);

        editor.putString("description", nameTask.getText().toString());

        editor.commit();
    }

    public void onStop() {
        super.onStop();
    }

    public void onRestart() {
        super.onRestart();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
    }

    public void onUserLeaveHint() {
        super.onUserLeaveHint();
    }
}