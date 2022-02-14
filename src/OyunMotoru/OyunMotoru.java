package OyunMotoru;

import java.io.IOException;

/*
 	* @author Ahmet Durur
 	* kendisinden t�retilen oyunModu alts�n�flar� i�in abstract metodlar� sa�lar
 	* baslangic anonsu static metoduyla oyun ba�lama anonsunu ekrana yazd�r�r
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
