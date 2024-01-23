package app.model;

import java.sql.Timestamp;

public class Recommendations{
	protected Integer RecommendationId;
	protected Timestamp Created;
	protected difficulty Difficulty;
	protected Users User;
	protected Trails Trail;
	
	
	public enum difficulty {
		BEGINNER,
		INTERMEDIATE,
		ADVANCED
	}

	public Recommendations(Integer recommendationId, Timestamp created, difficulty difficulty, Users user,
			Trails trail) {
		super();
		RecommendationId = recommendationId;
		Created = created;
		Difficulty = difficulty;
		User = user;
		Trail = trail;
	}
	
	public Recommendations(Integer recommendationId, Timestamp created) {
		super();
		RecommendationId = recommendationId;
		Created = created;
		Difficulty = null;
		User = null;
		Trail = null;
	}

	public Recommendations(Integer recommendationId) {
		RecommendationId = recommendationId;
		Created = new Timestamp(System.currentTimeMillis());
		Difficulty = null;
		User = null;
		Trail = null;
	}

	public Integer getRecommendationId() {
		return RecommendationId;
	}

	public void setRecommendationId(Integer recommendationId) {
		RecommendationId = recommendationId;
	}

	public Timestamp getCreated() {
		return Created;
	}

	public void setCreated(Timestamp created) {
		Created = created;
	}

	public difficulty getDifficulty() {
		return Difficulty;
	}

	public void setDifficulty(difficulty difficulty) {
		Difficulty = difficulty;
	}

	public Users getUser() {
		return User;
	}

	public void setUser(Users user) {
		User = user;
	}

	public Trails getTrail() {
		return Trail;
	}

	public void setTrail(Trails trail) {
		Trail = trail;
	}
	
	
}