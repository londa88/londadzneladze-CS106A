import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
	private ArrayList<NameSurferEntry> entryArrayList = new ArrayList<NameSurferEntry>();//creating the arraylist and storing every entry

	public NameSurferGraph() {
		addComponentListener(this);
	}

	public void clear() { //this method removes the graph
		entryArrayList.clear();
		update();
	}

	public void addEntry(NameSurferEntry entry) { //this method adds the graph
		entryArrayList.add(entry);
		update();
	}

	public void update() { 
		removeAll();
		drawLines();
		drawDecadeLabels();
		for (int i = 0; i < entryArrayList.size(); i++) {
			drawGraph(entryArrayList.get(i), i);
		}
	}

	private void drawLines() { //draws the horizontal and vertical lines
		for (int i = 0; i < NDECADES; i++) {
			double lineX1 = i * getWidth() / NDECADES;
			double lineY1 = 0;
			double lineX2 = lineX1;
			double lineY2 = getHeight();
			GLine verticalLine = new GLine(lineX1, lineY1, lineX2, lineY2);
			add(verticalLine);
		}
		double line1X1 = 0;
		double line1Y1 = GRAPH_MARGIN_SIZE;
		double line1X2 = getWidth();
		double line1Y2 = line1Y1;
		GLine horizontalLine1 = new GLine(line1X1, line1Y1, line1X2, line1Y2);
		add(horizontalLine1);
		double line2X1 = 0;
		double line2Y1 = getHeight() - GRAPH_MARGIN_SIZE;
		double line2X2 = getWidth();
		double line2Y2 = line2Y1;
		GLine horizontalLine2 = new GLine(line2X1, line2Y1, line2X2, line2Y2);
		add(horizontalLine2);
	}

	private void drawDecadeLabels() { //draws the labels of the decades
		String[] decades = new String[NDECADES];
		for (int i = 0; i < NDECADES; i++) {
			int startDecade = START_DECADE;
			startDecade += 10 * i;
			decades[i] = "" + startDecade;
		}
		for (int i = 0; i < NDECADES; i++) {
			GLabel decadeLabel = new GLabel(decades[i]);
			double labelX=i * getWidth() / NDECADES;
			double labelY=getHeight();
			add(decadeLabel, labelX,labelY);
		}
	}

	private void drawGraph(NameSurferEntry entry, int index) { //draws graph
		double x = (getHeight() - 2 * GRAPH_MARGIN_SIZE) / (double)MAX_RANK;

 		drawGraphLines(entry,index,x);
		drawLabels(entry,index,x);
	}
	private void drawGraphLines(NameSurferEntry entry, int index, double x) { //draws lines of the graph
		GLine line=null;
		for (int i = 0; i < NDECADES-1; i++) {
			if (entry.getRank(i) == 0 && entry.getRank(i + 1) == 0) {
				double line1X1=i * getWidth() / NDECADES;
				double line1Y1=getHeight() - GRAPH_MARGIN_SIZE;
				double line1X2=(i + 1) * getWidth() / NDECADES;
				double line1Y2= getHeight() - GRAPH_MARGIN_SIZE;
				GLine line1 = new GLine(line1X1, line1Y1,line1X2, line1Y2);
				line = line1;
				
			} else if (entry.getRank(i) == 0 && entry.getRank(i + 1) != 0) {
				double line1X1=i * getWidth() / NDECADES;
				double line1Y1=getHeight() - GRAPH_MARGIN_SIZE;
				double line1X2=(i + 1) * getWidth() / NDECADES;
				double line1Y2=GRAPH_MARGIN_SIZE + (x * entry.getRank(i + 1));
				GLine line1 = new GLine(line1X1, line1Y1, line1X2, line1Y2);
				line = line1;
				
			}else if(entry.getRank(i)!=0&&entry.getRank(i+1)==0) {
				double line1X1=i * getWidth() / NDECADES;
				double line1Y1=GRAPH_MARGIN_SIZE + (x * entry.getRank(i));
				double line1X2=(i+1) * getWidth() / NDECADES;
				double line1Y2=getHeight() - GRAPH_MARGIN_SIZE;
				GLine line1=new GLine(line1X1, line1Y1, line1X2, line1Y2);
				line=line1;
			}
			else if(entry.getRank(i) != 0 && entry.getRank(i + 1) != 0){
				double line1X1=i * getWidth() / NDECADES;
				double line1Y1=GRAPH_MARGIN_SIZE + (x * entry.getRank(i));
				double line1X2=(i + 1) * getWidth() / NDECADES;
				double line1Y2=GRAPH_MARGIN_SIZE + (x * entry.getRank(i + 1));
				GLine line1 = new GLine(line1X1, line1Y1, line1X2, line1Y2);
				line = line1;
				
			}
		    add(line);
		    if (index % 4 == 0) {
				line.setColor(Color.MAGENTA);
			} else if (index % 4 == 1) {
				line.setColor(Color.RED);

			} else if (index % 4 == 2) {
				line.setColor(Color.BLUE);

			} else if (index % 4 == 3) {
				line.setColor(Color.GREEN);

			}
	}
	}
		private void drawLabels(NameSurferEntry entry, int index, double x) { //draws each label , name with the rank
			
			GLabel label = null;
			
	     	for (int i = 0; i < NDECADES; i++) {
				if (entry.getRank(i) == 0) {

					double label1X= i * getWidth() / NDECADES;
					double label1Y=getHeight() - GRAPH_MARGIN_SIZE;
					GLabel label1 = new GLabel(entry.getName() + " " + "*", label1X,label1Y);
					label = label1;
				}	else {
					double label1X=i*getWidth()/NDECADES;
					double label1Y=GRAPH_MARGIN_SIZE+(x*entry.getRank(i));
					GLabel label1=new GLabel(entry.getName()+" "+entry.getRank(i),label1X, label1Y);
					label=label1;
					
				}
				add(label);
		
	
				if (index % 4 == 0) { //using four colors for graphs and changing it one another
					label.setColor(Color.MAGENTA);
				} else if (index % 4 == 1) {
					label.setColor(Color.RED);
	
				} else if (index % 4 == 2) {
					label.setColor(Color.BLUE);
	
				} else if (index % 4 == 3) {
					
					label.setColor(Color.GREEN);
	
				}
		}	
		}


	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
