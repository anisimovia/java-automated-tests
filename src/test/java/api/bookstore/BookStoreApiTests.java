package api.bookstore;

import com.aqa.course.api.models.bookstore.Book;
import com.aqa.course.api.models.bookstore.BooksResponse;
import com.aqa.course.api.models.bookstore.UserCredentials;
import com.aqa.course.requests.bookstore.BookStoreApiClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookStoreApiTests {

    private final BookStoreApiClient client = new BookStoreApiClient();

    // --- Простые GET-тесты ---

    @Test
    public void getAllBooksReturnsList() {
        Response response = client.getAllBooks();

        assertThat(response.statusCode()).isEqualTo(200);

        BooksResponse books = response.as(BooksResponse.class);
        assertThat(books.getBooks()).isNotEmpty();
        assertThat(books.getBooks()).hasSizeGreaterThan(5);
    }

    @Test
    public void allBooksHaveRequiredFields() {
        BooksResponse books = client.getAllBooks().as(BooksResponse.class);

        for (Book book : books.getBooks()) {
            assertThat(book.getIsbn()).as("ISBN is missing").isNotBlank();
            assertThat(book.getTitle()).as("Title is missing").isNotBlank();
            assertThat(book.getAuthor()).as("Author is missing").isNotBlank();
        }
    }

    @Test
    public void getBookByIsbnReturnsCorrectBook() {
        // Сначала узнаём ISBN первой книги
        String isbn = client.getAllBooks()
                .as(BooksResponse.class)
                .getBooks()
                .get(0)
                .getIsbn();

        Response response = client.getBookByIsbn(isbn);

        assertThat(response.statusCode()).isEqualTo(200);

        Book book = response.as(Book.class);
        assertThat(book.getIsbn()).isEqualTo(isbn);
    }

    @Test
    public void getBookByInvalidIsbnReturnsError() {
        Response response = client.getBookByIsbn("invalid-isbn-123");

        // demoqa обычно возвращает 400 или 404
        assertThat(response.statusCode()).isIn(400, 404);
    }

    // --- Тесты регистрации ---

    @Test
    public void createUserReturns201() {
        String username = "testuser_" + System.currentTimeMillis();
        String password = "Test@1234";
        UserCredentials credentials = new UserCredentials(username, password);

        Response response = client.createUser(credentials);

        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.jsonPath().getString("userID")).isNotBlank();
        assertThat(response.jsonPath().getString("username")).isEqualTo(username);
    }

    @Test
    public void createUserWithWeakPasswordReturns400() {
        // Слишком простой пароль
        UserCredentials credentials = new UserCredentials("weakpass", "123");

        Response response = client.createUser(credentials);

        assertThat(response.statusCode()).isEqualTo(400);
    }

    @Test
    public void generateTokenForNewUserReturnsToken() {
        String username = "tokenuser_" + System.currentTimeMillis();
        String password = "Test@1234";
        UserCredentials credentials = new UserCredentials(username, password);

        // Создаём пользователя
        client.createUser(credentials);

        // Генерируем токен
        Response response = client.generateToken(credentials);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("token")).isNotBlank();
        assertThat(response.jsonPath().getString("status")).isEqualTo("Success");
    }

    @Test
    public void authorizedReturnsTrueForValidCredentials() {
        String username = "authuser_" + System.currentTimeMillis();
        String password = "Test@1234";
        UserCredentials credentials = new UserCredentials(username, password);

        client.createUser(credentials);
        client.generateToken(credentials);

        // Повторяем до 10 раз с интервалом 500ms, пока не получим true
        Boolean authorized = null;
        for (int i = 0; i < 10; i++) {
            Response response = client.isAuthorized(credentials);
            if (response.statusCode() == 200 && response.jsonPath().getBoolean("$")) {
                authorized = true;
                break;
            }
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        }

        assertThat(authorized).as("Authorization never returned true").isTrue();
    }

    @Test
    public void authorizedReturnsFalseForInvalidCredentials() {
        UserCredentials credentials = new UserCredentials("nonexistent", "Wrong@123");

        Response response = client.isAuthorized(credentials);

        // Demoqa теперь возвращает 404 для несуществующего пользователя
        assertThat(response.statusCode()).isIn(200, 404);
        // Если 200 — проверяем, что тело false
        if (response.statusCode() == 200) {
            assertThat(response.jsonPath().getBoolean("$")).isFalse();
        }
    }
}