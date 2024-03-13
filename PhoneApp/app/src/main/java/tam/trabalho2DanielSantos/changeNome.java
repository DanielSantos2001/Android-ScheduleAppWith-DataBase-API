package tam.trabalho2DanielSantos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class changeNome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_nome);

        SharedPreferences prefs = getSharedPreferences("PrefsChangeNome", MODE_PRIVATE);

        Button changeButton = (Button) findViewById(R.id.ChangeNameButton);


        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nomeText = findViewById(R.id.Name);
                String nome = nomeText.getText().toString();

                if (prefs.getString("nomeText", null) != null) {
                    nomeText.setText(prefs.getString("nomeText", null));
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("nome",nome);
                setResult(2, resultIntent);
                finish();
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

        SharedPreferences prefs = getSharedPreferences("PrefsChangeNome", MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        EditText nomeText = findViewById(R.id.Name);

        editor.putString("nomeText", nomeText.getText().toString());

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