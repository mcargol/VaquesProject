package net.xaviersala.Vaca.Model;

import java.util.ArrayList;
import java.util.List;

public class Camio {
	
	private double pesMaxim;
    private double pesActual;
    private List<Vaca> vaques;
    private double litres;
	
    public Camio(double pesMax)
    {
        pesMaxim = pesMax;
        pesActual = 0;
        vaques = new ArrayList<Vaca>();
    } 
    
    public double getPesMaxim() {
		return pesMaxim;
	}

	public double getPesActual() {
		return pesActual;
	}

	public List<Vaca> getVaques() {
		return vaques;
	}

	public double getLitres() {
		return litres;
	}

	public double getLitresRecalculant() { 
    	return vaques.stream().mapToDouble(i -> i.GetLitres()).sum();
    }

    public boolean entraVaca(Vaca vaca)
    {
        if (pesActual + vaca.getPes() > pesMaxim)
            return false;

        vaques.add(vaca);
        pesActual += vaca.getPes();
        return true;
    }

    public boolean treuVaca(Vaca vaca)
    {
        boolean resultat = vaques.remove(vaca);

        if (resultat)
            pesActual -= vaca.getPes();
        return resultat;
    }

    @Override
    public String toString() {
    	return "Camio max: " + pesMaxim + "(Pes actual: " + pesActual + " - vaques: " + vaques.size() + ")";
    }

}
