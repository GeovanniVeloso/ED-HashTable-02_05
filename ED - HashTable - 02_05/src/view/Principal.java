package view;

import java.io.IOException;
import javax.swing.JOptionPane;
import controller.BancoController;

public class Principal {

	public static void main(String[] args) {
		BancoController bc = new BancoController();
		int c = 9;
		try {
			bc.readHash();
		} catch (IOException e) {
			System.err.println(e);
		}
		while (c != 0) {
			c = Integer.parseInt(JOptionPane.showInputDialog(
					"Digite 1 para consultar um cliente;\nDigite 2 para adicionar um cliente;\nDigite 3 para remover um cliente;\nDigite 4 para salvar;\nDigite 0 para sair."));
			switch (c) {
			case 1:
				bc.consultaHash();
				break;
			case 2:
				bc.addhash();
				break;
			case 3:
				bc.removeHash();
				break;
			case 4:
				try {
					bc.saveHash();
				} catch (IOException e) {
					System.err.println(e);
				}
				break;
			case 0:
				System.out.println("Finalizando...");
				break;
			default:
				System.err.println("Opção Inválida.");
				break;
			}
		}

	}

}
