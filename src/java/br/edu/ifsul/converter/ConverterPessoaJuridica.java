/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.converter;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.PessoaJuridica;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



/**
 *
 * @author Vini
 */
@FacesConverter(value = "converterPessoaJuridica")
public class ConverterPessoaJuridica implements Converter, Serializable{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equals("Selecione um registro")){
            return null;
        }else {
            return EntityManagerUtil.getEntityManager().find(PessoaJuridica.class, Integer.parseInt(string));
                  
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o == null){
            return null;
        }
        PessoaJuridica obj = (PessoaJuridica) o;
        return obj.getId().toString();
    }
    
}
