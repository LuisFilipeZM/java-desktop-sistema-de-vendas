package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class PessoaVO implements Serializable {

	private static final long serialVersionUID = 390695892933900176L;
	
	//
	private BigInteger id;
	
	//CPF CNPJ - 12 CARACTERE 
	private String cpfcnp;
	
	//TIPO DE PESSOA - 1 CARACTERE (F-FISICA / J-JURIDICA)
	private String tippes;
	
	//NOME- 100 CARACTERE
	private String descri;
	
	//CEP
	private Integer cepend;
	
	//NOME DA RUA - 80 CARACTERES
	private String ruaend;
	
	//BAIRRO - 30 CARACTERES
	private String baiend;
	
	//COMPLEMENTO DO ENDEREÇO - 80 CARACTERES
	private String comend;
	
	//NUMERO DO ENDEREÇO - 20 CARACTERES
	private String numend;
	
	//CIDADE
	private String cidade;
	
	//ESTADO
	private String estado;
	
	//cliente - chave estrangeira
	private ClienteVO clienteVO;


	public PessoaVO() {
		super();
	}

	public PessoaVO(BigInteger id) {
		super();
		this.id = id;
	}

	public PessoaVO(BigInteger id, String cpfcnp, String tippes, String descri, Integer cepend, String ruaend,
			String baiend, String comend, String numend, String cidade, String estado) {
		super();
		this.id = id;
		this.cpfcnp = cpfcnp;
		this.tippes = tippes;
		this.descri = descri;
		this.cepend = cepend;
		this.ruaend = ruaend;
		this.baiend = baiend;
		this.comend = comend;
		this.numend = numend;
		this.cidade = cidade;
		this.estado = estado;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getCpfcnp() {
		return cpfcnp;
	}

	public void setCpfcnp(String cpfcnp) {
		this.cpfcnp = cpfcnp;
	}

	public String getTippes() {
		return tippes;
	}

	public void setTippes(String tippes) {
		this.tippes = tippes;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
	}

	public Integer getCepend() {
		return cepend;
	}

	public void setCepend(Integer cepend) {
		this.cepend = cepend;
	}

	public String getRuaend() {
		return ruaend;
	}

	public void setRuaend(String ruaend) {
		this.ruaend = ruaend;
	}

	public String getBaiend() {
		return baiend;
	}

	public void setBaiend(String baiend) {
		this.baiend = baiend;
	}

	public String getComend() {
		return comend;
	}

	public void setComend(String comend) {
		this.comend = comend;
	}

	public String getNumend() {
		return numend;
	}

	public void setNumend(String numend) {
		this.numend = numend;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaVO other = (PessoaVO) obj;
		return Objects.equals(id, other.id);
	}
}
