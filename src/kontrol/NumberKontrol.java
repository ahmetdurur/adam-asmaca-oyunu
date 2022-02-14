package kontrol;

import java.util.List;

/*
 * @author Ahmet Durur
 */

public class NumberKontrol implements Kontrol {

	/*
	 * gonderilen number parametresi start(inclusive)  ve end (inclus,ve) aralýðýndaysa true döndürür
	 * deðilse invoke eden nesnenin gönderdiði mesajý konsola yazdýrýr.
	 */
	public static boolean numericRangeKontrol(Integer start , Integer end , Integer number , String mesaj) {
		if(number < start || number > end ) {
			if(mesaj != null) {
				System.out.println(mesaj);
			}
			return false;
		}
		return true ;
	}
	/*
	 * ornek durum : a h _ e t bilgisiyar m harfini tahmini etti bildimmi sorusuna e yanýtý aldý
	 * kaç harf bildim sorusuna 2 yanýtý alýrsa açýlmamýþ 1 harf kaldýðý için false döndürüp
	 * konsola en fazla 1 harf söylenebilir yazdýrýr
	 */
	
	public static boolean kalanAcilmamisHarfAdet (List<Character> gosterilenKelime, int i) {
		Integer counter =0 ;
		for (Character character : gosterilenKelime) {
			if(character == '_') {
				counter++;
			}
		}
		if(i > counter) {
			System.out.println("En fazla  " + counter + " harf söylenebilir\nKaç harf bildim : ");
			return false;
		}
		return true;
	}
	
}
