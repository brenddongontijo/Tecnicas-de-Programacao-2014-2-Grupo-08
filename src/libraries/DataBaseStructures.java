package libraries;

import android.database.SQLException;

/**
 * Class Name: DataBaseStructures.
 * This class make all SQL commands to create/delete the necessary tables on Database.
 */
public class DataBaseStructures extends DataBase {
	
	// Call DataBase constructor.
    public DataBaseStructures() throws SQLException{
        super();
    }
    
    /**
     * The method initDB create all tables on DataBase.
     * @throws SQLException
     */
    public void initDB() throws SQLException {
        this.openConnection();
        
        this.database.execSQL("CREATE TABLE IF NOT EXISTS 'android_metadata' (locale TEXT)");
        this.database.execSQL("INSERT INTO android_metadata VALUES ('pt_BR')");
        
        this.buildTableArticles();
        this.buildTableEvaluation();
        this.buildTableCourse();
        this.buildTableCoursesInstitutions();
        this.buildTableInstitution();
        this.buildTableBooks();
        this.buildTableSearch();

        this.closeConnection();
    }
    
    /**
     * The method dropDB() delete all tables from Database. 
     * @throws SQLException
     */
    public void dropDB() throws SQLException {
    	this.openConnection();
    	
        this.database.execSQL("DROP TABLE IF EXISTS 'course'");
        this.database.execSQL("DROP TABLE IF EXISTS 'institution'");
        this.database.execSQL("DROP TABLE IF EXISTS 'courses_institutions'");
        this.database.execSQL("DROP TABLE IF EXISTS 'articles'");
        this.database.execSQL("DROP TABLE IF EXISTS 'books'");
        this.database.execSQL("DROP TABLE IF EXISTS 'evaluation'");
        this.database.execSQL("DROP TABLE IF EXISTS 'android_metadata'");
        this.database.execSQL("DROP TABLE IF EXISTS 'search'");
        
        this.closeConnection();
    }

    /** 
     * This method creates the table "course" on Database.
     */
    private void buildTableCourse() throws SQLException {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS 'course' (" +
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    			"'name' TEXT NOT NULL)";
        
        this.database.execSQL(sqlCommand);
    }
    
    /** 
     * This method creates the table "institution" on Database.
     */
    private void buildTableInstitution() throws SQLException {
    	String sqlCommand = "CREATE TABLE IF NOT EXISTS 'institution' (" +
    		    "'_id' INTEGER PRIMARY KEY AUTOINCREMENT,"+
    		    "'acronym' TEXT NOT NULL)";
    	
    	this.database.execSQL(sqlCommand);
    }

    /**
     * This method creates the table "courses_institutions" on Database.
     * Relation of course and institution: N...N.
     */
    private void buildTableCoursesInstitutions() throws SQLException {
    	String sqlCommand = "CREATE TABLE IF NOT EXISTS 'courses_institutions' (" +
    				"'id_institution' INTEGER NOT NULL," +
    				"'id_course' INTEGER NOT NULL)";
    	
    	this.database.execSQL(sqlCommand);
    }

    /** 
     * This method creates the table "articles" on Database.
     */
    private void buildTableArticles() throws SQLException {
    	String sqlCommand = "CREATE TABLE IF NOT EXISTS 'articles' (" +
    		    "'_id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    		    "'published_journals' INTEGER," +
    		    "'published_conference_proceedings' INTEGER)";
    	
    	this.database.execSQL(sqlCommand);
    }
    
    /** 
     * This method creates the table "books" on Database.
     */
    private void buildTableBooks() throws SQLException {
    	String sqlCommand = "CREATE TABLE IF NOT EXISTS 'books' (" +
    		    "'_id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    		    "'integral_text' INTEGER," +
    		    "'chapters' INTEGER," +
    		    "'collections' INTEGER," +
    		    "'entries' INTEGER)";
    	
    	this.database.execSQL(sqlCommand);
    }

    /** 
     * This method creates the table "evaluation" on Database.
     */
    private void buildTableEvaluation() throws SQLException {
    	String sqlCommand = "CREATE TABLE IF NOT EXISTS 'evaluation' (" +
    		    "'_id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    		    "'id_institution' INTEGER NOT NULL," +
    		    "'id_course' INTEGER NOT NULL," +
    		    "'year' INTEGER NOT NULL," +
    		    "'modality' TEXT NOT NULL," +
    		    "'master_degree_start_year' INTEGER," +
    		    "'doctorate_start_year' INTEGER," +
    		    "'triennial_evaluation' INTEGER NOT NULL," +
    		    "'permanent_teachers' INTEGER," +
    		    "'theses' INTEGER," +
    		    "'dissertations' INTEGER," +
    		    "'id_articles' INTEGER NOT NULL," +
    		    "'id_books' INTEGER," +
    		    "'artistic_production' INTEGER)";
    	
    	this.database.execSQL(sqlCommand);
    }

    /**
     *  This method creates the table "search" on Database.
     */
    private void buildTableSearch() throws SQLException {
    	String sqlCommand = "CREATE TABLE IF NOT EXISTS 'search' (" +
    		    "'_id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    			"'date' DATETIME," +
    			"'year' INTEGER," +
    			"'option' INTEGER," +
    			"'indicator' TEXT," +
    			"'min_value' INTEGER," +
    			"'max_value' INTEGER)";
    	
    	this.database.execSQL(sqlCommand);
    }
    
}
