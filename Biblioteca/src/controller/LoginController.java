package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import application.Session;
import application.Util;
import factory.JPAFactory;
import model.Usuario;

@Named
@ViewScoped
public class LoginController extends Controller<Usuario> implements Serializable {

    private static final long serialVersionUID = 5133323995601528105L;

    private String filtro;


    public void entrar() {
        String senha = Util.hashSHA256(getEntity().getSenha());

//        System.out.println(senha);

        EntityManager em = JPAFactory.getEntityManager();
        Query query = em.createQuery("Select a " + "From Usuario a " + "Where a.cpf = :cpf AND a.senha = :senha");
        query.setParameter("senha", senha);
        query.setParameter("cpf", getEntity().getCpf());
        try {
            entity = (Usuario) query.getSingleResult();

        } catch (NoResultException e) {
            entity = null;
        }

        if (entity != null) {
//        	Session.getInstance().setAttribute("usuarioLogado", entity);
            Util.redirect("usuario.xhtml");}
        else
            Util.addMessageError("Erro");
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    @Override
    public Usuario getEntity() {
        if (entity == null)
            entity = new Usuario();
        return entity;
    }

}