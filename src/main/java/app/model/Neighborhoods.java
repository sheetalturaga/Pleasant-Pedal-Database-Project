package app.model;

public class Neighborhoods {
	protected String Name; 
	protected String District;
	
	public Neighborhoods(String name, String district) {
		Name = name;
		District = district;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

}
