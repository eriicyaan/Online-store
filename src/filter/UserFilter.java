package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class UserFilter implements Filter {
    private final List<String> PUBLIC_PATH = List.of("/login", "/registration");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();

        if(isPublicPath(uri) || isLoginIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            Object referer = servletRequest.getAttribute("referer");
            if(referer != null) {
                ((HttpServletResponse)servletResponse).sendRedirect(referer.toString());
            }
            else {
                ((HttpServletResponse)servletResponse).sendRedirect("/login");
            }
        }
    }

    private boolean isLoginIn(ServletRequest request) {
        return ((HttpServletRequest)request).getSession().getAttribute("user") != null;
    }


    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream()
                .anyMatch(path -> path.startsWith(uri));
    }
}
