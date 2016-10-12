package gersondeveloper.com.br.challengev2.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import gersondeveloper.com.br.challengev2.Model.Transaction;
import gersondeveloper.com.br.challengev2.R;

/**
 * Created by gerso on 09/10/2016.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getName();
    private static final String DATABASE_NAME = "transaction.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Transaction, Integer> transactionsDAO;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Transaction.class);
        } catch (SQLException ex) {
            Log.e(TAG, "Error on creating database" + ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Transaction.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException ex) {
            Log.e(TAG, "Error on upgrade database from version " + oldVersion + " to " + newVersion + ex.getMessage());
        }
    }

    // create dao methods
    public Dao<Transaction, Integer> getTransactionDAO() throws SQLException {
        if (transactionsDAO == null) {
            transactionsDAO = getDao(Transaction.class);
        }
        return transactionsDAO;
    }
}
