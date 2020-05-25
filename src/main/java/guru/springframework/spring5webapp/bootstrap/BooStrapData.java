package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BooStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BooStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher pub1 = new Publisher("Publisher #1", "awesome address #1");

        publisherRepository.save(pub1);

        Author dan = new Author("Dan", "Brown");
        Book b1 = new Book("Illuminati", "123123");
        dan.getBooks().add(b1);
        b1.getAuthors().add(dan);
        b1.setPublisher(pub1);
        pub1.getBooks().add(b1);

        authorRepository.save(dan);
        bookRepository.save(b1); // save to H2 database
        publisherRepository.save(pub1);

        Author andy = new Author("Andy", "Weir");
        Book b2 = new Book("The Marsian", "456456");
        andy.getBooks().add(b2);
        b2.getAuthors().add(andy);
        b2.setPublisher(pub1);
        pub1.getBooks().add(b2);

        authorRepository.save(andy);
        bookRepository.save(b2);
        publisherRepository.save(pub1);

        System.out.println("Started in bootstrap\nNumber of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count() + " number of books: " + pub1.getBooks().size());
    }
}
