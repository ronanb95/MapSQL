package mapsql.sql.test;

import mapsql.sql.core.SQLException;
import mapsql.sql.core.SQLResult;
import mapsql.sql.core.SQLStatement;
import mapsql.sql.condition.Equals;
import mapsql.sql.condition.LessThan;
import mapsql.sql.condition.LessThanOrEqual;
import mapsql.sql.condition.GreaterThan;
import mapsql.sql.condition.GreaterThanOrEqual;
import mapsql.sql.condition.NotEqual;
import mapsql.sql.condition.Like;
import mapsql.sql.condition.OrCondition;
import mapsql.sql.core.Condition;
import mapsql.sql.core.Field;
import mapsql.sql.core.SQLException;
import mapsql.sql.core.SQLManager;
import mapsql.sql.core.SQLResult;
import mapsql.sql.core.SQLStatement;
import mapsql.sql.field.CHARACTER;
import mapsql.sql.field.INTEGER;
import mapsql.sql.statement.CreateTable;
import mapsql.sql.statement.Delete;
import mapsql.sql.statement.DropTable;
import mapsql.sql.statement.Insert;
import mapsql.sql.statement.Select;
import mapsql.sql.statement.Update;



public class ConditionalTests {
static SQLManager manager = new SQLManager();
	
	public static void main(String[] args) {
		createTableStatement();

		showTables();
		insertData();
		

		insertPartialData();
		
		System.out.println("Checking for id = 2");
		selectEquals();
		
		System.out.println("Checking for id != 2");
		selectNotEquals();
		
		System.out.println("Checking for id < 2");
		selectLessThan();
		
		System.out.println("Checking for id <= 2");
		selectLessThanOrEqual();
		
		System.out.println("Checking for id > 2");
		selectGreaterThan();
		
		System.out.println("Checking for id >= 2");
		selectGreaterThanOrEqual();
		
		System.out.println("Testing Wildcard for name last letters 'an' ");
		wildCardLast();
		
		System.out.println("Testing Wildcard for name first letters 'He' ");
		wildCardFirst();
		
		System.out.println("Testing Wildcard for name any letters 'a' ");
		wildCardAll();
		
		
	}

	private static void executeStatement(SQLStatement statement) {
		try {
			SQLResult result = manager.execute(statement);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createTableStatement() {
		executeStatement(
				new CreateTable(
						"contact", 
						new Field[] {
								new INTEGER("id", true, false, true), 
								new CHARACTER("name", 30, false, true), 
								new CHARACTER("email", 30, false, false)
						}
				)
		);
	}
	
	public static void showTables() {
		executeStatement(new Select("mapsql.tables", new String[] { "*" }));
	}
	
	public static void selectTable() {
		executeStatement(new Select("contact", new String[] { "*" }));
	}
	
	public static void dropTable() {
		executeStatement(new DropTable("contact"));
	}
	
	public static void insertData() {
		executeStatement(
				new Insert(
						"contact", 
						new String[] {"name", "email"}, 
						new String[] {"Ronan", "Ronan.@ucd.ie"}
				)
		);

	}

	public static void insertPartialData() {
		executeStatement(new Insert("contact", new String[] {"name"}, new String[] {"Henry"}));
		executeStatement(new Insert("contact", new String[] {"name"}, new String[] {"Garry"}));
	}
	
	public static void selectEquals() {
		executeStatement(new Select("contact", new String[] { "*" }, new Equals("id", "2")));
	}
	
	public static void selectNotEquals() {
		executeStatement(new Select("contact", new String[] { "*" }, new NotEqual("id", "2")));
	}
	
	public static void selectLessThan() {
		executeStatement(new Select("contact", new String[] { "*" }, new LessThan("id", "2")));
	}
	
	public static void selectLessThanOrEqual() {
		executeStatement(new Select("contact", new String[] { "*" }, new LessThanOrEqual("id", "2")));
	}
	
	public static void selectGreaterThan() {
		executeStatement(new Select("contact", new String[] { "*" }, new GreaterThan("id", "2")));
	}
	
	public static void selectGreaterThanOrEqual() {
		executeStatement(new Select("contact", new String[] { "*" }, new GreaterThanOrEqual("id", "2")));
	}
	
	public static void wildCardLast() {
		executeStatement(new Select("contact", new String[] { "*" }, new Like("name", "%an")));
	}
	
	public static void wildCardFirst() {
		executeStatement(new Select("contact", new String[] { "*" }, new Like("name", "He%")));
	}
	
	public static void wildCardAll() {
		executeStatement(new Select("contact", new String[] { "*" }, new Like("name", "%an%")));
	}

}
