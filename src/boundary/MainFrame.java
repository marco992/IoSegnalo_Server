package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerComunicazione;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainFrame extends JFrame {
    Thread Thread1 = null;
    ControllerComunicazione sm;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setResizable(false);
		setTitle("IoSegnalo Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel statoLbl = new JLabel("OFFLINE");
		statoLbl.setForeground(Color.RED);
		statoLbl.setBounds(164, 40, 58, 14);
		contentPane.add(statoLbl);
		JButton fermaBtn = new JButton("Ferma Server");
		fermaBtn.setEnabled(false);
		JButton avviaBtn = new JButton("Avvia Server");
		avviaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		avviaBtn.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {

			        Thread1 = new Thread(new Thread1());
			        Thread1.start();
			        avviaBtn.setEnabled(false);
					fermaBtn.setEnabled(true);

			        statoLbl.setText("ONLINE");
			        statoLbl.setForeground(Color.green);
			
			}
		});
		avviaBtn.setBounds(26, 97, 120, 23);
		contentPane.add(avviaBtn);
		

		fermaBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					sm.setEnable(false);
					avviaBtn.setEnabled(true);
					fermaBtn.setEnabled(false);
					Thread1.join();
					statoLbl.setText("OFFLINE");
			        statoLbl.setForeground(Color.red);

			        
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
			}
		});
		fermaBtn.setBounds(177, 97, 120, 23);
		contentPane.add(fermaBtn);
		
		JLabel label1 = new JLabel("Stato server: ");
		label1.setBounds(71, 40, 84, 14);
		contentPane.add(label1);
		
		

	}
	class Thread1 implements Runnable {
        @Override
        public void run() {

        	sm = new ControllerComunicazione(7777); 
        	try {
				sm.ascolta();
			} catch (ClassNotFoundException e) {
							System.err.println("Errore durante la creazione del Server"); 

				e.printStackTrace();
			}
        }
	}
}
