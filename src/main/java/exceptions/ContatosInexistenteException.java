package exceptions;

public class ContatosInexistenteException extends Exception{

    public ContatosInexistenteException(String msg){
        super(msg);
    }

    public ContatosInexistenteException(){
        super("Não existem contatos");
    }
}
