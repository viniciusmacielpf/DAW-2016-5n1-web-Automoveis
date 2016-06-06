///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package br.edu.ifsul.controle;
//
//import br.edu.ifsul.dao.ReparosDAO;
//
//import br.edu.ifsul.dao.AutomovelDAO;
//import br.edu.ifsul.modelo.Reparos;
////import br.edu.ifsul.modelo.ReparosItem;
//import br.edu.ifsul.modelo.PessoaJuridica;
//import br.edu.ifsul.modelo.Automovel;
//import br.edu.ifsul.util.Util;
//import java.io.Serializable;
//import java.util.Calendar;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//
///**
// *
// * @author Vini
// */
//@ManagedBean(name = "controleReparos")
//@ViewScoped
//public class ControleReparos implements Serializable {
//
//    private ReparosDAO<Reparos> dao;
//    private Reparos obj;
//    private AutomovelDAO<Automovel> daoAutomovel;
//    private ReparosItem item;
//    private Boolean novoItem;
//    
//    public ControleReparos() {
//        dao = new ReparosDAO<>();
////        daoPessoaJuridica = new PessoaJuridicaDAO<>();
//        daoAutomovel = new AutomovelDAO<>();
//    
//    }
//    
////    public void atualizaValorTotalVenda(){
////        Double valorTotal = 0.0;
////        for(ReparosItem ei : obj.getItens()){
////            valorTotal += ei.getValorTotal();
////        }
////        obj.setValorTotal(valorTotal);
////    }
//    
//    public void novoItem(){
//        item = new ReparosItem();
//        novoItem = true;
//    }
//    
//    public void alterarItem(int index){
//        item = obj.getItens().get(index);
//        novoItem = false;
//    }
//    
//    public void salvarItem(){
//        if(novoItem){
//            obj.addItem(item);
//        }else{
//            atualizaValorTotalVenda();
//        }
//        Util.mensagemInformacao("Operação realizada com sucesso!");
//    }
//    
//    public void calculaValorTotalItem(){
//        if(item.getQuantidade() != null && item.getValorUnitario()!= null){
//            item.setValorTotal(item.getValorUnitario()*item.getQuantidade());
//        }
//    }
//    
//    public void removerItem(int index){
//        obj.removeItens(index);
//        Util.mensagemInformacao("Objeto removido com sucesso!");
//    }
//    
//    public String listar() {
//
//        return "/privado/entrada/listar?faces-redirect=true";
//    }
//
//    public void novo() {
//        obj = new Reparos();
//        obj.setData(Calendar.getInstance());
//        
//    }
//
//    public void salvar() {
//        boolean persistiu;
//        if(obj.getId() == null){
//            persistiu = dao.persist(obj);
//        }else {
//            persistiu = dao.merge(obj);
//        }
//        
//        if (persistiu) {
//            Util.mensagemInformacao(dao.getMensagem());
//        } else {
//            Util.mensagemErro(dao.getMensagem());
//        }
//    }
//
//    
//
//    public void editar(Integer id) {
//        try {
//            obj = dao.localizar(id);
//        } catch (Exception e) {
//            Util.mensagemErro("Erro ao recuperar objeto :" + Util.getMensagemErro(e));
//
//        }
//    }
//
//    public void remover(Integer id) {
//        try {
//            obj = dao.localizar(id);
//            if(dao.remover(obj)){
//                Util.mensagemInformacao(dao.getMensagem());
//            }else {
//                Util.mensagemErro(dao.getMensagem());
//            }
//            
//        } catch (Exception e) {
//            Util.mensagemErro("Erro ao recuperar objeto :" + Util.getMensagemErro(e));
//            
//        }
//    }
//
//    public ReparosDAO getDao() {
//        return dao;
//    }
//
//    public void setDao(ReparosDAO dao) {
//        this.dao = dao;
//    }
//
//    public Reparos getObj() {
//        return obj;
//    }
//
//    public void setObj(Reparos obj) {
//        this.obj = obj;
//    }
//
//    public AutomovelDAO<Automovel> getDaoAutomovel() {
//        return daoAutomovel;
//    }
//
//    public void setDaoAutomovel(AutomovelDAO<Automovel> daoAutomovel) {
//        this.daoAutomovel = daoAutomovel;
//    }
//
//    public PessoaJuridicaDAO<PessoaJuridica> getDaoPessoaJuridica() {
//        return daoPessoaJuridica;
//    }
//
//    public void setDaoPessoaJuridica(PessoaJuridicaDAO<PessoaJuridica> daoPessoaJuridica) {
//        this.daoPessoaJuridica = daoPessoaJuridica;
//    }
//
//    public ReparosItem getItem() {
//        return item;
//    }
//
//    public void setItem(ReparosItem item) {
//        this.item = item;
//    }
//
//    public Boolean isNovoItem() {
//        return novoItem;
//    }
//
//    public void setNovoItem(Boolean novoItem) {
//        this.novoItem = novoItem;
//    }
//
//    
//
//}
