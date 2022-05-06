import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class GUI extends JFrame{
	
	private JButton calc = new JButton("Calculate");
	private JButton reset = new JButton("Reset");
	
	private JLabel summary = new JLabel("SUMMARY");
	private JLabel lapLabel = new JLabel("Total Lap:");
	private JLabel lapNum = new JLabel("0");
	private JLabel fuelLabel = new JLabel("Total Fuel Needed:");
	private JLabel fuelNecess = new JLabel("0");
	
	private JLabel calculatorLabel = new JLabel("CALCULATOR");
	private JLabel raceTimeLabel = new JLabel("Total Race Time:");
	private JSpinner raceTimeH = new JSpinner();
	private JSpinner raceTimeM = new JSpinner();
	private JLabel lapTimeLabel = new JLabel("Average Lap Time:");
	private JSpinner lapTimeM = new JSpinner();
	private JSpinner lapTimeS = new JSpinner();
	private JLabel totLapLabel = new JLabel("Total Lap:");
	private JSpinner lapTot = new JSpinner();
	private JSpinner fuel = new JSpinner();
	private JSpinner fuelTank = new JSpinner();
	
	private FlowLayout buttonLay = new FlowLayout();
	private GridLayout summaryLay = new GridLayout(4,2);
	
	private JPanel buttonPane = new JPanel();
	private JPanel summaryPane = new JPanel();
	private JPanel calcPane = new JPanel();
	
	private Listener listener = new Listener(this);
	
	public GUI() {
		
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setSize(400,400);
		this.setLocationRelativeTo(null);
		
		this.getContentPane().setLayout(new BorderLayout());
		
		buttonPane.setLayout(buttonLay);
		buttonPane.add(calc);
		buttonPane.add(reset);
		this.getContentPane().add(buttonPane,BorderLayout.PAGE_END);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		
		summaryPane.setLayout(summaryLay);
		summaryPane.add(summary);
		summaryPane.add(new JLabel());
		summaryPane.add(lapLabel);
		summaryPane.add(lapNum);
		summaryPane.add(fuelLabel);
		summaryPane.add(fuelNecess);
		summaryPane.add(new JLabel());
		
		p1.add(summaryPane,BorderLayout.PAGE_END);
		this.getContentPane().add(p1,BorderLayout.CENTER);
		
		SpinnerModel valueH =  new SpinnerNumberModel(0,0,24,1);
		raceTimeH.setModel(valueH);
		JPanel raceTimePanel = new JPanel(new GridLayout(1,4));
		raceTimePanel.add(raceTimeH);
		raceTimePanel.add(new JLabel(" Hrs"));
		SpinnerModel valueM =  new SpinnerNumberModel(0,0,59,1);
		raceTimeH.setModel(valueM);
		raceTimePanel.add(raceTimeM);
		raceTimePanel.add(new JLabel(" Mins"));
		
		SpinnerModel valueLapM =  new SpinnerNumberModel(0,0,59,1);
		lapTimeM.setModel(valueLapM);
		JPanel lapTimePanel = new JPanel(new GridLayout(1,4));
		lapTimePanel.add(lapTimeM);
		lapTimePanel.add(new JLabel(" Mins"));
		SpinnerModel valueLapS = new SpinnerNumberModel(0,0,59,1);
		lapTimeS.setModel(valueLapS);
		lapTimePanel.add(lapTimeS);
		lapTimePanel.add(new JLabel(" Secs"));
		
		SpinnerModel valueLapTot =  new SpinnerNumberModel(0,0,500,1);
		lapTot.setModel(valueLapTot);
		JPanel lapTotPanel = new JPanel(new GridLayout(1,2));
		lapTotPanel.add(lapTot);
		lapTotPanel.add(new JLabel(" Laps"));
		
		SpinnerModel valueFuel =  new SpinnerNumberModel(0,0,10,0.01);
		fuel.setModel(valueFuel);
		JPanel fuelPanel = new JPanel(new GridLayout(1,2));
		fuelPanel.add(fuel);
		fuelPanel.add(new JLabel(" Litres"));
		
		SpinnerModel valueFuelTank =  new SpinnerNumberModel(0,0,1000,1);
		fuelTank.setModel(valueFuelTank);
		JPanel fuelTankPanel = new JPanel(new GridLayout(1,2));
		fuelTankPanel.add(fuelTank);
		fuelTankPanel.add(new JLabel(" Litres"));
		
		calcPane.setLayout(new GridLayout(11,1));
		calcPane.add(calculatorLabel);
		calcPane.add(raceTimeLabel);
		calcPane.add(raceTimePanel);
		calcPane.add(lapTimeLabel);
		calcPane.add(lapTimePanel);
		calcPane.add(totLapLabel);
		calcPane.add(lapTotPanel);
		calcPane.add(new JLabel("Fuel Per Lap:"));
		calcPane.add(fuelPanel);
		calcPane.add(new JLabel("Fuel Tank Capacity:"));
		calcPane.add(fuelTankPanel);
		
		//Listener
		reset.addActionListener(listener);
		reset.setActionCommand(listener.RESET);
		calc.addActionListener(listener);
		calc.setActionCommand(listener.CALCULATE);
		
		this.getContentPane().add(calcPane,BorderLayout.NORTH);
		setTitle("Fuel Estimator");
		this.setSize(400,360);
	}
	
	public void resetField() {
		
		lapNum.setText("0");
		fuelNecess.setText("0");
		raceTimeH.setValue(0);
		raceTimeM.setValue(0);
		lapTimeM.setValue(0);
		lapTimeS.setValue(0);
		lapTot.setValue(0);
		fuel.setValue(0);
	}
	
	public void calc() {
		
		int raceH = (int)raceTimeH.getValue();
		int raceM = (int)raceTimeM.getValue();
		int totRaceTimeSec = (raceH*60*60) + (raceM*60);
		
		int lapM = (int)lapTimeM.getValue();
		int lapS = (int)lapTimeS.getValue();
		int totLapTimeSec = (lapM*60) + lapS;
		
		int lapNumCalc = (totRaceTimeSec / totLapTimeSec) + 1;
		
		double fuelL = (double)fuel.getValue();
		double fuelNecessary = ((double)lapNumCalc) * fuelL; 
		
		lapTot.setValue(lapNumCalc);
		lapNum.setText(String.valueOf(lapNumCalc));
		fuelNecess.setText(String.valueOf((int)fuelNecessary+1));
		
	}
}
