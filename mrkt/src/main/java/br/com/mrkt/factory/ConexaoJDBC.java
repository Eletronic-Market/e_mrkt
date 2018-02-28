package br.com.mrkt.factory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface para ser utilizada e efetuar conexão com qualquer Banco de Dados.
 * @author Lenno Sousa
 */
public interface ConexaoJDBC {
    
    public Connection getConnection();
    
    public void close();
    
    public void commit() throws SQLException;
    
    public void rollback();
}
