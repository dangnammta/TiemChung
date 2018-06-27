package render;

import hapax.Template;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

/**
 *
 * @author namdv
 */
public class RenderBase {

	private static final Logger log = Log.getLogger(RenderBase.class);
	private static RenderBase _instance = new RenderBase();

	public static RenderBase getInstance() {
		return _instance;
	}

	protected Template getCTemplate(String tpl) throws Exception {
		TemplateLoader templateLoader = TemplateResourceLoader.create("view/");
		Template template = templateLoader.getTemplate(tpl);
		return template;
	}
}
