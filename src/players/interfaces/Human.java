package players.interfaces;

import java.util.Scanner;

/*	@author Ahmet Durur
 * 
 *  Player interface'ini extend eder kendisinden türetilen Human tipli classlar için metodlar saðlar
 *  name static metoduyla kullanýcýnýn ismini alýr
 */

public interface Human extends Player {
	
	public static String name() {
		System.out.println("Ýsminizi Giriniz");
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}
	public void setName ();
	public String getName ();
}
