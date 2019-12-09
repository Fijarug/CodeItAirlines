package enums;

public enum Cargos {

	PILOTO("Piloto"),
    OFICIAL("Oficial"),
    CHEFESERVICO("Chefe de Servi�o"),
    COMISSARIA("Comiss�ria"),
    POLICIAL("Policial"),
    PRESIDIARIO("Presidi�rio");
 
    private String descricao;
 
    Cargos(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
	
}
