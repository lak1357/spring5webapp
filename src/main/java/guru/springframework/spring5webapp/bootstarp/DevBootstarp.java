package guru.springframework.spring5webapp.bootstarp;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstarp implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;

	public DevBootstarp(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
	}

	private void initData() {

		Publisher publisher1 = new Publisher("Robert Coperation", "Galle Road, Colombo 06");
		Publisher publisher2 = new Publisher("Sarasavi Ltd", "Maradana, Colombo 13");

		publisherRepository.save(publisher1);
		publisherRepository.save(publisher2);

		Author author1 = new Author("Lakshitha", "Herath");
		Book book1 = new Book("How to learn Java", "1254-BR21-9987", publisher1);
		author1.getBooks().add(book1);
		book1.getAuthors().add(author1);

		Author author2 = new Author("Rob", "Jhonson");
		Book book2 = new Book("Adventure of Rob", "9999-1111-9987", publisher2);
		author2.getBooks().add(book2);
		book2.getAuthors().add(author2);

		authorRepository.save(author1);
		authorRepository.save(author2);

		bookRepository.save(book1);
		bookRepository.save(book2);

	}

}
