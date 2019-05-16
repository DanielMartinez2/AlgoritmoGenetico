package pacote1;

import java.util.Random;

public class Solução implements Comparable<Solução>{
	Requisitos req = new Requisitos(); 
	public int chromosome_size;
	public int[] chromosome;
	public double fitness;
	public boolean vale = false;
	
	public Solução(int[] releases) {
		this.chromosome_size = releases.length;
		this.chromosome = releases.clone();
	       
		this.fitness=0;
	    }
	public int getChromosome_size() {
		return chromosome_size;
	}
	public void setChromosome_size(int chromosome_size) {
		this.chromosome_size = chromosome_size;
	}
	public int[] getChromosome() {
		return chromosome;
	}
	public void setChromosome(int[] chromosome) {
		this.chromosome = chromosome;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	// Cálculo do score problema(fitness)
	public int calculaScore(){
		int score=0;
		for(int k=0;k<chromosome_size;k++) {
			if(chromosome[k]!=0){
				score = score + ((4- chromosome[k])*req.tabelaReq[k][2]  - chromosome[k]*req.tabelaReq[k][1]);
				
			}
		}
		return score;
	}
	// Verifica se a solução é valida, ou seja se ultrapassa o limite do custo estipulado.
	public void  vale(int[] custo) {
		if(custo[1] > 125 || custo[2] > 125 || custo[3] > 125) {
			vale = false;
		}else {
			vale = true;
		}
		
	}
	
	public void Reparar() {
		int[] custo = new int[4];
		Random r = new Random();
		int cont=0;
		int i;
		while(vale == false) {
			while(cont<4) {
				custo[cont] = 0;
				cont++;
			}
			
			switch(r.nextInt(2)) {
				case 0:
					for(i=0;i < chromosome_size;i++) {
						switch(chromosome[i]) {
							case 1:
								
								if((custo[1]+ req.tabelaReq[i][0])<125) {
									custo[1] = custo[1] + req.tabelaReq[i][0];
								}else{
									if((custo[2]<125 || custo[3] < 125)) {
										chromosome[i] = minCusto(custo,2,3);
									}else {
										chromosome[i] = 0;
									}
								}
								break;
							case 2:
								
								if((custo[2]+ req.tabelaReq[i][0])<125) {
									custo[2] = custo[2] + req.tabelaReq[i][0];
								}else {
									if((custo[1]<125 || custo[3] < 125)) {
										chromosome[i] = minCusto(custo,1,3);
									}else {
										chromosome[i] = 0;
									}
								}
								break;
							case 3:
								if((custo[3]+ req.tabelaReq[i][0])<125) {
									custo[3] = custo[3] + req.tabelaReq[i][0];
								}else {
									if((custo[1]<125 || custo[2] < 125)) {
										chromosome[i] = minCusto(custo,2,1);
									}else {
										chromosome[i] = 0;
									}
								}
								break;
							default:
								break;
						
						}
						
						
					}
				case 1:
					for(int j = (chromosome_size -1); j>=0;j--) {
						switch(chromosome[j]) {
							case 1:
								if((custo[1]+ req.tabelaReq[j][0])<125) {
									custo[1] = custo[1] + req.tabelaReq[j][0];
								}else {
									if((custo[2]<125 || custo[3] < 125)) {
										chromosome[j] = minCusto(custo,2,3);
									}else {
										chromosome[j] = 0;
									}
								}
								break;
							case 2:
								if((custo[2]+ req.tabelaReq[j][0])<125) {
								custo[2] = custo[2] + req.tabelaReq[j][0];
							}else {
								if((custo[1]<125 || custo[3] < 125)) {
									chromosome[j] = minCusto(custo,1,3);
								}else {
									chromosome[j] = 0;
								}
							}
								break;
							case 3:
								if((custo[3]+ req.tabelaReq[j][0])<125) {
									custo[3] = custo[3] + req.tabelaReq[j][0];
								}else {
									if((custo[1]<125 || custo[2] < 125)) {
										chromosome[j] = minCusto(custo,2,1);
									}else {
										chromosome[j] = 0;
									}
								}
								break;
							default:
								break;
						
						}
					}
			}
		
			vale(custo);
		}
		fitness = calculaScore();
		 
		
	}
	
	public int minCusto(int[] releases, int x, int y) {
		// verifica o menor custo.
		if(releases[x] >= releases[y]) {
			return y;
		}else {
			return x;
		}
		
	}
	
	@Override
	public int compareTo(Solução result) {
		// TODO Auto-generated method stub
		if(this.fitness > result.fitness) {
			return -1;
		}
		if(this.fitness < result.fitness) {
			return 1;
		}
		
		return 0;
	
	}
	
	
	
	
	

}
