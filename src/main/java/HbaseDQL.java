import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseDQL {
    private Table table;
    private final String tableName = "cars";
    private final String family1 = "general";
    private final String family2 = "engine";

    public void createHbaseTable() throws IOException {
        Configuration config = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(config);
//        Admin admin = connection.getAdmin();
//
//        HTableDescriptor ht = new HTableDescriptor(TableName.valueOf(tableName));
//        ht.addFamily(new HColumnDescriptor(family1));
//        ht.addFamily(new HColumnDescriptor(family2));
//        System.out.println("connecting");
//
//        System.out.println("Creating Table");
//        createOrOverwrite(admin, ht);
//        System.out.println("Done......");

        table = connection.getTable(TableName.valueOf(tableName));

    }

    public static void createOrOverwrite(Admin admin, HTableDescriptor table) throws IOException {
        if (admin.tableExists(table.getTableName())) {
            admin.disableTable(table.getTableName());
            admin.deleteTable(table.getTableName());
        }
        admin.createTable(table);
    }

    public void addEntry(String matricule, double speed, double lat, double long_, double temperature,  String state) throws IOException {
        Configuration config = HBaseConfiguration.create();
        try (Connection connection = ConnectionFactory.createConnection(config)) {
            table = connection.getTable(TableName.valueOf(tableName));

            byte[] row = Bytes.toBytes(matricule);
            Put p = new Put(row);
            p.addColumn(family1.getBytes(), "speed".getBytes(), Bytes.toBytes(speed));
            p.addColumn(family1.getBytes(), "lat".getBytes(), Bytes.toBytes(lat));
            p.addColumn(family1.getBytes(), "long".getBytes(), Bytes.toBytes(long_));
            p.addColumn(family2.getBytes(), "temperature".getBytes(), Bytes.toBytes(temperature));
            p.addColumn(family2.getBytes(), "state".getBytes(), Bytes.toBytes(state));
            table.put(p);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            table.close();
        }
    }

    public void init() throws IOException {
        HbaseDQL admin = new HbaseDQL();
        admin.createHbaseTable();
    }
}



