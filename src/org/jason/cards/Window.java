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
import java.awt.Graphics;
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
	private JButton quit;
	private JButton replay;
	private JLabel message_box;	
	private String message = "Start Game!";
	private JPanel bottom = new JPanel();
	private JPanel bottom_left = new JPanel();
	private JPanel bottom_right = new JPanel();
	private JPanel center = new JPanel();
	private JPanel top_center = new JPanel();
	private JPanel bottom_center = new JPanel();
	private Deck deck = new Deck();
	private Hand player_hand = new Hand();
	private Hand dealer_hand = new Hand();
	private JLabel player_score = new JLabel("");
	private JLabel dealer_score = new JLabel();
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
		//bottom.setOpaque(false);
		bottom_left.setOpaque(false);
		bottom_right.setOpaque(false);
		center.setOpaque(false);
		top_center.setOpaque(false);
		bottom_center.setOpaque(false);
		
		
	
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
		quit = new JButton("Quit");
		replay = new JButton("Replay");
		
		bottom_left.setLayout(new GridLayout(2,1,1,1));
		bottom_left.add(hit);
		bottom_left.add(stay);
		bottom_left.add(replay);
		bottom_left.add(quit);
		
		
		
		
													
		message_box=new JLabel(message, SwingConstants.LEADING);
		message_box.setBorder(border);
		message_box.setFont(new Font("Serif", Font.BOLD, 48));
		
		bottom_right.setLayout(new BorderLayout());
		bottom_right.add(message_box, BorderLayout.SOUTH);
		//bottom_right.add(player_score, BorderLayout.SOUTH);
		
		
		//***south-end***
		
		
		thehandler handler = new thehandler();
		hit.addActionListener(handler);
		stay.addActionListener(handler);
		quit.addActionListener(handler);
		replay.addActionListener(handler);
		
		
		//***card game logic***
		PlayCard workingCard;//the card that just got taken out of the deck
		
		
		deck.generate();
		deck.shuffle();
		//give player 2 cards
		workingCard = deck.poll();
		ImageIcon cardImage = createImageIcon("images/"+workingCard.getImgUri(), "");
		player_hand.addCard(workingCard);
		bottom_center.add(new JButton(cardImage));
	
		workingCard = deck.poll();
		cardImage = createImageIcon("images/"+workingCard.getImgUri(), "");
		player_hand.addCard(workingCard);
		bottom_center.add(new JButton(cardImage));
		
	
	
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
			ImageIcon cardImage = createImageIcon("images/"+workingCard.getImgUri(), "");
			player_hand.addCard(workingCard);
			
			
			if(player_hand.isOver21()){
				message = "PLAYER BUST";
				message_box.setText(message);
				hit.setEnabled(false);
				stay.setEnabled(false);

				
			}
			
			
		
			
			
			bottom_center.add(new JButton(cardImage));			
			bottom_center.revalidate();
			bottom_center.validate();
		
		}
		else if(event.getSource()==replay){
			int index = bottom_center.getComponentCount();
			int index2 = top_center.getComponentCount();
			for (int i=0; i<index; i++){
				bottom_center.getComponent(i).setVisible(false);
			}
			for (int i=0; i<index2; i++){
				top_center.getComponent(i).setVisible(false);
			}
			deck.reset();
			deck.generate();
			deck.shuffle();
			player_hand.reset();
			dealer_hand.reset();
			
			//give player 2 cards
			PlayCard workingCard;
			workingCard = deck.poll();
			ImageIcon cardImage = createImageIcon("images/"+workingCard.getImgUri(), "");
			player_hand.addCard(workingCard);
			bottom_center.add(new JButton(cardImage));
		
			workingCard = deck.poll();
			cardImage = createImageIcon("images/"+workingCard.getImgUri(), "");
			player_hand.addCard(workingCard);
			bottom_center.add(new JButton(cardImage));
			hit.setEnabled(true);
			stay.setEnabled(true);
			
			top_center.revalidate();
			top_center.validate();

			
			
		}
		else if(event.getSource()==quit){
			System.out.println("close");
			System.exit(0);
		}
		else if(event.getSource()==stay){
			message = "stay";
			message_box.setText(message);
			hit.setEnabled(false);
			while(dealer_hand.count()<14){
				PlayCard workingCard = deck.poll();
				ImageIcon cardImage = createImageIcon("images/"+workingCard.getImgUri(), "");				
				dealer_hand.addCard(workingCard);
				
				
				top_center.add(new JButton(cardImage));
				top_center.revalidate();
				top_center.validate();
				
			}
			
			if(dealer_hand.count()>21){
				message = "dealer bust";
				
			}
			else if(dealer_hand.count()<player_hand.count()){
				message = "player wins";		
			}
			else if(dealer_hand.count()>player_hand.count()){
				message = "dealer wins";
			}
			else{
				message = "tie";
			}
			message_box.setText(message);
			
		
		}
		
		
//		JOptionPane.showMessageDialog(null, string);
	}
	
	}

}
	
