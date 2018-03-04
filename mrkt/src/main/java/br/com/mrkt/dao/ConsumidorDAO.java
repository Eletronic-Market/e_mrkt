package br.com.mrkt.dao;

import br.com.mrkt.factory.ConexaoJDBC;
import br.com.mrkt.factory.ConexaoPostgreJDBC;
import br.com.mrkt.model.Consumidor;
import br.com.mrkt.model.Endereco;
import br.com.mrkt.model.Informacoes;
import br.com.mrkt.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsável por conter os métodos de acesso ao Banco de Dados para o objeto Consumidor.
 * @author Lenno Sousa
 */
public class ConsumidorDAO {
    
    private final ConexaoJDBC conexao;
    private static ConsumidorDAO instance;
    
    private static final String VERIFICAR_EMAIL_EXISTENTE = "SELECT email FROM usuario WHERE email = ?";
    private static final String CADASTRAR_DADOS_USUARIO_CONSUMIDOR = "INSERT INTO usuario (email, senha, tipo_usuario, status) VALUES (?,CRYPT(?, gen_salt('bf',8)), 1, 1)";
    private static final String VERIFICAR_DOCUMENTO_EXISTENTE = "SELECT cpf FROM documento_pf WHERE cpf = ?";
    private static final String CADASTRAR_DOCUMENTO_CONSUMIDOR = "INSERT INTO documento_pf (cpf) VALUES (?)";
    private static final String CADASTRAR_DADOS_CONSUMIDOR = "INSERT INTO consumidor (nome, sobrenome, usuario, documento_pf) VALUES (?, ?, ?, ?)";
    private static final String CADASTRAR_ENDERECO_CONSUMIDOR = "INSERT INTO endereco (estado, cidade, bairro, cep, rua, numero) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String CADASTRAR_DADOS_ENDERECO_CONSUMIDOR = "INSERT INTO consumidor (endereco) VALUES (?) WHERE id_consumidor = ?";
    private static final String CONSULTAR_CONSUMIDOR_POR_ID = "SELECT c.id_consumidor, c.nome, c.sobrenome, c.endereco, u.email, u.id_usuario FROM consumidor AS c, usuario AS u WHERE c.id_consumidor = ? AND u.id_usuario = c.usuario";
    private static final String CONSULTAR_ENDERECO_CONSUMIDOR = "SELECT * FROM endereco WHERE id_endereco = ?";
    private static final String ATUALIZAR_DADOS_CONSUMIDOR = "UPDATE consumidor SET nome = ?, sobrenome = ? WHERE id_consumidor = ?";
    private static final String VERIFICAR_NOVO_EMAIL = "SELECT email FROM usuario WHERE id_usuario = ?";
    private static final String ATUALIZAR_EMAIL_CONSUMIDOR = "UPDATE usuario SET email = ? WHERE id_usuario = ?";
    private static final String VERIFICAR_NOVA_SENHA = "SELECT email FROM usuario WHERE senha = CRYPT(?, senha) AND id_usuario = ?";
    private static final String ATUALIZAR_SENHA_CONSUMIDOR = "UPDATE usuario SET senha = CRYPT(?, gen_salt('bf',8)) WHERE id_usuario = ?";
    private static final String DESATIVAR_CONSUMIDOR = "UPDATE usuario SET status = 0 WHERE id_usuario = ?";
    private static final String CADASTRAR_NOTIFICACOES_PENDENTES = "INSERT into notificacoes_pendentes (id_usuario, tipo_notificacao) VALUES (?, ?)";
    private static final String CONSULTAR_QUANTIDADE_CONSUMIDORES = "SELECT COUNT (nome) FROM consumidor";
    
    
    
