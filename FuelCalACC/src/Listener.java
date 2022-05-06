import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener{

		public static final String RESET = "reset", CALCULATE = "calculate";
		private GUI g; 
		
		public Listener(GUI g) {
			this.g = g;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String cmd = e.getActionCommand();
			
			if(cmd.equals(Listener.RESET)) {
				
				g.resetField();
				
			}else if(cmd.equals(Listener.CALCULATE)) {
				
				g.calc();
				
			}
		}
}
