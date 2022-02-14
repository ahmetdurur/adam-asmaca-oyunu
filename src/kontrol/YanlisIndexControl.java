package kontrol;

import java.util.List;

public class YanlisIndexControl implements Kontrol {
	
	/*
	 * @author Ahmet Durur
	 */
	
	
	/*	Input Kontrolu saðlar.Örnek durum kelimenin güncel hali :  s t _ r l  _ n g
	 *  Bilgisayar a harfi tahmin etti Bildimmi sorusuna e yanýtý verildi 
	 * 	Kac harf bildim sorusuna 2 yanýtý verildi
	 *  1. harfin sýrasý : 1 yanýtý gelirse ( s karakterinin bulundugu sýra ) 
	 *  metodu invoke eden nesnenin gonderdigi uyarý mesajý konsola yazdýrýlýr 
	 */
	public static  boolean  indexKontrol(List<Character> gosterilenKelime , Integer index , String mesaj) {
		if(gosterilenKelime.get(index-1) != '_') {
			System.out.println(mesaj);
			return false;
		}
		return true;
	}
	
	
	/*	Input Kontrolu saglar.Örnek durum Kullanýcý kaç harf bildim sorusuna 3 yanýtý verir 
	 *  1. harfin sýrasý : 1
	 *  2. harfin sýrasý : 1  girilirse uyarý mesajý konsola yazdýrýlýr  
	 */
	public static boolean  tekrarlananIndex(List<Integer> indisList, Integer indis, String mesaj) {
		if(indisList.contains((Integer) indis-1)) {
			System.out.println(mesaj);
			return false;
		}		
		return true ;
	}
}
