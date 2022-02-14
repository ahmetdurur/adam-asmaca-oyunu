package players.interfaces;

import java.io.IOException;
import java.util.Scanner;

import kontrol.CharacterControl;
import kontrol.NumberKontrol;
import kontrol.StringControl;
import players.enums.EnumCompPlayers;

/* @author Ahmet Durur
 * 
 * Player interface'ini extend eder kendisinden türetilen AI tipli classlar için metodlar saðlar 
 * AIsec static metoduyla enemy yapay zeka tipinin seçilmesini saðlar
 */

public interface AI extends Player {
	public static EnumCompPlayers AIsec() {
		for (EnumCompPlayers compPlayer : EnumCompPlayers.values()) {
			System.out.println(compPlayer.getId() + " " + compPlayer);
		}
		System.out.println("Lütfen Yukarýda Listelenen Yapay Zekalardan Yarýþmak Ýstediðinizin Numarasýný Giriniz: ");
		Scanner sc = new Scanner(System.in);
		String secim = sc.next();
		while(!StringControl.tumKarakterlerNumerikmiKontrol(secim, "Lütfen Numerik Karakter Girin") ||
				!NumberKontrol.numericRangeKontrol(1, 4, Integer.parseInt(secim), "Seçiminiz 1 ve 4 arasýnda olmalýdýr") )
			
		{
			System.out.println("Lütfen Yukarýda Listelenen Yapay Zekalardan Yarýþmak Ýstediðinizin Numarasýný Giriniz: ");
			secim = sc.next();
		}
		return EnumCompPlayers.fromId(Integer.parseInt(secim));
	}
	public void aIPlayerSec();
	public EnumCompPlayers getAIPlayer();
}
