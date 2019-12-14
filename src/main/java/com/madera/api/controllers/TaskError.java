package com.madera.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Controlleur pour gérer les erreurs.
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 0.2-PRE-RELEASE
 */
@Controller
public class TaskError implements ErrorController {

    private static final Logger log = LoggerFactory.getLogger(TaskError.class);

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        // normalement c'est ça... :/
        log.error("Error with server {}", exception == null ? "N/A" : exception.getMessage());
        return String.format(
                "<html>" + "<body>" + "<h2>Une Erreur sauvage apparaît !</h2>" + "<div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div>" + "</body>" + "</html>",
                statusCode, exception == null ? "N/A" : exception.getMessage());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
