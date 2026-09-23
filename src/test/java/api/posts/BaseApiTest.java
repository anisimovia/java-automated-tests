package api.posts;

import com.aqa.course.data.Generator;
import com.aqa.course.data.RandomData;
import com.aqa.course.requests.posts.SuccessfulRequests;
import com.aqa.course.requests.posts.Requests;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseApiTest {
    protected Requests requests;
    protected SuccessfulRequests successfulRequests;
    protected RandomData randomData;
    protected Generator generator;

    @BeforeAll
    public static void restAssured() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @BeforeEach
    public void setupTest() {
        requests = new Requests();
        successfulRequests = new SuccessfulRequests();
        randomData = new RandomData();
        generator = new Generator();
    }

}
