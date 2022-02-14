package OyunMotoru;

import java.io.IOException;

/*
 	* @author Ahmet Durur
 	* kendisinden türetilen oyunModu altsýnýflarý için abstract metodlarý saðlar
 	* baslangic anonsu static metoduyla oyun baþlama anonsunu ekrana yazdýrýr
*/

public abstract class OyunMotoru {

	public abstract void init() throws IOException;
	public abstract void kelimeInit();
	public abstract void kelimeGoster();
	public abstract void oyunBaslat() throws IOException;
	public abstract void harfKontrol(Character c );
	public abstract boolean oyunBitir() throws IOException;
	public abstract void kelimeAc();
	public static void baslangisAnonsu(String soran , String cevaplayan) {
		System.out.println(soran.toUpperCase() + " versus " + cevaplayan.toUpperCase());
	}
}
