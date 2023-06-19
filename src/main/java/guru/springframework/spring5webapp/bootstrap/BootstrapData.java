package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository= publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author Tom = new Author();
        Tom.setFirstName("Tom");
        Tom.setLastName("Boomer");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author mahesh = new Author();
        mahesh.setFirstName("Mahesh");
        mahesh.setLastName("Gawade");

        Book twn = new Book();
        twn.setTitle("Travel with Nature");
        twn.setIsbn("070707");

        Author tomSaved = authorRepository.save(Tom);
        Book bookSaved = bookRepository.save(ddd);

        Author mahesh_temp = authorRepository.save(mahesh);
        Book twn_temp = bookRepository.save(twn);

        tomSaved.getBooks().add(bookSaved);
        mahesh_temp.getBooks().add(twn);
        ddd.getAuthors().add(Tom);
        twn.getAuthors().add(mahesh);
        authorRepository.save(tomSaved);
        authorRepository.save(mahesh_temp);

        Publisher pub1 = new Publisher();
        pub1.setPublisherName("Times");
        pub1.setAddress("kothrud");
        pub1.setCity("Pune");
        pub1.setState("Maharashtra");
        pub1.setZip(411038);
        Publisher pub2 = publisherRepository.save(pub1);

        bookSaved.setPublisher(pub2);
        twn_temp.setPublisher(pub2);

        bookRepository.save(bookSaved);
        bookRepository.save(twn_temp);

        System.out.println("In BootstrapData");
        System.out.println( "Author Count : " + authorRepository.count());
        System.out.println("book Count :  " + bookRepository.count());
        System.out.println("Publisher Count : " + publisherRepository.count());
    }
}
