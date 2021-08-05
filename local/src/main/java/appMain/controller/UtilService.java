package appMain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

	@Autowired
	private myInterface myInterface;

	public void 現在是用DB還是EMPT() {
		myInterface.init();
	}
}
