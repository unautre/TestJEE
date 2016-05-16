package tk.poneycorp.training;

import org.glassfish.hk2.utilities.reflection.Logger;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by unautre on 16/05/16.
 */
public class ExceptionListener implements ApplicationEventListener {

    @Override
    public void onEvent(ApplicationEvent applicationEvent) {

    }

    @Override
    public RequestEventListener onRequest(RequestEvent requestEvent) {
        return new ExceptionRequestEventListener();
    }

    public static class ExceptionRequestEventListener implements RequestEventListener {
        private final Logger logger;

        public ExceptionRequestEventListener() {
            this.logger = Logger.getLogger();
        }

        @Override
        public void onEvent(RequestEvent requestEvent) {
            switch(requestEvent.getType()){
                case ON_EXCEPTION:
                    Throwable t = requestEvent.getException();
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    t.printStackTrace(pw);
                    //while(t.getCause() != null) t = t.getCause();
                    logger.warning("Exception caught !\n" + sw.toString());
            }
        }
    }
}
