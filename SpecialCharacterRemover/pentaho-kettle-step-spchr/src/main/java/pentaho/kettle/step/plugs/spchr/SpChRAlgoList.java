package pentaho.kettle.step.plugs.spchr;

import java.util.HashMap;
import java.util.Map;

public class SpChRAlgoList {
	
	private static Map<String,String> algomap;
	private String selectedPattern;
	
	
	public String getAlgoPattern(String selectedalgo){
		
		algomap=new HashMap<String, String>();
		
		algomap.put("Remove all the Special Characters other than A-Z,a-z,0-9 including white-spaces", "[^A-Za-z0-9]");
		algomap.put("Remove all the Special Characters other than A-Z,a-z,0-9 keep the white-spaces", "[^A-Za-z0-9\\s]");
		algomap.put("Remove anything outside ASCII code 0 to 255", "[^\\x00-\\x7F]");
		algomap.put("Remove Unicode Block", "\\P{InBasic_Latin}");
		algomap.put("Keep Unicode Block, remove the rest", "\\p{InBasic_Latin}");
		algomap.put("Custom Regular Expression", "D");
		algomap.put("Keep A-Z,a-z,0-9 and ADD Exceptions","E");
		
		if(algomap.containsKey(selectedalgo)){
			setSelectedPattern(algomap.get(selectedalgo));
		}else{
			setSelectedPattern("[^A-Za-z0-9\\s]");
		}
		
		return getSelectedPattern();
	}


	public String getSelectedPattern() {
		return selectedPattern;
	}


	public void setSelectedPattern(String selectedPattern) {
		this.selectedPattern = selectedPattern;
	}

	
}
