package br.com.cadastro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.cadastro.dao.DAO;
import br.com.cadastro.modelo.Aluno;
import br.com.cadastro.modelo.Professor;

@ViewScoped
@ManagedBean
public class AlunoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Aluno aluno = new Aluno();
	private Integer alunoId;
	private List<Aluno> alunos;
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Integer getAlunoId() {
		return alunoId;
	}
	
	public void setAlunoId(Integer alunoId) {
		this.alunoId = alunoId;
	}
	
	public List<Aluno> getLivros() {
		DAO<Aluno> dao = new DAO<Aluno>(Aluno.class);
		if(this.alunos == null) {
			this.alunos = dao.listaTodos();
		}
		return alunos;
	}

	public List<Professor> getProfessores() {
		return new DAO<Professor>(Professor.class).listaTodos();
	}

	public List<Professor> getProfessoresDoAluno() {
		return this.aluno.getProfessores();
	}

	public void carregarLivroPelaId() {
		this.aluno = new DAO<Aluno>(Aluno.class).buscaPorId(this.aluno.getId()); 
	}
	
	public void gravarProfessor() {
		Professor professor = new DAO<Professor>(Professor.class).buscaPorId(this.professorId);
		this.aluno.adicionaProfessor(professor);
		System.out.println("Tem aula com: " + professor.getNome());
	}

	public void gravar() {
		System.out.println("Gravando aluno " + this.aluno.getTitulo());

		if (aluno.getProfessores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("professor",
					new FacesMessage("Aluno deve ter pelo menos um Professor."));
			return;
		}

		DAO<Aluno> dao = new DAO<Aluno>(Aluno.class);
		if(this.aluno.getId() == null) {
			dao.adiciona(this.aluno);
			this.alunos = dao.listaTodos();
		} else {
			dao.atualiza(this.aluno);
		}

		this.aluno = new Aluno();
	}

	public void remover(Aluno aluno) {
		System.out.println("Removendo aluno");
		DAO<Aluno> dao = new DAO<Aluno>(Aluno.class);
		dao.remove(alunos);
		this.alunos = dao.listaTodos();
	}
	
	public void removerAutorDoLivro(Professor professor) {
		this.aluno.removeProfessor(professor);
	}
	
	public void carregar(Aluno aluno) {
		System.out.println("Carregando aluno");
		this.aluno = aluno;
	}
	
	public String formProfessor() {
		System.out.println("Chamanda do formulario do Professor.");
		return "autor?faces-redirect=true";
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"CPF deveria começar com 1"));
		}

	}
	
	public void comecaComDigitoNove(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("9")) {
			throw new ValidatorException(new FacesMessage(
					"Telefone deveria começar com 9"));
		}
	}
	
	
}