    /**
     * Método construtor da classe ConsumidorDAO.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ConsumidorDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ConexaoPostgreJDBC();
    }
    
    
    
    /**
     * Método responsável por criar uma instância da classe ConsumidorDAO (Singleton Pattern).
     * @return instance
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ConsumidorDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new ConsumidorDAO();
        }
        return instance;
    }
    
    
    
    /**
     * Método responsável por verificar se já existe no Banco de Dados, o e-mail que o usuário Consumidor está inserindo.
     * @param consumidor
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor verificarEmailExistente(Consumidor consumidor) throws SQLException, ClassNotFoundException{

        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(VERIFICAR_EMAIL_EXISTENTE);
            pstmt.setString(1, consumidor.getUsuario().getEmail());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                consumidor.getUsuario().setEmail(rs.getString("email"));
            }else{
                consumidor.getUsuario().setEmail(null);
            }
            
            conexao.close();

        }catch(SQLException e){
            consumidor = null;
            throw new RuntimeException(e);
        }

        return consumidor;
        
    }

    
    
    /**
     * Método responsável por verificar se já existe no Banco de Dados, o cpf que o usuário Consumidor está inserindo.
     * @param consumidor
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor verificarDocumentoExistente(Consumidor consumidor) throws SQLException, ClassNotFoundException{

        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(VERIFICAR_DOCUMENTO_EXISTENTE);
            pstmt.setString(1, consumidor.getDocumentoPF().getCpf());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                consumidor.getDocumentoPF().setCpf(rs.getString("cpf"));
            }else{
                consumidor.getDocumentoPF().setCpf(null);
            }
            
            conexao.close();

        }catch(SQLException e){
            consumidor = null;
            throw new RuntimeException(e);
        }

        return consumidor;

    }

    
    
    /**
     * Método responsável por cadastrar o usuário Consumidor no Banco de Dados.
     * @param consumidor
     * @return Consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor cadastrarConsumidor(Consumidor consumidor) throws  SQLException, ClassNotFoundException{

        try{
            PreparedStatement pstmtUsuario = conexao.getConnection().prepareStatement(CADASTRAR_DADOS_USUARIO_CONSUMIDOR, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmtUsuario.setString(1, consumidor.getUsuario().getEmail());
            pstmtUsuario.setString(2, consumidor.getUsuario().getSenha());
            pstmtUsuario.execute();

            ResultSet rsIdUsuario = pstmtUsuario.getGeneratedKeys();

            if(rsIdUsuario.next()){

                PreparedStatement pstmtDocumentoPF = conexao.getConnection().prepareStatement(CADASTRAR_DOCUMENTO_CONSUMIDOR, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmtDocumentoPF.setString(1, consumidor.getDocumentoPF().getCpf());
                pstmtDocumentoPF.execute();

                ResultSet rsIdDocumentoPF = pstmtDocumentoPF.getGeneratedKeys();

                if(rsIdDocumentoPF.next()){

                    int idUsuario = rsIdUsuario.getInt("id_usuario");
                    int idDocumentoPF = rsIdDocumentoPF.getInt("id_documento_pf");

                    PreparedStatement pstmtConsumidor = conexao.getConnection().prepareStatement(CADASTRAR_DADOS_CONSUMIDOR, PreparedStatement.RETURN_GENERATED_KEYS);
                    pstmtConsumidor.setString(1, consumidor.getNome());
                    pstmtConsumidor.setString(2, consumidor.getSobrenome());
                    pstmtConsumidor.setInt(3, idUsuario);
                    pstmtConsumidor.setInt(4, idDocumentoPF);
                    pstmtConsumidor.execute();

                    ResultSet rsIdConsumidor = pstmtConsumidor.getGeneratedKeys();

                    if(rsIdConsumidor.next()) {
                        consumidor.setIdConsumidor(rsIdConsumidor.getInt(1));
                    }else{
                        consumidor = null;
                    }
                    
                }else{
                    consumidor = null;
                }

            }else{
                consumidor = null;
            }
            
            conexao.commit(); 
        }catch(SQLException e){
            consumidor = null;
            conexao.rollback();
            throw new RuntimeException(e);
        }

        return consumidor;
    }

    
    
    /**
     * Método responsável por cadastrar o endereço do usuário Consumidor.
     * @param consumidor
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor cadastrarEnderecoConsumidor(Consumidor consumidor) throws SQLException, ClassNotFoundException {

        try{
            PreparedStatement pstmtEndereco = conexao.getConnection().prepareStatement(CADASTRAR_ENDERECO_CONSUMIDOR, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmtEndereco.setString(1, consumidor.getEndereco().getEstado());
            pstmtEndereco.setString(2, consumidor.getEndereco().getCidade());
            pstmtEndereco.setString(3, consumidor.getEndereco().getBairro());
            pstmtEndereco.setString(4, consumidor.getEndereco().getCep());
            pstmtEndereco.setString(5, consumidor.getEndereco().getRua());
            pstmtEndereco.setString(6, consumidor.getEndereco().getNumero());
            pstmtEndereco.execute();

            ResultSet rsIdEndereco = pstmtEndereco.getGeneratedKeys();

            if(rsIdEndereco.next()){

                int idEndereco = rsIdEndereco.getInt("id_endereco");

                PreparedStatement pstmtConsumidor = conexao.getConnection().prepareStatement(CADASTRAR_DADOS_ENDERECO_CONSUMIDOR, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmtConsumidor.setInt(1, idEndereco);
                pstmtConsumidor.setInt(2, consumidor.getIdConsumidor());
                pstmtConsumidor.execute();

                consumidor.setIdConsumidor(consumidor.getIdConsumidor());

            }
            
            conexao.commit();
            
        }catch(SQLException e){
            consumidor = null;
            conexao.rollback();
            throw new RuntimeException(e);
        }

        return consumidor;

    }

    
    
    /**
     * Método responsável por consultar os dados do usuário Consumidor.
     * @param consumidor
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor consultarConsumidor(Consumidor consumidor) throws  SQLException, ClassNotFoundException{
        
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CONSULTAR_CONSUMIDOR_POR_ID);
            pstmt.setInt(1, consumidor.getIdConsumidor());

            ResultSet rsConsumidor = pstmt.executeQuery();

            if(rsConsumidor.next()){

                consumidor.setIdConsumidor(rsConsumidor.getInt("id_consumidor"));
                consumidor.setNome(rsConsumidor.getString("nome"));
                consumidor.setSobrenome(rsConsumidor.getString("sobrenome"));

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rsConsumidor.getInt("id_usuario"));
                usuario.setEmail(rsConsumidor.getString("email"));

                Endereco endereco = new Endereco();
                endereco.setIdEndereco(rsConsumidor.getInt("endereco"));

                consumidor.setEndereco(endereco);
                consumidor.setUsuario(usuario);

            }
            
            conexao.close();
            
        }catch(SQLException e){
            consumidor = null;
            throw new RuntimeException(e);
        }

        return consumidor;

    }

    
    
    /**
     * Método responsável por consultar os endereços cadastrados pelo usuário Consumidor.
     * @param consumidor
     * @return ArrayList <Endereco> enderecos
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Endereco> consultarEnderecosConsumidor(Consumidor consumidor) throws SQLException, ClassNotFoundException{
        
        ArrayList<Endereco> enderecos = new ArrayList<>();

        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CONSULTAR_ENDERECO_CONSUMIDOR);
            pstmt.setInt(1,consumidor.getEndereco().getIdEndereco());

            ResultSet rsEndereco = pstmt.executeQuery();

            while(rsEndereco.next()){

                Endereco endereco = new Endereco();
                endereco.setEstado(rsEndereco.getString("estado"));
                endereco.setCidade(rsEndereco.getString("cidade"));
                endereco.setBairro(rsEndereco.getString("bairro"));
                endereco.setCep(rsEndereco.getString("cep"));
                endereco.setRua(rsEndereco.getString("rua"));
                endereco.setNumero(rsEndereco.getString("numero"));
                endereco.setApelido(rsEndereco.getString("apelido"));

                enderecos.add(endereco);

            }
            
            conexao.close();

        }catch(SQLException e){
            enderecos = null;
            throw new RuntimeException(e);
        }

        return enderecos;

    }

    
    
    /**
     * Método responsável por atualizar os dados do usuário Consumidor.
     * @param consumidor
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor atualizarDadosConsumidor(Consumidor consumidor) throws SQLException, ClassNotFoundException {

        int linhasAfetadas = 0;
        
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(ATUALIZAR_DADOS_CONSUMIDOR);
            pstmt.setString(1, consumidor.getNome());
            pstmt.setString(2, consumidor.getSobrenome());
            pstmt.setInt(3, consumidor.getIdConsumidor());
            
            linhasAfetadas = pstmt.executeUpdate();

            if(linhasAfetadas == 1){
                consumidor.setIdConsumidor(consumidor.getIdConsumidor());
                this.conexao.commit();
            }
            
        }catch (SQLException e) {
            consumidor = null;
            conexao.rollback();
            throw new RuntimeException(e);
        }

        return consumidor;

    }

    
    
    /**
     * Método responsável por verificar o novo e-mail que o usuário Consumidor está alterando.
     * @param consumidor
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor verificarNovoEmail(Consumidor consumidor) throws  SQLException, ClassNotFoundException{

        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(VERIFICAR_NOVO_EMAIL);
            pstmt.setInt(1, consumidor.getUsuario().getIdUsuario());

            ResultSet rsVerifica = pstmt.executeQuery();

            if(rsVerifica.next()){
                consumidor.getUsuario().setEmail(rsVerifica.getString("email"));
            }else{
                consumidor = null;
            }
            
            conexao.close();
            
        }catch (SQLException e) {
            consumidor = null;
            conexao.close();
            throw new RuntimeException(e);
        }

        return consumidor;

    }

    
    
    /**
     * Método responsável por atualizar o novo e-mail do usuário Consumidor.
     * @param consumidor
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor atualizarNovoEmail(Consumidor consumidor) throws SQLException, ClassNotFoundException{

        int linhasAfetadas = 0;
        
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(ATUALIZAR_EMAIL_CONSUMIDOR);
            pstmt.setString(1, consumidor.getUsuario().getEmail());
            pstmt.setInt(2, consumidor.getUsuario().getIdUsuario());
            
            linhasAfetadas = pstmt.executeUpdate();

            if(linhasAfetadas == 1){
                consumidor.getUsuario().setIdUsuario(consumidor.getIdConsumidor());
                this.conexao.commit();
            }

        }catch (SQLException e) {
            consumidor = null;
            conexao.rollback();
            throw new RuntimeException(e);
        }

        return consumidor;

    }

    
    
    /**
     * Método responsável por fazer a verificação da nova senha que o usuário Consumidor está alterando.
     * @param consumidor
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor verificarNovaSenha(Consumidor consumidor) throws SQLException, ClassNotFoundException {

        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(VERIFICAR_NOVA_SENHA);
            pstmt.setString(1, consumidor.getUsuario().getSenha());
            pstmt.setInt(2, consumidor.getUsuario().getIdUsuario());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                consumidor.getUsuario().setEmail(rs.getString("email"));
            }else{
                consumidor.getUsuario().setEmail(null);
            }
            
            conexao.close();
            
        }catch (SQLException e) {
            consumidor = null;
            conexao.close();
            throw new RuntimeException(e);
        }

        return consumidor;

    }

    
    
    /**
     * Método responsável por atualizar a nova senha do usuário Consumidor.
     * @param consumidor
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor atualizarSenhaConsumidor(Consumidor consumidor) throws SQLException, ClassNotFoundException {

        int linhasAfetadas = 0;
        
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(ATUALIZAR_SENHA_CONSUMIDOR);
            pstmt.setString(1, consumidor.getUsuario().getSenha());
            pstmt.setInt(2, consumidor.getUsuario().getIdUsuario());
            
            linhasAfetadas = pstmt.executeUpdate();

            if(linhasAfetadas == 1){
                consumidor.getUsuario().setIdUsuario(consumidor.getIdConsumidor());
                this.conexao.commit();
            }
            
        }catch (SQLException e) {
            consumidor = null;
            conexao.rollback();
            throw new RuntimeException(e);
        }

        return consumidor;

    }

    
    
    /**
     * Método responsável por desativar a conta do usuário Consumidor.
     * @param consumidor
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor desativarConsumidor(Consumidor consumidor) throws SQLException, ClassNotFoundException {

        int linhasAfetadas = 0;
        
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(DESATIVAR_CONSUMIDOR);
            pstmt.setInt(1, consumidor.getUsuario().getIdUsuario());
            
            linhasAfetadas = pstmt.executeUpdate();

            if(linhasAfetadas == 1){
                consumidor.getUsuario().setIdUsuario(consumidor.getIdConsumidor());
                this.conexao.commit();
            }

        }catch (SQLException e) {
            consumidor = null;
            conexao.rollback();
            throw new RuntimeException(e);
        }

        return consumidor;

    }

    
    
    /**
     * Método responsável por cadastrar os códigos de notificações que ainda não foram enviadas ao usuário Consumidor devido a erros no servidor.
     * @param consumidor
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Consumidor cadastrarNotificacoesPendentes(Consumidor consumidor) throws SQLException, ClassNotFoundException{

        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CADASTRAR_NOTIFICACOES_PENDENTES);
            pstmt.setInt(1, consumidor.getUsuario().getIdUsuario());
            pstmt.setString(2, consumidor.getNotificacoesPendentes().getTipo_notificacao());
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                conexao.commit();
            }

        }catch (SQLException e) {
            consumidor = null;
            conexao.rollback();
            throw new RuntimeException(e);
        }

        return consumidor;
        
    }

    
    
    /**
     * Método responsável por consultar a quantidade de usuários Consumidores para serem apresentados no painel do usuário Administrador.
     * @return consumidor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Informacoes consultarQuantidade() throws  SQLException, ClassNotFoundException{
        
        Informacoes informacoes = new Informacoes();

        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CONSULTAR_QUANTIDADE_CONSUMIDORES);
            pstmt.executeQuery();

            ResultSet rs = pstmt.getResultSet();

            if(rs.next()){
                informacoes.setQuantidadeUsuariosConsumidores(rs.getInt(1));
            }else{
                informacoes = null;
            }
            
            conexao.close();
            
        }catch (SQLException e) {
            informacoes = null;
            conexao.close();
            throw new RuntimeException(e);
        }

        return informacoes;

    }
    
}
