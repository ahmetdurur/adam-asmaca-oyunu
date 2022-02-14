package kontrol;

import java.util.List;

/*
 * @author Ahmet Durur
 */

public class NumberKontrol implements Kontrol {

	/*
	 * gonderilen number parametresi start(inclusive)  ve end (inclus,ve) aral���ndaysa true d�nd�r�r
	 * de�ilse invoke eden nesnenin g�nderdi�i mesaj� konsola yazd�r�r.
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
	 * ornek durum : a h _ e t bilgisiyar m harfini tahmini etti bildimmi sorusuna e yan�t� ald�
	 * ka� harf bildim sorusuna 2 yan�t� al�rsa a��lmam�� 1 harf kald��� i�in false d�nd�r�p
	 * konsola en fazla 1 harf s�ylenebilir yazd�r�r
	 */
	
	public static boolean kalanAcilmamisHarfAdet (List<Character> gosterilenKelime, int i) {
		Integer counter =0 ;
		for (Character character : gosterilenKelime) {
			if(character == '_') {
				counter++;
			}
		}
		if(i > counter) {
			System.out.println("En fazla  " + counter + " harf s�ylenebilir\nKa� harf bildim : ");
			return false;
		}
		return true;
	}
	
}
