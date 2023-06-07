package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author Tom = new Author();
        Tom.setFirstName("Tom");
        Tom.setLastName("Boomer");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author tomSaved = authorRepository.save(Tom);
        Book bookSaved = bookRepository.save(ddd);

        tomSaved.getBooks().add(bookSaved);

        authorRepository.save(tomSaved);
        System.out.println("In BootstrapData");
        System.out.println( "Author Count " + authorRepository.count());
        System.out.println("book Count " + bookRepository.count());
    }
}
