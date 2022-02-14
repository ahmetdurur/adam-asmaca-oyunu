package kontrol;

public class StringControl implements Kontrol {
	
	/*
	 * @author Ahmet Durur
	 * 
	 * G�nderilen stringin t�m karakterleri numerikse true d�nd�r�r
	 * de�ilse g�nderilen mesaj� konsola bast�r�p false d�nd�r�r
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
