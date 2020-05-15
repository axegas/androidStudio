package com.example.pruebatab;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class conectaBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "tareas.db";
    private static final int VERSION_BD = 1;
    private static final String TABLA_TAREAS = "CREATE TABLE tareas (ID INTEGER PRIMARY KEY AUTOINCREMENT, nombreAsignatura TEXT, titulo TEXT, descripcion TEXT, fecha TEXT, hecha TEXT )";

    public conectaBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_TAREAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tareas");
        db.execSQL(TABLA_TAREAS);
    }
    public void borraTabla(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS tareas");
        db.execSQL(TABLA_TAREAS);
    }

    public void agregarTarea(String asignatura, String titulo, String descripcion, String fecha){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO tareas VALUES(null,'"+asignatura+"','"+titulo+"','"+descripcion+"','"+fecha+"','no')");
            db.close();
        }
    }
    public void borraTarea(int id){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("DELETE FROM tareas WHERE id = '"+id+"'");
            db.close();
        }
    }
    public ArrayList<tarea> mostrarTareas(int valor){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        switch(valor){
            case 1:
                cursor = db.rawQuery("SELECT * FROM tareas",null);
                break;
            case 2:
                cursor = db.rawQuery("SELECT * FROM tareas WHERE hecha = 'no'",null);
                break;
            case 3:
                cursor = db.rawQuery("SELECT * FROM tareas WHERE hecha = 'si'",null);
                break;
            default:
                break;
        }
        ArrayList<tarea> tareas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                tareas.add(new tarea(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }while(cursor.moveToNext());
        }
        if(cursor != null)
            cursor.close();
        return tareas;
    }
    public tarea buscaTarea(int id){
        tarea tar = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM tareas WHERE ID = '"+id+"'",null);
        if(cursor.moveToFirst()){
            tar = new tarea(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        }
        if(cursor!=null){
            cursor.close();
        }
        return tar;
    }

    public void marcar(String marca, int id){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("UPDATE tareas SET hecha = '"+marca+"' where ID = "+id);
            db.close();
        }
    }
}
