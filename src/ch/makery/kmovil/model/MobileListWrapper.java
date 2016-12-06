package ch.makery.kmovil.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "persons")
public class MobileListWrapper {

	private List<Mobile> persons;

	@XmlElement(name = "person")
	public List<Mobile> getPersons() {
		return persons;
	}

	public void setPersons(List<Mobile> persons) {
		this.persons = persons;
	}
}
