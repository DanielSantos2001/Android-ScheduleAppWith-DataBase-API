package tam.trabalho2DanielSantos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Lists extends AppCompatActivity {

    private ArrayList<ListInfo> listListInfo = new ArrayList<ListInfo>();
    private ListInfo listInfo;
    private DAO dao = new ClientRest();
    private int sessionList;
    ArrayAdapter arrayAdapter;
    String nome= "Nome";
    TextView nomeTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = findViewById(R.id.listTask);
        list.setOnItemClickListener(null);

        FloatingActionButton addButton = findViewById(R.id.addList);
        FloatingActionButton changeButton = findViewById(R.id.changeList);
        FloatingActionButton deleteButton = findViewById(R.id.deleteList);
        FloatingActionButton listAllButton = findViewById(R.id.listAll);

        FloatingActionButton aboutItButton = findViewById(R.id.AboutIt);
        FloatingActionButton LogoutButton = findViewById(R.id.changeNomeUtilizador);

        nomeTextView = findViewById(R.id.nomeUtilizador);
        nomeTextView.setText(Login.sessionUser.getNome());

        getList();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listListInfo);

        list.setAdapter(arrayAdapter);

        listener(list);

        arrayAdapter.notifyDataSetChanged();

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(getApplicationContext(), addList.class);
                startActivityForResult(switchActivityIntent, 1);
            }
        });

        listAllButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(getApplicationContext(), listAll.class);
                startActivity(switchActivityIntent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(Lists.this, "Selecione lista para eliminar", Toast.LENGTH_SHORT).show();
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (!taskExists(listListInfo.get(i).getId())) {
                            listInfo = new ListInfo(listListInfo.get(i).getId(),"0",Login.sessionUser.getNome());

                            dao.removerList(listInfo, new DAO.removerListListener() {
                                @Override
                                public void onSuccess(ListInfo listInfo) {
                                    Toast.makeText(Lists.this, "Eliminado com sucesso!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(String message) {
                                    Toast.makeText(Lists.this, message, Toast.LENGTH_SHORT).show();
                                }
                            });
                            getList();
                        } else {
                            Toast.makeText(Lists.this, "Existem tarefas na lista", Toast.LENGTH_SHORT).show();
                        }
                        listener(list);
                    }
                });
            }
        });

        changeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(Lists.this, "Selecione lista para mudar", Toast.LENGTH_SHORT).show();
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (!taskExists(listListInfo.get(i).getId())) {

                            Intent switchActivityIntent = new Intent(getApplicationContext(), changeList.class);
                            switchActivityIntent.putExtra("oldName", listListInfo.get(i).getNome());
                            switchActivityIntent.putExtra("oldId", listListInfo.get(i).getId());
                            startActivityForResult(switchActivityIntent, 1);
                        } else {
                            Toast.makeText(Lists.this, "Existem tarefas na lista", Toast.LENGTH_SHORT).show();
                        }
                        listener(list);
                    }
                });
            }
        });

        aboutItButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    Intent switchActivityIntent = new Intent(getApplicationContext(), aboutIt.class);
                    startActivityForResult(switchActivityIntent, 1);
            }
        });

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(1, resultIntent);
                finish();
            }
        });
    }

    public void listener(ListView list) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Lists.this, "Selecionado: " + listListInfo.get(i), Toast.LENGTH_SHORT).show();
                sessionList = listListInfo.get(i).getId();
                System.out.println(sessionList);
                Intent switchActivityIntent = new Intent(getApplicationContext(), ToDoList.class);
                switchActivityIntent.putExtra("sessionList",sessionList);
                startActivity(switchActivityIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getList();
        arrayAdapter.notifyDataSetChanged();
    }

    public boolean taskExists(int id) {
        if (listListInfo != null) {
            /*for (int i = 0; i < listList.size(); i++) {
                if (listList.get(id).getId() == ) {
                    return true;
                }
            }*/
        }
        return false;
    }

    public void getList() {
        listListInfo.clear();
        System.out.println(Login.sessionUser.getNome());
        listInfo = new ListInfo(0,"123",Login.sessionUser.getNome());

        dao.getList(listInfo, new DAO.getListListener() {
            @Override
            public void onSuccess(List<ListInfo> lists) {
                listListInfo.addAll(lists);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(Lists.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}