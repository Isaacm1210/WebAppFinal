
package filters;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserService;

/**
 *
 * @author mhame
 */
public class AdminFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();
        String email = (String) session.getAttribute("email");
        
        UserDB us = new UserDB();
        try {
            int roleId = us.getUser(email).getRole().getRoleId();
            if(roleId != 1){
                HttpServletResponse httpResponse = (HttpServletResponse)response;
                httpResponse.sendRedirect("user");
                return;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            chain.doFilter(request, response);
  
        }


    public void destroy() {        
    }


    public void init(FilterConfig filterConfig) {        
        
    }

    
}
