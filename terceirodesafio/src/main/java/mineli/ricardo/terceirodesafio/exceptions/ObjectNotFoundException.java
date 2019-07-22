package mineli.ricardo.terceirodesafio.exceptions;


public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(Long id,String tipo){
        super("Object not found! Id: " + id + ", type: " + tipo);
    }

    public ObjectNotFoundException(Long id,String tipo, Throwable cause){
        super("Object not found! Id: " + id + ", type: " + tipo, cause);
    }

}
