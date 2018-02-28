package br.com.mrkt.resource;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Classe responsável pelo caminho para acesso ao Web Service.
 * @author Lenno Sousa
 */
@ApplicationPath("rest")
public class MRKTApplication extends ResourceConfig{
    
    public MRKTApplication(){
        packages("br.com.mrkt.controller");
        register(CORSResponseFilter.class);
    }
}
