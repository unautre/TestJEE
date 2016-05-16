package tk.poneycorp.training.exceptions;

import javax.ws.rs.core.Response;

/**
 * Created by unautre on 16/05/16.
 */
public class LoginRequiredException extends HermesException {
    public LoginRequiredException() {
        super(Response.Status.FORBIDDEN, "Une authentification est requise");
    }
}
