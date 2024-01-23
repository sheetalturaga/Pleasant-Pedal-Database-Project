package app.model;

import java.sql.Timestamp;

public class SimilarTrails {


	public Integer TrailId;
	public String SimilarTrail;
	
	
	
	public SimilarTrails(Integer TrailId, String SimilarTrail) {
		
	}
	
	
	public Integer getTrailId() {
		return TrailId;
		}

	
	public void setTrailId() {
		this.TrailId = TrailId;
		}
	
	
	public String getSimilarTrail() {
		return SimilarTrail;
		}

	
	public void setSimilarTrail() {
		this.SimilarTrail = SimilarTrail;
		}
		
	
	
}