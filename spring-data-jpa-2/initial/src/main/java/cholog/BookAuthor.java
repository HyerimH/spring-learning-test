package cholog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Author author;

    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public BookAuthor() {
    }

}
