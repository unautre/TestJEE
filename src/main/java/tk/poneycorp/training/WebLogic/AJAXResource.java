package tk.poneycorp.training.WebLogic;

import org.glassfish.jersey.process.internal.RequestScoped;
import sun.plugin2.message.Message;
import tk.poneycorp.training.BusinessLogic.AuthorEJB;
import tk.poneycorp.training.BusinessLogic.MessagesEJB;
import tk.poneycorp.training.data.AuthorBean;
import tk.poneycorp.training.data.MessageBean;

import javax.annotation.ManagedBean;
import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by unautre on 27/04/16.
 */
@Path("/") @Produces(MediaType.APPLICATION_XML)
public class AJAXResource {
    public AJAXResource() { }

    @EJB MessagesEJB messagesEJB;

    @POST @Path("message")
    public MessageBean sendMessage(@FormParam("message") String msg, @Context HttpServletRequest req){
        AuthorBean authorBean = (AuthorBean) req.getSession().getAttribute("author");
        System.out.println(String.format("Got message %s from %s", msg, authorBean));
        return messagesEJB.sendMessage(msg, authorBean);
    }

    @GET // @Path("{id}")
    public List<MessageBean> getMessages(/*@PathParam("id") long from_id*/){
        return messagesEJB.getAllMessages();
    }
}
