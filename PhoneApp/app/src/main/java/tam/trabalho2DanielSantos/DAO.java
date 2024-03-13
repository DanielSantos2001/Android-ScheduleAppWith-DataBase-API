package tam.trabalho2DanielSantos;

import java.util.List;

public interface DAO {

    //USERS

    public interface loginListener{
        public void onSuccess(User user);
        public void onError(String message);
    }
    public void userLogin(User user,loginListener listener);

    public interface signupListener{
        public void onSuccess(User user);
        public void onError(String message);
    }
    public void userSignup(User user,signupListener listener);

    //LISTAS

    public interface getListListener{
        public void onSuccess(List<ListInfo> listInfo);
        public void onError(String message);
    }
    public void getList(ListInfo listInfo, getListListener listener);

    public interface criarListListener{
        public void onSuccess(ListInfo listInfo);
        public void onError(String message);
    }
    public void criarList(ListInfo listInfo, criarListListener listener);

    public interface removerListListener{
        public void onSuccess(ListInfo listInfo);
        public void onError(String message);
    }
    public void removerList(ListInfo listInfo, removerListListener listener);

    public interface atualizarNomeListListener{
        public void onSuccess(ListInfo listInfo);
        public void onError(String message);
    }
    public void atualizarNomeList(ListInfo listInfo, atualizarNomeListListener listener);

    //TASKS

    public interface getTaskListener{
        public void onSuccess(List<TaskAPI> task);
        public void onError(String message);
    }
    public void getTask(TaskAPI task,getTaskListener listener);

    public interface criarTaskListener{
        public void onSuccess(TaskAPI task);
        public void onError(String message);
    }
    public void criarTask(TaskAPI task,criarTaskListener listener);

    public interface removerTaskListener{
        public void onSuccess(TaskAPI task);
        public void onError(String message);
    }
    public void removerTask(TaskAPI task,removerTaskListener listener);

    public interface atualizarDescricaoTaskListener{
        public void onSuccess(TaskAPI task);
        public void onError(String message);
    }
    public void atualizarDescricaoTask(TaskAPI task,atualizarDescricaoTaskListener listener);

    public interface atualizarDataTaskListener{
        public void onSuccess(TaskAPI task);
        public void onError(String message);
    }
    public void atualizarDataTask(TaskAPI task,atualizarDataTaskListener listener);

    public interface atualizarLimiteTaskListener{
        public void onSuccess(TaskAPI task);
        public void onError(String message);
    }
    public void atualizarLimiteTask(TaskAPI task,atualizarLimiteTaskListener listener);

    public interface atualizarConcluidoTaskListener{
        public void onSuccess(TaskAPI task);
        public void onError(String message);
    }
    public void atualizarConcluidoTask(TaskAPI task,atualizarConcluidoTaskListener listener);

    public interface getAllTaskListener{
        public void onSuccess(List<TaskAPI> task);
        public void onError(String message);
    }
    public void getAllTask(TaskAPI task,getAllTaskListener listener);
}
