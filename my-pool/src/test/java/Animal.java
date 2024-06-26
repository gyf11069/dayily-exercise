import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Animal {
    private int id;
    private String nickName;
    private int mode;
    public void setMode (Mode mode) {
        this.mode = mode == null ? 0 : mode.getIdInt();
    }
    public Mode getMode() {
        return Mode.findById(mode);
    }
}
