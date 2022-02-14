package players.interfaces;

import java.util.List;
import java.util.Map;

import Kelime.Kelime;

/*	@author Ahmet Durur
 * 
 *  Player interface'ini extend eder kendisinden t�retilen Soran tipli classlar i�in metodlar sa�lar
 */
public interface Soran extends Player{

	public Kelime kelimeSec();
	public void setHarfAdet(Integer harfAdet);
	public List<Integer> harfKontrol(Character harf);
}
