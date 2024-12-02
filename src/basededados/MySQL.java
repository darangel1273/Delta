package basededados;

import rui.pereira.classesauxiliares.Contacto;

import java.sql.*;
import java.util.*;

public class MySQL {
    private final String Controlador ;
    private final String URL;
    private final String SQL;
    private final String ISQL;
    private final String filt;
    private final String ord;
    private final String p;
    private final Connection Liga ;
    private final Statement Com ;
    private PreparedStatement Prep ;
    private ResultSet Res;
    private final Properties Inf;

    public MySQL() {
        Controlador = "com.mysql.jdbc.Driver";
        Inf = new Properties();
        Inf.put("password", "contacto");
        Inf.put("user", "contacto");
        URL="jdbc:mysql://ruin:3306/contacto";
        SQL = "SELECT cc,nif,nome,apelido,nascimento,sexo,morada,email,telefone,foto FROM `pessoa` " ;
//        ISQL = " INSERT INTO pessoa (cc,nif,nome,apelido,nascimento,sexo,morada,email,telefone) VALUES( ?,?,?,?,?,?,?,?,?,);" ;
        ISQL = " INSERT INTO pessoa VALUES( ?,?,?,?,?,?,?,?,?,);" ;
        filt = " WHERE cc=" ;
        ord = " order by 1 " ;
        p = " ; " ;
        try {
            Class.forName( Controlador );
            Liga = DriverManager.getConnection( URL,Inf );
            Com = Liga.createStatement();
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     *
     * @param c
     * @return
     */
    public int addContacto( Contacto c) {
        int ret = 0;
        try {
            Prep = Liga.prepareStatement( ISQL );
            Prep.setLong(1, c.getBI() );
            Prep.setLong(2, c.getNIF() );
            Prep.setString(3, c.getNome() );
            Prep.setString(4, c.getApelido() );
            Prep.setDate(5, c.getNascimento() );
            Prep.setString(6, c.getSexo() );
            Prep.setString(7, c.getMorada().toString() );
            Prep.setString(8, c.getEmail() );
            Prep.setLong(9, Long.parseLong( c.getTelefone() ) );
            Prep.executeUpdate()  ;
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
        finally {
            return ret;
        }
    }
    /**
     *
     * @return
     */
    public List<Contacto> darContactos(){
        List<Contacto> ls= new ArrayList<>() ;
        try{
            Res = Com.executeQuery(SQL +ord+p) ;
            while( Res.next() ){
                System.out.printf(Locale.getDefault(),"\nS %d\t%d\t%S\t%S\t%S\t%s\t%s\t%s\t%d",  Res.getLong(1  ),  Res.getLong(2 ),
                        Res.getString(3 ) , Res.getString(4 ) , Res.getDate(5 ).toLocalDate().toString() ,
                        Res.getString(6) , Res.getString(7) , Res.getString(8 ) , Res.getLong(9 )   ) ;
                ls.add( new Contacto(  String.valueOf(Res.getLong(1 ) ), String.valueOf( Res.getLong(2 ) ),
                        Res.getString(3 ) , Res.getString(4 ) , String.valueOf(Res.getDate(5 ) ) ,
                        Res.getString(6) , Res.getString(7) , Res.getString(8 ) , String.valueOf(Res.getLong(9 ) )
                        )
                ) ;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.printf(Locale.getDefault(),"\n %S ",e.getLocalizedMessage()  ) ;
            throw new RuntimeException(e);
        }
        finally{
            return ls;
        }
    }
    /**
     *
     * @return
     */
    public List<Contacto> darContacto(String cc){
        List<Contacto> ls= new ArrayList<>() ;
        try{
            Res = Com.executeQuery(SQL+filt+cc+ord+p ) ;
            while( Res.next() ){
                System.err.printf(Locale.getDefault(),"\nC %d\t%d\t%s\t%s\t%tYYYY-MM-dd\t%s\t%s\t%s\t%d",  Res.getLong(1  ),  Res.getLong(2 ),
                        Res.getString(3 ) , Res.getString(4 ) , Res.getDate(5 ).toLocalDate()  ,
                        Res.getString(6) , Res.getString(7) , Res.getString(8 ) , Res.getLong(9 )   ) ;
                ls.add( new Contacto(  String.valueOf(Res.getLong(1 ) ), String.valueOf( Res.getLong(2 ) ),
                                Res.getString(3 ) , Res.getString(4 ) , String.valueOf(Res.getDate(5 ) ) ,
                                Res.getString(6) , Res.getString(7) , Res.getString(8 ) , String.valueOf(Res.getLong(9 ) )
                        )
                ) ;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.printf(Locale.getDefault(),"\n %s ",e.getLocalizedMessage()  ) ;
            throw new RuntimeException(e);
        }
        finally{
            return ls;
        }
    }
}
