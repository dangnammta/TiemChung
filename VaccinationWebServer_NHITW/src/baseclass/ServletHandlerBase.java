package baseclass;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

/**
 * Servlet request handler which wraps the request and response objects
 * @author namdv
 */
public class ServletHandlerBase implements Runnable {
	protected Logger log = Log.getLogger(ServletHandlerBase.class);
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	protected AsyncContext aCtx;
	public static ThreadPoolExecutor executor;
	public ServletHandlerBase(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	public ServletHandlerBase(AsyncContext context) {
		aCtx = context;
		req = (HttpServletRequest)context.getRequest();
		resp = (HttpServletResponse)context.getResponse();
		if (executor == null) {
			executor = new ThreadPoolExecutor(50, 100, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
		}
	}
	
	@Override
	public void run() {
		complete();
	}
	public void complete() {
		if (aCtx != null) {
			aCtx.complete();
		}
	}
	
}
