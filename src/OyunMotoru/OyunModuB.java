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
 *  Oyun Modu B (AI kelime seçer Ýnsan tahmin eder )
 *  OyunMotoru abstract sýnýfýndan türetilmiþtir.
 */

public class OyunModuB extends OyunMotoru {
	
	private Integer tahminHakký = 10;
	private Kelime kelime ;
	private Map<Character, List<Integer>> acilanKarakterler = new HashMap<>();
	private List<Character> gosterilenKelime ;
	CevaplayanHuman cevaplayanHuman = new CevaplayanHuman();
	SoranAI soranAI = new SoranAI();
	
	// OyunModuB nesnesi instantiate edildiðinde constructor'ýndan init methodunu çaðýrarak oyun baþlatýlmasýný tetikler.
	public OyunModuB() throws IOException {
		init();
	}
	
	
	/*	soranAI ve cevaplayanHuman nesnelerinin konfigürasyonlarý yapýlýr 
	 *  soranAI kelimeSec metoduyla seçilen kelime bu classtaki kelime field'ýna döndürülür
	 * 	kelimeInit metodu ile gösterilecek kelime ayarlanýr
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
	
	// kelimeInit metodunda gosterilenKelime Class field'ýna kullanýcý tarafýndan seçilen kelime uzunlugu kadar "_" karakteri atanýr
	@Override 
	public void kelimeInit() {
		gosterilenKelime = new ArrayList<>() ;
		for (int i =0 ; i<kelime.getHarfSayac() ; i++) {
			gosterilenKelime.add('_');
		}
	}
	
	// kelimeGoster metodu gosterilenKelime character dizisinin elemanlarý arasýna bir Tab boþluk býrakarak ekrana yazdýrýr
	@Override
	public void kelimeGoster() {
		for(int i =0 ; i<gosterilenKelime.size() ; i++) {
			System.out.print(gosterilenKelime.get(i));
			System.out.print("\t");
		}
		System.out.println();
	}
	
	/* OyunMotoru'ndan override edildi
	 * 10 tahmin hakký bitene veya oyunBitir metodu true döndürene kadar oyun devam eder her döngüde 
	 * cevaplayanHuman nesnesinin harfSec metodu invoke edilir ve harfSec metodundan dönen karakter
	 * harfKontrol metoduna yollanýr eðer oyunBitir metodu true döndüðü için while break olursa oyun kazanýlýr
	 * eðer tahmin hakký bittiði için while'dan çýkýlýrsa oyun kaybedilir.  */
	@Override
	public void oyunBaslat() {
		while(tahminHakký>0 ) {
			harfKontrol(cevaplayanHuman.harfSec(null,null));			
			if(oyunBitir()) {
				break ;
			}
		}
		if(tahminHakký == 0) {
			System.out.println("Oyunu Kaybettiniz.");
		}
	}
	
	/* oyunBitir metodu gosterilenKelime field'ý artýk hiç '_' karakteri içermiyorsa Oyunun kazanýldýðýný konsola yazdýrýr
	 * ardýndan true döndürerek oyunBaslat metodunun döngüsünü bitirir */
	@Override
	public boolean oyunBitir() {
		if (!gosterilenKelime.contains('_')) {
			System.out.println("\nOyunu Kazandýnýz.");
			return true ;
		}
		return false ;
	}
	
	/*	cevaplayanHuman nesnesinin seçtiði harfi soranAI nesnesinin harfKontrol metoduna yollar 
	 *  NOT:soranAI nesnesinin harfKontrol metodu aldýðý karakter seçilen kelimede bulunuyorsa karakterin kelimede bulunan bütün indislerini bir Integer listesiyle döndürür
	 *  eger donen liste bos deðilse key olarak karakter value olarak Integer Listesi tutan acilanKarakterler Map'ine eklenir ve kelimeAc  metodu cagrilir
	 *  donen liste boþsa yani aranan karakter secilen kelimede yoksa kalan tahmin hakký bir azaltýlýp ekrana yazdýrýlýr. */
	@Override
	public void harfKontrol(Character c ) {
		List<Integer> l =  soranAI.harfKontrol(c);
		if(!l.isEmpty()) {
			acilanKarakterler.put(c, l);
			kelimeAc();
		} else {
			System.out.println("Bilemediniz " + --tahminHakký + " hakkýnýz kaldý.");
		}
	}
	
	/*	kelimeAc metodu Map<Character, List<Integer>> tipindeki acilanKarakterler Map'ini foreach olarak döndürür her entry icin icerde bir dongu daha vardýr
	 * 	icerdeki dongu key olarak tutulan karakterin indis listesi buyuklugu kadar doner(yani basarýlý tahmin edilen harf kelimede kac adet varsa) ve her cycle'da 
	 * 	gosterilenKelime'nin ilgili indisinde bulunan '_' karakteri yerine mapte key olarak tutulan yani kullanýcýnýn basarýlý bir sekilde tahmin ettigi karakter set edilir. 
	 * 	Ekrana bildiniz yazdýrýlýp ardýndan kelimeGoster metodu cagrilir. */
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
