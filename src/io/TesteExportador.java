package io;

import java.io.IOException;
import java.util.Arrays;

import application.Exportador;
import br.com.casadocodigo.livraria.Autor;
import br.com.casadocodigo.livraria.produtos.Livro;
import br.com.casadocodigo.livraria.produtos.LivroFisico;

public class TesteExportador {
	public static void main(String[] args) throws IOException {

		Autor autor = new Autor();
		autor.setNome("Rodrigo Turini");
		autor.setEmail("rodrigo.turini@caelum.com.br");
		autor.setCpf("123.456.789.10");

		Livro livroFisico = new LivroFisico(autor);
		livroFisico.setNome("Desbravando a OO");
		livroFisico.setDescricao("Livro de java e OO");
		livroFisico.setValor(59.90);
		livroFisico.setIsbn("321-54-67890-11-2");

		Livro outroLivroFisico = new LivroFisico(autor);
		outroLivroFisico.setNome("Java 8 Pratico");
		outroLivroFisico.setDescricao("Novos recursos da linguagem");
		outroLivroFisico.setValor(59.90);
		outroLivroFisico.setIsbn("978-85-66250-46-6");

		new Exportador().paraCSV(Arrays.asList(livroFisico, outroLivroFisico));

	}
}
