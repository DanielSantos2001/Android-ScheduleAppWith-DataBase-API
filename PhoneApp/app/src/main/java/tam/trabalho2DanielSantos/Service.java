package tam.trabalho2DanielSantos;

import java.util.List;
import retrofit2.*;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

//loginUser (POST), signup (POST)
//criar_lista(POST), get_lists(POST), remover_list(POST), atualizar_nome_lista(PUT)
//criar_task(POST), get_tasks(GET), remover_task(POST), atualizar_descricao_task(PUT), atualizar_data_task(PUT), atualizar_limite_task(PUT), atualizar_concluido_task(PUT)
public interface Service {

    //USERS

    @Headers("Accept: application/json")
    @POST("loginUser")
    Call<User> userLogin(@Body User user);

    @Headers("Accept: application/json")
    @POST("signup")
    Call<User> userSignup(@Body User user);

    //LISTAS

    @Headers("Accept: application/json")
    @POST("get_lists")
    Call<List<ListInfo>> getList(@Body ListInfo list);

    @Headers("Accept: application/json")
    @POST("criar_lista")
    Call<ListInfo> criarList(@Body ListInfo list);

    @Headers("Accept: application/json")
    @POST("remover_list")
    Call<ListInfo> removerList(@Body ListInfo list);

    @Headers("Accept: application/json")
    @PUT("atualizar_nome_lista")
    Call<ListInfo> atualizarNomeList(@Body ListInfo list);

    //TASKS

    @Headers("Accept: application/json")
    @POST("get_tasks")
    Call<List<TaskAPI>> getTask(@Body TaskAPI task);

    @Headers("Accept: application/json")
    @POST("criar_tasks")
    Call<TaskAPI> criarTask(@Body TaskAPI task);

    @Headers("Accept: application/json")
    @POST("remover_task")
    Call<TaskAPI> removerTask(@Body TaskAPI task);

    @Headers("Accept: application/json")
    @PUT("atualizar_descricao_task")
    Call<TaskAPI> atualizarDescricaoTask(@Body TaskAPI task);

    @Headers("Accept: application/json")
    @PUT("atualizar_data_task")
    Call<TaskAPI> atualizarDataTask(@Body TaskAPI task);

    @Headers("Accept: application/json")
    @PUT("atualizar_limite_task")
    Call<TaskAPI> atualizarLimiteTask(@Body TaskAPI task);

    @Headers("Accept: application/json")
    @PUT("atualizar_concluido_task")
    Call<TaskAPI> atualizarConcluidoTask(@Body TaskAPI task);

    @Headers("Accept: application/json")
    @POST("get_AllTasks")
    Call<List<TaskAPI>> getAllTask(@Body TaskAPI task);
}
