package api.restaurant.entity.enums;

public enum Profile {

	ADMIN(1, "ROLE_ADMIN"), CLIENT(2, "ROLE_CLIENT");

	private Integer cod;
	private String description;

	private Profile(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static Profile toEnum(Integer id) {

		if (id == null) {
			return null;
		}
		for (Profile x : Profile.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);

	}

}
