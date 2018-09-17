package br.com.caelum.livraria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Autor patrick = geraAutor("Patrick Cardoso");
		em.persist(patrick);

		Autor herval = geraAutor("Herval Lemos");
		em.persist(herval);

		Livro rafaela = geraLivro("17020568726", "Rafaela Andrews",
					"19/03/1996", "975078554", "rafaela@gmail.com", patrick);
		Livro vitor = geraLivro("01345872569",
					"Vitor Andrews", "26/05/1999", "998855131", "vitor@gmail.com", patrick);
		Livro paulo = geraLivro("14785269874", "Paulo José",
					"01/01/1999", "998825511", "paulo@gmail.com", patrick);

		em.persist(rafaela);
		em.persist(vitor);
		em.persist(paulo);

		Livro daivisson = geraLivro("14789754788", "Daivisson",
					"01/01/1937", "919775522", "daivisson@hotmail.com", herval);
		Livro pat = geraLivro("11144785236",
					"Patrick", "01/01/1995", "978844123", "pat@gmail.com", herval);

		em.persist(daivisson);
		em.persist(pat);

		em.getTransaction().commit();
		em.close();

	}

	private static Autor geraAutor(String nome) {
		Autor autor = new Autor();
		autor.setNome(nome);
		return autor;
	}

	private static Livro geraLivro(String isbn, String titulo, String data, String telefone,
				String email, Autor autor) {
		Livro livro = new Livro();
		livro.setIsbn(isbn);
		livro.setTitulo(titulo);
		livro.setDataLancamento(parseData(data));
		livro.setEmail(email);
		livro.setTelefone(telefone);
		livro.adicionaAutor(autor);
		return livro; 
	}

	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
