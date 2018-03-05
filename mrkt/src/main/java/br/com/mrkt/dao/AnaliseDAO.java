package br.com.mrkt.dao;

import br.com.mrkt.factory.ConexaoJDBC;
import br.com.mrkt.factory.ConexaoPostgreJDBC;
import br.com.mrkt.model.Informacoes;
import br.com.mrkt.model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsável por conter os métodos de acesso ao Banco de Dados para as análises direcionadas aos usuários.
 * @author Lenno Sousa
 */
public class AnaliseDAO {
    
    private final ConexaoJDBC conexao;
    private static AnaliseDAO instance;
    
    private static final String CONSULTAR_MAIS_CLICADOS = "SELECT SUM (quantidade) quantidade, id_produto, codigo_barra, descricao FROM itens_lista, produtos WHERE produto = id_produto GROUP BY id_produto ORDER BY quantidade DESC LIMIT 5";
    private static final String CONSULTAR_QUANTIDADE_PRODUTOS = "SELECT COUNT (descricao) FROM produto WHERE supermercado_id = ?";
    private static final String CONSULTAR_QUANTIDADE_PRODUTOS_GERAL = "SELECT COUNT (descricao) FROM analise_produto";
    private static final String CONSULTAR_QUANTIDADE_CONSUMIDORES_CADASTRADOS = "SELECT COUNT (nome) FROM consumidor";
    private static final String CONSULTAR_QUANTIDADE_CONSUMIDORES_ATIVOS = "SELECT COUNT (nome) FROM consumidor";
    
    
    
    
    /**
     * Método construtor da classe AnaliseDAO.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public AnaliseDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ConexaoPostgreJDBC();
    }
    
    
    
    /**
     * Método responsável por criar uma instância da classe AnaliseDAO (Singleton Pattern).
     * @return instance
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static AnaliseDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new AnaliseDAO();
        }
        return instance;
    }
    
    
    
    /**
     * Método responsável por consultar os 5 produtos mais clicados.
     * @param produto
     * @return ArrayList <Produto> maisClicados
     * @throws SQLException 
     * @throws ClassNotFoundException
     */
    public ArrayList<Produto> consultarMaisClicados(Produto produto) throws SQLException, ClassNotFoundException{
        
        ArrayList<Produto> maisClicados = new ArrayList<>();
        
        try{
            
        PreparedStatement pstmt = conexao.getConnection().prepareStatement(CONSULTAR_MAIS_CLICADOS);
        
        ResultSet rs = pstmt.executeQuery();
        
        while(rs.next()){
            
            Produto produtoMaisClicado = new Produto();
            produtoMaisClicado.setCodigoDeBarra(rs.getString("codigo_barra"));
            produtoMaisClicado.setNome(rs.getString("descricao"));
            
            maisClicados.add(produtoMaisClicado);
        }
        
        conexao.close();
        
        }catch(SQLException e){
            conexao.close();
            throw new RuntimeException(e);
        }

        return maisClicados;
    }
    
    
    
    /**
     * Método responsável por consultar a quantidade de produtos cadastrados de um supermercado. 
     * @param produto
     * @return produto
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Produto consultarQuantidadeProdutos(Produto produto) throws SQLException, ClassNotFoundException{
        
        try{
            
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CONSULTAR_QUANTIDADE_PRODUTOS);
            pstmt.setInt(1, produto.getSupermercado().getIdSupermercado());
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                produto.setQuantidade(rs.getInt(1));
            }
            
            conexao.close();
        }catch(SQLException e){
            conexao.close();
            throw new RuntimeException(e);
        }
        
        return produto;
    }
    
    
    
    /**
     * Método responsável por consultar a quantidade de produtos cadastrados no sistema, sem repeti-los.
     * @param produto
     * @return produto
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Produto consultarQuantidadeProdutosGeral(Produto produto) throws SQLException, ClassNotFoundException{
        
        try{
            
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CONSULTAR_QUANTIDADE_PRODUTOS_GERAL);
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                produto.setQuantidade(rs.getInt(1));
            }
            
            conexao.close();
        }catch(SQLException e){
            conexao.close();
            throw new RuntimeException(e);
        }
        
        return produto;
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
            PreparedStatement pstmt = conexao.getConnection().prepareStatement(CONSULTAR_QUANTIDADE_CONSUMIDORES_CADASTRADOS);
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
