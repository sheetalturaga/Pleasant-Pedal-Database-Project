package app.model;

import java.sql.Timestamp;
import java.sql.Blob;


public class Reviews{
	protected int ReviewId;
	protected Timestamp Created;
	protected float Rating;
	protected Blob Picture;
	protected String userName;
	protected int trailId;
	protected String image;
	protected String trailName;

	public Reviews(Integer reviewId, Timestamp created, float rating, String image, String user, int trail) {
		ReviewId = reviewId;
		Created = created;
		Rating = rating;
		this.image = image;
		userName = user;
		trailId = trail;
	}
	
	public Reviews(Integer reviewId, Timestamp created, String image, float rating, String user, int trail, String trailName) {
		ReviewId = reviewId;
		Created = created;
		Rating = rating;
		this.image = image;
		userName = user;
		trailId = trail;
		this.trailName = trailName;
	}
	
	public Reviews(Integer reviewId, Timestamp created, float rating, String user, int trail, String trailName) {
		ReviewId = reviewId;
		Created = created;
		Rating = rating;
		this.image = null;
		userName = user;
		trailId = trail;
		this.trailName = trailName;
	}
	public Reviews( float rating, String user, int trail) {
		Rating = rating;
		image = null;
		userName = user;
		trailId = trail;
	}
	
	public String getTrailName() {
		return trailName;
	}

	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getReviewId() {
		return ReviewId;
	}

	public void setReviewId(Integer reviewId) {
		ReviewId = reviewId;
	}

	public Timestamp getCreated() {
		return Created;
	}

	public void setCreated(Timestamp created) {
		Created = created;
	}

	public float getRating() {
		return Rating;
	}

	public void setRating(float rating) {
		Rating = rating;
	}

	public Blob getPicture() {
		return Picture;
	}

	public void setPicture(Blob picture) {
		Picture = picture;
	}

	public String getUserName() {
		return userName;
	}

	public void setUser(String user) {
		userName = user;
	}

	public int getTrail() {
		return trailId;
	}

	public void setTrail(int trail) {
		trailId = trail;
	}
}