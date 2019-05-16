package pacote1;



public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				AlgoritmoGenetico ga = new AlgoritmoGenetico();
		
		ga.setCHROMOSOME_SIZE(10);
		ga.setMAX_GENERATIONS(10);
		ga.setPOPULATION_SIZE(10);
		ga.setCROSSOVER_RATE(0.90f);
		ga.setMUTATION_RATE(0.01f);
		
		
		
		
		ga.Resolve();
		
	}
	
		
		
}


