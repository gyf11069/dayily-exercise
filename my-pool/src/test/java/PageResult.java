import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private List<T> data;
    private long totalRecords;
    private int pageSize;
    private int currentPage;

    // 构造函数、getter/setter省略...
}
