package com.fiap.burguer.config;
import com.fiap.burguer.core.application.usecases.*;
import com.fiap.burguer.core.application.utils.JwtUtil;
import com.fiap.burguer.infraestructure.adapters.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {



    @Autowired
    ClientAdapter clientAdapter;

//
//    @Autowired
//    JwtUtil jwtUtil;//JwtUtil


    @Bean
    public ClientUseCases getClientService() {
        return new ClientUseCases(clientAdapter);
    }



}
