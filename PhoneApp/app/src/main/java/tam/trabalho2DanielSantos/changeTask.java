package tam.trabalho2DanielSantos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Calendar;

public class changeTask extends AppCompatActivity {

    TaskAPI taskAPI;
    private DAO dao = new ClientRest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_task);

        Button changeButton = (Button) findViewById(R.id.changeTaskButton);

        SharedPreferences prefs = getSharedPreferences("PrefsChangeTask", MODE_PRIVATE);

        TextView nameTask = findViewById(R.id.nameChangeTask);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        nameTask.setText(getIntent().getExtras().getString("descricao"));

        if (prefs.getString("descricao", null) != null) {
            nameTask.setText(prefs.getString("descricao", null));
        }

        Calendar data_limite = Calendar.getInstance();

        changeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String descricao = nameTask.getText().toString();

                String data = "" + datePicker.getYear() + "-" + datePicker.getMonth() + "-" + datePicker.getDayOfMonth() + "-" + timePicker.getHour() + "-" + timePicker.getMinute();

                int id = getIntent().getExtras().getInt("id");

                taskAPI = new TaskAPI(id,descricao,data,"",0,0);
                atualizarDescricao();
                atualizarData();

                finish();
            }
        });
    }

    public void atualizarDescricao(){
        dao.atualizarDescricaoTask(taskAPI, new DAO.atualizarDescricaoTaskListener() {
            @Override
            public void onSuccess(TaskAPI task) {
                Toast.makeText(changeTask.this, "Task atualiza com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(changeTask.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void atualizarData(){
        dao.atualizarDataTask(taskAPI, new DAO.atualizarDataTaskListener() {
            @Override
            public void onSuccess(TaskAPI task) {

            }

            @Override
            public void onError(String message) {

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

        SharedPreferences prefs = getSharedPreferences("PrefsChangeTask", MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        TextView nameTask = findViewById(R.id.nameChangeTask);

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