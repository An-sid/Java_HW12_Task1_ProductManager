package ru.netology.manager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.netology.domain.Product;
import ru.netology.domain.Book;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

class ProductManagerTest {

    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    // test data
    Product book1 = new Book(1, "book1", 10, "author1");
    Product book2 = new Book(2, "book2", 10, "author2");
    Product book22 = new Book(22, "book22", 10, "author2");
    Product book3 = new Book(33, "book3", 10, "author3");
    Product book34 = new Book(33, "book3", 10, "author4");
    Product smart1 = new Smartphone(91, "smart1", 100, "lg");
    Product[] testData = {book1, book2, book22, book3, book34, smart1,};

    @Test
    void findBookByName() {
        repository.fillRepo(testData);
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy("book1");
        assertArrayEquals(expected, actual);
    }

    @Test
    void findOneBookByOneAuthor() {
        repository.fillRepo(testData);
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy("author1");
        assertArrayEquals(expected, actual);
    }

    @Test
    void findFewBooksByOneAuthor() {
        manager.add(book2);
        manager.add(book22);
        Product[] expected = new Product[]{book2, book22};
        Product[] actual = manager.searchBy("author2");
        assertArrayEquals(expected, actual);
    }

    @Test
    void findOneBookByFewAuthors() {
        manager.add(book3);
        manager.add(book34);
        Product[] expected = manager.searchBy("author4");
        Product[] actual = manager.searchBy("author3");
        assertArrayEquals(expected, actual);
    }

    @Test
    void findDeviceByName() {
        repository.fillRepo(testData);
        Product[] expected = new Product[]{smart1};
        Product[] actual = manager.searchBy("smart1");
        assertArrayEquals(expected, actual);
    }

    @Test
    void findDeviceByMfr() {
        repository.fillRepo(testData);
        Product[] expected = new Product[]{smart1};
        Product[] actual = manager.searchBy("lg");
        assertArrayEquals(expected, actual);
    }

    @Test
    void findNothing() {
        repository.fillRepo(testData);
        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("nothing");
        assertArrayEquals(expected, actual);
    }
}