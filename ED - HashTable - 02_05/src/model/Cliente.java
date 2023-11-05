package model;

public class Cliente {

	public int clientNum;
	public String clientName;
	public double clientTot;
	
	public Cliente(int clientNum, String clientName, double clientTot) {
		this.clientNum = clientNum;
		this.clientName = clientName;
		this.clientTot = clientTot;
	}

	public int getClientNum() {
		return clientNum;
	}

	public String getClientName() {
		return clientName;
	}

	public double getClientTot() {
		return clientTot;
	}
	
	

}
