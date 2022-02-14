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
 *  Oyun Modu A (Ýnsan kelime seçer AI tahmin eder )
 *  OyunMotoru abstract sýnýfýndan türerilmiþtir.
 */

public class OyunModuA extends OyunMotoru {
	
	private Integer tahminHakki = 10;
	private CevaplayanAI cevaplayanAI = new CevaplayanAI();
	private SoranHuman soranHuman = new SoranHuman();
	private List<Character> gosterilenKelime;
	private Map<Character, List<Integer>> acilanKarakterler = new HashMap<>();
	private List<Character> cachelenenHarfler = new ArrayList<>();
		
	// OyunModuA nesnesi instantiate edildiðinde constructor'ýndan init methodunu çaðýrarak oyun baþlatýlmasýný tetikler.
	public OyunModuA() throws IOException {
		init();
	}
	
	
	/*
	 * oyunda kullanýlmasý için oluþturulan cevaplayanAI ve soranHuman nesnelerinin konfigürasyonlarýný yapar
	 * ekrana seçilen harf sayýsý uzunluðunda kelimenin kapalý halini yazdýrýr ve oyunu baþlatýr 
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
	
	// gosterilen kelime Karakter Listesine seçilen harf sayýsý kadar alttire ( _ ) karakteri ekler 
	@Override
	public void kelimeInit() {
		gosterilenKelime = new ArrayList<>() ;
		for (int i =0 ; i<soranHuman.getHarfAdet() ; i++) {
			gosterilenKelime.add('_');
		}
	}
	
	// gosterilen kelime Karakter Listesini her karakter arasýnda bir tab boþluk olacak þekilde ekrana yazdýrýr
	@Override 
	public void kelimeGoster() {
		for(int i =0 ; i<gosterilenKelime.size() ; i++) {
			System.out.print(gosterilenKelime.get(i));
			System.out.print("\t");
		}
		System.out.println();
	}
	
	/* oyun baþlatýlýr tahminhakki  alaný 0 dan büyük olduðu sürece ve oyun bitir metodundan false döndüðü sürece oyun devam eder
	 oyun devam ettiði sürece cevaplayanAI objesinin harfSec metodundan dönen karakter bu class'ýn (OyunModuA classýnýn) harfKontrol metoduna gönderilir 
	 harfSec metoduna gönderilen cachelenenHarfler parametresi cevaplananAI'a bu harfleri daha önceden tahmin ettiðini bir daha seçmemesi gerektiðini söyler
	 gosterilenKelime parametresi ise kelimenin güncel halini gönderir örnek durum : ( s t a r _ a n _ ) 
	 eðer döngüden break olmadan yani tahmin hakký biterek çýkýldýysa oyun kaybedilir
	 eðer döngüden oyunBitir metodu true döndürdüðü için çýkýlýrsa oyun kazanýlýr */
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
	 *  sonrasýnda cevaplayanAI nesnesinin bildimmi metodu çaðrýlýr bu metod tahmin edilen karakteri kullanýcýyla iletiþime geçerek
	 *  doðruysa kendi alaný olan (cevaplayanAI.acilanKarakterler) acilanKarakterler mapine ekler yanlýþsa tahminHakkýný 1 azaltýp geri döndürür 
	 *  sonrasýnda bu classýn field'ý olan (OyunModuA.acilanKarakterler) map'ine cevaplayanAI nesnesindeki ayný isimli map döndürülüp eklenir
	 *  NOT: acilanKarakterler map'i key olarak karakter value olarak karakterin bulunduðu indisler Listesini tutar */	 
	@Override
	public void harfKontrol(Character c) {
		cachelenenHarfler.add(c);
		System.out.print("\nHarf tahmin ediyorum : " + c );
		tahminHakki =  cevaplayanAI.bildimmi(c , tahminHakki , gosterilenKelime);
		acilanKarakterler.putAll(cevaplayanAI.getAcilanKarakterler());
		kelimeAc();
		
	}
	
	/* gosterilenKelime karakter listesinde hiç alttire ( '_' ) karakteri kalmadýysa tüm harfler bilinmiþ olduðu için oyun kazanýlýr
	 * veya açýlan harflere göre listede tahmin edilebilecek tek bir kelim kaldýysa else if'in içinde kelimeyi açýp 
	 * ekrana mesaj yazdýrýlýp true döndürülerek oyunBaslat metodundaki döngü break edilir ve oyun bitirilmiþ olur
	 * if else if'e girmediyse kelime tahmin edilememiþtir false döndürülür
	 */
	@Override
	public boolean oyunBitir() throws IOException {
		List<String> altKume = KelimeAltKume.kelimeAltKumeGetir(gosterilenKelime);
		if (!gosterilenKelime.contains('_')) {
			System.out.println("\nOyunu Kazandým.");
			return true ;
		}
		else if (altKume.size() == 1 ) {
			System.out.println("\n\nTahmin ettiðiniz kelime : "  );
			for (int i=0 ; i<altKume.get(0).length() ; i++) {
				gosterilenKelime.set(i, altKume.get(0).charAt(i));
			}
			System.out.println();
			System.out.println();
			kelimeGoster();
			System.out.println( tahminHakki + " adet tahmin hakkýmý kullanmama gerek yok çünkü ben geliþmiþ bir yapay zekayým :)");
			System.out.println("\nOyunu Kazandým.");
			return true ;
		}
		return false ;
	}
	
	/*	
	 *  acilanKarakterler mapinde tutulan entryler için foreach dönülür her entrynin keyi olan karakter için içeride bir for daha döünlür
	 *  içerideki for karakterin indis listesi olan value'su kadar dönülür ve gosterilenKelime fieldýnýn ilgili indisine key'de tutulan karakter atanýr
	 *  sonra kelimeGoster metoduyla kelimenin guncel hali konsola yazdýrýlýr 
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
