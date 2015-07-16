package co.edu.udistrital.rrhh.web.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.udistrital.rrhh.web.LoginBean;

public class PagesFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {    
    	
    	HttpServletRequest req = (HttpServletRequest) request;
        
        String url = ((HttpServletRequest)request).getRequestURL().toString();
        String queryString = ((HttpServletRequest)request).getQueryString();
                
        LoginBean loginBean = (LoginBean) req.getSession().getAttribute("loginBean");
        
        if (loginBean != null && loginBean.getUser() != null) {
            // User is logged in, so just continue request.
            chain.doFilter(request, response);
        } else {
            // User is not logged in, so redirect to index.
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(req.getContextPath() + "/public/index.jsf");
        }
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

    // You need to override init() and destroy() as well, but they can be kept empty.
}
