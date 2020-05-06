package com.example.institutosqlv2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Iterator;

public class conectaBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "instituto.db";
    private static final int VERSION_BD = 1;
    private static final String TABLA_PROFESORES = "CREATE TABLE profesores (dni TEXT PRIMARY KEY, nombre TEXT, edad INTEGER, categoria TEXT)";
    private static final String TABLA_ALUMNOS = "CREATE TABLE alumnos (dni TEXT PRIMARY KEY, nombre TEXT, edad INTEGER, curso TEXT)";
    private static final String TABLA_ASIGNATURAS = "CREATE TABLE asignaturas (nombre TEXT PRIMARY KEY, libro TEXT, dniProf TEXT, CONSTRAINT Profesores_ProfesoresFK FOREIGN KEY (dniProf) REFERENCES profesores (dni))";
    private static final String TABLA_MATRICULAR = "CREATE TABLE matricular (nombreAsignatura TEXT, dniAlumno TEXT, CONSTRAINT Alumnos_AlumnosFK FOREIGN KEY (dniAlumno) REFERENCES alumnos (dni), CONSTRAINT Asignaturas_AsignaturasFK FOREIGN KEY (nombreAsignatura) REFERENCES asignaturas (nombre), PRIMARY KEY (nombreAsignatura,dniAlumno))";
    private static final String TABLA_EXAMENES = "CREATE TABLE examenes (ID INTEGER PRIMARY KEY AUTOINCREMENT, nombreAsignatura TEXT, dniAlumno TEXT, fecha DATE, nota DOUBLE, CONSTRAINT Matricula_MatriculaFK FOREIGN KEY (nombreAsignatura,dniAlumno) REFERENCES matricular (nombreAsignatura,dniAlumno))";

    public conectaBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLA_ALUMNOS);
        db.execSQL(TABLA_PROFESORES);
        db.execSQL(TABLA_ASIGNATURAS);
        db.execSQL(TABLA_MATRICULAR);
        db.execSQL(TABLA_EXAMENES);
    }

    public void inicial(){
        SQLiteDatabase db = getWritableDatabase();
/*
        db.execSQL("INSERT INTO alumnos VALUES('26753192y','Axel','31','1DAM')");
        db.execSQL("INSERT INTO alumnos VALUES('12345678y','Juan','25','1DAM')");
        db.execSQL("INSERT INTO alumnos VALUES('54878965g','Pepe','30','2DAM')");
        db.execSQL("INSERT INTO alumnos VALUES('24631578p','Jose','25','1DAM')");
        db.execSQL("INSERT INTO alumnos VALUES('78451236n','Mari','32','1DAM')");
        db.execSQL("INSERT INTO alumnos VALUES('78451203u','Ana','25','1DAM')");
        db.execSQL("INSERT INTO alumnos VALUES('78645237j','Edgar','28','1DAW')");


        db.execSQL("INSERT INTO profesores VALUES('12345678y','Paco','25','Interino')");
        db.execSQL("INSERT INTO profesores VALUES('54878965g','Lucia','30','Titular')");
        db.execSQL("INSERT INTO profesores VALUES('24631578p','Isabel','25','Interina')");
        db.execSQL("INSERT INTO profesores VALUES('78451236n','Tamara','32','Interina')");
        db.execSQL("INSERT INTO profesores VALUES('78451203u','Raul','25','Titular')");
        db.execSQL("INSERT INTO profesores VALUES('78645237j','Sofia','28','Titular')");
        db.execSQL("INSERT INTO profesores VALUES('78645784j','Silvia','28','Titular')");

        db.execSQL("INSERT INTO asignaturas VALUES('Programacion','Java','12345678y')");
        db.execSQL("INSERT INTO asignaturas VALUES('Bases de datos','Bases','54878965g')");
        db.execSQL("INSERT INTO asignaturas VALUES('Sistemas informaticos','Sistemas','78451203u')");
        db.execSQL("INSERT INTO asignaturas VALUES('Entornos de desarrollo','Entornos','78451236n')");
        db.execSQL("INSERT INTO asignaturas VALUES('LMI','Lenguaje','24631578p')");
        db.execSQL("INSERT INTO asignaturas VALUES('Redes','Redes','78645237j')");
        db.execSQL("INSERT INTO asignaturas VALUES('Ingles','Ingles','78645784j')");


        db.execSQL("INSERT INTO matricular VALUES('Programacion','26753192y')");
        db.execSQL("INSERT INTO matricular VALUES('Bases de datos','26753192y')");
        db.execSQL("INSERT INTO matricular VALUES('Sistemas informaticos','26753192y')");
        db.execSQL("INSERT INTO matricular VALUES('Entornos de desarrollo','54878965g')");
        db.execSQL("INSERT INTO matricular VALUES('Programacion','54878965g')");
        db.execSQL("INSERT INTO matricular VALUES('Programacion','12345678y')");
        db.execSQL("INSERT INTO matricular VALUES('Sistemas informaticos','12345678y')");
        db.execSQL("INSERT INTO matricular VALUES('Ingles','78645237j')");


        db.execSQL("INSERT INTO examenes VALUES(null,'Programacion','26753192y','22/04/2020','10')");
        db.execSQL("INSERT INTO examenes VALUES(null,'Bases de datos','26753192y','22/04/2020','10')");
        db.execSQL("INSERT INTO examenes VALUES(null,'Sistemas informaticos','26753192y','22/04/2020','10')");
        db.execSQL("INSERT INTO examenes VALUES(null,'Entornos de desarrollo','54878965g','22/04/2020','10')");
        db.execSQL("INSERT INTO examenes VALUES(null,'Programacion','54878965g','22/04/2020','10')");
        db.execSQL("INSERT INTO examenes VALUES(null,'Programacion','12345678y','22/04/2020','10')");
        db.execSQL("INSERT INTO examenes VALUES(null,'Sistemas informaticos','12345678y','22/04/2020','10')");
        db.execSQL("INSERT INTO examenes VALUES(null,'Ingles','78645237j','22/04/2020','10')");

        db.execSQL("INSERT INTO matricular VALUES('Programacion','26753192y')");
        db.execSQL("INSERT INTO matricular VALUES('Bases de datos','26753192y')");
        db.execSQL("INSERT INTO matricular VALUES('Sistemas informaticos','26753192y')");
        db.execSQL("INSERT INTO matricular VALUES('Entornos de desarrollo','54878965g')");
        db.execSQL("INSERT INTO matricular VALUES('Programacion','54878965g')");
        db.execSQL("INSERT INTO matricular VALUES('Programacion','12345678y')");
        db.execSQL("INSERT INTO matricular VALUES('Sistemas informaticos','12345678y')");
        db.execSQL("INSERT INTO matricular VALUES('Ingles','78645237j')");
        db.execSQL("INSERT INTO matricular VALUES('LMI','26753192y')");
        db.execSQL("INSERT INTO matricular VALUES('Redes','26753192y')");
        db.execSQL("INSERT INTO matricular VALUES('Ingles','26753192y')");
*/



        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS profesores");
        db.execSQL(TABLA_PROFESORES);

    }
    public void borraTabla(){
        SQLiteDatabase db = getWritableDatabase();
       /* db.execSQL("DROP TABLE IF EXISTS alumnos");
        db.execSQL(TABLA_ALUMNOS);*/
        db.execSQL("DROP TABLE IF EXISTS examenes");
        db.execSQL(TABLA_EXAMENES);
        db.execSQL("DROP TABLE IF EXISTS matricular");
        db.execSQL(TABLA_MATRICULAR);
        db.close();
    }

    public void agregarProfesor(String dni, String nombre, int edad, String categoria){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO profesores VALUES('"+dni+"','"+nombre+"','"+edad+"','"+categoria+"')");
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
                profesores.add(new Profesor(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3)));
            }while(cursor.moveToNext());
        }
        if(cursor != null)
            cursor.close();
        return profesores;
    }

    public ArrayList<Alumno> mostrarAlumnos(){
        Alumno alu;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM alumnos",null);
        ArrayList<Alumno> alumnos = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                alu = new Alumno(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3));
                alumnos.add(alu);
            }while(cursor.moveToNext());
        }
        if(cursor != null)
            cursor.close();
        return alumnos;
    }
    public ArrayList<Asignatura> mostrarAsignaturas(){
        SQLiteDatabase db = getReadableDatabase();
        Profesor prof;
        Cursor cursor = db.rawQuery("SELECT * FROM asignaturas",null);
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                prof = buscaProfesor(cursor.getString(2));
                asignaturas.add(new Asignatura(cursor.getString(0),cursor.getString(1),prof));
            }while(cursor.moveToNext());
        }
        if(cursor != null)
            cursor.close();
        return asignaturas;
    }

    public void matricular(String nombre, String dni){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO matricular VALUES('"+nombre+"','"+dni+"')");
            db.close();
        }
    }
    public void examinar(String nombre, String dni, String fecha, double nota){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO examenes (nombreAsignatura, dniAlumno, fecha, nota) VALUES('"+nombre+"','"+dni+"','"+fecha+"','"+nota+"')");
            db.close();
        }
    }
    public ArrayList<Examen> mostrarExamenesDeAlumno(String dni){
        SQLiteDatabase db = getReadableDatabase();
        Asignatura asig;
        Alumno alu;
        Cursor cursor = db.rawQuery("SELECT * FROM examenes WHERE dniAlumno = '"+dni+"'",null);
        ArrayList<Examen> examenes = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                asig = buscaAsignatura(cursor.getString(1));
                alu = buscaAlumno(cursor.getString(2));
                examenes.add(new Examen(cursor.getInt(0),asig,alu,cursor.getString(3),cursor.getDouble(4)));
            }while(cursor.moveToNext());
        }
        if(cursor != null)
            cursor.close();
        return examenes;
    }
    public ArrayList<Asignatura> verAsignaturasDeAlumno(String dni){
        SQLiteDatabase db = getReadableDatabase();
        Profesor prof;
        Cursor cursor = db.rawQuery("SELECT a.* FROM matricular m INNER JOIN asignaturas a ON m.nombreAsignatura=a.nombre WHERE m.dniAlumno = '"+dni+"'",null);
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                prof = buscaProfesor(cursor.getString(2));
                asignaturas.add(new Asignatura(cursor.getString(0),cursor.getString(1),prof));
            }while(cursor.moveToNext());
        }
        if(cursor != null)
            cursor.close();
        return asignaturas;
    }

    public Alumno buscaAlumno(String dni) {
        Alumno alu;
        Iterator<Alumno> iter = this.mostrarAlumnos().iterator();
        while (iter.hasNext()) {
            alu = iter.next();
            if (alu.getDNI().equals(dni)) {
                return alu;
            }
        }
        return null;
    }

    public Asignatura buscaAsignatura(String nombre) {
        Asignatura asig;
        Iterator<Asignatura> iter = this.mostrarAsignaturas().iterator();
        while (iter.hasNext()) {
            asig = iter.next();
            if (asig.getNombre().equals(nombre)) {
                return asig;
            }
        }
        return null;
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
