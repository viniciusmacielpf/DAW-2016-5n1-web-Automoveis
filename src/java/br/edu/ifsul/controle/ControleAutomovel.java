/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;


import br.edu.ifsul.dao.MarcaDAO;
import br.edu.ifsul.dao.AutomovelDAO;

import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Automovel;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Vini
 */
@ManagedBean(name = "controleAutomovel")
@ViewScoped
public class ControleAutomovel implements Serializable {

    private AutomovelDAO<Automovel> dao;
    private Automovel obj;
    private MarcaDAO<Marca> daoMarca;
    

    public ControleAutomovel() {
        dao = new AutomovelDAO<>();
        daoMarca = new MarcaDAO<>();
    
    }

    public String listar() {

        return "/privado/automovel/listar?faces-redirect=true";
    }

    public void novo() {
        obj = new Automovel();
        
    }

    public void salvar() {
        boolean persistiu;
        if(obj.getId() == null){
            persistiu = dao.persist(obj);
        }else { 
            persistiu = dao.merge(obj);
        }
        
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    

    public void editar(Integer id) {
        try {
            obj = dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto :" + Util.getMensagemErro(e));

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

    public AutomovelDAO getDao() {
        return dao;
    }

    public void setDao(AutomovelDAO dao) {
        this.dao = dao;
    }

    public Automovel getObj() {
        return obj;
    }

    public void setObj(Automovel obj) {
        this.obj = obj;
    }

    public MarcaDAO<Marca> getDaoMarca() {
        return daoMarca;
    }

    public void setDaoMarca(MarcaDAO<Marca> daoMarca) {
        this.daoMarca = daoMarca;
    }

 

    

}
