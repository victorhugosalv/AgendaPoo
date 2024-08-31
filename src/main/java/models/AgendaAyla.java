package models;

import entities.Contato;
import exceptions.ContatosInexistenteException;
import recorder_reader.GravadorDeDados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AgendaAyla implements Agenda{
    private Map<String, Contato> contatos;
    GravadorDeDados gravador = new GravadorDeDados();

    public AgendaAyla(){
        this.contatos = new HashMap<>();
    }


    @Override
    public void salvarDados() throws IOException {
        gravador.salvarContatos(contatos);
    }

    @Override
    public void recuperarDados() throws IOException {
        contatos = gravador.recuperaContatos();
    }

    @Override
    public boolean cadastraContato(String nome, int dia, int mes) {
        contatos.put(nome, new Contato(nome,dia,mes));
        return true;
    }

    @Override
    public Collection<Contato> pesquisaAniversariantes(int dia, int mes) throws ContatosInexistenteException {
        Collection<Contato> contatosPesquisados = new ArrayList<>();
        for (Contato c: contatos.values()){
            if (c.getDiaAniversario() == dia && c.getMesAniversario() == mes)
                contatosPesquisados.add(c);
        }
        if (contatosPesquisados.isEmpty())
            throw new ContatosInexistenteException();
        return contatosPesquisados;
    }

    @Override
    public boolean removeContato(String nome) throws ContatosInexistenteException{
        if(contatos.containsKey(nome)){
            contatos.remove(nome);
            return true;
        }
        throw new ContatosInexistenteException();
    }
}
