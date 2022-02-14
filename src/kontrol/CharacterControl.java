package kontrol;

/*
 * @author Ahmet Durur
 */

public class CharacterControl implements Kontrol {
	
	/*
	 * karakter numeric bir karakterse true döndürür
	 * deðilse invoke edilen yerden gönderilen mesajý ekrana bastýrýp false döndürür
	 */
	
	public static boolean numericKontrol(Character c , String mesaj) {
		if (Character.isDigit(c)) {return true ;} 
		else { 
			if(mesaj != null) {
				System.out.println(mesaj);
			}
			return false;
		}		
	}
		
	/*
	 * karakter harf bir karakterse true döndürür
	 * deðilse invoke edilen yerden gönderilen mesajý ekrana bastýrýp false döndürür
	 */
	
	public static boolean harfKontrol (Character c , String mesaj) {
		if (Character.isLetter(c)) {return true ;} 
		else {
			if(mesaj != null) {
				System.out.println(mesaj);
			}
			return false; 
		}
	}
	
}
