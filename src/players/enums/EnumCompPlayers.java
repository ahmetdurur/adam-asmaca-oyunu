package players.enums;

/*
 * 	@author Ahmet Durur
 * 
 * Farkl� enemy yapay zekalar i�in tip havuzu sa�lar 
 * e�er birden fazla yapay zeka modu kodlan�rsa kullan�lacakt�r
 * girilen isimler eski bir oyunun baz� karakterleridir
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
