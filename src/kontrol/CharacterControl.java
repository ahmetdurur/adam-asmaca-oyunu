package kontrol;

/*
 * @author Ahmet Durur
 */

public class CharacterControl implements Kontrol {
	
	/*
	 * karakter numeric bir karakterse true d�nd�r�r
	 * de�ilse invoke edilen yerden g�nderilen mesaj� ekrana bast�r�p false d�nd�r�r
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
	 * karakter harf bir karakterse true d�nd�r�r
	 * de�ilse invoke edilen yerden g�nderilen mesaj� ekrana bast�r�p false d�nd�r�r
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
