package tk.poneycorp.training.WebLogic;

import tk.poneycorp.training.BusinessLogic.AuthorEJB;
import tk.poneycorp.training.BusinessLogic.MessagesEJB;
import tk.poneycorp.training.data.AuthorBean;
import tk.poneycorp.training.data.MessageBean;

import javax.ejb.EJB;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by unautre on 24/04/16.
 */
@WebServlet("/registerservlet")
public class RegisterServlet extends HttpServlet {
    @EJB MessagesEJB messageEJB;
    @EJB AuthorEJB authorEJB;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean connexion = "true".equals(req.getParameter("connexion"));
        AuthorBean author;

        if(connexion){
            author = authorEJB.login(
                    req.getParameter("name"),
                    req.getParameter("pass")
            );
            if(author == null){
                resp.sendError(500, "Nom inconnu !");
                return;
            }
        }else{
            author = authorEJB.register(
                    req.getParameter("name"),
                    req.getParameter("pass")
            );
        }
        req.getSession().setAttribute("author", author);
        resp.sendRedirect("index.jsp");
        //req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}