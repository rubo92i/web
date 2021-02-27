package am.basic.jdbc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JspUtils {


    public static String getAttribute(HttpServletRequest request, HttpServletResponse response, String attributeName) throws IOException {
        String message = (String) request.getAttribute(attributeName);
        if (message != null) {
            return message;
        } else {
            return "";
        }
    }


    public static <T> T getAttribute(HttpServletRequest request, String attributeName, Class<T> type) throws IOException {
        T message = (T) request.getAttribute(attributeName);
        if (message != null) {
            return message;
        } else {
            return null;
        }
    }
}
