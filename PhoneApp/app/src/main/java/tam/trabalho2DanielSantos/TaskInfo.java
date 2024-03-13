package tam.trabalho2DanielSantos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class TaskInfo extends AppCompatActivity {

    TaskAPI taskAPI;
    private DAO dao = new ClientRest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        FloatingActionButton returnButton = (FloatingActionButton) findViewById(R.id.retur);
        FloatingActionButton changeButton = (FloatingActionButton) findViewById(R.id.changeTask);
        FloatingActionButton deleteButton = (FloatingActionButton) findViewById(R.id.deleteTask);
        Button concluidoButton = (Button) findViewById(R.id.concluidoButton);

        TextView info = findViewById(R.id.taskinfo);

        taskAPI = new TaskAPI(getIntent().getExtras().getInt("id"),getIntent().getExtras().getString("descricao"),getIntent().getExtras().getString("data"),"list",getIntent().getExtras().getInt("atrasado"),getIntent().getExtras().getInt("concluido"));
        info.setText(taskAPI.toString());

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(1, resultIntent);
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                taskAPI = new TaskAPI(getIntent().getExtras().getInt("id"),"","","",0,0);
                dao.removerTask(taskAPI, new DAO.removerTaskListener() {
                    @Override
                    public void onSuccess(TaskAPI task) {
                        Toast.makeText(TaskInfo.this, "Eliminado com sucesso!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(TaskInfo.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

                Intent resultIntent = new Intent();
                setResult(3, resultIntent);
                finish();
            }
        });

        changeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(getApplicationContext(), changeTask.class);
                switchActivityIntent.putExtra("id",getIntent().getExtras().getInt("id"));
                switchActivityIntent.putExtra("descricao",getIntent().getExtras().getString("descricao"));
                startActivity(switchActivityIntent);

                Intent resultIntent = new Intent();
                setResult(1, resultIntent);
                finish();
            }
        });

        concluidoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                taskAPI = new TaskAPI(getIntent().getExtras().getInt("id"),"","","",0,1);
                dao.atualizarConcluidoTask(taskAPI, new DAO.atualizarConcluidoTaskListener() {
                    @Override
                    public void onSuccess(TaskAPI task) {
                        Toast.makeText(TaskInfo.this, "Concluido com sucesso!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(TaskInfo.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

                Intent resultIntent = new Intent();
                setResult(1, resultIntent);
                finish();
            }
        });
    }

}