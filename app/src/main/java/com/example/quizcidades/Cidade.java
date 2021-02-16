package com.example.quizcidades;

public class Cidade {
    private String nome;
    private int path;

    public Cidade(String nome, int path) {
        this.nome = nome;
        this.path = path;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
