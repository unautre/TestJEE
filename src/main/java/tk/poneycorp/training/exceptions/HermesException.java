package tk.poneycorp.training.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by unautre on 15/05/16.
 */
public class HermesException extends WebApplicationException {
    public HermesException(Response.Status status, String message){
        super(Response.status(status).entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}
