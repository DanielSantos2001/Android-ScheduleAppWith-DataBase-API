package tam.trabalho2DanielSantos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class addList extends AppCompatActivity {

    String nameList;
    private DAO dao = new ClientRest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        Button addButton = (Button) findViewById(R.id.AddListButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView list = findViewById(R.id.nameList);
                nameList = list.getText().toString();

                SharedPreferences prefs = getSharedPreferences("PrefsAddList", MODE_PRIVATE);

                if (prefs.getString("descricao", null) != null) {
                    list.setText(prefs.getString("descricao", null));
                }

                addList();

                Intent resultIntent = new Intent();
                setResult(1, resultIntent);
                finish();
            }
        });
    }

    public void addList(){
        ListInfo listInfo = new ListInfo();
        String nome = nameList;
        String owner = Login.sessionUser.getNome();
        //listInfo = new ListInfo(nome,owner);
        listInfo.setNome(nome);
        listInfo.setOwner(owner);
        System.out.println(listInfo);
        System.out.println(owner);

        dao.criarList(listInfo, new DAO.criarListListener() {
            @Override
            public void onSuccess(ListInfo listInfo) {
                Toast.makeText(addList.this, "Lista adicionada com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(addList.this, message, Toast.LENGTH_SHORT).show();
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

        SharedPreferences prefs = getSharedPreferences("PrefsAddList", MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        TextView nameTask = findViewById(R.id.nameList);

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