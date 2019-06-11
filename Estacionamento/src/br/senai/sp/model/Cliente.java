package br.senai.sp.model;

public class Cliente {
	
	//ELEMENTOS
	private int codigo;
	private String placa;
	private String modelo;
	private String data_entrada;
	private String data_saida;
	private int tempo;
	private double valor_pago;
	private double valor_primeira_hora;
	private double valor_demais_horas;
	
	// GETTERS & SETTERS
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getValor_primeira_hora() {
		return valor_primeira_hora;
	}

	public void setValor_primeira_hora(double valor_primeira_hora) {
		this.valor_primeira_hora = valor_primeira_hora;
	}

	public double getValor_demais_horas() {
		return valor_demais_horas;
	}

	public void setValor_demais_horas(double valor_demais_horas) {
		this.valor_demais_horas = valor_demais_horas;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(String data_entrada) {
		this.data_entrada = data_entrada;
	}

	public String getData_saida() {
		return data_saida;
	}

	public void setData_saida(String data_saida) {
		this.data_saida = data_saida;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public double getValor_pago() {
		return valor_pago;
	}

	public void setValor_pago(double valor_pago) {
		this.valor_pago = valor_pago;
	}

}
