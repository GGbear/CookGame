
public class Ingredient {
	private int id;
	private String name;
	private String imageLocation;
	public Ingredient(int myId, String myName, String myImageLocation) {
		setId(myId);
		setName(myName);
		setImageLocation(myImageLocation);
	}
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getImageLocation() {
		return this.imageLocation;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	public String toString() {
		return String.format("%s",getName());
	}
}

