package players;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import kontrol.CharacterControl;
import kontrol.NumberKontrol;
import kontrol.StringControl;
import kontrol.YanlisIndexControl;
import players.enums.EnumCompPlayers;
import players.interfaces.AI;
import players.interfaces.Cevaplayan;
import players.interfaces.Player;
import yapayZeka.KarakterAgirliklariHesapla;
import yapayZeka.KelimeAltKume;
import yapayZeka.ListedekiTumKarakterler;

/*	@author Ahmet Durur
 * 	AI ve Cevaplayan interfacelerini implemente eder
 *  Kelime Tutan Kullan�c�n�n (SoranHuman) tuttu�u kelimeyi bulmaya calisan AI tipi playerd�r
 *  OyunModuA oyun motorunda islem g�r�r */	

public class CevaplayanAI implements AI , Cevaplayan {

	private Integer harfAdet ;
	private EnumCompPlayers enumCompPlayers;
	private Map<Character, List<Integer>> acilanKarakterler = new HashMap<>();
	private List<Character> gosterilenKelime ;
	
	
	// aIPlayerSec metodunu �a��rarak AI tipini se�er 
	public CevaplayanAI() {
		aIPlayerSec();
	}
	
	/*  Player interface'inin static metodundan sozlukteki en uzun kelimenin uzunlugu d�nd�r�l�r ve max'a atan�r
	 * 	sonras�nda 1 ve max aras�nda random bir say� �retir kullan�c�ya bu random say� uzunlugunda bir kelime se�mesini s�yler ve say�y� d�nd�r�r */
	@Override //Cevaplayandan override edildi
	public Integer harfAdediBelirle() throws IOException {
		Random r = new Random();
		this.harfAdet = r.nextInt(Player.enUzunKelime()) + 1 ; 
		//System.out.println(enumCompPlayers.name() + " Harf say�s�n� belirledi : " + harfAdet + " L�tfen s�zl�kten " + harfAdet +  " harfli bir kelime se�in"  );
		System.out.println("harf say�s�n� belirliyorum : " + harfAdet);
		return this.harfAdet ; 
	}
	
	
	/* AI interface'inin AIsec metodundan d�nen EnumCompPlayers tipindeki enumu enumCompPlayers field'�na atar */
	@Override // AI'dan override edildi
	public void aIPlayerSec() {
		this.enumCompPlayers = AI.AIsec();
	}
	
	// metodu invoke eden nesneye enumCompPlayers enum'unu d�nd�r�r
	@Override // AI'dan override edildi
	public EnumCompPlayers getAIPlayer() {
		return enumCompPlayers;
	}
	
	/*	ikinci parametre ile ald�g� character listesini KelimeAltKume s�n�f�n�n static kelimeAltKumeGetir metoduna yollar
	 * bu metod a��lan kelimenin e�le�en harflerine g�re bir alt liste d�nd�r�r �rne�in  a��lan kelime  ( a _ _ m ) d�nen �rnek liste { abam , abem , abim .... }
	 * d�nen bu liste ListedekiTumKarakterler class�n�n uniqueleriBul static metoduna yollan�r bu metod verilen String listesindeki t�m karakterlerin bulundu�u bir Character listesi d�nd�r�r
	 * bu d�nen character listesinden cachelenenHarfler parametresinde yollanan liste ��kar�l�r ��nk� bu listedeki harfleri daha �nceden tahmin ettik
	 * daha sonra KarakterAgirliklariHesapla class�n�n  enYuksekPuanliCharacter metoduna KarakterAgirliklariHesapla class�n�n  agirlikHesapla metodundan d�nen karakterPuanMap'i g�nderilir
	 * NOT: agirlikHesapla metodu listedeki t�m karakterlerin a��rl���n� hesaplay�p key olarak karakter value olarak karakterin puan�n� tutan bir map d�nd�r�r
	 * NOT: enYuksekPuanliCharacter metodu map'teki puan� en y�ksek olan karakteri d�nd�r�r  	*/
	@Override //Cevaplayandan override edildi
	public Character harfSec(List<Character> cachelenenHarfler, List<Character> gosterilenKelime2) throws IOException {
	    List<Character> l = ListedekiTumKarakterler.uniqueleriBul(KelimeAltKume.kelimeAltKumeGetir(gosterilenKelime2));
	    l.removeAll(cachelenenHarfler);
	    Character c  = KarakterAgirliklariHesapla.enYuksekPuanliCharacter(KarakterAgirliklariHesapla.agirlikHesapla(l, Player.kelimeleriAl(harfAdet)));
		return c;
	}
	
	
	/* metod harf tahmini ard�ndan kullan�c�ya bildimmi sorusunu sorar ve evet hay�r cevab� bekler
	 * e veya h karakteri girilmedigi s�rece uyar� mesaj� verir ve tekrardan giris bekler
	 * e�er h cevab� al�rsa tahminHakk�n� �nce azalt�p sonra konsola yazd�r�r
	 * e�er e cevab� al�rsa kacHarfBildim metodunu �a��r�r ve tahminHakk�'n� d�nd�r�r 	*/
	public Integer bildimmi(Character c, Integer tahminHakk�, List<Character> gosterilenKelime) {
		this.gosterilenKelime = gosterilenKelime;
		System.out.print("\nBildim mi? (e/h) : ");
		Scanner sc = new Scanner(System.in);
		Character eh = sc.next().charAt(0); 
		while(eh!= 'e' && eh!='h' ) {
			System.out.println("L�tfen ge�erli bir se�in yap�n. evet (e) hay�r (h)");
			eh = sc.next().charAt(0); 
		}
		
		if( eh == 'e' ) {
			kacHarfBildim(c);
		} else if(eh == 'h') {
			System.out.println(--tahminHakk� + " hakk�m kald�.");
		}
		return tahminHakk�;
	}
	
