/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mrkt.dao;

import br.com.mrkt.model.Usuario;
import java.sql.SQLException;

/**
 *
 * @author Lenno Sousa
 */
public class LoginDAO {
    
    private static LoginDAO instance;
    
    public static LoginDAO getInstance() throws SQLException, ClassNotFoundException{
        
        if(instance == null){
            instance = new LoginDAO();
        }
        return instance;
    }
    
    public Usuario consultarStatusConsumidor(Usuario usuario){
        
        return usuario;
    }

    public Usuario reativarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario loginConsumidor(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
