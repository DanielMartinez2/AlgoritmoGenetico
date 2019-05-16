package pacote1;

public class Requisitos {
	int[][] tabelaReq;
	private int qtdReq;
	
	public Requisitos(){
		// no caso são 10 requisitos
		qtdReq=10;
		tabelaReq = new int[][] {{60,3,80},{40,6,76},{40,2,50},{30,6,53},{20,4,59},
            {20,8,52},{25,9,50},{70,7,59},{50,6,56},{20,6,84}};
		//custo,risco, media ponderada da importancia.
	}
	public int getqtdReq() {
        return qtdReq;
    }
    
    public Requisitos(int tabela[][]){
        qtdReq = tabela.length;
        this.tabelaReq = tabelaReq.clone();
    }

    public int[][] getTabelaReq() {
        return tabelaReq;
    }
}
   