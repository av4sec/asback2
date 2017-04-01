package avasec;

import java.util.ArrayList;
import java.util.List;

public class Role extends Document {

    class Data {

        public String charid;
        public String name;
        public int version;

        public Data(String charid, String name, int version) {
            this.charid = charid;
            this.name = name;
            this.version = version;
        }
    }

    public long extid;
    public Data curr;
    public List<Data> hist;

    public Role() {}

    public Role(long extid, String charid, String name) {
        this.extid = extid;
        this.curr = new Data(charid, name, 1);
        this.hist = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format(
                "Role[id=%s, extid=%d, curr.charid='%s', curr.name='%s', curr.version=%d]",
                id, this.extid, this.curr.charid, this.curr.name, this.curr.version);
    }

}
