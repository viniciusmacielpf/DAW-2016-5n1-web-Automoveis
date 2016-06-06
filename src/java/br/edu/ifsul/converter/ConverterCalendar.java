/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.converter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Vini
 */
@FacesConverter(value = "converterCalendar")
public class ConverterCalendar implements Converter {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
    
    //Converte da tela para o objeto.
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Calendar retorno = Calendar.getInstance();
        
        if(string == null){
            return null;
        }
        try {
            retorno.setTime(sdf.parse(string));
            return retorno;
        } catch (Exception e) {
            return null;
        }
            
    }

    @Override //Converte do objeto para a tela
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Calendar obj = (Calendar) o;
        return sdf.format(obj.getTime());
    }
    
    
}
