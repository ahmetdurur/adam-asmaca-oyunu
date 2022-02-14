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
	
	/*	Klavyeden kullan�c�dan tahmin edece�i kelime boyutunu al�r en az 1 en fazla max aral���nda (ge�erli bir) se�im yap�lana kadar tekrar eder
	 *  yap�lan girdi StringControl class'�n�n tumKarakterlerNumerikmiKontrol metoduna gonderilip kontrol edilir uygunsa girdi dondurulur degilse
	 *  gecerli giris yap�lana kadar tekrar eder 
	 *  NOT: max sozlukteki en uzun kelimenin harfsay�s�d�r ve Player interface'inin enUzunKelime metodundan getirilir 	*/
	@Override //Cevaplayandan override edildi
	public Integer harfAdediBelirle() throws IOException {
		Integer max = Player.enUzunKelime();
		Scanner sc = new Scanner(System.in);
		System.out.println("Harf say�s�n� giriniz (En fazla "+max+" girilebilir) : ");
		String a = sc.next();
		while(!StringControl.tumKarakterlerNumerikmiKontrol(a, "L�tfen Ge�erli Giri� Yap�n")  || !NumberKontrol.numericRangeKontrol(1, max, Integer.parseInt(a), "1 ve "+max+" aras�nda giriniz") ) {
			System.out.println("Harf say�s�n� giriniz (En fazla "+max+" girilebilir) : ");
			a = sc.next();
		}
		return Integer.parseInt(a);
	}
	
	/*
	 * Kullan�c�n�n ismini girmesini ister 
	 */
	@Override // Humandan override edildi 
	public void setName() {
		this.name = Human.name();
	}

	/*
	 *  Kullan�c�dan al�nan ismi ba�lang�� anonsunda kullan�r
	 */
	@Override // Humandan override edildi 
	public String getName() {
		return this.name;
	}
	
	/*
	 *  OyunModuB'de kullan�lmak �zere kullan�c�n�n harf tahminini al�r  ve invoke eden objeye d�nd�r�r
	 */
	@Override //Cevaplayandan override edildi
	public Character harfSec(List<Character> cachelenenHarfler,List<Character> gosterilenKelime2) {
		System.out.print("\nHarf tahmin edin : ");
		Scanner sc = new Scanner(System.in);
		Character c  = sc.next().charAt(0);
		return c;
	}
	

}
