package domen;

public class Obuca {
	private int id_obuce;
  private int cena;
  private int broj;
  private String vrsta;
  private boolean muska;
public Obuca(int cena, int broj, String vrsta, boolean muska) {
	super();
	this.cena = cena;
	this.broj = broj;
	this.vrsta = vrsta;
	this.muska = muska;
}
public Obuca(int id_obuce, int cena, int broj, String vrsta, boolean muska) {
	super();
	this.id_obuce = id_obuce;
	this.cena = cena;
	this.broj = broj;
	this.vrsta = vrsta;
	this.muska = muska;
}
public int getId_obuce() {
	return id_obuce;
}
public void setId_obuce(int id_obuce) {
	this.id_obuce = id_obuce;
}
public int getCena() {
	return cena;
}
public void setCena(int cena) throws Exception {
	if(cena>0) {
	this.cena = cena;
	}
	else  
		throw new Exception("Cena mora biti veca od 0.");
}
public int getBroj() {
	return broj;
}
public void setBroj(int broj) throws Exception {
	if(broj>0) {
	this.broj = broj;
	}
	else
		throw new Exception("Morate uneti kolicinu.");
}
public String getVrsta() {
	return vrsta;
}
public void setVrsta(String vrsta) {
	this.vrsta = vrsta;
}
public boolean isMuska() {
	return muska;
}
public void setMuska(boolean muska) {
	this.muska = muska;
}
@Override
public String toString() {
	if (muska)
	    return "Cena: " + cena + "\nBroj: " + broj + "\nVrsta: " + vrsta + "\nMuska obuca"  ;
	else 
	   return	"Cena: " + cena + "\nBroj: " + broj + "\nVrsta: " + vrsta + "\nZenska obuca"  ;
}


}
