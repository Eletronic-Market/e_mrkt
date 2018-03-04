package br.com.mrkt.dao;

import br.com.mrkt.factory.ConexaoJDBC;
import br.com.mrkt.factory.ConexaoPostgreJDBC;
import java.sql.SQLException;

/**
 * Classe responsável por conter os métodos de acesso ao Banco de Dados para o objeto Listas do Dia.
 * @author Lenno Sousa
 */
public class ListasDoDiaDAO {
    
    private final ConexaoJDBC conexao;
    private static ListasDoDiaDAO instance;
    
    
    
    /**
     * Método construtor da classe ListasDoDiaDAO.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ListasDoDiaDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ConexaoPostgreJDBC();
    }
    
    
    
    /**
     * Método responsável por criar uma instância da classe ListasDoDiaDAO (Singleton Pattern).
     * @return instance
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ListasDoDiaDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new ListasDoDiaDAO();
        }
        return instance;
    }
}
