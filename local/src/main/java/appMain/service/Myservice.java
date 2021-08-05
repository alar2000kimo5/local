package appMain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Myservice {

	@Autowired
	private Myutil my;

	public String getMyName() {
		return my.getName();
	}
}
