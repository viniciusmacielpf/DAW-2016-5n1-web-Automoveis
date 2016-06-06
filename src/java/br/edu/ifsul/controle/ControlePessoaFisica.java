/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;


import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Reparos;
import br.edu.ifsul.modelo.Telefone;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Vini
 */
@ManagedBean(name = "controlePessoaFisica")
@ViewScoped
public class ControlePessoaFisica implements Serializable {

    private PessoaFisicaDAO<PessoaFisica> dao;
    private PessoaFisica obj;
    private CidadeDAO<Cidade> daoCidade;
    private Telefone telefones;
    private Boolean novoTelefone;
    
    
    public ControlePessoaFisica() {
        dao = new PessoaFisicaDAO<>();
        daoCidade = new CidadeDAO<>();
        
    }
    
    
    public void novoItem(){
        telefones = new Telefone();
        novoTelefone = true;
    }
    
    public void alterarItem(int index){
        telefones = obj.getTelefones().get(index);
        novoTelefone = false;
    }
    
    public void salvarItem(){
        if(novoTelefone){
            obj.addTelefone(telefones);
        }else{
            //atualizaValorTotalVenda();
        }
        Util.mensagemInformacao("Operação realizada com sucesso!");
    }
    
//    public void calculaValorTotalItem(){
//        if(telefones.getQuantidade() != null && item.getValorUnitario()!= null){
//            item.setValorTotal(item.getValorUnitario()*item.getQuantidade());
//        }
//    }
    
    public void removerItem(int index){
        obj.removeTelefone(index);
        Util.mensagemInformacao("Objeto removido com sucesso!");
    }

    public String listar() {

        return "/privado/pessoa_fisica/listar?faces-redirect=true";
    }

    public void novo() {
        obj = new PessoaFisica();
        
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

    public PessoaFisicaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDAO dao) {
        this.dao = dao;
    }

    public PessoaFisica getObj() {
        return obj;
    }

    public void setObj(PessoaFisica obj) {
        this.obj = obj;
    }

    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }

    public Telefone getTelefones() {
        return telefones;
    }

    public void setTelefones(Telefone telefones) {
        this.telefones = telefones;
    }

    public Boolean isNovoTelefone() {
        return novoTelefone;
    }

    public void setNovoTelefone(Boolean novoTelefone) {
        this.novoTelefone = novoTelefone;
    }

 

    

}
