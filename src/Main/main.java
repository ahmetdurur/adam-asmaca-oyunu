package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Scanner;

import OyunMotoru.OyunModuA;
import OyunMotoru.OyunModuB;
import kontrol.CharacterControl;
import kontrol.StringControl;
import players.CevaplayanAI;
import players.CevaplayanHuman;
import players.SoranAI;
import players.SoranHuman;
import players.enums.EnumCompPlayers;
import players.interfaces.Player;
import yapayZeka.KarakterAgirliklariHesapla;
import yapayZeka.KelimeAltKume;
import yapayZeka.ListedekiTumKarakterler;


public class main {
	
	public static void main (String[] args) throws IOException {
		oyunModuSec();
		
//		for(String s : KelimeAltKume.kelimeAltKumeGetir(Arrays.asList('a','_','e','_')) ) {
//			System.out.println(s);
//		}
 	}
	
	private static void oyunModuSec() throws IOException {
		System.out.println("Oyun Modu A: Kelime Belirleyin Bilgisayar Tahmin Etsin");
		System.out.println("Oyun Modu B: Bilgisayar Kelime Belirlesin Siz Tahmin Edin");
		System.out.println("Lütfen Oyun Modu Seçmek Ýçin \"A\" veya \"B\" Yazýp ENTER Tuþuna Basýn  ");
		Scanner sc = new Scanner(System.in);
		Character c  =sc.next().charAt(0);
		if(c == 'a' || c == 'A') {
			System.out.println("a seçildi");
			OyunModuA a = new OyunModuA();
		}
		if(c == 'b' || c == 'B') {
			System.out.println("b seçildi");
			OyunModuB b = new OyunModuB();
		}
	}
}

