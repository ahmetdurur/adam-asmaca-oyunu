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
	 * 	 kelimeriAl metodu "src/resources/turkish_dictionary.txt" adresindeki text dosyasýný satýr satýr okur 
	 * 	 her satýrdaki 0. indexten ilk boþluk karakterinin göründüðü indexe kadar olan kýsmý alýp yani kelimelerin yanýnda
	 *   bulunan etiketleri silip kelimeleri bir string listesine ekler son satýr okunduktan sonra dosyayý kapatýp listeyi döndürür 
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
