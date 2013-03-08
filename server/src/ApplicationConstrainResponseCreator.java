import beans.ApplicationConstrain;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;

/**
 * Extends the {@link ResponseCreator} for application request task task.
 */
public class ApplicationConstrainResponseCreator extends ResponseCreator {
	public ApplicationConstrainResponseCreator(Document dom) {
		super(dom);
	}

	public String createResponse() {
		Element root     = dom.getDocumentElement();
		String  country  = root.getAttribute("country");
		String  login    = root.getAttribute("login");
		String  password = root.getAttribute("password");

		return applicationConstrainToXML(getCountryApplicationConstrain(country, login, password));
	}

	//TODO: Need to be implemented with data from database.
	private ApplicationConstrain getCountryApplicationConstrain(String country, String login, String password) {
		Vector<ApplicationConstrain.SportConstrain> vec = new Vector<ApplicationConstrain.SportConstrain>();
		vec.add(new ApplicationConstrain.SportConstrain("Baseball",   10, ApplicationConstrain.Sex.Male));
		vec.add(new ApplicationConstrain.SportConstrain("Basketball", 15, ApplicationConstrain.Sex.Female));
		vec.add(new ApplicationConstrain.SportConstrain("Swim",       25, ApplicationConstrain.Sex.Undefined));

		return new ApplicationConstrain(vec);
	}

	private String applicationConstrainToXML(ApplicationConstrain applicationConstrain) {
		ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
		XMLEncoder e = new XMLEncoder(new BufferedOutputStream(byteArr));
		e.writeObject(applicationConstrain);
		e.close();
		return byteArr.toString();
	}
}
