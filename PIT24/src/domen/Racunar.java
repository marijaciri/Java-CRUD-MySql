package domen;

public class Racunar {
	private int id_racunara;
    private String vrsta;
    private double cena;
    private boolean nov;
	public Racunar(int id, String vrsta, double cena, boolean nov) {
		super();
		this.id_racunara=id;
		this.vrsta = vrsta;
		this.cena = cena;
		this.nov = nov;
	}
	public int getId_racunara() {
		return id_racunara;
	}
	public void setId_racunara(int id_racunara) {
		this.id_racunara = id_racunara;
	}
	public String getVrsta() {
		return vrsta;
	}
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public boolean isNov() {
		return nov;
	}
	public void setNov(boolean nov) {
		this.nov = nov;
	}
	public String status(boolean nov) {
		if(nov)
			return "Nov racunar.";
		else
			return "Polovan racunar.";
	}
	@Override
	public String toString() {
		return "Racunar: " + vrsta + "\nCena: " + cena + "\n" + status(nov)+"\n\n" ;
	}
    
    
    
}
