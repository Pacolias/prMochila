/**
 * 
 * @author ***** Indicar aqui el autor de la practica *******
 *
 */
public class MochilaAV extends Mochila {

	public SolucionMochila resolver(ProblemaMochila pm) {
		SolucionMochila sm;
		
		double[] densidades = new double[pm.size()];
		
		for(int i = 0; i < densidades.length; i++)
			densidades[i] = (double) pm.getValor(i) / (double) pm.getPeso(i);

		int mayor;
		int[] indices = new int[pm.size()];
		
		for(int i = 0; i < densidades.length; i++) {
			mayor = indiceMayor(densidades);
			densidades[mayor] = -1;
			indices[i] = mayor;
		}

		int[] sol = new int[pm.size()];

		for(int i = 0; i < densidades.length; i++) {
			for(int j = 1; j <= pm.getUnidad(indices[i]); j++) {
				sol[indices[i]] = j;
				
				if(pm.sumaPesos(sol) > pm.getPesoMaximo())
					sol[indices[i]]--;
			}
			
			i++;
		}
		
		sm = new SolucionMochila(sol, pm.sumaPesos(sol), pm.sumaValores(sol));
		
		return sm;
	}
	
	private static int indiceMayor(double[] array) {
		int indice = 0;

		double mayor = array[0];
		for(int i = 0; i < array.length; i++) {
			if(array[i] > mayor) {
				mayor = array[i];
				indice = i;
			}
		}
		
		return indice;
	}
}
