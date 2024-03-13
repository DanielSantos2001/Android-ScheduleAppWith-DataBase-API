package tam.trabalho2DanielSantos;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRest implements DAO {

    Service service;

    public ClientRest(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://danieltam3.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build());

        Retrofit retrofit = builder.build();

        service = retrofit.create(Service.class);
    }


    @Override
    public void userLogin(User user, loginListener listener) {
        Call<User> call = service.userLogin(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                switch (response.code()) {
                    case 401:
                    case 404:
                        listener.onError("Wrong Credentials");
                        break;
                    case 200:
                        User user = response.body();
                        if (user == null) {
                            listener.onError("Error: Null User");
                            return;
                        }
                        listener.onSuccess(user);
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t);
                listener.onError("Error: onFailure");
            }
        });

    }

    @Override
    public void userSignup(User user, signupListener listener) {
        Call<User> call = service.userSignup(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                switch (response.code()) {
                    case 404:
                        listener.onError("Wrong Credentials");
                        break;
                    case 200:
                        User user = response.body();
                        if (user == null) {
                            listener.onError("Error: Null User");
                            return;
                        }
                        listener.onSuccess(user);
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t);
                listener.onError("Error: onFailure");
            }
        });
    }

    @Override
    public void getList(ListInfo list, getListListener listener) {
        Call<List<ListInfo>> call = service.getList(list);

        call.enqueue(new Callback<List<ListInfo>>() {
            @Override
            public void onResponse(Call<List<ListInfo>> call, Response<List<ListInfo>> response) {
                switch (response.code()) {
                    case 200:
                        List<ListInfo> lists = response.body();
                        listener.onSuccess(lists);
                        break;
                    case 204:
                        listener.onError("Lista vazia!");
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ListInfo>> call, Throwable t) {
                System.out.println(t);
                listener.onError("Error: onFailure");
            }
        });
    }

    @Override
    public void criarList(ListInfo listInfo, criarListListener listener) {
        Call<ListInfo> call = service.criarList(listInfo);

        call.enqueue(new Callback<ListInfo>() {
            @Override
            public void onResponse(Call<ListInfo> call, Response<ListInfo> response) {
                switch (response.code()) {
                    case 200:
                        ListInfo l = response.body();
                        listener.onSuccess(l);
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ListInfo> call, Throwable t) {
                System.out.println(t);
                //listener.onError("Error: onFailure");
            }
        });
    }

    @Override
    public void removerList(ListInfo listInfo, removerListListener listener) {
        Call<ListInfo> call = service.removerList(listInfo);

        call.enqueue(new Callback<ListInfo>() {
            @Override
            public void onResponse(Call<ListInfo> call, Response<ListInfo> response) {
                switch (response.code()) {
                    case 200:
                        ListInfo l = response.body();
                        listener.onSuccess(l);
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ListInfo> call, Throwable t) {
                System.out.println(t);
                //listener.onError("Error: onFailure");
            }
        });
    }

    @Override
    public void atualizarNomeList(ListInfo listInfo, atualizarNomeListListener listener) {
        Call<ListInfo> call = service.atualizarNomeList(listInfo);

        call.enqueue(new Callback<ListInfo>() {
            @Override
            public void onResponse(Call<ListInfo> call, Response<ListInfo> response) {
                switch (response.code()) {
                    case 200:
                        ListInfo l = response.body();
                        listener.onSuccess(l);
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ListInfo> call, Throwable t) {
                System.out.println(t);
                //listener.onError("Error: onFailure");
            }
        });
    }

    @Override
    public void getTask(TaskAPI task, getTaskListener listener) {
        Call<List<TaskAPI>> call = service.getTask(task);

        call.enqueue(new Callback<List<TaskAPI>>() {
            @Override
            public void onResponse(Call<List<TaskAPI>> call, Response<List<TaskAPI>> response) {
                switch (response.code()) {
                    case 200:
                        List<TaskAPI> tasks = response.body();
                        listener.onSuccess(tasks);
                        break;
                    case 204:
                        listener.onError("Lista vazia!");
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<TaskAPI>> call, Throwable t) {
                System.out.println(t);
                listener.onError("Error: onFailure");
            }
        });
    }

    @Override
    public void criarTask(TaskAPI task, criarTaskListener listener) {
        Call<TaskAPI> call = service.criarTask(task);

        call.enqueue(new Callback<TaskAPI>() {
            @Override
            public void onResponse(Call<TaskAPI> call, Response<TaskAPI> response) {
                switch (response.code()) {
                    case 200:
                        TaskAPI ta = response.body();
                        listener.onSuccess(ta);
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TaskAPI> call, Throwable t) {
                System.out.println(t);
                //listener.onError("Error: onFailure");
            }
        });
    }

    @Override
    public void removerTask(TaskAPI task, removerTaskListener listener) {
        Call<TaskAPI> call = service.removerTask(task);

        call.enqueue(new Callback<TaskAPI>() {
            @Override
            public void onResponse(Call<TaskAPI> call, Response<TaskAPI> response) {
                switch (response.code()) {
                    case 200:
                        TaskAPI t = response.body();
                        listener.onSuccess(t);
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TaskAPI> call, Throwable t) {
                System.out.println(t);
                //listener.onError("Error: onFailure");
            }
        });
    }

    @Override
    public void atualizarDescricaoTask(TaskAPI task, atualizarDescricaoTaskListener listener) {
        Call<TaskAPI> call = service.atualizarDescricaoTask(task);

        call.enqueue(new Callback<TaskAPI>() {
            @Override
            public void onResponse(Call<TaskAPI> call, Response<TaskAPI> response) {
                switch (response.code()) {
                    case 200:
                        TaskAPI t = response.body();
                        listener.onSuccess(t);
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TaskAPI> call, Throwable t) {
                System.out.println(t);
                //listener.onError("Error: onFailure");
            }
        });
    }

    @Override
    public void atualizarDataTask(TaskAPI task, atualizarDataTaskListener listener) {
        Call<TaskAPI> call = service.atualizarDataTask(task);

        call.enqueue(new Callback<TaskAPI>() {
            @Override
            public void onResponse(Call<TaskAPI> call, Response<TaskAPI> response) {
                switch (response.code()) {
                    case 200:
                        TaskAPI t = response.body();
                        listener.onSuccess(t);
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TaskAPI> call, Throwable t) {
                System.out.println(t);
                //listener.onError("Error: onFailure");
            }
        });
    }

    @Override
    public void atualizarLimiteTask(TaskAPI task, atualizarLimiteTaskListener listener) {
        Call<TaskAPI> call = service.atualizarLimiteTask(task);

        call.enqueue(new Callback<TaskAPI>() {
            @Override
            public void onResponse(Call<TaskAPI> call, Response<TaskAPI> response) {
                switch (response.code()) {
                    case 200:
                        TaskAPI t = response.body();
                        listener.onSuccess(t);
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TaskAPI> call, Throwable t) {
                System.out.println(t);
                //listener.onError("Error: onFailure");
            }
        });
    }

    @Override
    public void atualizarConcluidoTask(TaskAPI task, atualizarConcluidoTaskListener listener) {
        Call<TaskAPI> call = service.atualizarConcluidoTask(task);

        call.enqueue(new Callback<TaskAPI>() {
            @Override
            public void onResponse(Call<TaskAPI> call, Response<TaskAPI> response) {
                switch (response.code()) {
                    case 200:
                        TaskAPI t = response.body();
                        listener.onSuccess(t);
                        break;
                    default:
                        listener.onError("Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TaskAPI> call, Throwable t) {
                System.out.println(t);
                //listener.onError("Error: onFailure");
            }
        });
    }

    public void getAllTask(TaskAPI task, getAllTaskListener listener) {
        try {
            Call<List<TaskAPI>> call = service.getAllTask(task);

            call.enqueue(new Callback<List<TaskAPI>>() {
                @Override
                public void onResponse(Call<List<TaskAPI>> call, Response<List<TaskAPI>> response) {
                    switch (response.code()) {
                        case 200:
                            List<TaskAPI> tasks = response.body();
                            listener.onSuccess(tasks);
                            break;
                        case 204:
                            listener.onError("Lista vazia!");
                            break;
                        default:
                            listener.onError("Response code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<TaskAPI>> call, Throwable t) {
                    System.out.println(t);
                    listener.onError("Error: onFailure");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
