package enums;

public enum Cargos {

	PILOTO("Piloto"),
    OFICIAL("Oficial"),
    CHEFESERVICO("Chefe de Serviço"),
    COMISSARIA("Comissária"),
    POLICIAL("Policial"),
    PRESIDIARIO("Presidiário");
 
    private String descricao;
 
    Cargos(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
	
}
