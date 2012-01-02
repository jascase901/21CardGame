package org.jason.cards;


import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton; 
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Window extends JFrame {
	private JButton hit;	
	private JButton stay;
	private JLabel message_box;	
	private String message = "Start Game!!!";
	private JPanel bottom = new JPanel();
	private JPanel bottom_left = new JPanel();
	private JPanel bottom_right = new JPanel();
	private JPanel center = new JPanel();
	private JPanel top_center = new JPanel();
	private JPanel bottom_center = new JPanel();
	private Deck deck = new Deck();
	private Hand player_hand = new Hand();
	private Hand cpu_hand = new Hand();
	/** Returns an ImageIcon, or null if the path was invalid. */
	
	protected ImageIcon createImageIcon(String path,
	                                           String description) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	public Window(){
		super("Black Jack");
		setLayout(new BorderLayout());
		LineBorder border = new LineBorder(Color.GRAY, 1);
	
		//***center start***
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		add(center, BorderLayout.WEST);
		center.add(top_center);
		center.add(bottom_center);
		
		top_center.setLayout(new FlowLayout());
		
		
		bottom_center.setLayout(new FlowLayout());
		
		
		//***center end***
		
		//***south-start***
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		add(bottom, BorderLayout.SOUTH);
		
		
		bottom.add(bottom_left);
		bottom.add(bottom_right);
		
		
		hit = new JButton("Hit");
		stay = new JButton("Stay");
		bottom_left.setLayout(new GridLayout(2,1,1,1));
		bottom_left.add(hit);
		bottom_left.add(stay);
		
													
		message_box=new JLabel(message, SwingConstants.LEADING);
		message_box.setBorder(border);
		message_box.setFont(new Font("Serif", Font.BOLD, 48));
		
		bottom_right.setLayout(new BorderLayout());
		bottom_right.add(message_box, BorderLayout.SOUTH);
		
		//***south-end***
		
		
		thehandler handler = new thehandler();
		hit.addActionListener(handler);
		stay.addActionListener(handler);
		
		//***card game logic***
		PlayCard workingCard;//the card that just got taken out of the deck
		
		
		deck.generate();
		deck.shuffle();
		//give player 2 cards
		workingCard = deck.poll();
		//ImageIcon cardImage = createImageIcon("back-blue-75-1.png", "");
		//player_hand.addCard(workingCard);
		//bottom_center.add(new JButton(cardImage));
	
		
		workingCard = deck.poll();
		player_hand.addCard(workingCard);
		bottom_center.add(new JButton(workingCard.getImgUri())); 
		
		System.out.println(deck.poll().getImgUri());
		
		
		
	}
	private class thehandler implements ActionListener{
		//private Hand player_hand = new Hand();
		
		
		public void actionPerformed(ActionEvent event){
		//player_hand.addCard(deck.poll());	
		if (event.getSource()==hit){
			message = "hit";
			message_box.setText(message);
			
			PlayCard workingCard = deck.poll();
			player_hand.addCard(workingCard);
			
			if(player_hand.isOver21()){
				message = "PLAYER BUST";
				message_box.setText(message);
			}
			
			
		
			
			
			bottom_center.add(new JButton(workingCard.getImgUri())); 
			bottom_center.revalidate();
			bottom_center.validate();
		
		}
		else if(event.getSource()==stay){
		message = "stay";
		message_box.setText(message);
		
		PlayCard workingCard = deck.poll();
		cpu_hand.addCard(workingCard);
		
		bottom_center.add(new JButton(workingCard.getImgUri())); 
		bottom_center.revalidate();
		bottom_center.validate();
		
		
		}
		
//		JOptionPane.showMessageDialog(null, string);
	}
	}
}
	
