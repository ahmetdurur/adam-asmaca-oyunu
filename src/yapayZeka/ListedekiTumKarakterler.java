package yapayZeka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import players.interfaces.Player;

/* 
 * @author Ahmet Durur
 */

public class ListedekiTumKarakterler {
		
	
	/*
	 *  static metoda g�nderilen kelime listesinde bulunan t�m karakterlerin bulundu�u bir Karakter Listesi d�nd�r�r
	 */
	
	
	public static List<Character> uniqueleriBul(List<String> kelimeList) {
		List<Character> listedekiUniqeKarakterler = new ArrayList<Character>();
		listedekiUniqeKarakterler.addAll(arindir(kelimeList.get(0)));
		for (String kelime : kelimeList) {
			for(int i=0; i<kelime.length() ; i++) {
				if(!listedekiUniqeKarakterler.contains(kelime.charAt(i))) {
					listedekiUniqeKarakterler.add(kelime.charAt(i));
				}
			}
		}
		//System.out.println(listedekiUniqeKarakterler);
		return listedekiUniqeKarakterler;
	}
	
	// arindir metodu kelimeyi tekrar eden harflerinden ar�nd�r�r �rnek parametre "adana" d�nen character listesi "adn"  
	
	private static List<Character> arindir(String str) {
    	List<Character> l = new ArrayList<Character>();
    	l.add(str.charAt(0));
    	for(int i= 0 ; i< str.length() ; i++) {
    		if(!l.contains(str.charAt(i))) {
    			l.add(str.charAt(i));
    		}
    	}
    	return l;
	}

	
}
