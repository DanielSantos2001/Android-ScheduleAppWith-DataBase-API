package tam.trabalho2DanielSantos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private DAO dao = new ClientRest();
    public static User sessionUser = new User();
    String username;
    String password;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.LoginButton);
        Button signupButton = (Button) findViewById(R.id.SignupButton);

        /*SharedPreferences prefs = getSharedPreferences("userSettings", MODE_PRIVATE);
        String name = prefs.getString("nome", null);
        String pass = prefs.getString("pass", null);

        if (name != null && !name.isEmpty() && pass != null && !pass.isEmpty()) {
            user = new User(name,pass);
            Login.sessionUser.setNome(name);
            Login.sessionUser.setPassword(pass);
            login();
        }*/

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView nome = findViewById(R.id.usernameLogin);
                username = nome.getText().toString();
                TextView pass = findViewById(R.id.passwordLogin);
                password = pass.getText().toString();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(Login.this, "Campos username e password obrigatórios!", Toast.LENGTH_SHORT).show();
                } else {
                    Login.sessionUser.setNome(username);
                    Login.sessionUser.setPassword(password);
                    login();
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView nome = findViewById(R.id.usernameLogin);
                username = nome.getText().toString();
                TextView pass = findViewById(R.id.passwordLogin);
                password = pass.getText().toString();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(Login.this, "Campos username e password obrigatórios!", Toast.LENGTH_SHORT).show();
                } else {
                    signup();
                }

                Toast.makeText(Login.this, "Registado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login(){
        user = new User(username,password);
        dao.userLogin(user, new DAO.loginListener() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(Login.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

                /*SharedPreferences prefs = getSharedPreferences("userSettings", MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("nome", Login.sessionUser.getNome());
                editor.putString("pass", Login.sessionUser.getPassword());

                editor.commit();*/

                Intent switchActivityIntent = new Intent(getApplicationContext(), Lists.class);
                startActivityForResult(switchActivityIntent, 1);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signup(){
        user = new User(username,password);
        dao.userSignup(user, new DAO.signupListener() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(Login.this, "Signup realizado com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}