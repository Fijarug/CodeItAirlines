package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.Tripulante;
import enums.Cargos;

@ManagedBean
@ViewScoped
public class AeroportoController {

	private List<Tripulante> terminal;
	private List<Tripulante> smartForTwo;
	private List<Tripulante> aviao;
	private Boolean smartForTwoIsOnTerminal = true;
	private String mensagem;

	public AeroportoController() {
		super();
		reiniciar();
	}

	public void reiniciar() {
		terminal = new ArrayList<Tripulante>();
		smartForTwo = new ArrayList<Tripulante>();
		aviao = new ArrayList<Tripulante>();
		smartForTwoIsOnTerminal = true;
		mensagem = "";

		terminal.add(new Tripulante(Cargos.PILOTO));
		terminal.add(new Tripulante(Cargos.OFICIAL));
		terminal.add(new Tripulante(Cargos.OFICIAL));
		terminal.add(new Tripulante(Cargos.CHEFESERVICO));
		terminal.add(new Tripulante(Cargos.COMISSARIA));
		terminal.add(new Tripulante(Cargos.COMISSARIA));
		terminal.add(new Tripulante(Cargos.POLICIAL));
		terminal.add(new Tripulante(Cargos.PRESIDIARIO));
	}

	public void adicionar(List<Tripulante> lista, Tripulante tripulante) {
		lista.add(tripulante);
	}

	public void remover(List<Tripulante> lista, Tripulante tripulante) {
		lista.remove(tripulante);
	}

	public void subirNoSmartForTwo(Tripulante tripulante) {
		mensagem = "Subiu " + tripulante.getCargo().getDescricao();
		if (validarCapacidadeSmartForTwo()) {
			if (smartForTwoIsOnTerminal) {
				remover(terminal, tripulante);
				adicionar(smartForTwo, tripulante);
			} else {
				remover(aviao, tripulante);
				adicionar(smartForTwo, tripulante);
			}
		}
	}

	public void descerDoSmartForTwo(Tripulante tripulante) {
		mensagem = "Desceu " + tripulante.getCargo().getDescricao();
		if (tripulante != null) {
			if (smartForTwoIsOnTerminal) {
				remover(smartForTwo, tripulante);
				adicionar(terminal, tripulante);
			} else {
				remover(smartForTwo, tripulante);
				adicionar(aviao, tripulante);
			}
		}
		if(aviao.size() == 8) {
			mensagem = "Resolução concluída.";
		}
	}

	public boolean validarMotorista() {
		for (Tripulante t : smartForTwo) {
			if (Cargos.PILOTO.equals(t.getCargo()) || Cargos.CHEFESERVICO.equals(t.getCargo())
					|| Cargos.POLICIAL.equals(t.getCargo())) {
				return true;
			}
		}
		return false;
	}

	public boolean validarChefeOficialPiloto() {
		if (validarChefeOficialPiloto(terminal) || validarChefeOficialPiloto(smartForTwo)
				|| validarChefeOficialPiloto(aviao)) {
			mensagem = "Nenhum Oficial pode ficar sozinho com o Chefe de Serviço.";
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validarChefeOficialPiloto(List<Tripulante> lista) {
		if (lista.size() == 2 && (hasCargoEspecifico(lista, Cargos.CHEFESERVICO) && hasCargoEspecifico(lista, Cargos.OFICIAL))) {
			return true;
		}
		return false;
	}

	public boolean validarChefeComissariaPiloto() {
		if (validarChefeComissariaPiloto(terminal) || validarChefeComissariaPiloto(smartForTwo)
				|| validarChefeComissariaPiloto(aviao)) {
			mensagem = "Nenhuma Comissária pode ficar sozinha com o Piloto.";
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validarChefeComissariaPiloto(List<Tripulante> lista) {
		if (lista.size() == 2 && (hasCargoEspecifico(lista, Cargos.PILOTO) && hasCargoEspecifico(lista, Cargos.COMISSARIA))) {
			return true;
		}
		return false;
	}

	public boolean validarPolicialPresidiarioTripulante() {
		if (validarPolicialPresidiarioTripulante(terminal) || validarPolicialPresidiarioTripulante(smartForTwo)
				|| validarPolicialPresidiarioTripulante(aviao)) {
			return true;
		} else {
			mensagem = "O Presidiário não pode ficar com os outros tripulantes sem a presença do Polical.";
			return false;
		}
	}
	
	public boolean validarPolicialPresidiarioTripulante(List<Tripulante> lista) {
		if(hasCargoEspecifico(lista, Cargos.POLICIAL) && hasCargoEspecifico(lista, Cargos.PRESIDIARIO) || lista.size() == 1 && hasCargoEspecifico(lista, Cargos.PRESIDIARIO)) {
			return true;
		}
		return false;
	}
	
	public boolean hasCargoEspecifico(List<Tripulante> lista, Cargos cargos) {
		for (Tripulante t : lista) {
			if (cargos.equals(t.getCargo())) {
				return true;
			}
		}
		return false;
	}

	public boolean validarCapacidadeSmartForTwo() {
		if (smartForTwo.size() < 2) {
			return true;
		} else {
			mensagem = "A capacidade do Smart ForTwo são de dois tripulantes.";
			return false;
		}
	}

	public void andarSmartForTwo() {
		boolean statusInicial = smartForTwoIsOnTerminal;
		if (validarMotorista()) {
			smartForTwoIsOnTerminal = !smartForTwoIsOnTerminal;
			if(!smartForTwoIsOnTerminal) {
				mensagem = "SmartForTwo chegou ao Avião";
			} else {
				mensagem = "SmartForTwo voltou ao Terminal";
			}
			
		} else {
			mensagem = "Nenhuma pessoa autorizada para dirigir o veículo.";
		}

		if (!validarChefeOficialPiloto()) {
			smartForTwoIsOnTerminal = statusInicial;
		}
		if (!validarChefeComissariaPiloto()) {
			smartForTwoIsOnTerminal = statusInicial;
		}
		if (!validarPolicialPresidiarioTripulante()) {
			smartForTwoIsOnTerminal = statusInicial;
		}
	}

	public List<Tripulante> getTerminal() {
		return terminal;
	}

	public List<Tripulante> getSmartForTwo() {
		return smartForTwo;
	}

	public List<Tripulante> getAviao() {
		return aviao;
	}

	public Boolean getSmartForTwoIsOnTerminal() {
		return smartForTwoIsOnTerminal;
	}

	public String getMensagem() {
		return mensagem;
	}

}
