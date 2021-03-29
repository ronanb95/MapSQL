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



public class NotNullandTableDeleteTests {
static SQLManager manager = new SQLManager();
	
	public static void main(String[] args) {
		createTableStatement();

		showTables();
		insertData();
		
		//Tests not null by inserting only names
			//Having issue with this, revisit
		insertPartialData();
		selectLessThan();
		
		deleteTable();
		showTables();
		
		
		
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
								new CHARACTER("email", 30, false, true)
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
	
	public static void selectLessThan() {
		executeStatement(new Select("contact", new String[] { "*" }, new LessThan("id", "500")));
	}

	public static void insertPartialData() {
		executeStatement(new Insert("contact", new String[] {"name"}, new String[] {"Henry"}));
		executeStatement(new Insert("contact", new String[] {"name"}, new String[] {"Garry"}));
	}
	
	public static void updateData() {
		executeStatement(
				new Update(
						"contact", 
						new String[] {"email"}, 
						new String[] {"henry.mcloughlin@ucd.ie"}, 
						new Equals("id", "2")
				)
		);
	}
	
	public static void deleteData() {
		executeStatement(new Delete("contact", new Equals("id", "1")));
	}
	
	public static void deleteTable() {
		executeStatement(new DropTable("mapsql.tables"));
		executeStatement(new DropTable("contact"));
	}
	
	
	

}
