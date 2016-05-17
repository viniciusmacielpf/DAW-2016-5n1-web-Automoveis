/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MarcaDAO;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Vini
 */
@ManagedBean(name = "controleMarca")
@SessionScoped
public class ControleMarca implements Serializable {

    private MarcaDAO<Marca> dao;
    private Marca obj;

    public ControleMarca() {
        dao = new MarcaDAO<>();
    }

    public String listar() {

        return "/privado/marca/listar?faces-redirect=true";
    }

    public String novo() {
        obj = new Marca();
        return "formulario";
    }

    public String salvar() {
        boolean persistiu;
        if(obj.getId() == null){
            persistiu = dao.persist(obj);
        }else {
            persistiu = dao.merge(obj);
        }
        
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }

    public String cancelar() {

        return "listar";
    }

    public String editar(Integer id) {
        try {
            obj = dao.localizar(id);
            return "formulario";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto :" + Util.getMensagemErro(e));
            return "listar";
        }
    }

    public void remover(Integer id) {
        try {
            obj = dao.localizar(id);
            if(dao.remover(obj)){
                Util.mensagemInformacao(dao.getMensagem());
            }else {
                Util.mensagemErro(dao.getMensagem());
            }
            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto :" + Util.getMensagemErro(e));
            
        }
    }

    public MarcaDAO getDao() {
        return dao;
    }

    public void setDao(MarcaDAO dao) {
        this.dao = dao;
    }

    public Marca getObj() {
        return obj;
    }

    public void setObj(Marca obj) {
        this.obj = obj;
    }

}
