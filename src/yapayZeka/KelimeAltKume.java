package yapayZeka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import players.interfaces.Player;

/* 
 * @author Ahmet Durur
 */

public class KelimeAltKume {
	
	
	/* bu metot kabaca �unu yapmaktad�r �rnek verilen parametre ( _ b _ ) ornek donen liste ( aba , abb , abc , abd , abe ....... ) 
	 * 
	 * parametre olarak ald�g� karakter listesinin boyutundaki tum kelimeleri kelimeHavuzu de�i�kenine al�r ornek parametre ('_','b','_') i�in sozlukteki
	 * b�t�n 3 harfli kelimeler getirilir ilk for'da verilen parametredeki t�m alttire olmayan karakterlerin indisi indisList'e al�n�r
	 * ikinci for t�m n harfli kelimelerin al�nd�g� String listesinde d�ner her bir kelime i�in verilen parametredeki indisle kelimenin indisinindeki karakterler e�itse
	 * counter'� art�r�r  i� for'dan ��k�nca counter la (yani e�it olan harf say�s� ) ile indisListesinin b�y�kl��� ayn� ise kelime havuzAltKume listesine eklenir
	 * ve ikinci for bitince havuzAltKume listesi d�nd�r�l�r 	*/
	public static List<String> kelimeAltKumeGetir(List<Character> gosterilenKelime ) throws IOException{
		
		List<String> kelimeHavuzu = Player.kelimeleriAl(gosterilenKelime.size());
		List<String> havuzAltKume = new ArrayList<>(); 
		List<Integer> indisList = new ArrayList<>();
		
		for (Character c : gosterilenKelime) {
			if(c != '_') {
				indisList.add(gosterilenKelime.indexOf(c));
			}
		}
		
		for (String kelime : kelimeHavuzu) {
			Integer counter = 0;
			for(int i=0 ; i < gosterilenKelime.size() ; i++) {
				if(kelime.charAt(i) == gosterilenKelime.get(i)) {
					counter++;
				}
			}
			if(counter == indisList.size()) {
				havuzAltKume.add(kelime);
			}
		}
		return havuzAltKume;
	}
	
}
