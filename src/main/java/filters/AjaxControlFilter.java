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
            // 필터 객체가 최초로 생성된 후
            // 역할을 제대로 수행 할 수 있도록 준비를 시키기 위해 호출함.
            // - 필터의 역할 수행에 필요한 작업을 명시하면 됨.

            allowDomain = filterConfig.getInitParameter("allowDomain");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                    FilterChain filterChain) throws IOException, ServletException {
            // TODO Auto-generated method stub

            System.out.println("doFilter before");

            ((HttpServletResponse) response).setHeader(
                            "Access-Control-Allow-Origin", "*");
            filterChain.doFilter(request, response);

            System.out.println("dofilter after");

    }

    @Override
    public void destroy() {
            // TODO Auto-generated method stub

    }

}
