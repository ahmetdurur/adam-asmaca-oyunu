package Kelime;

public class Kelime {
	
	private String kelime ;
	private Integer harfSayac;
	
	public Kelime(String kelime) {
		this.kelime = kelime ;
		harfSayac = kelime.length();
	}
	
	public String getKelime() {
		return kelime;
	}
	
	public void setKelime(String kelime) {
		this.kelime = kelime;
	}
	
	public Integer getHarfSayac() {
		return harfSayac;
	}
}
