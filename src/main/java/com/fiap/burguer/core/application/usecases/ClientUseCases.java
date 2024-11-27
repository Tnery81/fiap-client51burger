package com.fiap.burguer.core.application.usecases;

import com.fiap.burguer.core.application.Exception.InvalidCPFException;
import com.fiap.burguer.core.application.Exception.InvalidEmailException;
import com.fiap.burguer.core.application.Exception.RequestException;
import com.fiap.burguer.core.application.Exception.ResourceNotFoundException;
import com.fiap.burguer.core.application.ports.AuthenticationPort;
import com.fiap.burguer.core.application.ports.ClientPort;
import com.fiap.burguer.core.application.utils.CPFUtils;
import com.fiap.burguer.core.application.utils.EmailUtils;
import com.fiap.burguer.core.domain.Client;
import org.springframework.stereotype.Service;


public class ClientUseCases {
    private final ClientPort clientPort;
    private final AuthenticationPort authenticationPort;

    public ClientUseCases(ClientPort clientPort, AuthenticationPort authenticationPort) {
        this.clientPort = clientPort;
        this.authenticationPort = authenticationPort;
    }

    public Client saveClientOrUpdate(Client client) {
        client.setCpf(client.getCpf().replaceAll("\\D", ""));
        if (!CPFUtils.isValidCPF(client.getCpf())) {
            throw new InvalidCPFException("CPF '" + client.getCpf() + "' inválido!");
        }

        if (!EmailUtils.isValidEmail(client.getEmail())) {
            throw new InvalidEmailException("E-mail '" + client.getEmail() + "' inválido!");
        }

        return clientPort.save(client);
    }

    public Client findById(int id, String authorizationHeader) {
        authenticationPort.validateAuthorizationHeader(authorizationHeader);
        Client client = clientPort.findById(id);
        if(id < 1 ) throw new RequestException("Id do cliente inválido!");
        if(client == null) throw new ResourceNotFoundException("Cliente não encontrado");
        return client;
    }

    public Client findByIdWithOutAuth(int id) {
        Client client = clientPort.findById(id);
        if(id < 1 ) throw new RequestException("Id do cliente inválido!");
        if(client == null) throw new ResourceNotFoundException("Cliente não encontrado");
        return client;
    }

    public Client findByCpf(String cpf) {
       cpf = cpf.replaceAll("\\D", "");
        if (!CPFUtils.isValidCPF(cpf)) throw new InvalidCPFException("CPF '" + cpf + "' inválido!");
        Client client = clientPort.findByCpf(cpf);
        if(client == null) throw new ResourceNotFoundException("Cliente não encontrado a partir de CPF");
        return client;
    }

    public void deleteById(int id) {
        this.verifyClientExistance(id);
        clientPort.deleteById(id);
    }

    public boolean verifyClientExistance(int id){
        this.findByIdWithOutAuth(id);
        return true;
    }
}
