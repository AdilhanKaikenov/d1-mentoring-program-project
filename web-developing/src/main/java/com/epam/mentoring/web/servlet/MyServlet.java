package com.epam.mentoring.web.servlet;

import com.epam.mentoring.web.appstate.ApplicationState;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Kaikenov Adilhan
 **/
public class MyServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(MyServlet.class);

    private static final String APPLICATION_JSON = "application/json";
    private static final String UTF_8 = "UTF-8";

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String NEWNAME = "newname";

    private static ApplicationState applicationState;

    @Override
    public void init() throws ServletException {
        applicationState = ApplicationState.getInstance();
        log.info("The state of the application has been initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Map<Long, String> namesMap = applicationState.getNamesRepositoryMap();
        write(response, namesMap);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String username = request.getParameter(USERNAME);

        if (StringUtils.isBlank(username)) {
            log.error("Username is not valid");
        } else {
            applicationState.putName(username);
            log.debug("Username '{}' has been added", username);
        }

        write(response, applicationState.getNamesRepositoryMap());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonString = convertStreamToString(request.getInputStream());
        JSONObject jsonObject = new JSONObject(jsonString);

        long id = jsonObject.getLong(ID);
        String newname = jsonObject.getString(NEWNAME);

        if (StringUtils.isBlank(newname)) {
            log.error("Username is not valid");
        } else {
            applicationState.putName(id, newname);
            log.debug("Username of user with id = {} has been updated", id);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParameter = request.getParameter(ID);

        if (StringUtils.isBlank(idParameter)) {
            log.error("Id parameter can not be empty");
        } else {
            long userId = Long.parseLong(idParameter);
            applicationState.deleteNameById(userId);
            log.debug("User with id = {} has been deleted", userId);
        }

        write(response, applicationState.getNamesRepositoryMap());
    }

    private String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, UTF_8);
        return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : StringUtils.EMPTY;
    }

    private void write(HttpServletResponse response, Map<Long, String> map) throws IOException {
        response.setContentType(APPLICATION_JSON);
        response.setCharacterEncoding(UTF_8);
        response.getWriter().write(new Gson().toJson(map));
    }
}