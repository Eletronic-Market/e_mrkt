package br.com.mrkt.dao;

import br.com.mrkt.factory.ConexaoJDBC;
import br.com.mrkt.factory.ConexaoPostgreJDBC;
import br.com.mrkt.model.Supermercado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por conter os métodos de acesso ao Banco de Dados para o objeto Supermercado.
 * @author Lenno Sousa
 */
public class SupermercadoDAO {
    
    private final ConexaoJDBC conexao;
    private static SupermercadoDAO instance;
    
    private static final String VERIFICAR_EMAIL_EXISTENTE = "";
    private static final String REGISTRAR_SUPERMERCADO = "";
    private static final String CADASTRAR_NOTIFICACOES_PENDENTES = "";
    
    /**
     * Método construtor da classe SupermercadoDAO.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public SupermercadoDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ConexaoPostgreJDBC();
    }
    
    /**
     * Método responsável por criar uma instância da classe SupermercadoDAO (Singleton Pattern).
     * @return instance
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static SupermercadoDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new SupermercadoDAO();
        }
        return instance;
    }
    
    
    
    
    public Supermercado verificarEmailExistente(Supermercado supermercado) throws SQLException, ClassNotFoundException{

        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(VERIFICAR_EMAIL_EXISTENTE);
            pstmt.setString(1, supermercado.getUsuario().getEmail());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){

                supermercado.getUsuario().setEmail(rs.getString("email"));

            }else{

                supermercado.getUsuario().setEmail(null);
                conexao.close();

            }

        }catch(Exception e){
            supermercado = null;
            throw new RuntimeException(e);
        }conexao.close();

        return supermercado;
    }
    
    //PRIMEIRO REGISTRO, SERVIRÁ PARA ENTRAR EM CONTATO COM O SUPERMERCADO E APÓS ISSO, AI SIM TERÁ O CADASTRO COMPLETO COM TODAS AS INFORMAÇÕES.
    public Supermercado registrarSupermercado(Supermercado supermercado) throws SQLException, ClassNotFoundException{

        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(REGISTRAR_SUPERMERCADO);
            pstmt.setString(1, supermercado.getRazaoSocial());
            pstmt.setString(2, supermercado.getDocumentoPJ().getCnpj());
            pstmt.setString(3, supermercado.getTelefone());
            pstmt.setString(4, supermercado.getUsuario().getEmail());
            pstmt.execute();

            supermercado.getUsuario().setEmail(supermercado.getUsuario().getEmail());

        }catch (Exception e){
            supermercado = null;
            throw  new RuntimeException(e);
        }conexao.close();

        return supermercado;

    }
    
    
       public Supermercado cadastrarNotificacoesPendentes(Supermercado supermercado) throws SQLException, ClassNotFoundException{

        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CADASTRAR_NOTIFICACOES_PENDENTES);
            pstmt.setInt(1, supermercado.getUsuario().getIdUsuario());
            pstmt.setString(2, supermercado.getNotificacoesPendentes().getTipo_notificacao());
            pstmt.execute();

        }catch (Exception e) {
            supermercado = null;
            throw new RuntimeException(e);
        } conexao.close();
        return supermercado;
    }
       
       
       
       
       
}
