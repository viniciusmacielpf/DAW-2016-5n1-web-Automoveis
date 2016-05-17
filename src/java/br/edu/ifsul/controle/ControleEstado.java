/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Vini
 */
@ManagedBean(name = "controleEstado")
@SessionScoped
public class ControleEstado implements Serializable {

    private EstadoDAO<Estado> dao;
    private Estado obj;

    public ControleEstado() {
        dao = new EstadoDAO<>();
    }

    public String listar() {

        return "/privado/estado/listar?faces-redirect=true";
    }

    public String novo() {
        obj = new Estado();
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

    public EstadoDAO getDao() {
        return dao;
    }

    public void setDao(EstadoDAO dao) {
        this.dao = dao;
    }

    public Estado getObj() {
        return obj;
    }

    public void setObj(Estado obj) {
        this.obj = obj;
    }

}
