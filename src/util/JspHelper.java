package util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {
    private static final String PATH = "WEB-INF/jsp/%s.jsp";

    public static String getPath(String jspName) {
        return PATH.formatted(jspName);
    }
}
