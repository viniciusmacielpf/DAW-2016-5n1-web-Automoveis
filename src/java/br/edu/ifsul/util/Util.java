/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Vini
 */
public class Util {

    public static String getMensagemErro(Exception e) {

        while (e.getCause() != null) {
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        if (retorno.contains("foreign key") || retorno.contains(" chave estrangeira")) {
            retorno = "Registro contem ligação com outra tabela no banco de dados ";

        }
        return retorno;
    }
    
    
    public static void mensagemInformacao( String msg){
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,"");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
    public static void mensagemErro( String msg){
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,"");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
}
