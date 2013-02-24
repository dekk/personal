package org.jid.pruebas.async.servlet30;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.AsyncContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class SubscriptionTask extends JedisPubSub implements Runnable 
{
	Logger logger=Logger.getLogger(SubscriptionTask.class.getName());
	
	AsyncContext context=null;
	
	String channel="";
	
	public SubscriptionTask(String channel, AsyncContext context)
	{
		this.channel=channel;
		this.context=context;
	}
	
	@Override
	public void run() 
	{
		Jedis redis=new Jedis("localhost");
		
		redis.subscribe(this, channel);
  
	}

	@Override
	public void onMessage(String channel, String msg) {
		logger.info("JID: Se ha recibido: "+channel+" - "+msg);
		
		try 
		{
			context.getResponse().setContentType("text/plain");
			context.getResponse().getWriter().println("Se ha recibido: "+channel+" - "+msg);
			context.getResponse().flushBuffer();
		
			//Si no se invoca a complete o a dispatch la petición no termina con lo que se tiene un "efecto streaming"
			//context.complete();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onPMessage(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPSubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPUnsubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribe(String channel, int arg1) {
		logger.info("JID: Se ha suscrito: "+channel+" - "+arg1);

	}

	@Override
	public void onUnsubscribe(String arg0, int arg1) {
		logger.info("JID: No esta suscrito: "+channel+" - "+arg1);

	}

}
