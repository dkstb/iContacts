package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class AjaxControlFilter implements Filter {

	String allowDomain;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

            allowDomain = filterConfig.getInitParameter("allowDomain");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                    FilterChain filterChain) throws IOException, ServletException {
            // TODO Auto-generated method stub

            System.out.println("doFilter before");

            ((HttpServletResponse) response).setHeader(
                            "Access-Control-Allow-Origin", "*");

            // 유저 아이디 캐스팅
//            if (request.getAttribute("id") != null) {
//            	int id = (Integer) request.getAttribute("id");
//            	request.setAttribute("id", id);
//			}
            
            filterChain.doFilter(request, response);

            System.out.println("dofilter after");

    }

    @Override
    public void destroy() {
            // TODO Auto-generated method stub

    }

}
