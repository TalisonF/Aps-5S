package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Servidor.*;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.DropMode;

public class View extends JFrame {

	private JPanel contentPane;
	private static JTextPane logServidor;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		
		super("Servidor");
		
		
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation(((int)ds.getWidth() - (int)dw.getWidth()) / 2, ((int)ds.getHeight() - (int)dw.getHeight()) / 2);
        
        
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setVisible(false);
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnFechar.setBounds(33, 3, 114, 25);
		contentPane.add(btnFechar);
		
		logServidor = new JTextPane();
		logServidor.setEditable(false);
		logServidor.setContentType("text");
		logServidor.setForeground(Color.BLACK);
		logServidor.setBounds(12, 40, 424, 217);
		contentPane.add(logServidor);
		
		JButton inicarServidor = new JButton("Inicar o servidor");
		inicarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logServidor.setText("Iniciando o servidor...\n");
				new ServidorSocket();
				inicarServidor.setText("Servidor Inciado");
				inicarServidor.setEnabled( false );
				
				btnFechar.setVisible(true);
				
			}
		});
		inicarServidor.setBounds(258, 3, 163, 25);
		contentPane.add(inicarServidor);
		
		setVisible(true);
	}
	
	public void centralizarComponente() {
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation(((int)ds.getWidth() - (int)dw.getWidth()) / 2, ((int)ds.getHeight() - (int)dw.getHeight()) / 2);
    }
	
	public static void AddLog(String a) {
		logServidor.setText(getLog() + a);
	}
	public static String getLog() {
		return logServidor.getText();
	}
}
