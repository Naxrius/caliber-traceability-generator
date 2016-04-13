package com.github.naxrius.traceabilitygenerator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import com.starbase.caliber.server.RemoteServerException;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import java.awt.Dimension;
import javax.swing.DropMode;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField uNameField;
	private static JPasswordField uPassField;
	private static JTextField folderIDField;
	private static JLabel lblUsername;
	private static JLabel lblPassword;
	private static JLabel lblFolder;
	private static JTextArea textArea;
	private static JButton loginButton;
	private static JButton genButton;
	static String uName;
	static String uPass;
	static String folderID;

	/**
	 * Create the frame.
	 */
	public Frame() {
		setSize(new Dimension(100, 500));
		setTitle("Caliber Traceability Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(100, 10));
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		uNameField = new JTextField();
		uNameField.setHorizontalAlignment(SwingConstants.LEFT);
		uNameField.setBackground(Color.LIGHT_GRAY);
		uNameField.setToolTipText("");
		uNameField.setColumns(10);
		
		uPassField = new JPasswordField();
		uPassField.setPreferredSize(new Dimension(6, 24));
		uPassField.setMinimumSize(new Dimension(6, 24));
		uPassField.setBackground(Color.LIGHT_GRAY);
		uPassField.setHorizontalAlignment(SwingConstants.LEFT);
		uPassField.setColumns(10);
		
		lblUsername = new JLabel("User Name:");
		lblUsername.setForeground(Color.LIGHT_GRAY);
		lblUsername.setBackground(Color.LIGHT_GRAY);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.LIGHT_GRAY);
		
		folderIDField = new JTextField();
		folderIDField.setBackground(Color.LIGHT_GRAY);
		folderIDField.setHorizontalAlignment(SwingConstants.LEFT);
		folderIDField.setColumns(10);
		
		lblFolder = new JLabel("Folder:");
		lblFolder.setForeground(Color.LIGHT_GRAY);
		
		loginButton = new JButton("Login");
		loginButton.setBorderPainted(false);
		loginButton.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		loginButton.setForeground(Color.BLACK);
		loginButton.setBackground(Color.WHITE);
		
		genButton = new JButton("Generate");
		genButton.setBorder(new CompoundBorder());
		genButton.setBackground(Color.WHITE);
		genButton.setBorderPainted(false);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setDropMode(DropMode.INSERT);
		textArea.setMaximumSize(new Dimension(4, 22));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setForeground(Color.BLACK);
		textArea.setBorder(null);
		textArea.setLineWrap(true);
		
		//text area scrolling feature
		JScrollPane scroll = new JScrollPane(textArea);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scroll))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(101)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblFolder)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblPassword)
										.addComponent(lblUsername))
									.addGap(10)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(uPassField, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
								.addComponent(uNameField, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
								.addComponent(folderIDField, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
								.addComponent(genButton, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
								.addComponent(loginButton, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
							.addGap(161)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(uNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(uPassField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(loginButton)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(folderIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFolder))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(genButton)
					.addGap(18)
					.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{uNameField, uPassField, loginButton, folderIDField, genButton, textArea, scroll, lblFolder, lblUsername, lblPassword}));
	}
	
	// Displays the frame
	public void OpenFrame(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setTitle("Caliber Traceability Generator");
					genButton.setVisible(false);
					folderIDField.setVisible(false);
					lblFolder.setVisible(false);
					frame.setVisible (true);
					setListeners();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getuName(){
		return uName;
	}
	
	public String getuPass(){
		return uPass;
	}
	
	public String getFolderID(){
		return folderID;
	}
	
	public JTextArea getTextArea(){
		return textArea;
	}
	
	public void setTextArea(String str){
		textArea.append(str);
	}
	
	//Activate listeners and hide/show components	
	public void setListeners(){
		// Login button listener
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uName = uNameField.getText();
				uPass = new String(uPassField.getPassword());
				textArea.append("Logging in...\n");
				if(TraceabilityGenerator.UserLogin(uName, uPass) != null){
					textArea.append("Login successful.\n");
					loginButton.setVisible(false);
					uNameField.setVisible(false);
					uPassField.setVisible(false);
					lblUsername.setVisible(false);
					lblPassword.setVisible(false);				
					genButton.setVisible(true);
					folderIDField.setVisible(true);
					lblFolder.setVisible(true);
					return;
				}
				textArea.append("Invalid username or password.\n");
				textArea.setCaretPosition(textArea.getDocument().getLength());
			}
		});
		
		// Generate button listener
		genButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					folderID = folderIDField.getText();
					if(TraceabilityGenerator.GetFolder(Integer.valueOf(folderID)) != null){
						textArea.append("Generating traceability...\n");
						TraceabilityGenerator.TraverseFolder();
					}
				} catch (NumberFormatException | RemoteServerException e1) {
					textArea.append("Invalid folder specified.\n");
					genButton.setVisible(true);
				} 
				return;
			}
		});
	}
}
