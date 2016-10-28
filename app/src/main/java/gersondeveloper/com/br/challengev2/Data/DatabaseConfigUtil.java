package gersondeveloper.com.br.challengev2.Data;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by gerso on 09/10/2016.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    public static void main(String[] args) throws SQLException, IOException
    {
        //defines the configuration file
        writeConfigFile("ormlite_config.txt");
    }
}
