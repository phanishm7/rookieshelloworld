package demo.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
public class WebAppInitializer implements WebApplicationInitializer {
	public void onStartup(ServletContext container) throws ServletException {  
       AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();  
        ctx.register(Application.class);  
        ctx.setServletContext(container);    
        Dynamic dynamic = container.addServlet("dispatcher", new DispatcherServlet(ctx));  
        dynamic.addMapping("/");  
        dynamic.setLoadOnStartup(1);  
		
		/* // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(Application.class);
        
        container.addListener(new ContextLoaderListener(rootContext));
        
        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.setServletContext(container);
        dispatcherContext.setParent(rootContext);
        dispatcherContext.register(Application.class);
        
        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");*/
        
      //  container.addFilter("shiroFilter", new DelegatingFilterProxy("shiroFilterBean", dispatcherContext))
      //  .addMappingForUrlPatterns(null, false, "/*");
   }  
} 
