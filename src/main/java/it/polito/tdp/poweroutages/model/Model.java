package it.polito.tdp.poweroutages.model;

import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	Ricorsione ricorsione;
	
	public Model() {
		podao = new PowerOutageDAO();
		ricorsione = new Ricorsione();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	public void attivaRicorsione(Nerc n, int anni, int ore) {
		List<Outages> o = podao.getNercOutages(n.getId());
		ricorsione.attivaRicorsione(anni, ore, o);
	}
	public List<Outages> getBest(){
		
		return this.ricorsione.getBest();
	}
	public int bestCustomers() {
		return this.ricorsione.getBestCustomers();
	}
	
	

}
