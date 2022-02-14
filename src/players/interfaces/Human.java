package players.interfaces;

import java.util.Scanner;

/*	@author Ahmet Durur
 * 
 *  Player interface'ini extend eder kendisinden t�retilen Human tipli classlar i�in metodlar sa�lar
 *  name static metoduyla kullan�c�n�n ismini al�r
 */

public interface Human extends Player {
	
	public static String name() {
		System.out.println("�sminizi Giriniz");
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}
	public void setName ();
	public String getName ();
}