	/* ilk while dongusunde eger kullan�c� taraf�ndan girilen de�erde uygunsuzluk yoksa kacHarfBildiminin cevab�n� harfAdet de�i�kenine al�r
	 * bu d�ng�de girilen karakter numerikmi numerikse kelime uzunlugundan k���km� kelime uzunlugundan k�c�kse kalan acilmamis harften k�c�km� �nput kontrolleri yap�l�r
	 * sonra for d�ng�s� harf adet kadar d�ner �rnek kullan�c� 2 cevab�n� verdi s�ras�yla 1. harfin s�ras� ve 2. harfin s�ras� �eklinde girdi al�r 
	 * for i�erisindeki while'da yine input kontroller yap�l�r  eger uygun karakterler girildiyse indisList (Integer) listesi doldurulur
	 * for'un d���nda acilanKarakterler map'ine key olarak karakter ve value olarak indisList eklenir 	 */
	public void kacHarfBildim(Character c) {
		Scanner sc = new Scanner(System.in);
		String kacHarfBildim = "\nKa� harf bildim : ";
		System.out.println(kacHarfBildim);
		String mesajStringKontrol = "L�tfen ge�erli cevap veriniz";
		String numericRangeKontrol = "Ge�erli cevap 1 ve " + this.harfAdet + " aras�nda bir say� olmal�d�r" ;
		String characterKontrol = "L�tfen rakam giriniz\nKa� harf bildim : " ;
		String yanlisIndex = "S�ylediginiz s�ray� daha �nce s�ylediniz ";
		Character harfAdet = sc.next().charAt(0);
		while(!CharacterControl.numericKontrol(harfAdet, characterKontrol ) || 
				!NumberKontrol.numericRangeKontrol(1, this.harfAdet, Integer.parseInt(harfAdet.toString()), numericRangeKontrol )
				|| !NumberKontrol.kalanAcilmamisHarfAdet(gosterilenKelime , Integer.parseInt(harfAdet.toString() ))
			 )
		{
			harfAdet = sc.next().charAt(0);
		}
		List<Integer> indisList = new ArrayList<>(); 
		for ( int i = 1 ; i <= Integer.parseInt(harfAdet.toString()) ; i++) {
			System.out.println( i + ". harfin s�ras� : ");
			String s = sc.next();

			numericRangeKontrol = "Ge�erli cevap 1 ve " + this.harfAdet + " aras�nda bir say� olmal�d�r" ;
			while(!StringControl.tumKarakterlerNumerikmiKontrol(s, mesajStringKontrol) || 
					!NumberKontrol.numericRangeKontrol(1, this.harfAdet, Integer.parseInt(s), numericRangeKontrol ) 
					|| !YanlisIndexControl.indexKontrol(gosterilenKelime, Integer.parseInt(s), yanlisIndex)
					|| !YanlisIndexControl.tekrarlananIndex(indisList , Integer.parseInt(s) , yanlisIndex )
				 ) 
			{	
				System.out.println( i + ". harfin s�ras� : ");
				s = sc.next();
			}	
			
			indisList.add(Integer.parseInt(s)-1);
		}
		acilanKarakterler.put(c, indisList);
	}
	
	// kacHarfBildim metodunda guncellenen acilananKarakterler map'ini d�nd�r�r
	public Map<Character, List<Integer>> getAcilanKarakterler() {
		return acilanKarakterler;
	}
	
	
	
	
}
