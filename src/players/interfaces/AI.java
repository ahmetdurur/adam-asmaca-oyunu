package players.interfaces;

import java.io.IOException;
import java.util.Scanner;

import kontrol.CharacterControl;
import kontrol.NumberKontrol;
import kontrol.StringControl;
import players.enums.EnumCompPlayers;

/* @author Ahmet Durur
 * 
 * Player interface'ini extend eder kendisinden t�retilen AI tipli classlar i�in metodlar sa�lar 
 * AIsec static metoduyla enemy yapay zeka tipinin se�ilmesini sa�lar
 */

public interface AI extends Player {
	public static EnumCompPlayers AIsec() {
		for (EnumCompPlayers compPlayer : EnumCompPlayers.values()) {
			System.out.println(compPlayer.getId() + " " + compPlayer);
		}
		System.out.println("L�tfen Yukar�da Listelenen Yapay Zekalardan Yar��mak �stedi�inizin Numaras�n� Giriniz: ");
		Scanner sc = new Scanner(System.in);
		String secim = sc.next();
		while(!StringControl.tumKarakterlerNumerikmiKontrol(secim, "L�tfen Numerik Karakter Girin") ||
				!NumberKontrol.numericRangeKontrol(1, 4, Integer.parseInt(secim), "Se�iminiz 1 ve 4 aras�nda olmal�d�r") )
			
		{
			System.out.println("L�tfen Yukar�da Listelenen Yapay Zekalardan Yar��mak �stedi�inizin Numaras�n� Giriniz: ");
			secim = sc.next();
		}
		return EnumCompPlayers.fromId(Integer.parseInt(secim));
	}
	public void aIPlayerSec();
	public EnumCompPlayers getAIPlayer();
}
