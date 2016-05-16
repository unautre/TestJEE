package tk.poneycorp.training;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import tk.poneycorp.training.BusinessLogic.MessagesEJB;
import tk.poneycorp.training.WebLogic.AJAXResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by unautre on 23/04/16.
 */
@ApplicationPath("api")
public class JAXRSApplication extends ResourceConfig {
    public JAXRSApplication() {
        /*register(new AbstractBinder() {
            @Override
            protected void configure() {
                //bind(MessagesEJB.class).to(MessagesEJB.class);
                //bind(AJAXResource.class).to(AJAXResource.class);
            }
        }); */
        register(ExceptionListener.class);
        packages(true, "tk.poneycorp.training");
    }
}
