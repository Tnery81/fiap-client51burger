package com.fiap.burguer.core.domain;


import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable {
    private int id;

    private String name;

    private String email;

    private String cpf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(name, client.name) && Objects.equals(email, client.email) && Objects.equals(cpf, client.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, cpf);
    }
}
