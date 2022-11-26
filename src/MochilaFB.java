/**
 * 
 * @author ***** Jose A. Onieva ******* Asumimos que: a) Todos los items tienen
 *         un valor >=1 b) W>0
 */

public class MochilaFB extends Mochila {

	public SolucionMochila resolver(ProblemaMochila pm) {
		SolucionMochila sm = new SolucionMochila();
		
		int[] solucion = new int[pm.size()];

		sm.setSumaPesos(0);
		sm.setSumaValores(0);

		System.out.println(valorMaximo(pm, sm, pm.getPesoMaximo(), pm.size(), solucion, 0));
		
		//sm = new SolucionMochila(solucion, pm.sumaPesos(solucion), pm.sumaValores(solucion));
		
		return sm;
	}

	private static int valorMaximo(ProblemaMochila pm, SolucionMochila sm, int peso, int i, int[] solucion, int unidades) {
		if (i == 0 || peso == 0)
			return 0;
		if (pm.getPeso(i - 1) > peso)
			return valorMaximo(pm, sm, peso, i - 1, solucion, 0);

		int valorMaximo = valorMaximo(pm, sm, peso, i - 1, solucion, 0);
		solucion[i - 1] = 0;

		int b = 0;
		for (int k = 1; k <= pm.getUnidad(i - 1); k++) {
			solucion[i - 1] = k;

			if (pm.sumaPesos(solucion) <= peso && pm.sumaValores(solucion) > b) {
				b = pm.sumaValores(solucion);
			} else if ((pm.sumaPesos(solucion) > peso || pm.sumaValores(solucion) < sm.getSumaValores()) && solucion[i - 1] > 0) {
				solucion[i - 1]--;
			}
		}

		if (valorMaximo > b)
			solucion[i - 1] = 0;

		// Primera -> Última (x) -> Primera (?) -> valorMaximo (?)

		if (pm.sumaPesos(solucion) <= pm.getPesoMaximo() && pm.sumaValores(solucion) > sm.getSumaValores()) {
			sm.setSolucion(ArrayUtils.toArray(solucion));
			sm.setSumaPesos(pm.sumaPesos(solucion));
			sm.setSumaValores(pm.sumaValores(solucion));
		}

		int sumaValores = sm.getSumaValores();
		return sumaValores;
	}

}
