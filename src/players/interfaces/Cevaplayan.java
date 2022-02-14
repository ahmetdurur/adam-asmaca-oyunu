package players.interfaces;

import java.io.IOException;
import java.util.List;

/*	@author Ahmet Durur
 * 
 *  Player interface'ini extend eder kendisinden türetilen Cevaplayan tipli classlar için metodlar saðlar
 */
public interface Cevaplayan extends Player {
	
	public Integer harfAdediBelirle() throws IOException;
	public Character harfSec(List<Character> cachelenenHarfler , List<Character> gosterilenKelime2) throws IOException;

}
