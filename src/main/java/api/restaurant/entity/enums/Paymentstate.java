package api.restaurant.entity.enums;

public enum Paymentstate {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private Integer cod;
	private String description;

	private Paymentstate(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static Paymentstate toEnum(Integer id) {

		if (id == null) {
			return null;
		}
		for (Paymentstate x : Paymentstate.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);

	}

}
