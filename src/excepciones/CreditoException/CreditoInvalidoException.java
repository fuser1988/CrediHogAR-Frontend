/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones.CreditoException;

/**
 *
 * @author OVERCROSS
 */
public class CreditoInvalidoException extends RuntimeException{
    
    public CreditoInvalidoException (String msg){
	super(msg);
    }
    
}