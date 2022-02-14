package players.interfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public interface Player {
	/*
	 * 	 kelimeriAl metodu "src/resources/turkish_dictionary.txt" adresindeki text dosyas�n� sat�r sat�r okur 
	 * 	 her sat�rdaki 0. indexten ilk bo�luk karakterinin g�r�nd��� indexe kadar olan k�sm� al�p yani kelimelerin yan�nda
	 *   bulunan etiketleri silip kelimeleri bir string listesine ekler son sat�r okunduktan sonra dosyay� kapat�p listeyi d�nd�r�r 
	 */
	public static List<String> kelimeleriAl() throws IOException {
		File dosya = new File("src/resources/turkish_dictionary.txt");
		BufferedReader br = null;
		List<String> kelimeSozluk = new ArrayList<>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(dosya) , "UTF8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String str;
		
		while ((str = br.readLine()) != null) {
			kelimeSozluk.add(str.substring(0, str.indexOf(" ")));
		}
		br.close();
		return kelimeSozluk;
	}
	
	public static List<String> kelimeleriAl(Integer harfAdet ) throws IOException {
		File dosya = new File("src/resources/turkish_dictionary.txt");
		BufferedReader br = null;
		List<String> kelimeSozluk = new ArrayList<>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(dosya) , "UTF8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String str;
		
		while ((str = br.readLine()) != null) {
			if(str.substring(0, str.indexOf(" ")).length() == harfAdet) {
				kelimeSozluk.add(str.substring(0, str.indexOf(" ")));
			}
		}
		br.close();
		return kelimeSozluk;
	}
	
	public static Integer enUzunKelime() throws IOException {
		List<String> sozluk = Player.kelimeleriAl();
		Integer max = 0;
		for (String string : sozluk) {
			if(string.length()> max) {
				max = string.length();
			}
		}
		return max;
	}
	

}
