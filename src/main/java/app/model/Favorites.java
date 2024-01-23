package app.model;

public class Favorites{
	protected Integer FavoriteId;
	protected Users User;
	// Replace with Trails object:
	protected Trails Trail;
	
	public Favorites(Integer favoriteId, Users user, Trails trail) {
		super();
		FavoriteId = favoriteId;
		User = user;
		Trail = trail;
	}

	public Integer getFavoriteId() {
		return FavoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		FavoriteId = favoriteId;
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