package com.example.institutosql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Iterator;

public class conectaBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "instituto.db";
    private static final int VERSION_BD = 1;
    private static final String TABLA_PROFESORES = "CREATE TABLE profesores (dni TEXT PRIMARY KEY, nombre TEXT, edad INTEGER)";
    private static final String TABLA_ALUMNOS = "CREATE TABLE alumnos (dni TEXT PRIMARY KEY, nombre TEXT, edad INTEGER, curso TEXT)";
    private static final String TABLA_ASIGNATURAS = "CREATE TABLE asignaturas (nombre TEXT PRIMARY KEY, libro TEXT, dniProf TEXT," +
            "                                           CONSTRAINT Profesores_ProfesoresFK FOREIGN KEY (dniProf) REFERENCES profesores (dni))";

    public conectaBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_ALUMNOS);
        db.execSQL(TABLA_PROFESORES);
        db.execSQL(TABLA_ASIGNATURAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS profesores");
        db.execSQL(TABLA_PROFESORES);
    }
    public void borraTabla(String tabla){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS asignaturas");
        db.execSQL(TABLA_ASIGNATURAS);
        db.close();
    }

    public void agregarProfesor(String dni, String nombre, int edad){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO profesores VALUES('"+dni+"','"+nombre+"','"+edad+"')");
            db.close();
        }
    }
    public void agregarAlumno(String dni, String nombre, int edad, String curso){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO alumnos VALUES('"+dni+"','"+nombre+"','"+edad+"','"+curso+"')");
            db.close();
        }
    }
    public void agregarAsignatura(String nombre, String libro, String dniProf){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO asignaturas VALUES('"+nombre+"','"+libro+"','"+dniProf+"')");
            db.close();
        }
    }

    public ArrayList<Profesor> mostrarProfesores(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM profesores",null);
        ArrayList<Profesor> profesores = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                profesores.add(new Profesor(cursor.getString(0),cursor.getString(1),cursor.getInt(2)));
            }while(cursor.moveToNext());
        }
        return profesores;
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
    public ArrayList<Asignatura> mostrarAsignaturas(){
        SQLiteDatabase db = getReadableDatabase();
        String nombre;
        String libro;
        Profesor prof;
        Cursor cursor = db.rawQuery("SELECT * FROM asignaturas",null);
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                prof = buscaProfesor(cursor.getString(2));
                asignaturas.add(new Asignatura(cursor.getString(0),cursor.getString(1),prof));
            }while(cursor.moveToNext());
        }
        return asignaturas;
    }
    public Profesor buscaProfesor(String dni) {
        Profesor prof;
        Iterator<Profesor> iter = this.mostrarProfesores().iterator();
        while (iter.hasNext()) {
            prof = iter.next();
            if (prof.getDNI().equals(dni)) {
                return prof;
            }
        }
        return null;
    }
}
