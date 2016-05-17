/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Vini
 */
public class DAOGenerico<T> implements Serializable {

    private List<T> listaObjetos;
    private List<T> listaTodos;
    private EntityManager em;
    private Class classePersistente;
    private String mensagem = "";
    private String ordem = "id";
    private String filtro = "";
    private Integer maximoObjeto = 3;
    private Integer posicaoAtual = 0;
    private Integer totalObjetos = 0;

    public DAOGenerico() {
        em = EntityManagerUtil.getEntityManager();
    }

    public boolean remover(T obj) {
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Removido com sucesso";
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "erro ao excluir :" + Util.getMensagemErro(e);
            return false;
        }
    }

    public boolean persist(T obj) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            mensagem = "Persistido com sucesso";
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "erro ao persistir objeto :" + Util.getMensagemErro(e);
            return false;
        }
    }

    public T localizar(Integer id) {
        return (T) em.find(classePersistente, id);
    }

    public boolean merge(T obj) {
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            mensagem = "Persistido com sucesso";
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "erro ao persistir objeto :" + Util.getMensagemErro(e);
            return false;
        }
    }
    public String protegeFiltro(String filtro){
        filtro = filtro.replaceAll("['!;-/=]","");
        return filtro;        
    }
    public List<T> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
//        filtro = protegeFiltro(filtro);
        if (filtro.length() > 0) {
            
            String osmardito[] = {"=", "+", "'", "OR", "AND", ",", ";", "#", "@", "!", "\'", "\'"};
            for (int i = 0; i < filtro.length(); i++) {
                String t = String.valueOf(filtro.charAt(i));
                for (int j = 0; j < osmardito.length; j++) {
                    if (t.equals(osmardito[j])) {
                        //System.out.println("Entrou");
                        filtro = "";
                    }
                }

            }
            if (ordem.equals("id")) {

                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "' ";
                } catch (Exception e) {
                    Util.mensagemErro("Filtro invalido");
                }
            } else {
                where += " where upper(" + ordem + ")like  '" + filtro.toUpperCase() + "%'";
            }
        }
        jpql += where;
        jpql += " order by " + ordem;
        totalObjetos = em.createQuery("select id from "
                + classePersistente.getSimpleName() + where + " order by " + ordem).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).
                setMaxResults(maximoObjeto).getResultList();
    }

    public void primeiro() {
        posicaoAtual = 0;
    }

    public void anterior() {
        posicaoAtual -= maximoObjeto;
        if (posicaoAtual < 0) {
            posicaoAtual = 0;
        }
    }

    public void proxima() {

        if (posicaoAtual + maximoObjeto < totalObjetos) {
            posicaoAtual += maximoObjeto;
        }
    }

    public void ultimo() {
        int resto = totalObjetos % maximoObjeto;
        if (resto > 0) {
            posicaoAtual += maximoObjeto;

        } else {
            posicaoAtual = totalObjetos - maximoObjeto;
        }
    }

    public String getMensagemNavegacao() {
        int ate = posicaoAtual + maximoObjeto;
        if (ate > totalObjetos) {
            ate = totalObjetos;
        }

        return "Listagem de " + (posicaoAtual + 1) + " até " + ate + " de " + totalObjetos + " registros!";

    }

    public void setListaObjetos(List<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Integer getMaximoObjeto() {
        return maximoObjeto;
    }

    public void setMaximoObjeto(Integer maximoObjeto) {
        this.maximoObjeto = maximoObjeto;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }

    public List<T> getListaTodos() {
        String jqpl = "from "+ classePersistente.getSimpleName()+" order by "+ ordem;       
        return em.createQuery(jqpl).getResultList();
    }

    public void setListaTodos(List<T> listaTodos) {
        this.listaTodos = listaTodos;
    }
}
