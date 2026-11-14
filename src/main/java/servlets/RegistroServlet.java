package servlets;

import beans.Usuario;
import daos.UsuarioDao;
import utils.PasswordUtil;


import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistroServlet",value = "/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException{

        request.getRequestDispatcher("registro.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServlectResponse response)
        throws ServletException, IOException{
        UsuarioDao usuarioDao = new UsuarioDao();

        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");

        if (nombres == null || apellidos == null || email == null || password == null){

            HttpSession session = request.getSession();
            session.setAttribute("Session",user);

            response.sendRedirect(request.getContextPath());

        }else{
            responde.sendRedirect(request.getContextPath() + "/LoginServlet?error");
        }

    }
}
