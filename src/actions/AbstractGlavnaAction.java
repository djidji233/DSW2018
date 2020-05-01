package actions;

import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class AbstractGlavnaAction extends AbstractAction {
	
	//pravi ikonicu na osnovu zadatog imena
	
	public Icon loadIcon(String fileName){
		URL imageURL = getClass().getResource(fileName);
		Icon icon = null;
		
		if (imageURL != null) {                      
	        icon = new ImageIcon(imageURL);
	    } else {                                     
	        System.err.println("!!! " + fileName + "NOT FOUND !!!");
	    }

		return icon;
	}

}
