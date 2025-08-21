package sistema.modelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lancamento {
    protected LocalDateTime datahora;
    private Double valor;
    private String tipo;
    
    public Lancamento(Double valor, String tipo) {
        this.datahora = LocalDateTime.now(); 
        this.valor = valor;
        this.tipo = tipo;
    }
    
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Lançamento: Data/Hora=" + datahora.format(formatter) +
               ", Valor=" + valor +
               ", Tipo=" + tipo;
    }

	public LocalDateTime getDatahora() {
		return this.datahora;
	}

	public String getTipo() {
		return this.tipo;
	}

	public Double getValor() {
		return this.valor;
	}
}
