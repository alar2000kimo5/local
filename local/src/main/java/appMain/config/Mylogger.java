package appMain.config;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.message.MessageFactory;

public class Mylogger  extends Logger{

	protected Mylogger(LoggerContext context, String name, MessageFactory messageFactory) {
		super(context, name, messageFactory);
		// TODO Auto-generated constructor stub
	}

}
