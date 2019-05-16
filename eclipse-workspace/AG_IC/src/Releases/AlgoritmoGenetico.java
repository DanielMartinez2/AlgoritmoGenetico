package pacote1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;






public class AlgoritmoGenetico {
	private Random r = new Random();
	private int CHROMOSOME_SIZE;
	private int MAX_GENERATIONS;
	private int POPULATION_SIZE;
	private float CROSSOVER_RATE;
	private float MUTATION_RATE;
	
	
	public int getCHROMOSOME_SIZE() {
		return CHROMOSOME_SIZE;
	}

	public void setCHROMOSOME_SIZE(int cHROMOSOME_SIZE) {
		CHROMOSOME_SIZE = cHROMOSOME_SIZE;
	}

	public int getMAX_GENERATIONS() {
		return MAX_GENERATIONS;
	}

	public void setMAX_GENERATIONS(int mAX_GENERATIONS) {
		MAX_GENERATIONS = mAX_GENERATIONS;
	}

	
	public int getPOPULATION_SIZE() {
		return POPULATION_SIZE;
	}

	public void setPOPULATION_SIZE(int pOPULATION_SIZE) {
		POPULATION_SIZE = pOPULATION_SIZE;
	}

	public float getCROSSOVER_RATE() {
		return CROSSOVER_RATE;
	}

	public void setCROSSOVER_RATE(float cROSSOVER_RATE) {
		CROSSOVER_RATE = cROSSOVER_RATE;
	}
	public float getMUTATION_RATE() {
		return MUTATION_RATE;
	}

	public void setMUTATION_RATE(float mUTATION_RATE) {
		MUTATION_RATE = mUTATION_RATE;
	}
	
	public ArrayList<Solução> population = new ArrayList<>();
	public ArrayList<Solução> offspring = new ArrayList<>();
	
	public AlgoritmoGenetico() {
		
		// GA is initialized whit these parameters by default 
		CHROMOSOME_SIZE 	= 10;
		MAX_GENERATIONS 	= 30;
		POPULATION_SIZE 	= 30;
		CROSSOVER_RATE 		= 0.90f;
		MUTATION_RATE 		= 0.01f;
		
				
	}
	
	//Inicia a população 
		public void inicializaPop() {

			for (int i = 0; i < POPULATION_SIZE; i++) {
				int[] individual = new int[CHROMOSOME_SIZE];
				for (int j = 0; j < individual.length; j++) {
					individual[j] = r.nextInt(4);
					population.add(new Solução(individual));
				}
				Solução novo = new Solução(individual);
				novo.Reparar();
	            population.add(novo);
			}
			
		}
		public Solução Resolve() {
			this.inicializaPop();
			
			Collections.sort(this.population);
			
			int generation = 0;
			while (generation < this.MAX_GENERATIONS) {
				
				this.performeCrossover();
				
				this.mutation();
				
				this.mergePopulationsByRank();
					
				
				Collections.sort(this.population);

				System.out.println(this.avgGenerationFitness() + "\t" + this.population.get(0).fitness);

				generation++;

			}
			return this.population.get(0);
		}
			public int torneioBinario(){
				Random r = new Random();
				int[] vetor = new int[2];
		        for(int i=0;i<2;i++){
		            vetor[i] = r.nextInt(CHROMOSOME_SIZE);
		        }
		        
		        if(population.get(vetor[0]).getFitness()>population.get(vetor[1]).getFitness()){
		            return vetor[0];
		        }else{
		            return vetor[1];
		        }
		        
		    }

			public void performeCrossover() {

					while (this.offspring.size() < POPULATION_SIZE){
				            int father_a;
				            int father_b;
				            father_a = torneioBinario();
				            father_b = torneioBinario();
				            
				            if(r.nextDouble() < CROSSOVER_RATE){
				                ArrayList<Solução> sons = this.cortaCorta(population.get(father_a),population.get(father_b));
				                sons.get(0).Reparar();   
				                sons.get(1).Reparar();

				                this.offspring.add(sons.get(0));
				                this.offspring.add(sons.get(1));
				            }else{
				                this.offspring.add(new Solução(population.get(father_a).chromosome));
				                this.offspring.add(new Solução(population.get(father_b).chromosome));
				            }
				        
				        
				    }
			}
			public ArrayList<Solução> cortaCorta(Solução parent_a, Solução parent_b) {

				ArrayList<Solução> sons = new ArrayList<>();

				int cut_point = r.nextInt(CHROMOSOME_SIZE - 2) + 1;
				int[] son_a = new int[CHROMOSOME_SIZE];
				int[] son_b = new int[CHROMOSOME_SIZE];

				for (int i = 0; i < CHROMOSOME_SIZE; i++) {

					if (i <= cut_point) {
						son_a[i] = parent_a.chromosome[i];
						son_b[i] = parent_b.chromosome[i];
					} else {
						son_a[i] = parent_b.chromosome[i];
						son_b[i] = parent_a.chromosome[i];
					}

				}
								
				sons.add(new Solução(son_a));
				sons.add(new Solução(son_b));

				return sons;
			}
			public void mutation() {
				for (Solução solution : offspring) {
					for (int i = 0; i < solution.chromosome.length; i++) {
						if (r.nextFloat() <= MUTATION_RATE) {

							solution.chromosome[i] = r.nextInt(4);
							
						
						}
						solution.Reparar();
					}
					
				}
	
	 
			}
			private void mergePopulationsByRank() {

				for (Solução solution : offspring)
					this.population.add(new Solução(solution.chromosome));

				Collections.sort(this.population);

				for (int i = this.population.size() - 1; i >= this.POPULATION_SIZE; i--)
					this.population.remove(i);

				this.offspring.clear();

			}
			public float avgGenerationFitness() {
				float avg = 0;
				for (Solução solution : population)
					avg += solution.fitness;
				return avg / population.size();
			}

		
}