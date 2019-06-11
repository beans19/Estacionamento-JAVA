package br.senai.sp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.senai.sp.model.Cliente;

public class Data {

	// MÉTODO PARA CONVERTER DATAS PARA O FORMATO DO BANCO
	public static String converterParaBanco(String data) {

		// 28/03/2019

		String dataBanco;

		String dia = data.substring(0, 2);
		String mes = data.substring(3, 5);
		String ano = data.substring(6, 10);
		String hora = data.substring(11, 13);
		String minuto = data.substring(14, 16);
		String segundo = data.substring(16, 19);

		dataBanco = ano + "-" + mes + "-" + dia + " " + hora + ":" + minuto + "" + segundo;
		return dataBanco;
	}

	// MÉTODO PARA CONVERTER DATAS PARA O FORMATO NORMAL (USUÁRIO)
	public static String converterParaTela(String data) {

		// 1999-01-26

		String dataTela;

		String dia = data.substring(8, 10);
		String mes = data.substring(5, 7);
		String ano = data.substring(0, 4);
		String hora = data.substring(11, 13);
		String minuto = data.substring(14, 16);
		String segundo = data.substring(16, 19);

		dataTela = dia + '/' + mes + '/' + ano + " " + hora + ":" + minuto + "" + segundo;

		return dataTela;
	}

	// MÉTODO PARA MOSTRAR A HORA ATUAL
	public static String horaAtual() {

		Date hoje = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = df.format(hoje);

		return dataFormatada;
	}

	// MÉTODO PARA CALCULAR A QUANTIDADE DE HORAS QUE O CARRO FICOU ESTACIONADO
	// LEVANDO EM CONTA A TOLERÂNCIA DE 5MIN
	public static int quantidadeHoras(String dataEntrada, String dataSaida) {

		// Criar um formatador de datas
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		// Transformar a data de entrada em uma data
		Date dataEntradaDate = null;
		Date dataSaidaDate = null;
		try {
			dataEntradaDate = df.parse(dataEntrada);
			dataSaidaDate = df.parse(dataSaida);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Obter a diferença entre a data de hoje e a data de entrada em milisegundos
		long milissegundos = dataSaidaDate.getTime() - dataEntradaDate.getTime();

		// Obter a quantidade de horas/minutos dos milissegundos com a seguinte
		// operação:
		double hora = milissegundos / 1000 / 60 / 60;
		double minuto = milissegundos / 1000 / 60;

		int tolerancia = 5;

		if (minuto > tolerancia) {
			hora += 1.0;
		} else if (minuto > hora * 60) {
			if ((minuto - hora * 60) > tolerancia) {
				hora += 1;
			}
		}

		return (int) hora;
	}
}