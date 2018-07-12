package za.ac.unisa.myadmin.server.configurations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.commons.lang3.exception.ExceptionUtils;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;

/**
 * Exception handler that will handle exceptions in the rest layer. The service
 * exceptions are translated to rest response statuses with the actual exception
 * stacktrace messages placed on the response headers.
 * 
 * @author Jannie
 *
 */
public class ExceptionHandler implements ExceptionMapper<Exception> {

    private static final Map<String, Response.Status> MAP = new LinkedHashMap<String, Response.Status>();

    static {
        // these are the ones that the Producer says it handles 
        MAP.put(IllegalArgumentException.class.getSimpleName(), Response.Status.BAD_REQUEST);
        MAP.put(IllegalStateException.class.getSimpleName(), Response.Status.BAD_REQUEST);
        MAP.put(SecurityException.class.getSimpleName(), Response.Status.FORBIDDEN);
        
		// catch some
// reserved for authz:        MAP.put(PermissionDeniedException.class.getSimpleName(), Response.Status.FORBIDDEN);        
        MAP.put(InvalidParameterException.class.getSimpleName(), Response.Status.BAD_REQUEST);
        MAP.put(MissingParameterException.class.getSimpleName(), Response.Status.BAD_REQUEST);
        MAP.put(DoesNotExistException.class.getSimpleName(), Response.Status.NOT_FOUND);
        // TODO: add more into the handler as needed
    }
    
	@Override
	public Response toResponse(Exception exception) {
		Response.Status status = MAP.get(exception.getClass().getSimpleName());
        if (status == null) {
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        
        System.out.println("Exception thrown by service: " + exception.toString());
        ExceptionUtils.printRootCauseStackTrace(exception);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream ();
        PrintStream printStream = new PrintStream (baos);
        ExceptionUtils.printRootCauseStackTrace(exception, printStream);
		// Getting an exception 8192 is the maxHttpHeaderSize
        // making max 6000 because I don't know if it 8192 is the total header size of the max size
        // for just one header
        String stackTrace = baos.toString();
        if (stackTrace.length() > 6000) {
            stackTrace = stackTrace.substring(0, 6000);
        }        
        return Response
                .status(status)
                .header("message", exception.getMessage())
                .header("class", exception.getClass().getSimpleName())
                .header("stacktrace", stackTrace).build();
	}

}
