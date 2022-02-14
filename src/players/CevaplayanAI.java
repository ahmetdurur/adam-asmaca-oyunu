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
 *  Kelime Tutan Kullanýcýnýn (SoranHuman) tuttuðu kelimeyi bulmaya calisan AI tipi playerdýr
 *  OyunModuA oyun motorunda islem görür */	

public class CevaplayanAI implements AI , Cevaplayan {

	private Integer harfAdet ;
	private EnumCompPlayers enumCompPlayers;
	private Map<Character, List<Integer>> acilanKarakterler = new HashMap<>();
	private List<Character> gosterilenKelime ;
	
	
	// aIPlayerSec metodunu çaðýrarak AI tipini seçer 
	public CevaplayanAI() {
		aIPlayerSec();
	}
	
	/*  Player interface'inin static metodundan sozlukteki en uzun kelimenin uzunlugu döndürülür ve max'a atanýr
	 * 	sonrasýnda 1 ve max arasýnda random bir sayý üretir kullanýcýya bu random sayý uzunlugunda bir kelime seçmesini söyler ve sayýyý döndürür */
	@Override //Cevaplayandan override edildi
	public Integer harfAdediBelirle() throws IOException {
		Random r = new Random();
		this.harfAdet = r.nextInt(Player.enUzunKelime()) + 1 ; 
		//System.out.println(enumCompPlayers.name() + " Harf sayýsýný belirledi : " + harfAdet + " Lütfen sözlükten " + harfAdet +  " harfli bir kelime seçin"  );
		System.out.println("harf sayýsýný belirliyorum : " + harfAdet);
		return this.harfAdet ; 
	}
	
	
	/* AI interface'inin AIsec metodundan dönen EnumCompPlayers tipindeki enumu enumCompPlayers field'ýna atar */
	@Override // AI'dan override edildi
	public void aIPlayerSec() {
		this.enumCompPlayers = AI.AIsec();
	}
	
	// metodu invoke eden nesneye enumCompPlayers enum'unu döndürür
	@Override // AI'dan override edildi
	public EnumCompPlayers getAIPlayer() {
		return enumCompPlayers;
	}
	
	/*	ikinci parametre ile aldýgý character listesini KelimeAltKume sýnýfýnýn static kelimeAltKumeGetir metoduna yollar
	 * bu metod açýlan kelimenin eþleþen harflerine göre bir alt liste döndürür örneðin  açýlan kelime  ( a _ _ m ) dönen örnek liste { abam , abem , abim .... }
	 * dönen bu liste ListedekiTumKarakterler classýnýn uniqueleriBul static metoduna yollanýr bu metod verilen String listesindeki tüm karakterlerin bulunduðu bir Character listesi döndürür
	 * bu dönen character listesinden cachelenenHarfler parametresinde yollanan liste çýkarýlýr çünkü bu listedeki harfleri daha önceden tahmin ettik
	 * daha sonra KarakterAgirliklariHesapla classýnýn  enYuksekPuanliCharacter metoduna KarakterAgirliklariHesapla classýnýn  agirlikHesapla metodundan dönen karakterPuanMap'i gönderilir
	 * NOT: agirlikHesapla metodu listedeki tüm karakterlerin aðýrlýðýný hesaplayýp key olarak karakter value olarak karakterin puanýný tutan bir map döndürür
	 * NOT: enYuksekPuanliCharacter metodu map'teki puaný en yüksek olan karakteri döndürür  	*/
	@Override //Cevaplayandan override edildi
	public Character harfSec(List<Character> cachelenenHarfler, List<Character> gosterilenKelime2) throws IOException {
	    List<Character> l = ListedekiTumKarakterler.uniqueleriBul(KelimeAltKume.kelimeAltKumeGetir(gosterilenKelime2));
	    l.removeAll(cachelenenHarfler);
	    Character c  = KarakterAgirliklariHesapla.enYuksekPuanliCharacter(KarakterAgirliklariHesapla.agirlikHesapla(l, Player.kelimeleriAl(harfAdet)));
		return c;
	}
	
	
	/* metod harf tahmini ardýndan kullanýcýya bildimmi sorusunu sorar ve evet hayýr cevabý bekler
	 * e veya h karakteri girilmedigi sürece uyarý mesajý verir ve tekrardan giris bekler
	 * eðer h cevabý alýrsa tahminHakkýný önce azaltýp sonra konsola yazdýrýr
	 * eðer e cevabý alýrsa kacHarfBildim metodunu çaðýrýr ve tahminHakký'ný döndürür 	*/
	public Integer bildimmi(Character c, Integer tahminHakký, List<Character> gosterilenKelime) {
		this.gosterilenKelime = gosterilenKelime;
		System.out.print("\nBildim mi? (e/h) : ");
		Scanner sc = new Scanner(System.in);
		Character eh = sc.next().charAt(0); 
		while(eh!= 'e' && eh!='h' ) {
			System.out.println("Lütfen geçerli bir seçin yapýn. evet (e) hayýr (h)");
			eh = sc.next().charAt(0); 
		}
		
		if( eh == 'e' ) {
			kacHarfBildim(c);
		} else if(eh == 'h') {
			System.out.println(--tahminHakký + " hakkým kaldý.");
		}
		return tahminHakký;
	}
	
