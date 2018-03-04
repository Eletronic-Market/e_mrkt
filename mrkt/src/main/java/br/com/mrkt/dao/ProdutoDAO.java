package br.com.mrkt.dao;

import br.com.mrkt.factory.ConexaoJDBC;
import br.com.mrkt.factory.ConexaoPostgreJDBC;
import java.sql.SQLException;

/**
 * Classe responsável por conter os métodos de acesso ao Banco de Dados para o objeto Produto.
 * @author Lenno Sousa
 */
public class ProdutoDAO {
    
    private final ConexaoJDBC conexao;
    private static ProdutoDAO instance;
    
    
    
    /**
     * Método construtor da classe ProdutoDAO.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ProdutoDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ConexaoPostgreJDBC();
    }
    
    
    
    /**
     * Método responsável por criar uma instância da classe ProdutoDAO (Singleton Pattern).
     * @return instance
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ProdutoDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new ProdutoDAO();
        }
        return instance;
    }
}
