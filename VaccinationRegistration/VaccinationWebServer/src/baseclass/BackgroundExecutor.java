package baseclass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author namdv
 */
public class BackgroundExecutor {
	private static ExecutorService _executor = Executors.newFixedThreadPool(16);
	public static ExecutorService get() {
		return _executor;
	}
}
