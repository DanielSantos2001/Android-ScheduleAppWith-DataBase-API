package tam.trabalho2DanielSantos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class changeList extends AppCompatActivity {

    String nameList;
    private DAO dao = new ClientRest();
    ListInfo listInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_list);

        Button changeButton = (Button) findViewById(R.id.ChangeListButton);

        changeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView list = findViewById(R.id.changeNameList);
                nameList = list.getText().toString();

                SharedPreferences prefs = getSharedPreferences("PrefsChangeList", MODE_PRIVATE);

                if (prefs.getString("descricao", null) != null) {
                    list.setText(prefs.getString("descricao", null));
                }

                atualizarLista();

                Intent resultIntent = new Intent();
                setResult(1, resultIntent);
                finish();
            }
        });
    }

    public void atualizarLista(){
        int id = getIntent().getExtras().getInt("oldId");
        listInfo = new ListInfo(id,nameList,"");
        dao.atualizarNomeList(listInfo, new DAO.atualizarNomeListListener() {
            @Override
            public void onSuccess(ListInfo listInfo) {
                Toast.makeText(changeList.this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(changeList.this, message, Toast.LENGTH_SHORT).show();
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

        SharedPreferences prefs = getSharedPreferences("PrefsChangeList", MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        TextView nameTask = findViewById(R.id.changeNameList);

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