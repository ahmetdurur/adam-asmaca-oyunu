package OyunMotoru;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Kelime.Kelime;
import players.CevaplayanHuman;
import players.SoranAI;


/*
 * @author Ahmet Durur
 * 
 *  Oyun Modu B (AI kelime se�er �nsan tahmin eder )
 *  OyunMotoru abstract s�n�f�ndan t�retilmi�tir.
 */

public class OyunModuB extends OyunMotoru {
	
	private Integer tahminHakk� = 10;
	private Kelime kelime ;
	private Map<Character, List<Integer>> acilanKarakterler = new HashMap<>();
	private List<Character> gosterilenKelime ;
	CevaplayanHuman cevaplayanHuman = new CevaplayanHuman();
	SoranAI soranAI = new SoranAI();
	
	// OyunModuB nesnesi instantiate edildi�inde constructor'�ndan init methodunu �a��rarak oyun ba�lat�lmas�n� tetikler.
	public OyunModuB() throws IOException {
		init();
	}
	
	
	/*	soranAI ve cevaplayanHuman nesnelerinin konfig�rasyonlar� yap�l�r 
	 *  soranAI kelimeSec metoduyla se�ilen kelime bu classtaki kelime field'�na d�nd�r�l�r
	 * 	kelimeInit metodu ile g�sterilecek kelime ayarlan�r
	 *  kelimeGoster metodu ile ayarlanan kelime gosterilir */
	@Override
	public void init() throws IOException {
		//OyunMotoru.baslangisAnonsu(soranAI.getAIPlayer().name(), cevaplayanHuman.getName());
		soranAI.setHarfAdet(cevaplayanHuman.harfAdediBelirle()); 
		kelime = soranAI.kelimeSec();
		kelimeInit();
		kelimeGoster();
		oyunBaslat();
	}
	
	// kelimeInit metodunda gosterilenKelime Class field'�na kullan�c� taraf�ndan se�ilen kelime uzunlugu kadar "_" karakteri atan�r
	@Override 
	public void kelimeInit() {
		gosterilenKelime = new ArrayList<>() ;
		for (int i =0 ; i<kelime.getHarfSayac() ; i++) {
			gosterilenKelime.add('_');
		}
	}
	
	// kelimeGoster metodu gosterilenKelime character dizisinin elemanlar� aras�na bir Tab bo�luk b�rakarak ekrana yazd�r�r
	@Override
	public void kelimeGoster() {
		for(int i =0 ; i<gosterilenKelime.size() ; i++) {
			System.out.print(gosterilenKelime.get(i));
			System.out.print("\t");
		}
		System.out.println();
	}
	
	/* OyunMotoru'ndan override edildi
	 * 10 tahmin hakk� bitene veya oyunBitir metodu true d�nd�rene kadar oyun devam eder her d�ng�de 
	 * cevaplayanHuman nesnesinin harfSec metodu invoke edilir ve harfSec metodundan d�nen karakter
	 * harfKontrol metoduna yollan�r e�er oyunBitir metodu true d�nd��� i�in while break olursa oyun kazan�l�r
	 * e�er tahmin hakk� bitti�i i�in while'dan ��k�l�rsa oyun kaybedilir.  */
	@Override
	public void oyunBaslat() {
		while(tahminHakk�>0 ) {
			harfKontrol(cevaplayanHuman.harfSec(null,null));			
			if(oyunBitir()) {
				break ;
			}
		}
		if(tahminHakk� == 0) {
			System.out.println("Oyunu Kaybettiniz.");
		}
	}
	
	/* oyunBitir metodu gosterilenKelime field'� art�k hi� '_' karakteri i�ermiyorsa Oyunun kazan�ld���n� konsola yazd�r�r
	 * ard�ndan true d�nd�rerek oyunBaslat metodunun d�ng�s�n� bitirir */
	@Override
	public boolean oyunBitir() {
		if (!gosterilenKelime.contains('_')) {
			System.out.println("\nOyunu Kazand�n�z.");
			return true ;
		}
		return false ;
	}
	
	/*	cevaplayanHuman nesnesinin se�ti�i harfi soranAI nesnesinin harfKontrol metoduna yollar 
	 *  NOT:soranAI nesnesinin harfKontrol metodu ald��� karakter se�ilen kelimede bulunuyorsa karakterin kelimede bulunan b�t�n indislerini bir Integer listesiyle d�nd�r�r
	 *  eger donen liste bos de�ilse key olarak karakter value olarak Integer Listesi tutan acilanKarakterler Map'ine eklenir ve kelimeAc  metodu cagrilir
	 *  donen liste bo�sa yani aranan karakter secilen kelimede yoksa kalan tahmin hakk� bir azalt�l�p ekrana yazd�r�l�r. */
	@Override
	public void harfKontrol(Character c ) {
		List<Integer> l =  soranAI.harfKontrol(c);
		if(!l.isEmpty()) {
			acilanKarakterler.put(c, l);
			kelimeAc();
		} else {
			System.out.println("Bilemediniz " + --tahminHakk� + " hakk�n�z kald�.");
		}
	}
	
	/*	kelimeAc metodu Map<Character, List<Integer>> tipindeki acilanKarakterler Map'ini foreach olarak d�nd�r�r her entry icin icerde bir dongu daha vard�r
	 * 	icerdeki dongu key olarak tutulan karakterin indis listesi buyuklugu kadar doner(yani basar�l� tahmin edilen harf kelimede kac adet varsa) ve her cycle'da 
	 * 	gosterilenKelime'nin ilgili indisinde bulunan '_' karakteri yerine mapte key olarak tutulan yani kullan�c�n�n basar�l� bir sekilde tahmin ettigi karakter set edilir. 
	 * 	Ekrana bildiniz yazd�r�l�p ard�ndan kelimeGoster metodu cagrilir. */
	@Override
	public void kelimeAc() {
		for (Map.Entry<Character, List<Integer>> entry : acilanKarakterler.entrySet()) {
			Character c = entry.getKey();
			List<Integer> l = entry.getValue();
			for(int i= 0 ; i<l.size() ; i++) {
				gosterilenKelime.set(l.get(i), c);
			}
		}
		System.out.println("Bildiniz!");
		kelimeGoster();
	}
}
