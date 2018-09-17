package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Curso;

public class CursoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Curso curso = new Curso();
	
	private Integer cursoId;
	
	public Integer getCursoId() {
		return cursoId;
	}

	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}

	public void carregarCursoPelaId() {
		this.curso = new DAO<Curso>(Curso.class).buscaPorId(cursoId);
	}

	public String gravar() {
		System.out.println("Gravando curso " + this.curso.getNome());

		if(this.curso.getId() == null) {
			new DAO<Curso>(Curso.class).adiciona(this.curso);
		} else {
			new DAO<Curso>(Curso.class).atualiza(this.curso);
		}

		this.curso = new Curso();

		return "cursos?faces-redirect=true";
	}
	
	public void remover(Curso curso) {
		System.out.println("Removendo curso " + curso.getNome());
		new DAO<Curso>(Curso.class).remove(curso);
	}
	
	public List<Curso> getCursos() {
		return new DAO<Curso>(Curso.class).listaTodos();
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}

