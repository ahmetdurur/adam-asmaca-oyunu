package OyunMotoru;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kontrol.CharacterControl;
import players.CevaplayanAI;
import players.SoranHuman;
import yapayZeka.KelimeAltKume;


/*
 * @author Ahmet Durur
 * 
 *  Oyun Modu A (�nsan kelime se�er AI tahmin eder )
 *  OyunMotoru abstract s�n�f�ndan t�rerilmi�tir.
 */

public class OyunModuA extends OyunMotoru {
	
	private Integer tahminHakki = 10;
	private CevaplayanAI cevaplayanAI = new CevaplayanAI();
	private SoranHuman soranHuman = new SoranHuman();
	private List<Character> gosterilenKelime;
	private Map<Character, List<Integer>> acilanKarakterler = new HashMap<>();
	private List<Character> cachelenenHarfler = new ArrayList<>();
		
	// OyunModuA nesnesi instantiate edildi�inde constructor'�ndan init methodunu �a��rarak oyun ba�lat�lmas�n� tetikler.
	public OyunModuA() throws IOException {
		init();
	}
	
	
	/*
	 * oyunda kullan�lmas� i�in olu�turulan cevaplayanAI ve soranHuman nesnelerinin konfig�rasyonlar�n� yapar
	 * ekrana se�ilen harf say�s� uzunlu�unda kelimenin kapal� halini yazd�r�r ve oyunu ba�lat�r 
	 */
	@Override
	public void init() throws IOException {
		OyunMotoru.baslangisAnonsu(soranHuman.getName(), cevaplayanAI.getAIPlayer().name());
		soranHuman.setHarfAdet(cevaplayanAI.harfAdediBelirle());
		//soranHuman.kelimeSec();
		kelimeInit();
		kelimeGoster();
		oyunBaslat();
	}
	
	// gosterilen kelime Karakter Listesine se�ilen harf say�s� kadar alttire ( _ ) karakteri ekler 
	@Override
	public void kelimeInit() {
		gosterilenKelime = new ArrayList<>() ;
		for (int i =0 ; i<soranHuman.getHarfAdet() ; i++) {
			gosterilenKelime.add('_');
		}
	}
	
	// gosterilen kelime Karakter Listesini her karakter aras�nda bir tab bo�luk olacak �ekilde ekrana yazd�r�r
	@Override 
	public void kelimeGoster() {
		for(int i =0 ; i<gosterilenKelime.size() ; i++) {
			System.out.print(gosterilenKelime.get(i));
			System.out.print("\t");
		}
		System.out.println();
	}
	
	/* oyun ba�lat�l�r tahminhakki  alan� 0 dan b�y�k oldu�u s�rece ve oyun bitir metodundan false d�nd��� s�rece oyun devam eder
	 oyun devam etti�i s�rece cevaplayanAI objesinin harfSec metodundan d�nen karakter bu class'�n (OyunModuA class�n�n) harfKontrol metoduna g�nderilir 
	 harfSec metoduna g�nderilen cachelenenHarfler parametresi cevaplananAI'a bu harfleri daha �nceden tahmin etti�ini bir daha se�memesi gerekti�ini s�yler
	 gosterilenKelime parametresi ise kelimenin g�ncel halini g�nderir �rnek durum : ( s t a r _ a n _ ) 
	 e�er d�ng�den break olmadan yani tahmin hakk� biterek ��k�ld�ysa oyun kaybedilir
	 e�er d�ng�den oyunBitir metodu true d�nd�rd��� i�in ��k�l�rsa oyun kazan�l�r */
	@Override
	public void oyunBaslat() throws IOException {
		while(tahminHakki>0 ) {
			harfKontrol(cevaplayanAI.harfSec(cachelenenHarfler , gosterilenKelime));			
			if(oyunBitir()) {
				break ;
			}
		}
		if(tahminHakki == 0) {
			System.out.println("Oyunu Kaybettim.");
		}
	}
	
	/* 	cevaplayanAI nesnesinin harfSec metodundan donen karakteri cachelenenHarfler listesine ekler
	 *  sonras�nda cevaplayanAI nesnesinin bildimmi metodu �a�r�l�r bu metod tahmin edilen karakteri kullan�c�yla ileti�ime ge�erek
	 *  do�ruysa kendi alan� olan (cevaplayanAI.acilanKarakterler) acilanKarakterler mapine ekler yanl��sa tahminHakk�n� 1 azalt�p geri d�nd�r�r 
	 *  sonras�nda bu class�n field'� olan (OyunModuA.acilanKarakterler) map'ine cevaplayanAI nesnesindeki ayn� isimli map d�nd�r�l�p eklenir
	 *  NOT: acilanKarakterler map'i key olarak karakter value olarak karakterin bulundu�u indisler Listesini tutar */	 
	@Override
	public void harfKontrol(Character c) {
		cachelenenHarfler.add(c);
		System.out.print("\nHarf tahmin ediyorum : " + c );
		tahminHakki =  cevaplayanAI.bildimmi(c , tahminHakki , gosterilenKelime);
		acilanKarakterler.putAll(cevaplayanAI.getAcilanKarakterler());
		kelimeAc();
		
	}
	
	/* gosterilenKelime karakter listesinde hi� alttire ( '_' ) karakteri kalmad�ysa t�m harfler bilinmi� oldu�u i�in oyun kazan�l�r
	 * veya a��lan harflere g�re listede tahmin edilebilecek tek bir kelim kald�ysa else if'in i�inde kelimeyi a��p 
	 * ekrana mesaj yazd�r�l�p true d�nd�r�lerek oyunBaslat metodundaki d�ng� break edilir ve oyun bitirilmi� olur
	 * if else if'e girmediyse kelime tahmin edilememi�tir false d�nd�r�l�r
	 */
	@Override
	public boolean oyunBitir() throws IOException {
		List<String> altKume = KelimeAltKume.kelimeAltKumeGetir(gosterilenKelime);
		if (!gosterilenKelime.contains('_')) {
			System.out.println("\nOyunu Kazand�m.");
			return true ;
		}
		else if (altKume.size() == 1 ) {
			System.out.println("\n\nTahmin etti�iniz kelime : "  );
			for (int i=0 ; i<altKume.get(0).length() ; i++) {
				gosterilenKelime.set(i, altKume.get(0).charAt(i));
			}
			System.out.println();
			System.out.println();
			kelimeGoster();
			System.out.println( tahminHakki + " adet tahmin hakk�m� kullanmama gerek yok ��nk� ben geli�mi� bir yapay zekay�m :)");
			System.out.println("\nOyunu Kazand�m.");
			return true ;
		}
		return false ;
	}
	
	/*	
	 *  acilanKarakterler mapinde tutulan entryler i�in foreach d�n�l�r her entrynin keyi olan karakter i�in i�eride bir for daha d��nl�r
	 *  i�erideki for karakterin indis listesi olan value'su kadar d�n�l�r ve gosterilenKelime field�n�n ilgili indisine key'de tutulan karakter atan�r
	 *  sonra kelimeGoster metoduyla kelimenin guncel hali konsola yazd�r�l�r 
	  */
	@Override
	public void kelimeAc() {
		for (Map.Entry<Character, List<Integer>> entry : acilanKarakterler.entrySet()) {
			Character c = entry.getKey();
			List<Integer> l = entry.getValue();
			for(int i= 0 ; i<l.size() ; i++) {
				gosterilenKelime.set(l.get(i), c);
			}
		}
		kelimeGoster();
	}
}
