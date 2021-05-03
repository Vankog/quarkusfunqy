package app.dmo.funqy.books;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class Book {

    String id;
    String title;
    String author;

    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public static Book fromItem(Map<String, AttributeValue> item) {
        Book book = new Book();
        book.setId(item.get("id").s());
        book.setTitle(item.get("title").s());
        book.setAuthor(item.get("author").s());
        return book;
    }

    public Map<String, AttributeValue> toItem() {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(id == null ? UUID.randomUUID().toString() : id).build());
        item.put("title", AttributeValue.builder().s(title).build());
        item.put("author", AttributeValue.builder().s(author).build());
        return item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}