import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author gyf
 * @date 2023-03-14 17:41
 */
@Data
@AllArgsConstructor
public class Datafield {
    List<String> BELONGDEPTS;
    List<String> BELONGDEPTNAMES;
    String BELONGDEPT;
    String BELONGDEPTNAME;
    String COLUMNLEN;
    String COLUMNTYPE;
    String DATAFIELD;
    String DATAFIELDNAME;
    String IMPORTANT;
    String ISCANNULL;
    String ISKEY;
    String TABLEFIELD;
    String YYDMB;
    String WID;
    String XH;
    String ISSHARE;
    String BZ;
    String CAUSE;

    public Datafield() {

    }

//    @Override
//    public String toString() {
//        return "Datafield{" +
//                "BELONGDEPTS=" + BELONGDEPTS +
//                ", BELONGDEPTNAMES=" + BELONGDEPTNAMES +
//                ", BELONGDEPT='" + BELONGDEPT + '\'' +
//                ", BELONGDEPTNAME='" + BELONGDEPTNAME + '\'' +
//                ", COLUMNLEN='" + COLUMNLEN + '\'' +
//                ", COLUMNTYPE='" + COLUMNTYPE + '\'' +
//                ", DATAFIELD='" + DATAFIELD + '\'' +
//                ", DATAFIELDNAME='" + DATAFIELDNAME + '\'' +
//                ", IMPORTANT='" + IMPORTANT + '\'' +
//                ", ISCANNULL='" + ISCANNULL + '\'' +
//                ", ISKEY='" + ISKEY + '\'' +
//                ", TABLEFIELD='" + TABLEFIELD + '\'' +
//                ", YYDMB='" + YYDMB + '\'' +
//                ", WID='" + WID + '\'' +
//                ", XH='" + XH + '\'' +
//                ", ISSHARE='" + ISSHARE + '\'' +
//                '}';
//    }
}
