package net.rhm.appuser.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

/**
 * Utility class to properly display http request and response logs
 */
public class LogHeaders {

    public static String displayHeaders(HttpServletRequest request) {

        StringBuilder toLog = new StringBuilder();
        toLog.append("Displaying HEADERS :\n");

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            toLog.append(String.format("%s : %s",key, value));
        }

        return toLog.toString();
    }


    public static String displayHeaders(HttpServletResponse response) {

        StringBuilder toLog = new StringBuilder();
        toLog.append("Displaying response HEADERS :\n");

        List<String> headers = (List<String>) response.getHeaderNames();

        headers.forEach((header) -> {

            toLog.append(header);
            toLog.append(": ");
            toLog.append(response.getHeader(header));
        });

        return toLog.toString();
    }
}
