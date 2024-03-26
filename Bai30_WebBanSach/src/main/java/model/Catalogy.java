package model;

public class Catalogy {
	private String catalogyId;
	private String catalogyName;

	public Catalogy() {
	}

	public Catalogy(String catalogyId) {
		this.catalogyId = catalogyId;
	}

	public Catalogy(String catalogyId, String catalogyName) {
		this.catalogyId = catalogyId;
		this.catalogyName = catalogyName;
	}

	public String getCatalogyId() {
		return catalogyId;
	}

	public void setCatalogyId(String catalogyId) {
		this.catalogyId = catalogyId;
	}

	public String getCatalogyName() {
		return catalogyName;
	}

	public void setCatalogyName(String catalogyName) {
		this.catalogyName = catalogyName;
	}

	@Override
	public String toString() {
		return "Catalogy [catalogyId=" + catalogyId + ", catalogyName=" + catalogyName + "]";
	}

	
}
