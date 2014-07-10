package houtbecke.rs.le;

import java.util.UUID;

public interface LeScanRecord {

    LeRecord[] getRecords();
    LeRecord[] getRecords(int... types);

    UUID[] getServices();

    byte[] getRawData();

}
