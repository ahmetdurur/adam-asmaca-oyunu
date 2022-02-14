package kontrol;

public class StringControl implements Kontrol {
	
	/*
	 * @author Ahmet Durur
	 * 
	 * Gönderilen stringin tüm karakterleri numerikse true döndürür
	 * deðilse gönderilen mesajý konsola bastýrýp false döndürür
	 */
	public static boolean tumKarakterlerNumerikmiKontrol(String string , String mesaj ) {
		for (Character c : string.toCharArray()) {
			if(!Character.isDigit(c)) {
				if(mesaj != null) {
					System.out.println(mesaj);
				}
				return false ;
			}
		}
		return true;
	}

}
