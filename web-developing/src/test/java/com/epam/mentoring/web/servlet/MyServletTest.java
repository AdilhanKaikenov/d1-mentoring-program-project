package com.epam.mentoring.web.servlet;

import com.epam.mentoring.web.appstate.ApplicationState;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;

import static io.restassured.RestAssured.given;

@Ignore
@Deprecated
public class MyServletTest {

    private static final String TEST_USERNAME = "Test Name";
    private static final String USERNAME = "Username";

    private static ApplicationState applicationState;

    @Before
    public void init() throws Exception {
        applicationState = ApplicationState.getInstance();
//        given
        applicationState.putName(1L, USERNAME);
    }

    @After
    public void reset() throws Exception {
        Field field = ApplicationState.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(applicationState, null);
    }

    @Test
    public void getRequestTest() throws Exception {
        given().when()
                .get("http://localhost:8888/myServlet")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void postRequestTest() throws Exception {
        given().param("username", TEST_USERNAME)
                .when()
                .post("http://localhost:8888/myServlet")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void putRequestTest() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body("{\"id\":\"1\", \"newname\":\"New Name\"}")
                .when()
                .put("http://localhost:8888/myServlet")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void deleteRequestTest() throws Exception {
        given().param("id", "1")
                .when()
                .delete("http://localhost:8888/myServlet")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
