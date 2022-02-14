package players;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Kelime.HarfAdet;
import Kelime.Kelime;
import players.interfaces.Human;
import players.interfaces.Player;
import players.interfaces.Soran;

/*
 *  @author Ahmet Durur
 */

public class SoranHuman implements Human , Soran {
	private Integer harfAdet ;
	private String name ;
	private Kelime kelime;
	

	public SoranHuman () {
		setName();
	}
	
	/*	kelimeSec metodu Soran Interface'inden override edildi. Kullan�c�dan s�zl�kte bulunan bir kelime girmesini bekler
	 *  e�er kullan�c�n�n girdi�i kelime s�zl�kte bulunuyorsa se�ilen kelimeyle yeni bir Kelime nesnesi olu�turup d�nd�r�r
	 *  e�er s�zl�kte bulunmuyorsa do�ru girdi al�nana kadar tekrar eder.
	 */
	
	
	/* @Deprecated kelimeSec metodu kullan�c�n�n se�ti�i kelime s�zl�kte varm� yokmu kontrol etmesi amac�yla yaz�lm��t�r
	 * geli�tirme a�amas�nda geli�tirici Ahmet Durur taraf�ndan kullan�lan bu metot oyunu oynayan yar��mac�n�n akl�nda hile ��phesi olmamas� i�in 
	 * kullan�mdan kald�r�lm��t�r.
	 * 
	 * E�er bu metodu test ama�l� kullanmak isterseniz OyunModuA class�n�n init metodu i�ine kelimeInit() metodundan �nce
	 * soranHuman.kelimeSec(); sat�r�n� ekleyiniz
	 */
	@Override // Soran'dan override edildi
	public Kelime kelimeSec() {
		System.out.println("Se�ti�iniz Kelimeyi Yaz�m Hatas� Yapmadan Dikkatlice Giriniz. Merak etmeyin hile yapm�yoruz yaln�zca se�ti�iniz kelimenin s�zl�kte bulunup"
						 + " bulunmad���ndan emin olmak istiyoruz");
		Scanner sc = new Scanner(System.in);
		List<String> sozluk = new ArrayList<>();
		while(true) {
			System.out.println("L�tfen s�zl���m�zde bulunan ve rakibinizin se�ti�i uzunlukta bir kelime se�iniz : ( AI'�n se�ti�i uzunluk :  " + harfAdet + " )");
			String s = sc.next();
			try {
				sozluk = Player.kelimeleriAl();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(sozluk.contains(s) && s.length() == harfAdet) {
				System.out.println("Ge�erli bir kelime se�tiniz");
				kelime = new Kelime(s);
				return kelime ;
			} else {
				if (s.length() != harfAdet ) {
					System.out.println("L�tfen " + harfAdet + " harfli bir kelime se�in");
				} else {
					System.out.println("L�tfen kelimeyi hatas�z bir �ekilde girin ");
				}
			}
		}
	}
	
	// Kullan�c� ismini al�r
	@Override // Humandan override edildi
	public void setName() {
		this.name = Human.name();
	}
	
	// kUllan�c� ismini d�nd�r�r
	@Override // Humandan override edildi
	public String getName() {
		return this.name;
	}

	// harfAdet alan�n� d�nd�r�r
	public Integer getHarfAdet() {
		return harfAdet;
	}
	
	// harfAdet alan�n� set eder
	public void setHarfAdet(Integer harfAdet) {
		this.harfAdet = harfAdet;
	}
	
	// Soran'dan override edildi 
	@Override
	public List<Integer> harfKontrol(Character harf) {
		return null;
		// TODO Auto-generated method stub
		
	}

	

}
