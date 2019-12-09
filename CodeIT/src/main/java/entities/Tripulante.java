package entities;

import enums.Cargos;

public class Tripulante {
	
	private Cargos cargo;

	public Tripulante() {
		
	}
	
	public Tripulante(Cargos cargo) {
		super();
		this.cargo = cargo;
	}

	public Cargos getCargo() {
		return cargo;
	}
	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}
	
	

}
