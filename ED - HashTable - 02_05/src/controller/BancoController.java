package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import br.edu.fatec.zl.Lista;
import model.Cliente;

public class BancoController {

	Lista<Cliente>[] hashTable = new Lista[4];
	Cliente cliente;

	public BancoController() {
		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new Lista<Cliente>();
		}
	}

	public void readHash() throws IOException {
		File dir = new File("D:\\3 Semestre\\temp");
		File arq = new File("D:\\3 Semestre\\temp", "Produto_ED_Hash_02_05.csv");

		if (dir.exists() && dir.isDirectory()) {
			if (arq.exists() && arq.isFile()) {
				FileInputStream flux = new FileInputStream(arq);
				InputStreamReader reader = new InputStreamReader(flux);
				BufferedReader buffer = new BufferedReader(reader);
				String linha = buffer.readLine();
				while (linha != null) {
					String[] vet = linha.split(";");
					cliente = new Cliente(Integer.parseInt(vet[0]), vet[1], Double.parseDouble(vet[2]));
					int hash = hash(cliente.getClientNum());
					Lista<Cliente> lista = hashTable[hash];
					if (lista.isEmpty()) {
						lista.addFirst(cliente);
					} else {
						try {
							lista.addLast(cliente);
						} catch (Exception e) {
							System.err.println(e);
						}
					}
					linha = buffer.readLine();
				}
				buffer.close();
				reader.close();
				flux.close();
			}
		}
	}

	private int hash(int num) {
		int c = 0;
		while (num > 10) {
			num = num / 10;
			c = c + 1;
		}
		return c;
	}

	public void saveHash() throws IOException {
		File dir = new File("D:\\3 Semestre\\temp");
		File arq = new File("D:\\3 Semestre\\temp", "Produto_ED_Hash_02_05.csv");

		if (dir.exists() && dir.isDirectory()) {
			int c = 0;
			StringBuffer buffer = new StringBuffer();
			while (c < 4) {
				Lista<Cliente> lista = hashTable[c];
				while (!lista.isEmpty()) {
					try {
						cliente = lista.get(0);
					} catch (Exception e) {
						System.err.println(e);
					}
					buffer.append(
							cliente.getClientNum() + ";" + cliente.getClientName() + ";" + cliente.getClientTot());
					try {
						lista.remove(0);
					} catch (Exception e) {
						System.err.println(e);
					}
				}
				c = c + 1;
			}
			String thing = buffer.toString();
			FileWriter fileWriter = new FileWriter(arq);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(thing);
			print.flush();
			print.close();
			fileWriter.close();
		} else {
			System.err.println("Diretório Inválido");
		}
	}

	public void consultaHash() {
		int numConta = Integer.parseInt(JOptionPane.showInputDialog("Insira o número da conta do cliente"));

		int hash = hash(numConta);
		Lista<Cliente> lista = hashTable[hash];
		int size = lista.size();
		int cont = 0;
		boolean test = false;
		while (cont < size) {
			try {
				cliente = lista.get(cont);
			} catch (Exception e) {
				System.err.println(e);
			}
			if (cliente.getClientNum() == numConta) {
				System.out.println("O cliente " + cliente.getClientName() + " proprietário da conta #"
						+ cliente.clientNum + " possui R$" + cliente.clientTot + " de saldo.");
				cont = size;
				test = true;
			} else {
				cont = cont + 1;
			}
		}
		if (test == false) {
			System.out.println("Não foi encontrado um cliente com esse código de conta.");
		}
	}

	public void addhash() {
		String c = JOptionPane
				.showInputDialog("Insira o número da conta, o nome do cliente e o saldo divididos por ;.");
		String[] vet = c.split(";");
		int numConta = Integer.parseInt(vet[0]);
		int hash = hash(numConta);
		Lista<Cliente> lista = hashTable[hash];
		cliente = new Cliente(numConta, vet[1], Double.parseDouble(vet[2]));
		if (lista.isEmpty()) {
			lista.addFirst(cliente);
			System.out.println("Cliente Adicionado.");
		} else {
			try {
				lista.addLast(cliente);
				System.out.println("Cliente Adicionado.");
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
	
	public void removeHash() {
		int numConta = Integer.parseInt(JOptionPane.showInputDialog("Insira o número da conta do cliente a ser removido."));
		int hash = hash(numConta);
		Lista<Cliente> lista = hashTable[hash];
		int size = lista.size();
		int cont = 0;
		boolean test = false;
		while(cont < size) {
			try {
				cliente = lista.get(cont);
			} catch (Exception e) {
				System.err.println(e);
			}
			if(cliente.getClientNum() == numConta) {
				System.out.println("O cliente "+cliente.getClientName()+" da conta #"+cliente.getClientNum()+" foi removido.");
				try {
					lista.remove(cont);
				} catch (Exception e) {
					System.err.println(e);
				}
				test = true;
				cont = size;
			}else {
				cont = cont +1;
			}
		}
		if(test == false) {
			System.err.println("Cliente não encontrado.");
		}
	}

}
