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
	 *  metodu ile turkish_dictionary.txt dosyasý sozluk adýnda bir String listesine alýnýr. Sonrasýnda sozluk listesinin buyuklugu mesafesinde
	 *  random bir sayý secilerek sozlukten secilen indisteki kelime alýnýr. Eger secilen kelime kullanýcýn klavyeden 
	 *  girdigi harf uzunlugunda ise yeni bir Kelime nesnesi olusturulup döndürülür. Degilse kullanýcýnýn belirledigi
	 *  uzunlukta bir kelime sozlukten bulunana kadar islem tekrar eder. 
	 *  
	 *  ÖNEMLÝ NOT: System.out.println(enumCompPlayers.name() +  " tarafýndan sözlükten seçilen kelime: " + s); satýrý ile AI'ýn seçtiði kelime konsola yazdýrýlýr
	 *  bu kullanýcýnýn kelimeyi görüp sözlükte olup olmadýðýný test etmesi amacýyla açýk býrakýlmýþtýr. OyunModuB'de rekabetçi bir oyun oynamak için lütfen
	 *  System.out.println(enumCompPlayers.name() +  " tarafýndan sözlükten seçilen kelime: " + s); satýrýný yorum satýrý yapýnýz
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
		System.out.println(enumCompPlayers.name() +  " tarafýndan sözlükten seçilen kelime: " + s);
		kelime = new Kelime(s);
		return kelime ;
	}
	
	/*
	 * 	CevaplayanHuman nesnesinin belirlediði harf adedini kullanmak üzere alýr. harfAdet field'ý kelimeSec metodunda kullanýlýr.
	 */
	@Override // Soran'dan override edildi
	public void setHarfAdet(Integer harfAdet) {
		this.harfAdet = harfAdet;
	}
	
	/*
	 *  Kullanýcýdan rakip olarak yarýþmak istediði yapayzeka seçimini alýr.
	 */
	@Override // AI'dan override edildi
	public void aIPlayerSec() {
		this.enumCompPlayers = AI.AIsec();
	}
	
	/*
	 *  OyunMotoru Abstract Class'ýnýn baslagicAnonsu static metodunda kullanýlmak üzere AI player'ý döndürür
	 */
	@Override // AI'dan override edildi
	public EnumCompPlayers getAIPlayer() {
		return enumCompPlayers;
	}
	
	/*
	 *  CevaplayanHuman nesnesinin OyunModuB üzerinden yolladýðý Character seçilen kelime içerisinde yoksa empty bir Integer listesi döner
	 *  eðer karakter kelimede n adet bulunuyorsa bulunduðu yerlerin indislerini n size'lý bir Integer listesiyle döner 
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
