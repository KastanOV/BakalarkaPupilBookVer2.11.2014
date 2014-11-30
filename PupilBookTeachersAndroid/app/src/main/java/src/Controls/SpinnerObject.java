package src.Controls;

/**
 * Created by Topr on 11/30/2014.
 */
public class SpinnerObject {
    private  int databaseId;

    private String databaseValue;
    private String Login;

    public SpinnerObject(int databaseId, String databaseValue) {
        this.databaseId = databaseId;
        this.databaseValue = databaseValue;
    }
    public SpinnerObject(String Login, String databaseValue) {
        this.databaseId = databaseId;
        this.databaseValue = databaseValue;
    }
    public String getDatabaseValue() {
        return databaseValue;
    }

    public int getDatabaseId() {
        return databaseId;
    }

    public String getLogin() {return  Login; }

    @Override
    public String toString() {
        return databaseValue;
    }
}
