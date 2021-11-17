package ru.netology.manager;

import ru.netology.domain.Product;
import ru.netology.domain.Book;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

public class ProductManager {

    private ProductRepository repository;

    public ProductManager(ProductRepository repository) { this.repository = repository; }

    public void add(Product product) { repository.save(product); }

    public boolean matchesBook(Product product, String search) {
        if (product instanceof Book) {
            Book book = (Book) product;
            if (book.getName().contains(search)) {
                return true;
            }
            if (book.getAuthor().contains(search)) {
                return true;
            }
        }
        return false;
    }

    public boolean matchesSmart(Product product, String search) {
        if (product instanceof Smartphone) {
            Smartphone smartphone = (Smartphone) product;
            if (smartphone.getName().contains(search)) {
                return true;
            }
            if (smartphone.getMfr().contains(search)) {
                return true;
            }
        }
        return false;
    }

    public Product[] searchBy (String text){
        Product[] result = new Product[0];
        for (Product product : repository.getItems()) {
            if (matchesBook(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(repository.getItems(), 0, tmp, 0, tmp.length);
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
            if (matchesSmart(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(repository.getItems(), 0, tmp, 0, tmp.length);
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }
}









