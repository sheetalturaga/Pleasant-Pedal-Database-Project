package app.model;



public class Trails {
	protected int TrailId;
	protected String TrailName;
	protected String  Picture; 
	protected String Description; 
	protected Double Distance; 
	protected Difficulty Difficulty; 
	
	public enum Difficulty {
		BEGINNER, INTERMEDIATE, ADVANCED
	}


	public Trails(int trailId, String trailName, String picture, String description, 
			Double distance, Difficulty difficulty) {
		TrailId = trailId;
		TrailName = trailName;
		Picture = picture;
		Description = description;
		Distance = distance;
		Difficulty = difficulty; 
		
	}


	public int getTrailId() {
		return TrailId;
	}

	public void setTrailId(int trailId) {
		TrailId = trailId;
	}

	public String getTrailName() {
		return TrailName;
	}

	public void setTrailName(String trailName) {
		TrailName = trailName;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String picture) {
		Picture = picture;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Double getDistance() {
		return Distance;
	}

	public void setDistance(Double distance) {
		Distance = distance;
	}

	public Difficulty getDifficulty() {
		return Difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		Difficulty = difficulty;
	}
	
	
}