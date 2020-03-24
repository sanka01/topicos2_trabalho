package controller;

import factory.JPAFactory;
import model.Usuario;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class LoginController extends Controller<Usuario> implements Serializable {

	private static final long serialVersionUID = 5133323995601528105L;


	private String filtro;
	
	private List<Usuario> listaUsuario;
//
//	public void pesquisar() {
//		EntityManager em = JPAFactory.getEntityManager();
//		Query query = em.createQuery("Select a " + "From Usuario a " + "Where upper(a.nome) like upper(:filtro)");
//		query.setParameter("filtro", "%" + getFiltro() + "%");
//		listaUsuario = query.getResultList();
//	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null)
			listaUsuario = new ArrayList<Usuario>();
		return listaUsuario;
	}
	public void entrar(){

	}

	@Override
	public Usuario getEntity() {
		if (entity == null)
			entity = new Usuario();
		return entity;
	}

}