package players.enums;

/*
 * 	@author Ahmet Durur
 * 
 * Farklý enemy yapay zekalar için tip havuzu saðlar 
 * eðer birden fazla yapay zeka modu kodlanýrsa kullanýlacaktýr
 * girilen isimler eski bir oyunun bazý karakterleridir
 */
public enum EnumCompPlayers {
	HARBINGER (1, "Harbinger")  ,
	NESSAJ  (2, "Nessaj")  , 
	CRIXALIS  (3, "crixalis")  ,
	GOBLIN_SHREDDER (4, "goblin_shredder")  ;
	
	int id ;
	EnumCompPlayers(int i, String string) { this.id = i ;}
	
	public Integer getId() {
	    return id;
	  }
	
	public static EnumCompPlayers fromId(Integer id) {
	    for (EnumCompPlayers ap : EnumCompPlayers.values()) {
	      if (ap.getId().equals(id)) {
	        return ap;
	      }
	    }
	    return null;
	 }
	
	
	
}
