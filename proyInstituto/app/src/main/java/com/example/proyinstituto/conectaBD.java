package com.example.proyinstituto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class conectaBD extends SQLiteOpenHelper {
    //String dni,String nombre,int edad,String curso
    private  static final String NOMBRE_BD = "instituto.db";
    private static final int VERSION_BD = 1;
    private static final String TABLA_ALUMNOS = "CREATE TABLE alumnos (dni TEXT PRIMARY KEY, nombre TEXT, edad INTEGER, curso TEXT)";

    public conectaBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_ALUMNOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

 //        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLA_ALUMNOS);
        //sqLiteDatabase.execSQL(TABLA_ALUMNOS);
    }

    public void agregarAlumno(String dni, String nombre, int edad, String curso){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO alumnos VALUES('"+dni+"','"+nombre+"','"+edad+"','"+curso+"')");
            db.close();
        }
    }

    public ArrayList<Alumno> mostrarAlumnos(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM alumnos",null);
        ArrayList<Alumno> alumnos = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                alumnos.add(new Alumno(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3)));
            }while(cursor.moveToNext());
        }
        return alumnos;
    }

}
