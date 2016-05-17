package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Estado;
import java.io.Serializable;

/**
 *
 * @author Vini
 *
 */
public class EstadoDAO<T> extends DAOGenerico<Estado> implements Serializable {

    public EstadoDAO() {
        super();
        super.setClassePersistente(Estado.class);
        super.setOrdem("nome");//ordem padrao
        
     }
//    private Estado objetoSelecionado;
//    private String mensagem = "";
//    private EntityManager em;
//
//    public EstadoDAO() {
//        em = EntityManagerUtil.getEntityManager();
//    }
//
//    public boolean validaObjeto(Estado obj) {
//        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
//
//        Set<ConstraintViolation<Estado>> erros = validador.validate(obj);
//        if (erros.size() > 0) {
//            mensagem = "";
//            mensagem += "Objeto com erros \n ";
//            for (ConstraintViolation<Estado> erro : erros) {
//                mensagem += "erro>" + erro.getMessage() + "<br>";
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    public List<Estado> getLista() {
//        return em.createQuery(" from Estado order by nome").getResultList();
//    }
//
//    public boolean salvar(Estado obj) {
//        try {
//            em.getTransaction().begin();
//            if (obj.getId() == null) {
//                em.persist(obj);
//            } else {
//                em.merge(obj);
//            }
//             mensagem = "Salvo com sucesso";
//            em.getTransaction().commit();
//            return true;
//
//        } catch (Exception e) {
//            if (em.getTransaction().isActive() == false) {
//                em.getTransaction().begin();
//            }
//            em.getTransaction().rollback();
//            mensagem = "erro ao persistir :" + Util.getMensagemErro(e);
//            return false;
//        }
//    }
//    public Estado localizar(Integer id){
//        return em.find(Estado.class,id);
//    }
//    public boolean remover(Estado obj) {
//        try {
//            em.getTransaction().begin();
//            em.remove(obj);
//            em.getTransaction().commit();
//            mensagem = "Removido com sucesso";
//            return true;
//
//        } catch (Exception e) {
//            if (em.getTransaction().isActive() == false) {
//                em.getTransaction().begin();
//            }
//            em.getTransaction().rollback();
//            mensagem = "erro ao excluir :" + Util.getMensagemErro(e);
//            return false;
//        }
//    }
//
//    public Estado getObjetoSelecionado() {
//        return objetoSelecionado;
//    }
//
//    public void setObjetoSelecionado(Estado objetoSeleciona) {
//        this.objetoSelecionado = objetoSeleciona;
//    }
//
//    public String getMensagem() {
//        return mensagem;
//    }
//
//    public void setMensagem(String mensagem) {
//        this.mensagem = mensagem;
//    }
//
//    public EntityManager getEm() {
//        return em;
//    }
//
//    public void setEm(EntityManager em) {
//        this.em = em;
//    }
}
