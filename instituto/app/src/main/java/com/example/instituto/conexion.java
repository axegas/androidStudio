package com.example.instituto;

import java.sql.*;

public class conexion {
	    private static final String URL = "jdbc:mysql://127.0.0.1:3306/instituto";
	    private static final String USUARIO = "root";
	    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	    private static final String CLAVE = "apg946130";	    
	    
	    static {
	    	  try {
		        	Class.forName(DRIVER);

		        } catch (ClassNotFoundException e) {
		        	System.out.println("Error en el driver");
					e.printStackTrace();
		        }
	    }
	    public Connection conectar() {
	        Connection conexion = null;
	        
	        try {	
	            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
	        }catch (SQLException e) {
	            System.out.println("Error en la conexi�n");
	            e.printStackTrace();
	        }
	        
	        return conexion;
	    }
	    public conexion() {
	    	
	    }
	}
