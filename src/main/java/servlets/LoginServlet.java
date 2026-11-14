package servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException{

        HttpSession session = request.getSession(false);

        if (session != null &&
                session.getAttribute("usuario") != null) {

            response.sendRedirect(
                    request.getContextPath() + "/LibroServlet"
            );
            return;
        }

        request.getRequestDispatcher("login.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Obtener datos del formulario
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null) {
            request.setAttribute("error",
                    "Correo o contraseña incorrectos");

            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
            return;
        }

    }
}
