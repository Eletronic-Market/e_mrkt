package br.com.mrkt.dao;

import br.com.mrkt.factory.ConexaoJDBC;
import br.com.mrkt.factory.ConexaoPostgreJDBC;
import br.com.mrkt.model.Administrador;
import br.com.mrkt.model.Consumidor;
import br.com.mrkt.model.Supermercado;
import br.com.mrkt.model.Transportadora;
import br.com.mrkt.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por conter os métodos de acesso ao Banco de Dados para o objeto Login.
 * @author Lenno Sousa
 */
public class LoginDAO {
    
    private final ConexaoJDBC conexao;
    private static LoginDAO instance;    
    
    private static final String REATIVA_USUARIO = "UPDATE usuario SET status = 1 WHERE email = ? AND senha = CRYPT(?, senha)";
    private static final String VERIFICA_EXISTENCIA_USUARIO = "SELECT email, senha FROM usuario WHERE email = ? AND senha = CRYPT(?, senha)";
    //private static final String VERIFICA_STATUS_USUARIO = "SELECT status FROM usuario WHERE email = ? AND senha = CRYPT(?, senha)";
    private static final String VERIFICA_STATUS_USUARIO = "SELECT status FROM usuario WHERE email = ?";
    //private static final String VERIFICA_TIPO_USUARIO = "SELECT * FROM usuario WHERE email = ? AND senha = CRYPT(?, senha)";
    private static final String VERIFICA_TIPO_USUARIO = "SELECT * FROM usuario WHERE email = ?";
    private static final String LOGIN_USUARIO_CONSUMIDOR = "SELECT id_consumidor, nome, sobrenome, usuario, id_usuario FROM consumidor, usuario WHERE id_usuario = ? AND usuario = id_usuario;";
    private static final String LOGIN_USUARIO_ADMINISTRADOR_EMARKET = "";
    private static final String LOGIN_USUARIO_SUPERMERCADO = "";
    private static final String LOGIN_USUARIO_TRANSPORTADORA = "";

    
    /**
     * Método construtor da classe LoginDAO.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public LoginDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ConexaoPostgreJDBC();
    }
    
    /**
     * Método responsável por criar uma instância da classe LoginDAO (Singleton Pattern).
     * @return instance
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static LoginDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new LoginDAO();
        }
        return instance;
    }
    
    
    public Usuario consultarStatusConsumidor(Usuario usuario){
        
        return usuario;
    }

    
    public Usuario reativarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException{
        
        try{
            PreparedStatement pstmtReativaUsuario = conexao.getConnection().prepareStatement(REATIVA_USUARIO);
            pstmtReativaUsuario.setString(1, usuario.getEmail());
            pstmtReativaUsuario.setString(2, usuario.getSenha());

            ResultSet rs = pstmtReativaUsuario.executeQuery();

            if(rs.next()){

                usuario.setIdUsuario(rs.getInt("id_usuario"));

            }else{

                usuario = null;
                conexao.close();

            }
        }catch (RuntimeException e){
            throw new RuntimeException (e);
        }finally{
            conexao.close();
        }
        return usuario;
    }

    public Usuario verificaExistenciaUsuario(Usuario usuario) throws SQLException, ClassNotFoundException{

        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(VERIFICA_EXISTENCIA_USUARIO);
            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getSenha());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){

                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));

            }else{

                usuario = null;
                conexao.close();

            }

        }catch (RuntimeException e){
            usuario = null;
            throw new RuntimeException (e);
        }finally{
            conexao.close();
        }

        return usuario;

    }

    public Usuario verificaStatusUsuario(Usuario usuario) throws SQLException, ClassNotFoundException{
        
        try{
            PreparedStatement pstmtVerificaStatus = conexao.getConnection().prepareStatement(VERIFICA_STATUS_USUARIO);
            pstmtVerificaStatus.setString(1, usuario.getEmail());
            //pstmtVerificaStatus.setString(2, usuario.getSenha());

            ResultSet rs = pstmtVerificaStatus.executeQuery();

            if(rs.next()){

                usuario.setStatus(rs.getInt("status"));

            }else{

                usuario = null;
                conexao.close();

            }
        }catch (RuntimeException e){
            throw new RuntimeException (e);
        }finally{
            conexao.close();
        }
        return usuario;
    }

    public Usuario verificaTipoUsuario(Usuario usuario) throws SQLException, ClassNotFoundException{
        
        try{
            PreparedStatement pstmtTipoUsuario = conexao.getConnection().prepareStatement(VERIFICA_TIPO_USUARIO);
            pstmtTipoUsuario.setString(1, usuario.getEmail());
            //pstmtTipoUsuario.setString(2, usuario.getSenha());

            ResultSet rs = pstmtTipoUsuario.executeQuery();

            if(rs.next()){

                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipoUsuario(rs.getInt("tipo_usuario"));

            }else{

                usuario = null;
                conexao.close();
            }

        }catch (RuntimeException e){
            throw new RuntimeException (e);
        }finally{
            conexao.close();
        }
        return usuario;
    }

    public Consumidor loginConsumidor(Usuario usuario) throws SQLException, ClassNotFoundException{
        
        Consumidor consumidor = new Consumidor();
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(LOGIN_USUARIO_CONSUMIDOR);
            pstmt.setInt(1, usuario.getIdUsuario());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){

                consumidor.setIdConsumidor(rs.getInt("id_consumidor"));
                consumidor.setNome(rs.getString("nome"));
                consumidor.setSobrenome(rs.getString("sobrenome"));

            }else{

                consumidor = null;
                conexao.close();

            }

        }catch (RuntimeException e){
            throw new RuntimeException (e);
        }finally{
            conexao.close();
        }

        return consumidor;
    }

    public Administrador loginAdministrador(Usuario usuario) throws SQLException, ClassNotFoundException{
        
        Administrador administrador = new Administrador();
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(LOGIN_USUARIO_ADMINISTRADOR_EMARKET);
            pstmt.setInt(1, usuario.getIdUsuario());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){

                administrador.setIdAdministrador(rs.getInt("id_administrador"));
                administrador.setNome(rs.getString("nome"));
                administrador.setSobrenome(rs.getString("sobrenome"));

            }else{

                administrador = null;
                conexao.close();

            }

        }catch (RuntimeException e){
            throw new RuntimeException (e);
        }finally{
            conexao.close();
        }

        return administrador;
    }

    public Supermercado loginSupermercado(Usuario usuario) throws SQLException, ClassNotFoundException{
        
        Supermercado supermercado = new Supermercado();
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(LOGIN_USUARIO_SUPERMERCADO);
            pstmt.setInt(1,usuario.getIdUsuario());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){

                supermercado.setIdSupermercado(rs.getInt("id_supermercado"));
                supermercado.getDocumentoPJ().setCnpj(rs.getString("cnpj"));
                supermercado.setRazaoSocial(rs.getString("razaoSociall"));

            }else{

                supermercado = null;
                conexao.close();

            }

        }catch (RuntimeException e){
            throw new RuntimeException (e);
        }finally{
            conexao.close();
        }

        return supermercado;
    }

    private Transportadora loginTransportadora(Usuario usuario) throws SQLException, ClassNotFoundException{
        
        Transportadora transportadora = new Transportadora();
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(LOGIN_USUARIO_TRANSPORTADORA);
            pstmt.setInt(1, usuario.getIdUsuario());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){




            }else{

                transportadora = null;
                conexao.close();

            }

        }catch (RuntimeException e){
            throw new RuntimeException (e);
        }finally{
            conexao.close();
        }
        return transportadora;
    }

}
