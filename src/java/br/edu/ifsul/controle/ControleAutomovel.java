/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutomovelDAO;
import br.edu.ifsul.dao.MarcaDAO;
import br.edu.ifsul.dao.OpcionalDAO;
import br.edu.ifsul.dao.ReparosDAO;
import br.edu.ifsul.modelo.Automovel;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Opcionais;
import br.edu.ifsul.modelo.Reparos;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private OpcionalDAO<Opcionais> daoOp;
    private Reparos reparoItem;
    private Boolean novoReparo;

    private Opcionais opcionalItem;
    private Boolean novoOpcional;

    public ControleAutomovel() {
        dao = new AutomovelDAO<>();
        daoMarca = new MarcaDAO<>();
        daoOp = new OpcionalDAO<>();
    }
     
    public void imprimeAuto(Integer id) {
        obj = dao.localizar(id);
        // Deve-se criar uma lista com um único objeto entrada
        List<Automovel> listAuto = new ArrayList<>();
        listAuto.add(obj);
        HashMap parametros = new HashMap();
        // deve ser informada a lista para o subrelatório
        parametros.put("listaItens", obj.getReparos());
        UtilRelatorios.imprimeRelatorio("relatorioAutomovelReparoBean", parametros,
                listAuto);
    }
    
    public void imprimeAutomovel() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioAutomoveljavaBeans", parametros, dao.getListaTodos());

    }  
    public String listar() {

        return "/privado/automovel/listar?faces-redirect=true";
    }

    public void novoItem() {
        reparoItem = new Reparos();
        novoReparo = true;
    }

    public void alterarItem(int index) {
        reparoItem = obj.getReparos().get(index);
        novoReparo = false;
    }

    public void salvarItem() {
        if (novoReparo) {
            obj.addReparos(reparoItem);
        } else {
            //atualizaValorTotalVenda();
        }
        Util.mensagemInformacao("Operação realizada com sucesso!");
    }

//    public void calculaValorTotalItem(){
//        if(reparoItem.getQuantidade() != null && item.getValorUnitario()!= null){
//            item.setValorTotal(item.getValorUnitario()*item.getQuantidade());
//        }
//    }
    public void removerItem(int index) {
        obj.removeReparos(index);
        Util.mensagemInformacao("Objeto removido com sucesso!");
    }

    public void novoOp() {
        opcionalItem = new Opcionais();
        novoOpcional = true;
    }

    public void alterarOp(int index) {
        opcionalItem = obj.getOpcionais().get(index);
        novoOpcional = false;
    }

    public void salvarOp() {
        
        obj.addOp(opcionalItem);
        
        Util.mensagemInformacao("Operação realizada com sucesso!");
    }

//    public void calculaValorTotalItem(){
//        if(reparoItem.getQuantidade() != null && item.getValorUnitario()!= null){
//            item.setValorTotal(item.getValorUnitario()*item.getQuantidade());
//        }
//    }
    public void removerOp(int index) {
        obj.removeOp(index);
        Util.mensagemInformacao("Objeto removido com sucesso!");
    }

    public void novo() {
        obj = new Automovel();

    }

    public void salvar() {
        boolean persistiu;
        if (obj.getId() == null) {
            persistiu = dao.persist(obj);
        } else {
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
            if (dao.remover(obj)) {
                Util.mensagemInformacao(dao.getMensagem());
            } else {
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

    public Reparos getReparoItem() {
        return reparoItem;
    }

    public void setReparoItem(Reparos reparoItem) {
        this.reparoItem = reparoItem;
    }

    public Boolean isNovoReparo() {
        return novoReparo;
    }

    public void setNovoReparo(Boolean novoReparo) {
        this.novoReparo = novoReparo;
    }

    public Opcionais getOpcionalItem() {
        return opcionalItem;
    }

    public void setOpcionalItem(Opcionais opcionalItem) {
        this.opcionalItem = opcionalItem;
    }

    public Boolean isNovoOpcional() {
        return novoOpcional;
    }

    public void setNovoOpcional(Boolean novoOpcional) {
        this.novoOpcional = novoOpcional;
    }

    public OpcionalDAO<Opcionais> getDaoOp() {
        return daoOp;
    }

    public void setDaoOp(OpcionalDAO<Opcionais> daoOp) {
        this.daoOp = daoOp;
    }

}
