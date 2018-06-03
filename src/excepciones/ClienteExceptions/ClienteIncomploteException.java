/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones.ClienteExceptions;

/**
 *
 * @author bangho
 */
public class ClienteIncomploteException extends RuntimeException{
    
    public ClienteIncomploteException (String msg){
	super(msg);
    }
    
}
