import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gyunf.utils.EnumUtils;
import com.gyunf.utils.TitledIdInt;

import java.util.Map;

public enum Mode implements TitledIdInt {
    CONST(2, "属性"), DIM(0, "维度"), IND(1, "指标"), TAG(3, "标签");

    int id;
    String title;

    Mode(int id, String title) {
        this.id = id;
        this.title = title;
    }
    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    // {2=CONST, 0=DIM, 1=IND, 3=TAG}
    public static final Map<Integer, Mode> map = EnumUtils.createValuedEnumMap(Mode.class);

    @JsonCreator
    public static Mode findById(@JsonProperty("id") Integer id) {
        return map.get(id);
    }
}
