import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * @author gyf
 * @date 2023-03-14 17:42
 */
@Data
@AllArgsConstructor
public class Dataobject {
    String WID;
    String DATAOBJECT;
    String TABLENAME;
    String DATAOBJECTSIGN;
    String STATUS;
    String SYSTEM;
    String DATAOBJECTDESC;
    String DATAOBJECTNAME;
    String DATAOBJECTTYPE;
    String IMPORTANT;
    String CZBZ;
    Map<String,Object> param;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dataobject that = (Dataobject) o;
        return Objects.equals(WID, that.WID) &&
                Objects.equals(DATAOBJECT, that.DATAOBJECT) &&
                Objects.equals(TABLENAME, that.TABLENAME) &&
                Objects.equals(DATAOBJECTSIGN, that.DATAOBJECTSIGN) &&
                Objects.equals(STATUS, that.STATUS) &&
                Objects.equals(SYSTEM, that.SYSTEM) &&
                Objects.equals(DATAOBJECTDESC, that.DATAOBJECTDESC) &&
                Objects.equals(DATAOBJECTNAME, that.DATAOBJECTNAME) &&
                Objects.equals(DATAOBJECTTYPE, that.DATAOBJECTTYPE) &&
                Objects.equals(IMPORTANT, that.IMPORTANT) &&
                Objects.equals(CZBZ, that.CZBZ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(WID, DATAOBJECT, TABLENAME, DATAOBJECTSIGN, STATUS, SYSTEM, DATAOBJECTDESC, DATAOBJECTNAME, DATAOBJECTTYPE, IMPORTANT, CZBZ);
    }*/

//    @Override
//    public String toString() {
//        return "Dataobject{" +
//                "WID='" + WID + '\'' +
//                ", DATAOBJECT='" + DATAOBJECT + '\'' +
//                ", TABLENAME='" + TABLENAME + '\'' +
//                ", DATAOBJECTSIGN='" + DATAOBJECTSIGN + '\'' +
//                ", STATUS='" + STATUS + '\'' +
//                ", SYSTEM='" + SYSTEM + '\'' +
//                ", DATAOBJECTDESC='" + DATAOBJECTDESC + '\'' +
//                ", DATAOBJECTNAME='" + DATAOBJECTNAME + '\'' +
//                ", DATAOBJECTTYPE='" + DATAOBJECTTYPE + '\'' +
//                ", IMPORTANT='" + IMPORTANT + '\'' +
//                ", CZBZ='" + CZBZ + '\'' +
//                '}';
//    }
}
