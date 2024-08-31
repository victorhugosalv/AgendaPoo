import entities.Contato;
import exceptions.ContatosInexistenteException;
import models.AgendaAyla;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaAylaTest {
    @Test
    public void testaCadastroRemoveEPesquisa(){
        AgendaAyla  agenda = new AgendaAyla();
        assertTrue(agenda.cadastraContato("Silvo", 12,11));
        try {
            assertEquals(1, agenda.pesquisaAniversariantes(12, 11).size());
        } catch (ContatosInexistenteException e){
            fail("Não é para falhar");
        }

        try{
            assertTrue(agenda.removeContato("Silvo"));
        } catch (ContatosInexistenteException e) {
            fail("Não é para lançar exceção");
        }

        assertThrows(ContatosInexistenteException.class, () -> agenda.pesquisaAniversariantes(12,11));

    }

    @Test
    public void testaGravador(){
        AgendaAyla  agenda = new AgendaAyla();
        String caminho =  "contatos.dat";

        new File(caminho).delete();

        assertThrows(IOException.class, agenda::recuperarDados);

        try{
            agenda.cadastraContato("Silvios",12,11);
            agenda.salvarDados();
        } catch (IOException e) {
            fail("Deve estar lancando excessao");
        }

    }

    @Test
    public void testaRecuperacao(){

        AgendaAyla agenda = new AgendaAyla();

        try{

            agenda.recuperarDados();
            assertEquals(1,agenda.pesquisaAniversariantes(12,11).size());
            assertTrue(agenda.removeContato("Silvios"));
            agenda.salvarDados();
            agenda.recuperarDados();
            assertEquals(0,agenda.pesquisaAniversariantes(12,11).size());
            fail("Era para lançar exceção");
        } catch (IOException e) {
            fail("Deve estar lancando excessao");
        } catch (ContatosInexistenteException e) {
            //ok
        }
    }
}
