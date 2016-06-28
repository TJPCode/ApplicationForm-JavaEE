package pkg.model;

public class Applicant {
	int key;
	String firstname;
	String lastname;
	String application;
	int isRead;
	
	public Applicant(int key, String firstname, String lastname, int isRead) {
		this.key = key;
		this.firstname = firstname;
		this.lastname = lastname;
		this.isRead = isRead;
	}	
	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}
}