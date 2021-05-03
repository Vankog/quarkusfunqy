package app.dmo.funqy.books;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@QuarkusTest
@QuarkusTestResource(DynamoDbResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BooksTest {

    @Test
    @Order(1)
    public void getEmptyResult() {
        given().contentType("application/json")
                .get("/getBooks")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(0));
    }

    @Test
    @Order(2)
    public void createBook() {
        Book book = new Book("title", "author");
        given().contentType("application/json")
                .body(book).post("/createBook")
                .then().statusCode(204);
    }

    @Test
    @Order(3)
    public void getOneBookResult() {
        given().contentType("application/json")
                .get("/getBooks")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(1));
    }

    @Test
    @Order(4)
    public void createBookWithGet() {
        given().contentType("application/json")
                .param("title", "a title")
                .param("author", "an author")
                .get("/createBook")
                .then().statusCode(204);
    }

    @Test
    @Order(5)
    public void getTwoBooksResult() {
        given().contentType("application/json")
                .get("/getBooks")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(2));
    }
}