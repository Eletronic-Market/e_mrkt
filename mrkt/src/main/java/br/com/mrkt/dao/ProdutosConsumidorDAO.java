package br.com.mrkt.dao;

import br.com.mrkt.factory.ConexaoJDBC;
import br.com.mrkt.factory.ConexaoPostgreJDBC;
import java.sql.SQLException;

/**
 * Classe responsável por conter os métodos de acesso ao Banco de Dados para o objeto Produtos do Consumidor.
 * @author Lenno Sousa
 */
public class ProdutosConsumidorDAO {
    
    private final ConexaoJDBC conexao;
    private static ProdutosConsumidorDAO instance;
    
    
    
    /**
     * Método construtor da classe ProdutosConsumidorDAO.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ProdutosConsumidorDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ConexaoPostgreJDBC();
    }
    
    
    
    /**
     * Método responsável por criar uma instância da classe ProdutosConsumidorDAO (Singleton Pattern).
     * @return instance
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ProdutosConsumidorDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new ProdutosConsumidorDAO();
        }
        return instance;
    }
}
