package br.com.mrkt.dao;

import br.com.mrkt.factory.ConexaoJDBC;
import br.com.mrkt.factory.ConexaoPostgreJDBC;
import java.sql.SQLException;

/**
 * Classe responsável por conter os métodos de acesso ao Banco de Dados para o objeto e análises realizadas pelo Administrador.
 * @author Lenno Sousa
 */
public class AdministradorDAO {
    
    private final ConexaoJDBC conexao;
    private static AdministradorDAO instance;
    
    private static final String SELECIONAR_MAIS_CLICADOS = "";
    
    /**
     * Método construtor da classe AdministradorDAO.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public AdministradorDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ConexaoPostgreJDBC();
    }
    
    
    
    /**
     * Método responsável por criar uma instância da classe AdministradorDAO (Singleton Pattern).
     * @return instance
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static AdministradorDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new AdministradorDAO();
        }
        return instance;
    }
    
    
}
