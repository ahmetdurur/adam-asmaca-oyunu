package players;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Kelime.HarfAdet;
import Kelime.Kelime;
import players.interfaces.Human;
import players.interfaces.Player;
import players.interfaces.Soran;

/*
 *  @author Ahmet Durur
 */

public class SoranHuman implements Human , Soran {
	private Integer harfAdet ;
	private String name ;
	private Kelime kelime;
	

	public SoranHuman () {
		setName();
	}
	
	/*	kelimeSec metodu Soran Interface'inden override edildi. Kullanýcýdan sözlükte bulunan bir kelime girmesini bekler
	 *  eðer kullanýcýnýn girdiði kelime sözlükte bulunuyorsa seçilen kelimeyle yeni bir Kelime nesnesi oluþturup döndürür
	 *  eðer sözlükte bulunmuyorsa doðru girdi alýnana kadar tekrar eder.
	 */
	
	
	/* @Deprecated kelimeSec metodu kullanýcýnýn seçtiði kelime sözlükte varmý yokmu kontrol etmesi amacýyla yazýlmýþtýr
	 * geliþtirme aþamasýnda geliþtirici Ahmet Durur tarafýndan kullanýlan bu metot oyunu oynayan yarýþmacýnýn aklýnda hile þüphesi olmamasý için 
	 * kullanýmdan kaldýrýlmýþtýr.
	 * 
	 * Eðer bu metodu test amaçlý kullanmak isterseniz OyunModuA classýnýn init metodu içine kelimeInit() metodundan önce
	 * soranHuman.kelimeSec(); satýrýný ekleyiniz
	 */
	@Override // Soran'dan override edildi
	public Kelime kelimeSec() {
		System.out.println("Seçtiðiniz Kelimeyi Yazým Hatasý Yapmadan Dikkatlice Giriniz. Merak etmeyin hile yapmýyoruz yalnýzca seçtiðiniz kelimenin sözlükte bulunup"
						 + " bulunmadýðýndan emin olmak istiyoruz");
		Scanner sc = new Scanner(System.in);
		List<String> sozluk = new ArrayList<>();
		while(true) {
			System.out.println("Lütfen sözlüðümüzde bulunan ve rakibinizin seçtiði uzunlukta bir kelime seçiniz : ( AI'ýn seçtiði uzunluk :  " + harfAdet + " )");
			String s = sc.next();
			try {
				sozluk = Player.kelimeleriAl();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(sozluk.contains(s) && s.length() == harfAdet) {
				System.out.println("Geçerli bir kelime seçtiniz");
				kelime = new Kelime(s);
				return kelime ;
			} else {
				if (s.length() != harfAdet ) {
					System.out.println("Lütfen " + harfAdet + " harfli bir kelime seçin");
				} else {
					System.out.println("Lütfen kelimeyi hatasýz bir þekilde girin ");
				}
			}
		}
	}
	
	// Kullanýcý ismini alýr
	@Override // Humandan override edildi
	public void setName() {
		this.name = Human.name();
	}
	
	// kUllanýcý ismini döndürür
	@Override // Humandan override edildi
	public String getName() {
		return this.name;
	}

	// harfAdet alanýný döndürür
	public Integer getHarfAdet() {
		return harfAdet;
	}
	
	// harfAdet alanýný set eder
	public void setHarfAdet(Integer harfAdet) {
		this.harfAdet = harfAdet;
	}
	
	// Soran'dan override edildi 
	@Override
	public List<Integer> harfKontrol(Character harf) {
		return null;
		// TODO Auto-generated method stub
		
	}

	

}
