package api.restaurant.entity.enums;

public enum TypeClient {
	PESSOA_FISICA(1,"Pessoa Física"),
	PESSOA_JURIDICA(2,"Pessoa Juridica");

	private int cod;
	private String description;
	
	private TypeClient(int cod,String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	public String getDescription() {
		return description;
	}
	public static TypeClient toEnum(Integer id) {
		
		if(id == null) {
			return null;
		}
		for(TypeClient x : TypeClient.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalid "+ id);
		
	}
}
