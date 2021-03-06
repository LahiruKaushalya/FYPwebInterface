package services;

/**
 * created by Shankaja
 */

import connectors.PostgreConnector;
import model.CDRModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBServicePostGreIml implements DBService {

    private static DBService instance = null;
    private String tableName = "cdrtesttable";

    public static DBService getInstance()
    {
        if (instance == null )
            instance = new DBServicePostGreIml();
        return instance;
    }

    private DBServicePostGreIml(){}

    @Override
    public ArrayList<CDRModel> retrieveCDR(int numObjects) throws SQLException, ClassNotFoundException {
        ArrayList<CDRModel> result = new ArrayList<CDRModel>();
        Statement statement = PostgreConnector.getInstance().getConnection().createStatement();
        String sql = "SELECT * FROM "+tableName+" LIMIT "+numObjects+"";
        ResultSet rs = statement.executeQuery(sql);
        System.out.println("Results taken successfully, count = "+numObjects);


        while (rs.next())
        {
            result.add(new CDRModel(rs.getString("called_num"),
                    rs.getString("called_tower"),
                    rs.getString("recipient_num"),
                    rs.getString("recipient_tower"),
                    rs.getString("datetime"),
                    rs.getString("duration")));
        }
        statement.close();

//        result.add(new CDRModel("1", "2", "3", "4", "sdfe", "sdfe"));
        return result;
    }

    @Override
    public CDRModel retrieveCDRAt(int index) {
        return null;
    }

    @Override
    public int totalObjects() {
        return 0;
    }

    @Override
    public int indexOf(CDRModel model) {
        return 0;
    }
}
