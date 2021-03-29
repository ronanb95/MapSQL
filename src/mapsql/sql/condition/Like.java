package mapsql.sql.condition;

import java.util.Map;

import mapsql.sql.core.Field;
import mapsql.sql.core.SQLException;
import mapsql.sql.core.TableDescription;
import mapsql.sql.field.CHARACTER;

public class Like extends AbstractCondition {
	private String column;
	private String value;
	
	public Like(String column, String value) {
		this.column = column;
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean evaluate(TableDescription description, Map<String, String> data) throws SQLException {
		
		//value is the condition passed in eg. %ca would search for columns ending in ca
		//column is the column required. Can use data.get(column) to get values
		//data.get(column) loops over the columns values so can test on each of them
		
		//Contains values of the column
		String temp = data.get(column);
		
		//%ca looks for strings ending in ca
			//Uses .endsWith
		if (value.charAt(0) == '%' && value.charAt(value.length()-1) != '%') {
			String valueString = value.substring(1);
			if (temp.endsWith(valueString)){
				return true;
			}
		}
		
		
		//ca% looks for strings beginning in ca
			//Uses .startsWith
		if (value.charAt(0) != '%' && value.charAt(value.length()-1) == '%') {
			String valueString = value.substring(0, value.length()-1);
			if (temp.startsWith(valueString)){
				return true;
			}	
		}
		
		//%ca% looks for strings containing ca
			//Uses .contains
		if (value.charAt(0) == '%' && value.charAt(value.length()-1) == '%') {
			String valueString = value.substring(1, value.length()-1);
			if (temp.contains(valueString)){
				return true;
			}	
		}
		
		return false;
	}
}
