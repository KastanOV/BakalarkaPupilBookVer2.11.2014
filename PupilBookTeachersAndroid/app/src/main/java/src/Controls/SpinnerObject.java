package src.Controls;

/**
 * Created by Topr on 11/30/2014.
 */
public class SpinnerObject {
    private  int databaseId;

    private String databaseValue;

    public SpinnerObject(int databaseId, String databaseValue) {
        this.databaseId = databaseId;
        this.databaseValue = databaseValue;
    }
    public String getDatabaseValue() {
        return databaseValue;
    }

    public int getDatabaseId() {
        return databaseId;
    }

    @Override
    public String toString() {
        return databaseValue;
    }
}
