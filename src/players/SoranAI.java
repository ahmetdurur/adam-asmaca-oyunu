package players;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import Kelime.HarfAdet;
import Kelime.Kelime;
import players.enums.EnumCompPlayers;
import players.interfaces.AI;
import players.interfaces.Player;
import players.interfaces.Soran;

/*
 * @author Ahmet Durur
 */

public class SoranAI implements AI , Soran {
	
	private EnumCompPlayers enumCompPlayers;
	private Integer harfAdet ;
	private Kelime kelime;
	
	public SoranAI() {
		aIPlayerSec();
	}
	/*	kelimeSec metodu Soran interface'inden override edildi Player interface'inin static kelimeleriAl
	 *  metodu ile turkish_dictionary.txt dosyas� sozluk ad�nda bir String listesine al�n�r. Sonras�nda sozluk listesinin buyuklugu mesafesinde
	 *  random bir say� secilerek sozlukten secilen indisteki kelime al�n�r. Eger secilen kelime kullan�c�n klavyeden 
	 *  girdigi harf uzunlugunda ise yeni bir Kelime nesnesi olusturulup d�nd�r�l�r. Degilse kullan�c�n�n belirledigi
	 *  uzunlukta bir kelime sozlukten bulunana kadar islem tekrar eder. 
	 *  
	 *  �NEML� NOT: System.out.println(enumCompPlayers.name() +  " taraf�ndan s�zl�kten se�ilen kelime: " + s); sat�r� ile AI'�n se�ti�i kelime konsola yazd�r�l�r
	 *  bu kullan�c�n�n kelimeyi g�r�p s�zl�kte olup olmad���n� test etmesi amac�yla a��k b�rak�lm��t�r. OyunModuB'de rekabet�i bir oyun oynamak i�in l�tfen
	 *  System.out.println(enumCompPlayers.name() +  " taraf�ndan s�zl�kten se�ilen kelime: " + s); sat�r�n� yorum sat�r� yap�n�z
	*/
	@Override // Soran'dan override edildi
	public Kelime kelimeSec() {
		String s = null ;
		List<String> sozluk = new ArrayList<>();
		try {
			sozluk = Player.kelimeleriAl();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		Random r = new Random();
		while (s == null) {
			String tempString = sozluk.get(r.nextInt(sozluk.size())); 
			if(tempString.length() == harfAdet) {
				s = tempString;
			}
		}
		System.out.println(enumCompPlayers.name() +  " taraf�ndan s�zl�kten se�ilen kelime: " + s);
		kelime = new Kelime(s);
		return kelime ;
	}
	
	/*
	 * 	CevaplayanHuman nesnesinin belirledi�i harf adedini kullanmak �zere al�r. harfAdet field'� kelimeSec metodunda kullan�l�r.
	 */
	@Override // Soran'dan override edildi
	public void setHarfAdet(Integer harfAdet) {
		this.harfAdet = harfAdet;
	}
	
	/*
	 *  Kullan�c�dan rakip olarak yar��mak istedi�i yapayzeka se�imini al�r.
	 */
	@Override // AI'dan override edildi
	public void aIPlayerSec() {
		this.enumCompPlayers = AI.AIsec();
	}
	
	/*
	 *  OyunMotoru Abstract Class'�n�n baslagicAnonsu static metodunda kullan�lmak �zere AI player'� d�nd�r�r
	 */
	@Override // AI'dan override edildi
	public EnumCompPlayers getAIPlayer() {
		return enumCompPlayers;
	}
	
	/*
	 *  CevaplayanHuman nesnesinin OyunModuB �zerinden yollad��� Character se�ilen kelime i�erisinde yoksa empty bir Integer listesi d�ner
	 *  e�er karakter kelimede n adet bulunuyorsa bulundu�u yerlerin indislerini n size'l� bir Integer listesiyle d�ner 
	 */
	@Override // Soran'dan override edildi
	public List<Integer> harfKontrol(Character harf) {
		List<Integer> karakterIndexList = new ArrayList<>();
		for (int i =0 ; i < kelime.getHarfSayac() ; i++) {
			if(kelime.getKelime().indexOf( harf, i ) == i) {
				karakterIndexList.add(i);
			}
		}
		return karakterIndexList;
	}
	
	

}
