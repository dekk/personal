package org.jid.pruebas.async.servlet30;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PruebaAsync2
 */
@WebServlet(urlPatterns="/PruebaAsync2", asyncSupported=true)
public class PruebaAsync2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger=Logger.getLogger(PruebaAsync2.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaAsync2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		AsyncContext ctx=request.startAsync();
		
		ctx.setTimeout(60000);
		
		ctx.addListener(new AsyncListener() {
			
			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				logger.severe("JID: timeout en "+event.toString());
				
			}
			
			@Override
			public void onStartAsync(AsyncEvent arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onError(AsyncEvent arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onComplete(AsyncEvent arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
		});
		
		ctx.start(new SubscriptionTask("colaMensajes", ctx));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
