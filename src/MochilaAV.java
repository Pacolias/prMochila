/**
 *
 * @author ***** Indicar aqui el autor de la practica *******
 *
 */
public class MochilaAV extends Mochila {

	public SolucionMochila resolver(ProblemaMochila pm) {
		double[] densidades = new double[pm.size()];
		for(int i = 0; i < densidades.length; i++)
			densidades[i] = (double) pm.getValor(i) / (double) pm.getPeso(i);

		int[] indices = new int[pm.size()];
		for(int i = 0; i < densidades.length; i++) {
			int indiceMayor = indiceMayor(densidades);
			densidades[indiceMayor] = -1;
			indices[i] = indiceMayor;
		}

		int[] solucion = new int[pm.size()];
		for(int objetos = 0; objetos < densidades.length; objetos++) {
			boolean hayHuecoLocal = true;

			for (int peso = 1; hayHuecoLocal && peso <= pm.getUnidad(indices[objetos]); peso++) {
				solucion[indices[objetos]] = peso;

				if (pm.sumaPesos(solucion) > pm.getPesoMaximo()) {
					solucion[indices[objetos]]--;
					hayHuecoLocal = false;
				}
			}
		}

		int sumaPesos = pm.sumaPesos(solucion);
		int sumaValores = pm.sumaValores(solucion);

		return new SolucionMochila(solucion, sumaPesos, sumaValores);
	}

	private static int indiceMayor(double[] array) {
		int indiceMayor = 0;

		double mayorValor = array[0];
		for(int i = 0; i < array.length; i++)
			if(array[i] > mayorValor) {
				mayorValor = array[i];
				indiceMayor = i;
			}

		return indiceMayor;
	}
}