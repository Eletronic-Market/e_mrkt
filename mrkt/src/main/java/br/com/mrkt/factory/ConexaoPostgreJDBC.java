package br.com.mrkt.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por conter os métodos de conexão e recursos para o Banco de Dados.
 * @author Lenno Sousa
 */
public class ConexaoPostgreJDBC implements ConexaoJDBC{

    private Connection connection = null;
    
    public ConexaoPostgreJDBC()throws SQLException, ClassNotFoundException{
        
        Class.forName("org.postgresql.Driver");
        
        Properties properties = new Properties();
        properties.put("user", "postgres");
        properties.put("password", "mrktdb");
        
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/&mrkt?ApplicationName=EletronicMarket", properties);
        this.connection.setAutoCommit(false);
    }
    
    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void close() {
        if(this.connection != null){
            try{
                this.connection.close();
            }catch(SQLException ex){
                Logger.getLogger(ConexaoPostgreJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void commit() throws SQLException {
        this.connection.commit();
        this.close();
    }

    @Override
    public void rollback() {
        if(this.connection != null){
            try{
                this.connection.rollback();
            }catch(SQLException ex){
                Logger.getLogger(ConexaoPostgreJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                this.close();
            }
        }
    }
}