package br.senai.sp.model;

import br.senai.sp.dao.ClienteDAO;

public class Calculos {

	// C�LCULOS QUE REALIZAM A OPERA��O DE QUANTO DEVE SER O VALOR A PAGAR
	public static double valorPagar(int tempo) {
		double primeiraHora = ClienteDAO.primeiraHora();
		double demaisHoras = ClienteDAO.demaisHoras();
		double valorPagar = 0.0;

		// SE O TEMPO ESTACIONADO FOR 1 HORA, DEVER� SER PAGO 6 REAIS. A CADA HORA
		// DEVER� SER PAGO MAIS 2 REAIS
		if (tempo == 1) {
			valorPagar = primeiraHora;
		} else if (tempo >= 2) {
			valorPagar = primeiraHora + ((tempo - 1) * demaisHoras);
		}

		return valorPagar;
	}
}
