package tk.poneycorp.training.WebLogic;

import tk.poneycorp.training.BusinessLogic.MessagesEJB;
import tk.poneycorp.training.data.AuthorBean;
import tk.poneycorp.training.data.MessageBean;
import tk.poneycorp.training.exceptions.LoginRequiredException;

import javax.ejb.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by unautre on 27/04/16.
 */
@Path("/") @Produces(MediaType.APPLICATION_XML)
public class AJAXResource {
    public AJAXResource() { }

    @EJB MessagesEJB messagesEJB;

    @POST @Path("message")
    public MessageBean sendMessage(@FormParam("message") String msg, @Context HttpServletRequest req, @Context HttpServletResponse resp){
        AuthorBean authorBean = (AuthorBean) req.getSession().getAttribute("author");
        if(authorBean == null) throw new LoginRequiredException();
        return messagesEJB.sendMessage(msg, authorBean);
    }

    @GET @Path("getall")
    public List<MessageBean> getAllMessages(/*@PathParam("id") long from_id*/){
        return messagesEJB.getAllMessages();
    }

    @GET @Path("getsince/{id}")
    public List<MessageBean> getAllSince(@PathParam("id") long from_id){
        return messagesEJB.getAllSince(from_id);
    }
}
