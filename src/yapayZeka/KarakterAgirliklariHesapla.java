package yapayZeka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import players.interfaces.Player;

/*
 *  @author Ahmet Durur
 */

public class KarakterAgirliklariHesapla {

	/*	ilk for d�ng�s� i�erisinde verilen karakter listesindeki karakterlerin her biri i�in puan hesaplayan  ikinci bir for d�ng�s� vard�r
	 * ilk forun her cycle'�nda karakterin key puan�n value olarak tutuldu�u karakterPuanMap'e ekleme yap�l�r
	 * ikinci forun her cycle'�nda verilen karakterin kelime listesinde ka� adet bulundugu say�l�r
	 * ve karakterPuanMap d�nd�r�l�r	*/
	public static Map<Character, Integer> agirlikHesapla(List<Character> uniqeKarakterler , List<String> kelimeList) throws IOException {
		Map<Character, Integer> karakterPuanMap = new HashMap<>();
		for(int i =0 ; i<uniqeKarakterler.size() ; i++) {
			Integer count = 0 ;
			Character character = uniqeKarakterler.get(i);
			for (String kelime : kelimeList) {
				count += (int) kelime.chars().filter(ch -> ch == character).count();
			}
			karakterPuanMap.put(character, count);
		}
		//System.out.println(karakterPuanMap);
		return karakterPuanMap;
	}
	
	
	// parametre olarak ald�g� karakterPuanMap'i i�erisindeki en y�ksek puanl� karakteri d�nd�r�r 
	public static Character enYuksekPuanliCharacter (Map<Character, Integer> karakterPuanMap) {
		return Collections.max(karakterPuanMap.entrySet(), Map.Entry.comparingByValue()).getKey();
	}
	
}
