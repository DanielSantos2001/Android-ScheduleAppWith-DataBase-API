package tam.trabalho2DanielSantos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD {

    String DB_NAME = "TAM2";
    String DB_TABLE_LISTS = "Lists";
    String DB_TABLE_TASKS = "Tasks";

    String SQL_CREATE_LISTS = "CREATE TABLE " + DB_TABLE_LISTS +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "listName TEXT NOT NULL); ";

    String SQL_CREATE_TASKS = "CREATE TABLE " + DB_TABLE_TASKS +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "descricao TEXT NOT NULL,dataLimite TEXT NOT NULL," +
            "listName TEXT NOT NULL,atrasado INTEGER NOT NULL,concluido INTEGER NOT NULL); ";

    String SQL_DROP_LISTS = "DROP TABLE IF EXISTS " + DB_TABLE_LISTS;

    String SQL_DROP_TASKS = "DROP TABLE IF EXISTS " + DB_TABLE_TASKS;


    DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public BD(Context ctx) {
        dbHelper = new DatabaseHelper(ctx);
    }

    class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_LISTS);
            db.execSQL(SQL_CREATE_TASKS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DROP_LISTS);
            db.execSQL(SQL_DROP_TASKS);
            onCreate(db);
        }
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //List
    public long insertList(String listName) {
        ContentValues vals = new ContentValues();
        vals.put("listName", listName);
        return db.insert(DB_TABLE_LISTS, null, vals);
    }

    public Cursor getAllLists() {
        Cursor cursor = db.query(
                DB_TABLE_LISTS,
                new String[]{"listName"},
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    public void updateList(String oldListName,String newListName){
        ContentValues vals = new ContentValues();
        vals.put("listName", newListName);
        db.update(DB_TABLE_LISTS,
                vals,
                "listName=?",
                new String[]{ oldListName } );
    }

    public void removeList(String name){
        db.delete(DB_TABLE_LISTS,
                "listName=?",
                new String[]{ name} );
    }

    //Task
    public long insertTask(String aDescricao,String aDataLimite,String aListName,Boolean aAtrasado,Boolean aConcluido){
        ContentValues vals = new ContentValues();
        vals.put("descricao",aDescricao);
        vals.put("dataLimite",aDataLimite);
        vals.put("listName",aListName);
        vals.put("atrasado",aAtrasado);
        vals.put("concluido",aConcluido);
        return db.insert(DB_TABLE_TASKS, null, vals);
    }

    public Cursor getAllTasks(){
        Cursor cursor = db.query(
                DB_TABLE_TASKS,
                new String[]{"_id","descricao","dataLimite","listName","atrasado","concluido"},
                null,
                null,
                null,
                null,
                "dataLimite"
        );

        return cursor;
    }

    public void updateTaskDescricao(int aID,String aName){
        ContentValues vals = new ContentValues();
        vals.put("descricao", aName);
        db.update(DB_TABLE_TASKS,
                vals,
                "_id=?",
                new String[] {String.valueOf(aID)} );
    }

    public void updateTaskData(int aID,String aData){
        ContentValues vals = new ContentValues();
        vals.put("dataLimite", aData);
        db.update(DB_TABLE_TASKS,
                vals,
                "_id=?",
                new String[] {String.valueOf(aID)} );
    }

    public void updateTaskLate(int aID,int aAtrasado){
        ContentValues vals = new ContentValues();
        vals.put("atrasado", aAtrasado);
        db.update(DB_TABLE_TASKS,
                vals,
                "_id=?",
                new String[] {String.valueOf(aID)} );
    }

    public void updateTaskFinish(int aID,int aConcluido){
        ContentValues vals = new ContentValues();
        vals.put("concluido", aConcluido);
        //String id = Integer.toString(aID);
        db.update(DB_TABLE_TASKS,
                vals,
                "_id=?",
                new String[] {String.valueOf(aID)} );
        //db.execSQL("UPDATE Tasks SET concluido=true WHERE _id="+aID);
    }

    public void removeTask(int aID){
        db.delete(DB_TABLE_TASKS,
                "_id=?",
                new String[]{String.valueOf(aID)} );
    }

}
