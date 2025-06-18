
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.GCanvas;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
	private NameSurferGraph theDataGraph;
	private JTextField field;
	private JButton graph;
	private JButton clear;
	NameSurferDataBase dataBase = new NameSurferDataBase("names-data.txt");

	public void init() {
		theDataGraph = new NameSurferGraph(); //adding the object of the NameSurferGraph
		add(theDataGraph);
		addInteractors();
	}

	private void addInteractors() { //adding the interactors
		JLabel name = new JLabel("name");
		add(name, SOUTH);
		field = new JTextField(10);
		add(field, SOUTH);
		field.addActionListener(this);
		graph = new JButton("Graph");
		add(graph, SOUTH);
		clear = new JButton("Clear");
		add(clear, SOUTH);
		addActionListeners();
	}

	public void actionPerformed(ActionEvent e) { 

		if (!e.getActionCommand().equals("Clear")&&!field.getText().equals("")) {
			NameSurferEntry entry = dataBase.findEntry(field.getText()); //transffering typed name to the database method
			if(entry!=null) {
			theDataGraph.addEntry(entry);   //clicking on the graph button and enter draws the graph
			theDataGraph.update();
			}
		} else if(e.getActionCommand().equals("Clear")){
				theDataGraph.clear () ;  //clicking the clear button removes it
		
	}
		field.setText(""); //emptying the field for another name
	}
}

