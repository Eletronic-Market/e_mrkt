package br.com.mrkt.controller;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;

import java.sql.SQLException;

import br.com.mrkt.dao.ConsumidorDAO;
import br.com.mrkt.dao.LoginDAO;
import br.com.mrkt.model.Consumidor;
import br.com.mrkt.model.DocumentoPF;
import br.com.mrkt.model.Endereco;
import br.com.mrkt.model.Usuario;
import br.com.mrkt.model.notification.NotificacoesConsumidor;

/**
 * Classe Controller responsável por receber dados de aplicações externas e realizar ações do usuário Consumidor.
 * @author Lenno Sousa
 * @date 23/02/2018
 */
@Path("consumidor")
public class ConsumidorController {
    
    
    
    /**
     * Método responsável por cadastrar o usuário Consumidor.
     * @param consumidorWeb
     * @return OK || PARTIAL_CONTENT || CONFLICT || PRECONDITION_FAILED || INTERNAL_SERVER_ERROR
     */
    @POST //POST é utilizado para inserir informações.
    @Consumes(MediaType.APPLICATION_JSON) //Cadastro, Consultas e Atualizações iremos consumir um JSON.
    @Path("cadastrar/{nome}/{sobrenome}/{cpf}/{email}/{senha}/")
    public Response cadastrarConsumidor(
            @PathParam("nome") String nome, 
            @PathParam("sobrenome") String sobrenome, 
            @PathParam("cpf") String cpf, 
            @PathParam("email") String email, 
            @PathParam("senha") String senha){
        
        Response statusCadastroConsumidor = null;
        
        try{
            
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);
            
            DocumentoPF documentoPF = new DocumentoPF();
            documentoPF.setCpf(cpf);
            
            Consumidor consumidor = new Consumidor();
            consumidor.setNome(nome);
            consumidor.setSobrenome(sobrenome);
            consumidor.setUsuario(usuario);
            consumidor.setDocumentoPF(documentoPF);
            
            ConsumidorDAO cDAO = ConsumidorDAO.getInstance();
            
            consumidor = cDAO.verificarEmailExistente(consumidor);
            
            if(consumidor.getUsuario().getEmail() == null){
                
                consumidor = cDAO.verificarDocumentoExistente(consumidor);
                
                if(consumidor.getDocumentoPF().getCpf() == null){
                    
                    consumidor.getUsuario().setEmail(email);
                    consumidor.getDocumentoPF().setCpf(cpf);
                    
                    consumidor = cDAO.cadastrarConsumidor(consumidor);
                    
                    if(consumidor != null){
                        
                        NotificacoesConsumidor notificacoesC = new NotificacoesConsumidor();
                        
                        if(notificacoesC.notificacaoCadastroConsumidor(consumidor)){
                            
                            statusCadastroConsumidor = Response.status(Response.Status.OK).build(); // OK -> Sucesso total na execução de todo o método.
                            
                            //TO-DO on Front
                            //1º Redirecionar para uma outra tela ou exibir um aviso.
                            //2º Informar que os dados foram cadastrados com sucesso.
                            //3º Pedir para entrar no endereço de e-mail cadastrado e clicar no link para confirmar o cadastro.
                            
                        }else{

                            //Informar o tipo de notificação que não foi enviada.
                            consumidor.getNotificacoesPendentes().setTipo_notificacao("cadastro_consumidor");

                            //Cadastrar tais informações no banco de dados junto com o tipo de usuário.
                            cDAO.cadastrarNotificacoesPendentes(consumidor);
                            
                            //TO-DO on Back
                            //1º Criar uma Thread que irá verificar de tempos em tempos essa tabela no banco de dados.
                            //2º Se houver dados cadastrados nesta tabela, deverá recuperá-los.
                            //3º Então selecionar o os dados do usuário Consumidor para poder realizar o envio do e-mail.
                            //4º Se a notificação for enviada, deverá apagar o registro de usuário e notificação pendente do banco de dados.
                            //5º Caso ainda não seja possível reenviar a notificação, mantém os dados no banco, e gere tais informações em logs.

                            //TO-DO on Front
                            //1º Informar que os dados foram cadastrados e que em instante irá receber um e-mail de confirmação.
                            
                            statusCadastroConsumidor = Response.status(Response.Status.PARTIAL_CONTENT).build(); // PARTIAL_CONTENT -> Faltou apenas enviar o e-mail.
                        }
                    }else{
                        statusCadastroConsumidor = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build(); // INTERNAL_SERVER_ERROR -> No momento não foi possível realizar o cadastro.
                    }
                }else{
                    statusCadastroConsumidor = Response.status(Response.Status.CONFLICT).build(); // CONFLICT -> CPF digitado já está cadastrado, por favor, informe o número verdadeiro.
                }
            }else{
                statusCadastroConsumidor = Response.status(Response.Status.PRECONDITION_FAILED).build(); // PRECONDITION_FAILED -> E-mail já cadastrado no sistema, informe outro e-mail válido.
            }
        }catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(ConsumidorController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return statusCadastroConsumidor;
    }
    
    
    
