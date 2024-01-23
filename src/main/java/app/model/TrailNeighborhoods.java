package app.model;

public class TrailNeighborhoods extends Trails {
	protected Neighborhoods Neighborhood;

	public TrailNeighborhoods(int trailId, String trailName, String picture, String description, String location,
			Double distance, Difficulty difficulty, Neighborhoods neighborhood) {
		super(trailId, trailName, picture, description, distance, difficulty);
		Neighborhood = neighborhood;
	}

	public Neighborhoods getNeighborhood() {
		return Neighborhood;
	}

	public void setNeighborhood(Neighborhoods neighborhood) {
		Neighborhood = neighborhood;
	}
	
	

}
