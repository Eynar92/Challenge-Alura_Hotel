package jdbc.modelo;

import java.time.LocalDate;

public class Reserva {

	private Integer id;
	private LocalDate dateEntrada;
	private LocalDate dateSalida;
	private String valor;
	private String formaPago;

	public Reserva(LocalDate dateEntrada, LocalDate dateSalida, String valor, String formaPago) {
		this.dateEntrada = dateEntrada;
		this.dateSalida = dateSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Reserva(Integer id, LocalDate dateEntrada, LocalDate dateSalida, String valor, String formaPago) {
		super();
		this.id = id;
		this.dateEntrada = dateEntrada;
		this.dateSalida = dateSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public LocalDate getDateEntrada() {
		return dateEntrada;
	}

	public void setDateEntrada(LocalDate dateEntrada) {
		this.dateEntrada = dateEntrada;
	}

	public LocalDate getDateSalida() {
		return dateSalida;
	}

	public void setDateSalida(LocalDate dateSalida) {
		this.dateSalida = dateSalida;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
