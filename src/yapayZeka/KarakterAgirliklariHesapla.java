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

	/*	ilk for döngüsü içerisinde verilen karakter listesindeki karakterlerin her biri için puan hesaplayan  ikinci bir for döngüsü vardýr
	 * ilk forun her cycle'ýnda karakterin key puanýn value olarak tutulduðu karakterPuanMap'e ekleme yapýlýr
	 * ikinci forun her cycle'ýnda verilen karakterin kelime listesinde kaç adet bulundugu sayýlýr
	 * ve karakterPuanMap döndürülür	*/
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
	
	
	// parametre olarak aldýgý karakterPuanMap'i içerisindeki en yüksek puanlý karakteri döndürür 
	public static Character enYuksekPuanliCharacter (Map<Character, Integer> karakterPuanMap) {
		return Collections.max(karakterPuanMap.entrySet(), Map.Entry.comparingByValue()).getKey();
	}
	
}
