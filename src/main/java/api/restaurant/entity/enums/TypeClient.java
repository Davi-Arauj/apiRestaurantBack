package api.restaurant.entity.enums;

public enum TypeClient {
	PESSOA_FISICA(1,"Pessoa Física"),
	PESSOA_JURIDICA(2,"Pessoa Juridica");

	private Integer cod;
	private String description;
	
	private TypeClient(int i,String description) {
		this.cod = i;
		this.description = description;
	}
	
	public Integer getCod() {
		return cod;
	}
	public String getDescription() {
		return description;
	}
	public static TypeClient toEnum(Integer integer) {
		
		if(integer == null) {
			return null;
		}
		for(TypeClient x : TypeClient.values()) {
			if(integer.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalid "+ integer);
		
	}
}
