package kontrol;

import java.util.List;

public class YanlisIndexControl implements Kontrol {
	
	/*
	 * @author Ahmet Durur
	 */
	
	
	/*	Input Kontrolu sa�lar.�rnek durum kelimenin g�ncel hali :  s t _ r l  _ n g
	 *  Bilgisayar a harfi tahmin etti Bildimmi sorusuna e yan�t� verildi 
	 * 	Kac harf bildim sorusuna 2 yan�t� verildi
	 *  1. harfin s�ras� : 1 yan�t� gelirse ( s karakterinin bulundugu s�ra ) 
	 *  metodu invoke eden nesnenin gonderdigi uyar� mesaj� konsola yazd�r�l�r 
	 */
	public static  boolean  indexKontrol(List<Character> gosterilenKelime , Integer index , String mesaj) {
		if(gosterilenKelime.get(index-1) != '_') {
			System.out.println(mesaj);
			return false;
		}
		return true;
	}
	
	
	/*	Input Kontrolu saglar.�rnek durum Kullan�c� ka� harf bildim sorusuna 3 yan�t� verir 
	 *  1. harfin s�ras� : 1
	 *  2. harfin s�ras� : 1  girilirse uyar� mesaj� konsola yazd�r�l�r  
	 */
	public static boolean  tekrarlananIndex(List<Integer> indisList, Integer indis, String mesaj) {
		if(indisList.contains((Integer) indis-1)) {
			System.out.println(mesaj);
			return false;
		}		
		return true ;
	}
}
