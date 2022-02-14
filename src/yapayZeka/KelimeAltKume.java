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
	
	
	/* bu metot kabaca þunu yapmaktadýr örnek verilen parametre ( _ b _ ) ornek donen liste ( aba , abb , abc , abd , abe ....... ) 
	 * 
	 * parametre olarak aldýgý karakter listesinin boyutundaki tum kelimeleri kelimeHavuzu deðiþkenine alýr ornek parametre ('_','b','_') için sozlukteki
	 * bütün 3 harfli kelimeler getirilir ilk for'da verilen parametredeki tüm alttire olmayan karakterlerin indisi indisList'e alýnýr
	 * ikinci for tüm n harfli kelimelerin alýndýgý String listesinde döner her bir kelime için verilen parametredeki indisle kelimenin indisinindeki karakterler eþitse
	 * counter'ý artýrýr  iç for'dan çýkýnca counter la (yani eþit olan harf sayýsý ) ile indisListesinin büyüklüðü ayný ise kelime havuzAltKume listesine eklenir
	 * ve ikinci for bitince havuzAltKume listesi döndürülür 	*/
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
