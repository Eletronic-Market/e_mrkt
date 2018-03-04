package br.com.mrkt.dao;

import br.com.mrkt.factory.ConexaoJDBC;
import br.com.mrkt.factory.ConexaoPostgreJDBC;
import java.sql.SQLException;

/**
 * Classe responsável por conter os métodos de acesso ao Banco de Dados para o objeto Lista do Consumidor.
 * @author Lenno Sousa
 */
public class ListaConsumidorDAO {
    
    private final ConexaoJDBC conexao;
    private static ListaConsumidorDAO instance;
    
    
    
    /**
     * Método construtor da classe ListaConsumidorDAO.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ListaConsumidorDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ConexaoPostgreJDBC();
    }
    
    
    
    /**
     * Método responsável por criar uma instância da classe ListaConsumidorDAO (Singleton Pattern).
     * @return instance
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ListaConsumidorDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new ListaConsumidorDAO();
        }
        return instance;
    }
}
