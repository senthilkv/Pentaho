package pentaho.kettle.step.plugs.spchr;

import org.pentaho.di.core.exception.KettleValueException;
import org.pentaho.di.core.row.RowMetaInterface;

public class SpChRPattern {
	
		
	public String getCleanPattern(RowMetaInterface data,Object[] r) throws KettleValueException{
	
		String pattern="[^A-Za-z0-9\\s]";
		String replace_pattern="";
	
		String data_row=data.getString(r, 0);
	
		String SpecialCleaner=data_row.replaceAll(pattern, replace_pattern);
		
		return SpecialCleaner;
	
	}

}