    /**
     * Método responsável por fazer o login/reativação da conta do usuário Consumidor.
     * @return consumidor
     */
    @GET //GET é utilizado para recuperar informações no servidor.
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login/{email}/{senha}")
    public Consumidor loginConsumidor(@PathParam("email") String email, @PathParam("senha") String senha){
        
        try{
            
            LoginDAO lDAO = LoginDAO.getInstance();
            
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);
            
            usuario = lDAO.consultarStatusConsumidor(usuario);
            
            Consumidor consumidor = new Consumidor();
            
            if(usuario.getStatus() == 1){
                
                //Faz o Login
                usuario = lDAO.loginConsumidor(usuario);
                
                if(usuario != null){
                    
                    //TO DO - Anuncios Direcionados

                    //TO DO - Preferencias do Consumidor

                    //TO DO - Recuperar jornal com produtos para apresentar.

                    //TO DO - Recuperar Consumidor para alocar os dados na sessão.
                }else{
                    consumidor.getUsuario().setIdUsuario(1);
                }
            }else{
                
                //Faz a reativação da conta
                usuario = lDAO.reativarUsuario(usuario);
                
                if(usuario == null){
                    
                    // Informar que houve um erro ao reativar a conta.
                    consumidor.setUsuario(usuario);
                }
            }
            
            return consumidor;
        }catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(ConsumidorController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    /**
     * TO-DO: Implementar o método
     * 
     * Método responsável por receber os dados de uma aplicação externa e passar os dados para cadastrar o endereço do usuário Consumidor.
     * @param endereco
     * @return OK || INTERNAL_SERVER_ERROR
     */
    @POST //POST é utilizado para inserir informações.
    @Consumes(MediaType.APPLICATION_JSON) //ao invez de gerar, iremos consumir um JSON quando cadastramos, atualizamos e por ai vai
    @Path("cadastrar/endereco")
    public Response cadastrarEndereco(Endereco endereco){
        
        try{
            
            ConsumidorDAO cDAO = ConsumidorDAO.getInstance();
            
        }catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(ConsumidorController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return Response.status(Response.Status.OK).build();
    }
    
    
    
    /**
     * Método responsável por recuperar os dados do usuário Consumidor.
     * @param id
     * @return consumidor
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultar/{id}/")
    public Consumidor consultarDadosConsumidor(@PathParam("id") int id){
        
        try{
            
            ConsumidorDAO cDAO = ConsumidorDAO.getInstance();
            
            Consumidor consumidor = new Consumidor();
            consumidor.setIdConsumidor(id);
            
            consumidor = cDAO.consultarConsumidor(consumidor);
            
            if(consumidor != null){
                
                if(consumidor.getEndereco().getIdEndereco() !=0){
                    
                    ArrayList<Endereco> enderecos = cDAO.consultarEnderecosConsumidor(consumidor);
                    consumidor.setEnderecos(enderecos);
                    
                }
            }
            
            return consumidor;
            
        }catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(ConsumidorController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    


    /**
     * Método responsável por atualizar os dados pessoais do usuário Consumidor.
     * @param consumidor
     * @return OK || INTERNAL_SERVER_ERROR
     */
    @PUT //PUT é utilizado para atualizar informações.
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar/")    
    public Response atualizarDadosConsumidor(Consumidor consumidorWeb){
        
        Response statusDadosConsumidor = null;
        
        try{
            
            ConsumidorDAO cDAO = ConsumidorDAO.getInstance();
            
            Consumidor consumidor = new Consumidor();
            consumidor.setIdConsumidor(consumidorWeb.getIdConsumidor());
            consumidor.setNome(consumidorWeb.getNome());
            consumidor.setSobrenome(consumidorWeb.getSobrenome());
            
            consumidor = cDAO.atualizarDadosConsumidor(consumidor);
            
            if(consumidor != null){
                statusDadosConsumidor =  Response.status(Response.Status.OK).build();
            }else{
                statusDadosConsumidor =  Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        }catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(ConsumidorController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }        
        return statusDadosConsumidor;
    } 
    
    
    
    /**
     * Método responsável por atualizar o e-mail do usuário Consumidor.
     * @param consumidor
     * @return OK || PARTIAL_CONTENT || NOT_MODIFIED || PRECONDITION_FAILED || CONFLICT || INTERNAL_SERVER_ERROR
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar/email/{id}/{email}")
    public Response atualizarEmailConsumidor(@PathParam("id") int id, @PathParam("email") String email){
        
        Response statusEmailConsumidor = null;
        
        try{
            
            ConsumidorDAO cDAO = ConsumidorDAO.getInstance();
            
            String novoEmail = email;
            
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(id);
            usuario.setEmail(email);
            
            Consumidor consumidor = new Consumidor();
            consumidor.setUsuario(usuario);
            
            consumidor = cDAO.verificarEmailExistente(consumidor);
            
            if(consumidor.getUsuario().getEmail() == null){
                
                consumidor = cDAO.verificarNovoEmail(consumidor);
                
                if(!consumidor.getUsuario().getEmail().equals(novoEmail)){
                    
                    consumidor.getUsuario().setEmail(novoEmail);
                    
                    consumidor = cDAO.atualizarNovoEmail(consumidor);
                    
                    if(consumidor != null){
                        
                        NotificacoesConsumidor notificacoesC = new NotificacoesConsumidor();
                        
                        if(notificacoesC.notificacaoAtualizacaoEmailConsumidor(consumidor)){   
                            return Response.status(Response.Status.OK).build();
                        }else{
                            
                            //Informar o tipo de notificação que não foi enviada.
                            consumidor.getNotificacoesPendentes().setTipo_notificacao("alteracao_email_consumidor");

                            //Cadastrar tais informações no banco de dados junto com o tipo de usuário.
                            cDAO.cadastrarNotificacoesPendentes(consumidor);

                            //TO-DO on Back
                            //1º Criar uma Thread que irá verificar de tempos em tempos essa tabela no banco de dados.
                            //2º Se houver dados cadastrados nesta tabela, deverá recuperá-los.
                            //3º Então selecionar o os dados do usuário Consumidor para poder realizar o envio do e-mail.
                            //4º Se a notificação for enviada, deverá apagar o registro de usuário e notificação pendente do banco de dados.
                            //5º Caso ainda não seja possível reenviar a notificação, mantém os dados no banco, e gere tais informações em logs.

                            //TO-DO on Front
                            //1º Informar que os dados foram cadastrados e que em instante irá receber um e-mail de confirmação.
                            
                            statusEmailConsumidor = Response.status(Response.Status.PARTIAL_CONTENT).build();
                        }
                    }else{
                        statusEmailConsumidor = Response.status(Response.Status.NOT_MODIFIED).build(); // NOT_MODIFIED -> E-mail não foi alterado.
                    }
                }else{
                    
                    //Informar ao usuário que o email ainda é o mesmo, e que precisa ser outro caso queira alterar.
                    statusEmailConsumidor = Response.status(Response.Status.PRECONDITION_FAILED).build();
                }
            }else{
                
                //Informar ao usuário que o email já existe no nosso banco de dados. tem que ser outro.
                statusEmailConsumidor = Response.status(Response.Status.CONFLICT).build();
            }
        }catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(ConsumidorController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return statusEmailConsumidor;
    }
    
    
    
    /**
     * Método responsável por atualizar a senha do usuário Consumidor.
     * 
     * @param consumidor
     * @return OK || PRECONDITION_FAILED || CONFLICT || INTERNAL_SERVER_ERROR
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar/senha/{id}/{antigaSenha}/{novaSenha}/{novaSenhaRepetida}/")
    public Response atualizarSenhaConsumidor(
            @PathParam("id") int id, 
            @PathParam("antigaSenha") String antigaSenha, 
            @PathParam("novaSenha") String novaSenha, 
            @PathParam("novaSenhaRepetida") String novaSenhaRepetida){
        
        Response statusSenhaConsumidor = null;
        
        try{
            
            ConsumidorDAO cDAO = ConsumidorDAO.getInstance();
            
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(id);
            usuario.setSenha(antigaSenha);
            
            Consumidor consumidor = new Consumidor();
            consumidor.setUsuario(usuario);
            
            consumidor = cDAO.verificarNovaSenha(consumidor);
            
            if(consumidor.getUsuario().getEmail() != null){
                
                String senhaNova = novaSenha;
                String senhaNovaRepetida = novaSenhaRepetida;
                
                if(senhaNova.equals(senhaNovaRepetida)){
                    
                    consumidor.getUsuario().setSenha(senhaNova);
                    
                    consumidor = cDAO.atualizarSenhaConsumidor(consumidor);
                    
                    if(consumidor != null){
                        statusSenhaConsumidor =  Response.status(Response.Status.OK).build();
                    }else{
                        // Informar que não foi possível atualizar a senha devido a problemas internos.
                        statusSenhaConsumidor =  Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                    }
                }else{
                    // Informar que a nova senha não está digitada corretamente no outro campo
                    statusSenhaConsumidor = Response.status(Response.Status.PRECONDITION_FAILED).build();
                }
                // A senha antiga não está digitada corretamente. por favor, digite corretamente.
                statusSenhaConsumidor = Response.status(Response.Status.CONFLICT).build();
            }
        }catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(ConsumidorController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return statusSenhaConsumidor;
    }
    
    
    
    /**
     * Método responsável por inativar o usuário Consumidor.
     * @param id
     * @return OK || INTERNAL_SERVER_ERROR
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inativar/{id}/")
    public Response inativarConsumidor(@PathParam("id") int id){
        
        Response statusInativacaoConsumidor = null;
        
        try{
            
            ConsumidorDAO cDAO = ConsumidorDAO.getInstance();
            
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(id);
            
            Consumidor consumidor = new Consumidor();
            consumidor.setUsuario(usuario);
            
            consumidor = cDAO.desativarConsumidor(consumidor);
            
            if(consumidor != null){
                
                // Informar que a conta foi desativada e mudar para a index.
                statusInativacaoConsumidor = Response.status(Response.Status.OK).build();
                
            }else{
                
                // Informar que não foi possível desativar a conta no momento.
                statusInativacaoConsumidor = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(ConsumidorController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return statusInativacaoConsumidor;
    }
    
}
