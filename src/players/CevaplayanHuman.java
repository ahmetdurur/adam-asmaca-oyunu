package players;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Kelime.HarfAdet;
import kontrol.NumberKontrol;
import kontrol.StringControl;
import players.interfaces.Cevaplayan;
import players.interfaces.Human;
import players.interfaces.Player;

/*
 *  @author Ahmet Durur
 */

public class CevaplayanHuman implements Human , Cevaplayan {

	private Integer harfAdet ;
	private String name ; 
	
	public CevaplayanHuman() { 
		setName();	
	}
	
	/*	Klavyeden kullanýcýdan tahmin edeceði kelime boyutunu alýr en az 1 en fazla max aralýðýnda (geçerli bir) seçim yapýlana kadar tekrar eder
	 *  yapýlan girdi StringControl class'ýnýn tumKarakterlerNumerikmiKontrol metoduna gonderilip kontrol edilir uygunsa girdi dondurulur degilse
	 *  gecerli giris yapýlana kadar tekrar eder 
	 *  NOT: max sozlukteki en uzun kelimenin harfsayýsýdýr ve Player interface'inin enUzunKelime metodundan getirilir 	*/
	@Override //Cevaplayandan override edildi
	public Integer harfAdediBelirle() throws IOException {
		Integer max = Player.enUzunKelime();
		Scanner sc = new Scanner(System.in);
		System.out.println("Harf sayýsýný giriniz (En fazla "+max+" girilebilir) : ");
		String a = sc.next();
		while(!StringControl.tumKarakterlerNumerikmiKontrol(a, "Lütfen Geçerli Giriþ Yapýn")  || !NumberKontrol.numericRangeKontrol(1, max, Integer.parseInt(a), "1 ve "+max+" arasýnda giriniz") ) {
			System.out.println("Harf sayýsýný giriniz (En fazla "+max+" girilebilir) : ");
			a = sc.next();
		}
		return Integer.parseInt(a);
	}
	
	/*
	 * Kullanýcýnýn ismini girmesini ister 
	 */
	@Override // Humandan override edildi 
	public void setName() {
		this.name = Human.name();
	}

	/*
	 *  Kullanýcýdan alýnan ismi baþlangýç anonsunda kullanýr
	 */
	@Override // Humandan override edildi 
	public String getName() {
		return this.name;
	}
	
	/*
	 *  OyunModuB'de kullanýlmak üzere kullanýcýnýn harf tahminini alýr  ve invoke eden objeye döndürür
	 */
	@Override //Cevaplayandan override edildi
	public Character harfSec(List<Character> cachelenenHarfler,List<Character> gosterilenKelime2) {
		System.out.print("\nHarf tahmin edin : ");
		Scanner sc = new Scanner(System.in);
		Character c  = sc.next().charAt(0);
		return c;
	}
	

}
