package models;

import entities.Contato;
import exceptions.ContatosInexistenteException;

import java.io.IOException;
import java.util.Collection;

public interface Agenda {
    void salvarDados() throws IOException;
    void recuperarDados() throws IOException;
    boolean cadastraContato(String nome, int dia, int mes);
    Collection<Contato> pesquisaAniversariantes(int dia, int mes) throws ContatosInexistenteException;
    boolean removeContato(String nome) throws ContatosInexistenteException;
}
