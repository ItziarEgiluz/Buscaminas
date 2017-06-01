package PackBaseDeDatos;
import java.sql.*;
import javax.swing.JOptionPane;
import packBuscaminas.ListaOrdenada;
import packBuscaminas.CompararJugadores;
import packBuscaminas.Jugador;

// TODO el mismo jugador solo  puede tener una puntuacion
public class BaseDeDatos {
	//Es patrón Singleton
	private Connection con;
	private Statement st;
	private String url = "Ranking.sqlite";
	private static BaseDeDatos mBaseDeDatos;
	
	public static BaseDeDatos getInstance(){
		if ( mBaseDeDatos == null ){
			mBaseDeDatos = new BaseDeDatos();
		}
		return mBaseDeDatos;
	}
	
	private BaseDeDatos(){   
    }
    
    private void conectar(){
    	try {
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {		
			con = DriverManager.getConnection("jdbc:sqlite:" + url);	
			st = con.createStatement();	
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
		}	
	}    
    private void desconectar(){
    	try{    
    		st.close();  
            con.close(); 
        }
        catch (Exception e){                 
        	e.printStackTrace();  
        }  
    }
    
    private boolean insertar(String pSql){
        boolean valor = true;
        conectar();
        try{
            st.executeUpdate(pSql);
        } 
        catch (SQLException e){
        	valor = false;
            JOptionPane.showMessageDialog(null, e.getMessage());
        }      
        finally{  
            desconectar();
        }
        return valor;
    }
    private ResultSet consultar(String pSql){
    	conectar();
        ResultSet rs = null;
        try{
            rs = st.executeQuery(pSql);
        } 
        catch (SQLException e){
        	System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
            
            JOptionPane.showMessageDialog(null, ""+e.getMessage());
        }          
        return rs;
    }
    
    public ListaOrdenada<Jugador> cargarDatos(){
    	String i = "Select * from Ranking";
    	ResultSet rs = consultar( i );
    	ListaOrdenada<Jugador> jugadores = new ListaOrdenada<>( new CompararJugadores() );
    	
    	try{ 		
    		while( rs.next() ){
	    		String nombre = rs.getString("Nombre");
	    		int tiempo = rs.getInt("Tiempo");
	    		int nivel = rs.getInt("Nivel");
	    		jugadores.add( new Jugador(nombre,tiempo,nivel));
	    	}    		    			
    	}
    	catch( SQLException ex ){
    		ex.printStackTrace();
    	}   	
    	finally{
    		desconectar();
    	}
    	return jugadores;
    }
    
    public void guardarDatos( String pNombre , int pTiempo , int pNivel ){
    	try{
	    	String i = "Select Nombre,Tiempo,Nivel from Ranking where Nombre = '" + pNombre + "' and Nivel = " + pNivel;
	    	ResultSet rs = consultar( i );

	    	if( rs.next() == false ){
	    		String r = "Insert into Ranking ('Nombre','Tiempo','Nivel') values ('"+pNombre+"'," + pTiempo + "," + pNivel +")";    	
	    		insertar( r );
	    	}
	    	else{
	    		
    			String nombre = rs.getString("Nombre");
	    		int tiempo = rs.getInt("Tiempo");
	    		int nivel = rs.getInt("Nivel");
	    		
	    		if ( pTiempo < tiempo ){
	    			String j = "Update Ranking set Tiempo = " + pTiempo + " WHERE Nombre = '" + nombre + "' AND Nivel = " + nivel;	
	    			st.executeUpdate( j );
	    		}
		    	   	
	    	}
    	}
    	catch(SQLException ex ){
    		ex.printStackTrace();
    	}
    }   
}