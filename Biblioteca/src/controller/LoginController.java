package controller;

import factory.JPAFactory;
import model.Usuario;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class LoginController extends Controller<Usuario> implements Serializable {

	private static final long serialVersionUID = 5133323995601528105L;

	private String filtro;

	private Usuario usuario;

	public void entrar() {
		String senha = Util.hashSHA256(usuario.getSenha());

		System.out.println(senha);

		EntityManager em = JPAFactory.getEntityManager();
		Query query = em.createQuery("Select a " + "From Usuario a " + "Where a.cpf AND a.senha like upper(:filtro)");
		query.setParameter("filtro", "%" + getFiltro() + "%");
		usuario = (Usuario) query.getSingleResult();

		if (usuario != null)
			Util.redirect("Biblioteca/faces/usuario.xhtml");
		else
			Util.addMessageError("Erro");
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	/*
	 * public void entrar() {
	 * 
	 * }
	 */

	@Override
	public Usuario getEntity() {
		if (entity == null)
			entity = new Usuario();
		return entity;
	}

}