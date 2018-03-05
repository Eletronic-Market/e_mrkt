package br.com.mrkt.dao;

import br.com.mrkt.factory.ConexaoJDBC;
import br.com.mrkt.factory.ConexaoPostgreJDBC;
import br.com.mrkt.model.Anuncio;
import br.com.mrkt.model.Consumidor;
import br.com.mrkt.model.Supermercado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsável por conter os métodos de acesso ao Banco de Dados para o objeto Anuncio.
 * @author Lenno Sousa
 */
public class AnuncioDAO {
    
    private final ConexaoJDBC conexao;
    private static AnuncioDAO instance;
    
    private static final String CADASTRAR_ANUNCIO_SUPERMERCADO = "INSERT INTO anuncio (anuncio, supermercado, data_inicio, data_final, situacao, familia) VALUES (?, ?, ?, ?, 0, ?)";
    private static final String CONSULTAR_ANUNCIO = "SELECT * FROM anuncio WHERE id_anuncio = id_anuncio ORDER BY id_anuncio DESC";
    private static final String CONSULTAR_ANUNCIO_CONSUMIDOR = "SELECT * FROM anuncio WHERE familia > 9 AND situacao = 1";
    private static final String CADASTRAR_CONTADOR_ANUNCIO = "UPDATE anuncio SET contador = ? WHERE id_anuncio = ?";
    private static final String CONSULTAR_ANUNCIO_ID = "SELECT * FROM anuncio WHERE id_anuncio = ?";
    private static final String VALIDACAO_ANUNCIO = "UPDATE anuncio SET situacao = 1 WHERE id_anuncio = ?";
    private static final String INVALIDAR_ANUNCIO = "UPDATE anuncio SET situacao = 0 WHERE id_anuncio = ?";
    private static final String CONSULTAR_QUANTIDADE_ANUNCIOS =  "SELECT COUNT (anuncio) FROM anuncio";    
    private static final String RECUPERAR_ANUNCIOS_COMPATIVEIS_COM_PREFERENCIAS_CONSUMIDOR = "SELECT * FROM anuncio a JOIN preferencias p ON p.preferencia = a.familia WHERE p.consumidor = ? AND a.situacao<>0";
    
    
    
    
    /**
     * Método construtor da classe AnuncioDAO.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public AnuncioDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ConexaoPostgreJDBC();
    }
    
    
    
    /**
     * Método responsável por criar uma instância da classe AnuncioDAO (Singleton Pattern).
     * @return instance
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static AnuncioDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new AnuncioDAO();
        }
        return instance;
    }
    
    
    
    /**
     * Método responsável por cadastrar o anuncio criado pelo usuárrio Supermercado.
     * @param anuncio
     * @return anuncio
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Anuncio cadastrarAnuncio(Anuncio anuncio) throws SQLException, ClassNotFoundException{
        
        try{
            
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CADASTRAR_ANUNCIO_SUPERMERCADO);
            pstmt.setString(1, anuncio.getAnuncio());
            pstmt.setInt(2, anuncio.getSupermercado().getIdSupermercado());
            pstmt.setDate(3, new java.sql.Date (anuncio.getDataInicio().getTime()));
            pstmt.setDate(4, new java.sql.Date (anuncio.getDataFinal().getTime()));
            pstmt.setInt(5, anuncio.getFamiliaProduto().getIdFamiliaProduto());
            pstmt.execute();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next()){
                anuncio.setIdAnuncio(rs.getInt(1));
            }
            
            conexao.commit();
        }catch (SQLException e) {
            anuncio = null;
            conexao.rollback();
            throw new RuntimeException(e);
        }
        return anuncio;
    }
    
    
    
    /**
     * Método responsável por consultar os anuncios cadastrados para o usuário Supermercado.
     * @param anuncio
     * @return ArrayList <Anuncio> anuncios
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Anuncio> consultarAnuncios(Anuncio anuncio) throws SQLException, ClassNotFoundException{
        
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CONSULTAR_ANUNCIO);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                
                Supermercado supermercado = new Supermercado();
                supermercado.setIdSupermercado(rs.getInt("supermercado_id"));
                
                Anuncio listaAnuncios = new Anuncio();
                listaAnuncios.setIdAnuncio(rs.getInt("id_anuncio"));
                listaAnuncios.setAnuncio(rs.getString("anuncio"));
                listaAnuncios.setDataFinal(rs.getDate("dt_final"));
                listaAnuncios.setSituacao(rs.getInt("situacao"));
                listaAnuncios.setContador(rs.getInt("contador"));
                listaAnuncios.setSupermercado(supermercado);
                
                anuncios.add(listaAnuncios);
            }
            
            conexao.close();
        }catch (SQLException e) {
            anuncios = null;
            conexao.close();
            throw new RuntimeException(e);
        }
        
        return anuncios;
    }
    
    
    
    /**
     * Método responsável por consultar os anuncios direcionados para o usuário Consumidor;
     * 
     * @return anuncios
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Anuncio> consultarAnunciosConsumidor() throws SQLException, ClassNotFoundException{
        
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        
        try{
            
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CONSULTAR_ANUNCIO_CONSUMIDOR);
        
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Anuncio listaAnuncios = new Anuncio();
                listaAnuncios.setIdAnuncio(rs.getInt("id_anuncio"));
                listaAnuncios.setAnuncio(rs.getString("anuncio"));
                listaAnuncios.setContador(rs.getInt("contador"));
                
                anuncios.add(listaAnuncios);
            }
            
            conexao.close();
        }catch(SQLException e){
            anuncios = null;
            conexao.close();
            throw new RuntimeException(e);
        }

        return anuncios;
    }    
    
    
    
    /**
     * Método responsável por cadastrar o contador de um Anuncio e recuperar os atributos do mesmo.
     * @param anuncio
     * @return anuncio
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Anuncio consultarPorId(Anuncio anuncio) throws SQLException, ClassNotFoundException{
        
        try{
            PreparedStatement pstmtCadastro = conexao.getConnection().prepareStatement(CADASTRAR_CONTADOR_ANUNCIO);
            pstmtCadastro.setInt(1, anuncio.getContador());
            pstmtCadastro.setInt(2, anuncio.getIdAnuncio());
            pstmtCadastro.execute();
            
            PreparedStatement pstmtConsultar = conexao.getConnection().prepareStatement(CONSULTAR_ANUNCIO_ID);
            pstmtConsultar.setInt(1, anuncio.getIdAnuncio());
            
            ResultSet rs = pstmtConsultar.executeQuery();
                     
            if(rs.next()){
                
                anuncio.setIdAnuncio(rs.getInt("id_anuncio"));
                anuncio.setAnuncio(rs.getString("anuncio"));
                anuncio.setDataInicio(rs.getDate("dt_inicio"));
                anuncio.setDataFinal(rs.getDate("dt_final"));
                anuncio.setSituacao(rs.getInt("situacao"));
            }
            
            conexao.close();
        }catch (SQLException e) {
            anuncio = null;
            conexao.close();
            throw new RuntimeException(e);
            }
        
        return anuncio;
    }
    
    
    
    /**
     * Método responsável por fazer a validação do Anuncio
     * @param anuncio
     * @throws SQLException 
     */
    public void validacaoAnuncio(Anuncio anuncio) throws SQLException{
        
        int linhasAfetadas = 0;
        
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(VALIDACAO_ANUNCIO);
            pstmt.setInt(1, anuncio.getIdAnuncio());
            
            linhasAfetadas = pstmt.executeUpdate();
        
            if(linhasAfetadas == 1){
                conexao.commit();
            }
        }catch (SQLException e){
            conexao.rollback();
            throw new RuntimeException(e);
        }
    }
    
    
    
    /**
     * Método responsável por invalidar o Anuncio
     * @param anuncio
     * @throws SQLException 
     */
    public void invalidarAnuncio(Anuncio anuncio) throws SQLException{
        
        int linhasAfetadas = 0;
        
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(INVALIDAR_ANUNCIO);
            pstmt.setInt(1, anuncio.getIdAnuncio());
            
            linhasAfetadas = pstmt.executeUpdate();
        
            if(linhasAfetadas == 1){
                conexao.commit();
            }
        
        }catch (Exception e){
            conexao.rollback();
            throw new RuntimeException(e);
        }
    }  
    
    
    
    /**
     * Método responsável por consultar a quantidade de anuncios cadastrados e ativos em todo o sistema.
     * @return
     * @throws SQLException 
     */
    public Anuncio consultarQuantidadeAnuncios() throws SQLException {
        
        Anuncio anuncios = new Anuncio();
        try{
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CONSULTAR_QUANTIDADE_ANUNCIOS);
            pstmt.executeQuery();
            
            ResultSet rs = pstmt.getResultSet();
            
            if(rs.next()){  
                anuncios.setQuantidade(rs.getInt(1));
            }
                
        }catch(Exception e){
                throw new RuntimeException(e);
            }finally{
            conexao.close();
        }
        return anuncios;
    }
    
    
    
    /**
     * Método responsável por recuperar os anuncios direcionados ao usuárui Consumidor de acordo com as suas preferências.
     * @param preferencia
     * @return ArrayList <Anuncio> anuncios
     * @throws SQLException 
     */
    public ArrayList<Anuncio> listar_preferencia_cliente(Consumidor consumidor) throws SQLException{
        
        ArrayList<Anuncio> anuncios = new ArrayList<>();
    
        PreparedStatement pstmt = conexao.getConnection().prepareStatement(RECUPERAR_ANUNCIOS_COMPATIVEIS_COM_PREFERENCIAS_CONSUMIDOR);
        pstmt.setInt(1, consumidor.getIdConsumidor());
        ResultSet rs = pstmt.executeQuery();
        
        while(rs.next()){
            Anuncio listaAnuncios = new Anuncio();
            listaAnuncios.setIdAnuncio(rs.getInt("id_anuncio"));
            listaAnuncios.setAnuncio(rs.getString("anuncio"));
            listaAnuncios.setContador(rs.getInt("contador"));
            anuncios.add(listaAnuncios);
        }
        return anuncios;
    }
    
    
    
    
}
