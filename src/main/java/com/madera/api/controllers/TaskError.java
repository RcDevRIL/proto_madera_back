package com.madera.api.controllers;

import org.junit.runner.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Controlleur pour gérer les erreurs.
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.1-RELEASE
 */
@Controller
public class TaskError implements ErrorController {

    private static final Logger log = LoggerFactory.getLogger(TaskError.class);

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String messageError = (String)request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        //Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        log.error("Error with server {}", messageError == null ? "N/A" : messageError);
        return String.format(
                "<html>" + "<body>" + "<h2>Une Erreur est survenue !</h2>" + "<div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div>" + "</body>" + "</html>",
                statusCode, messageError == null ? "N/A" : messageError);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
