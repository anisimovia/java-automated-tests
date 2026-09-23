package api.posts;

import com.aqa.course.api.models.posts.Posts;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTests extends BaseApiTest {

    @Test
    public void googleTest() {
        given()
                .get("http://google.com/")
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createPostByBodeParametersTest() {
        Posts post = new Posts("Huh", "Far", "1");

        requests.createPosts(post)
                .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED);

        requests.getPosts()
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .body("userId", is(1))
                .body("title", is("qui est esse"));
    }

    private static Stream<Arguments> posts() {
        return Stream.of(
                Arguments.of(new Posts("Huh", "Far", "1")),
                Arguments.of(new Posts("Wow", "Wow wow wow", "2"))
        );
    }

    @ParameterizedTest
    @MethodSource("posts")
    public void createPostTest(Posts expectedPosts) {
        successfulRequests.createPosts(expectedPosts);

        Posts actualPost = successfulRequests.getPosts();

//        assertEquals(expectedPost, actualPost);
    }

    @Test
    public void updatePostsTest() {
        Posts posts = generator.getPosts();

        successfulRequests.createPosts(posts);

        Posts updatedPosts = generator.getPosts();

        successfulRequests.updatePosts(updatedPosts);
    }

    @Test
    @DisplayName("Delete Post")
    @Tag("Positive")
    public void deletePostTest() {
        successfulRequests.deletePosts();
    }
}
