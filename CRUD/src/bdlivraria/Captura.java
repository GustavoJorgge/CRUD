package bdlivraria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Captura implements ActionListener {
	
	private JButton consultar;
	private JButton deletar;
	private JButton insere;
	private JButton sair;
	
	public void janela() {
		
		JFrame frame = new JFrame();
		consultar = new JButton("Consultar");
		deletar = new JButton ("Deletar");
		insere = new JButton("Inserir");
		sair = new JButton("Sair");
		
		consultar.addActionListener(this);
		deletar.addActionListener(this);
		insere.addActionListener(this);
		sair.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
