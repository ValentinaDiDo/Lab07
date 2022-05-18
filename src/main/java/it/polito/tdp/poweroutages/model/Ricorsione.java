package it.polito.tdp.poweroutages.model;
import java.util.*;
public class Ricorsione {

	List<Outages> outages;
	int anni;
	int ore;
	List<Outages> best;
	int bestCustomers = 0;
	public void attivaRicorsione(int anni, int ore, List<Outages> outages) {
		//get all rilevamenti in quel nerc
		
		this.outages = outages;
		this.anni = anni;
		this.ore = ore;
		
		//attivo ricorsione 
		List<Outages> parziale = new ArrayList<Outages>();
		ricorsione(parziale);
	}
	
	public void ricorsione(List<Outages> parziale) {
		
		//inserisco primo elemento
		if(parziale.size() == 0) {
			parziale.add(outages.get(0));
		}
		
		//esco dal ciclo se ho superato il numero di anni o ore max
		if(oreSuperate(parziale) || anniSuperati(parziale)) {
			return ;
		}
		
		//aggiorno soluzione migliore
		int customersParziale = totCustomers(parziale);
		if(customersParziale > bestCustomers) {
			best.clear();
			best.addAll(parziale);
		//	best = parziale;
			bestCustomers = customersParziale;
		}
		
		//caso normale
		for(Outages o : outages) {
			parziale.add(o); 
			ricorsione(parziale); 
			parziale.remove(parziale.size()-1); //backtracking
		}
		
	}

	
	public int totCustomers(List<Outages> parziale) {
		int sum = 0;
		for(Outages o : parziale) {
			sum += o.getCustomers();
		}
		return sum;
	}
	
	public boolean anniSuperati(List<Outages> parziale) {
		if(parziale.size()>=2) {
			int o1 = parziale.get(0).getAnno();
			int o2 = parziale.get(parziale.size()-1).getAnno();
			
			if(o2-o1>anni)
				return true;
			return false;
		}
		return false;
	}
	public boolean oreSuperate(List<Outages> parziale) {
		if(parziale.size()>=2) {
		
			int o1 = parziale.get(0).getSpan();
			int o2 = parziale.get(parziale.size()-1).getSpan();
			
			if(o2-o1 > ore) 
				return true;
			return false;
		}
		return false;
	}

	public List<Outages> getBest() {
		return best;
	}

	

	public int getBestCustomers() {
		return bestCustomers;
	}

	
}
