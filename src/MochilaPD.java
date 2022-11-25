/**
 * 
 * @author ***** Jose A. Onieva *******
 *
 */
public class MochilaPD extends Mochila {

	public SolucionMochila resolver(ProblemaMochila pm) {
		SolucionMochila sm = null;

		int tabla[][] = new int[pm.size() + 1][pm.getPesoMaximo() + 1];

		for (int i = 0; i < tabla.length; i++) {
			for (int j = 0; j < tabla[i].length; j++) {
				tabla[i][j] = -2;
			}
		}

		int solucion[][] = new int[pm.size() + 1][pm.getPesoMaximo() + 1];

		for (int i = 0; i < solucion.length; i++) {
			for (int j = 0; j < solucion[i].length; j++) {
				solucion[i][j] = 0;
			}
		}

		valorMaximo(pm, tabla, pm.size(), pm.getPesoMaximo(), solucion);


		//System.out.println(ArrayUtils.toString(tabla));
		System.out.println("--------------------------------------------");
		//System.out.println(ArrayUtils.toString(solucion));


		int[] solucionArray = new int[pm.size()];

		for (int i = 0; i < pm.size(); i++) {
			solucionArray[i] = solucion[i + 1][pm.getPesoMaximo()];
		}

		sm = new SolucionMochila(solucionArray, pm.sumaPesos(solucionArray), pm.sumaValores(solucionArray));

		return sm;
		
	}

	public static int valorMaximo(ProblemaMochila pm, int[][] tabla, int i, int j, int[][] solucion) {

		if ((i >= 0) && (j >= 0) && tabla[i][j] == -2) {
			if (i == 0 || j == 0) {
				tabla[i][j] = 0;
			} else if (j < pm.getPeso(i - 1)) {
				tabla[i][j] = valorMaximo(pm, tabla, i - 1, j, solucion);
			} else {

				int a = valorMaximo(pm, tabla, i - 1, j, solucion);
				int b = 0;
				int unidades = 0;
				int valor;

				for (int k = 1; k <= pm.getUnidad(i - 1); k++) {

					if(k == pm.getUnidad(i - 1))
						valor = valorMaximo(pm, tabla, i - 1, j - k * pm.getPeso(i - 1), solucion) + k * pm.getValor(i - 1);
					else
						valor = valorMaximo(pm, tabla, i, j - k * pm.getPeso(i - 1), solucion) + k * pm.getValor(i - 1);
					
					if(valor > b) {
						b = valor;
						unidades = k;
					}
				}

				if (Math.max(a, b) == a) {
					tabla[i][j] = a;
					solucion[i][j] = 0;
				} else {
					tabla[i][j] = b;
					solucion[i][j] = unidades;
				}
			}
		} else {
			return -1000;
		}

		return tabla[i][j];

	}

}