	/* ilk while dongusunde eger kullanýcý tarafýndan girilen deðerde uygunsuzluk yoksa kacHarfBildiminin cevabýný harfAdet deðiþkenine alýr
	 * bu döngüde girilen karakter numerikmi numerikse kelime uzunlugundan küçükmü kelime uzunlugundan kücükse kalan acilmamis harften kücükmü ýnput kontrolleri yapýlýr
	 * sonra for döngüsü harf adet kadar döner örnek kullanýcý 2 cevabýný verdi sýrasýyla 1. harfin sýrasý ve 2. harfin sýrasý þeklinde girdi alýr 
	 * for içerisindeki while'da yine input kontroller yapýlýr  eger uygun karakterler girildiyse indisList (Integer) listesi doldurulur
	 * for'un dýþýnda acilanKarakterler map'ine key olarak karakter ve value olarak indisList eklenir 	 */
	public void kacHarfBildim(Character c) {
		Scanner sc = new Scanner(System.in);
		String kacHarfBildim = "\nKaç harf bildim : ";
		System.out.println(kacHarfBildim);
		String mesajStringKontrol = "Lütfen geçerli cevap veriniz";
		String numericRangeKontrol = "Geçerli cevap 1 ve " + this.harfAdet + " arasýnda bir sayý olmalýdýr" ;
		String characterKontrol = "Lütfen rakam giriniz\nKaç harf bildim : " ;
		String yanlisIndex = "Söylediginiz sýrayý daha önce söylediniz ";
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
			System.out.println( i + ". harfin sýrasý : ");
			String s = sc.next();

			numericRangeKontrol = "Geçerli cevap 1 ve " + this.harfAdet + " arasýnda bir sayý olmalýdýr" ;
			while(!StringControl.tumKarakterlerNumerikmiKontrol(s, mesajStringKontrol) || 
					!NumberKontrol.numericRangeKontrol(1, this.harfAdet, Integer.parseInt(s), numericRangeKontrol ) 
					|| !YanlisIndexControl.indexKontrol(gosterilenKelime, Integer.parseInt(s), yanlisIndex)
					|| !YanlisIndexControl.tekrarlananIndex(indisList , Integer.parseInt(s) , yanlisIndex )
				 ) 
			{	
				System.out.println( i + ". harfin sýrasý : ");
				s = sc.next();
			}	
			
			indisList.add(Integer.parseInt(s)-1);
		}
		acilanKarakterler.put(c, indisList);
	}
	
	// kacHarfBildim metodunda guncellenen acilananKarakterler map'ini döndürür
	public Map<Character, List<Integer>> getAcilanKarakterler() {
		return acilanKarakterler;
	}
	
	
	
	
}
