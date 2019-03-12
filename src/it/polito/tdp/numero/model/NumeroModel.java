package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {

	// che operazioni deve fare?Pensando al gioco. Quelle strettamente collegate alla logica di gioco. 
	// sicuramente un metodo per una nuova partita 
	// un metodo per il tentativo 
	// t max ecc anche, non fanno parte dell'interfaccia grafica. +
	
	private final int NMAX = 100;
	private final int TMAX = 8;

	public int getTMAX() {
		return TMAX;
	}

	private int segreto;
	
	public int getSegreto() {
		return segreto;
	}

	private int tentativiFatti;
	
	public int getTentativiFatti() {
		return tentativiFatti;
	}

	private boolean inGioco = false;
	public boolean isInGioco() {
		return inGioco;
	}


	//COSTRUTTORE CONTROLLER 
	public NumeroModel() {
		inGioco=false;
	}
	
	
	//METODO NUOVA PARTITA.Verranno usati dal controllore.
	//serve che mi restituisca qualcosa? volendo si, ma conviene avere un metodo get su inGioco.
	public void newGame() {
		inGioco=true;
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti = 0;	

	}
	
	//METODO PER FARE I TENTATIVI
	//serve tornare un valore? si!! dal controllore devo sapere com'era questo tentativo 
	//-1 troppo basso, 0 corretto, +1 se troppo alto 
	
	/**
	 * Metodo per effettuare un tentativo
	 * @param t il tentativo
	 * @return 1 se il tentativo è troppo alto, -1 se è troppo basso, 0 se l'utente ha indovinato
	 */
	
	
	public int tentativo(int t) {
		// controllare se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è terminata"); 
		}
		
		//controllare sel'imput è corretto
		// non sul tipo come prima, perchè suppongo che sia corretto. Controllo però il range 
		if(!tentativoValido(t)) {
			throw new InvalidParameterException(String.format("Devi inserire un numero tra %d e %d",1,NMAX ));
		}
		
		//gestisci tentativo 
		this.tentativiFatti++;
		if(this.tentativiFatti==this.TMAX) {
			//la partita è finita perchè ho esaurito i tentativi 
			this.inGioco=false;
		}
		
		if(t== this.segreto) {
			this.inGioco=false;
			return 0;
		}
		if(t>this.segreto) {
			return 1; 
			
		}
		 return -1;
	}
	
	public boolean tentativoValido(int t) {
		if(t<1 || t> NMAX) {
			return false;
			}else 
				return true;
		
		
	}
}
