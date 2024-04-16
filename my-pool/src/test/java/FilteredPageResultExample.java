//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Predicate;
//
//public class FilteredPageResultExample {
//
//    public static <T> PageResult<T> applyPostFilter(PageResult<T> originalPage, Predicate<T> filter) {
//        // 应用过滤器，得到过滤后的数据子集
//        List<T> filteredData = new ArrayList<>();
//        for (T item : originalPage.getData()) {
//            if (filter.test(item)) {
//                filteredData.add(item);
//            }
//        }
//
//        // 计算过滤后的实际总记录数
//        long filteredTotalRecords = filteredData.size();
//
//        // 重新计算有效的当前页码
//        int effectiveCurrentPage = Math.min(originalPage.getCurrentPage(), calculateLastPage(filteredTotalRecords, originalPage.getPageSize()));
//
//        // 根据新的当前页码和每页大小，确定数据子集的起始和结束索引
//        int startIndex = (effectiveCurrentPage - 1) * originalPage.getPageSize();
//        int endIndex = Math.min(startIndex + originalPage.getPageSize(), filteredData.size());
//
//        // 提取对应当前页的过滤后数据
//        List<T> currentPageData = filteredData.subList(startIndex, endIndex);
//
//        // 创建并返回新的PageResult对象，包含过滤后和重新计算的分页信息
//        return new PageResult<>(currentPageData, filteredTotalRecords, originalPage.getPageSize(), effectiveCurrentPage);
//    }
//
//    private static int calculateLastPage(long totalRecords, int pageSize) {
//        return (int) Math.ceil((double) totalRecords / pageSize);
//    }
//
//    public static void main(String[] args) {
//        // 假设这是从数据库获取的原始分页数据
//        PageResult<String> originalPage = new PageResult<>(
//                List.of("Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig", "Grape"),
//                7,
//                5,
//                2
//        );
//
//        // 定义一个过滤器，例如只保留以字母"C"开头的元素
//        Predicate<String> filter = s -> s.startsWith("C");
//
//        // 应用过滤器并重新计算分页信息
//        PageResult<String> filteredPage = applyPostFilter(originalPage, filter);
//
//        System.out.println("Original Page:");
//        System.out.println("Data: " + originalPage.getData());
//        System.out.println("Total Records: " + originalPage.getTotalRecords());
//        System.out.println("Page Size: " + originalPage.getPageSize());
//        System.out.println("Current Page: " + originalPage.getCurrentPage());
//
//        System.out.println("\nFiltered Page:");
//        System.out.println("Data: " + filteredPage.getData());
//        System.out.println("Total Records: " + filteredPage.getTotalRecords());
//        System.out.println("Page Size: " + filteredPage.getPageSize());
//        System.out.println("Current Page: " + filteredPage.getCurrentPage());
//    }
//}
//
