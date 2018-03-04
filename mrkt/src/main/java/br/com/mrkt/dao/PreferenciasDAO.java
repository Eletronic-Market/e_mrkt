package br.com.mrkt.dao;

import br.com.mrkt.factory.ConexaoJDBC;
import br.com.mrkt.factory.ConexaoPostgreJDBC;
import java.sql.SQLException;

/**
 * Classe responsável por conter os métodos de acesso ao Banco de Dados para o objeto Preferências do Consumidor.
 * @author Lenno Sousa
 */
public class PreferenciasDAO {
    
    private final ConexaoJDBC conexao;
    private static PreferenciasDAO instance;
    
    
    
    /**
     * Método construtor da classe PreferenciasDAO.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public PreferenciasDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ConexaoPostgreJDBC();
    }
    
    
    
    /**
     * Método responsável por criar uma instância da classe PreferenciasDAO (Singleton Pattern).
     * @return instance
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static PreferenciasDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new PreferenciasDAO();
        }
        return instance;
    }
}
