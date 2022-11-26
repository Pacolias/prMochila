/**
 *
 * @author ***** Indicar aqui el autor de la practica *******
 *
 */
public class MochilaAV extends Mochila {

	public SolucionMochila resolver(ProblemaMochila pm) {
		SolucionMochila sm;

		double[] densidad = new double[pm.size()];

		for(int i = 0; i < densidad.length; i++) {
			densidad[i] = (double) pm.getValor(i) / (double) pm.getPeso(i);
		}

		int mayor;
		int[] indices = new int[pm.size()];

		for(int i = 0; i < densidad.length; i++) {
			mayor = indiceMayor(densidad);
			densidad[mayor] = -1;
			indices[i] = mayor;
		}

		boolean hayHuecoLocal = true;
		boolean hayHuecoGlobal = true;
		int i = 0, j = 1;
		int[] sol = new int[pm.size()];

		while(hayHuecoGlobal && i < densidad.length) {
			j = 1;
			hayHuecoLocal = true;

			while(hayHuecoLocal && j <= pm.getUnidad(indices[i])) {
				sol[indices[i]] = j;

				if(pm.sumaPesos(sol) > pm.getPesoMaximo()) {
					sol[indices[i]]--;
					hayHuecoLocal = !hayHuecoLocal;
				}

				j++;
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