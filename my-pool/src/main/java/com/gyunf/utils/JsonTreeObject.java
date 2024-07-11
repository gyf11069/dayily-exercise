package com.gyunf.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonTreeObject {

    //@ApiModelProperty("id")
    private String id;
    //@ApiModelProperty("父级id")
    private String pid;
    //@ApiModelProperty("父目录名")
    private String pname;
    //@ApiModelProperty("节点名")
    private String text;
    //@ApiModelProperty("节点中文名")
    private String textCN;
    //@ApiModelProperty("节点状态")
    private String status;
    //@ApiModelProperty("节点数据")
    private Object data;
    //@ApiModelProperty("序号")
    private Integer xh;
    //@ApiModelProperty("节点类型")
    private String type;
    //@ApiModelProperty("是否拖拽(1是，0否)")
    private String drag;
    //@ApiModelProperty("是否一级目录")
    private Boolean isRoot;
    //@ApiModelProperty("扩展id")
    private String externalId;
    //@ApiModelProperty("数据源")
    private String dbwid;
    //@ApiModelProperty("数据库类型")
    private String db_type;
    //@ApiModelProperty("图标类型")
    private String icon_type;
    //@ApiModelProperty("子节点集合")
    private List<JsonTreeObject> children;
    //@ApiModelProperty("是否表")
    boolean isLeaf;

    public JsonTreeObject(String id, String pid, String pname, String text, String textCN, String status, Object data, Integer xh, String type, String drag, Boolean isRoot, String externalId, List<JsonTreeObject> children) {
        this.id = id;
        this.pid = pid;
        this.pname = pname;
        this.text = text;
        this.textCN = textCN;
        this.status = status;
        this.data = data;
        this.xh = xh;
        this.type = type;
        this.drag = drag;
        this.isRoot = isRoot;
        this.externalId = externalId;
        this.children = children;
    }

    /**
     * 转换List<Map<String,Object>>
     *
     * @param treeMap
     * @return
     */
    public static List<JsonTreeObject> transformMapToJsonTreeObject(List<Map<String, Object>> treeMap) {
        List<JsonTreeObject> jsonTreeObjectList = new ArrayList<>();
        for (Map<String, Object> map : treeMap) {
            JsonTreeObject jsonTreeObject = new JsonTreeObject();
            jsonTreeObject.setId(map.containsKey("id") ? (String) map.get("id") : null);
            jsonTreeObject.setPid(map.containsKey("pid") ? (String) map.get("pid") : null);
            jsonTreeObject.setPname(map.containsKey("pname") ? (String) map.get("pname") : null);
            jsonTreeObject.setText(map.containsKey("text") ? (String) map.get("text") : null);
            jsonTreeObject.setTextCN(map.containsKey("textcn") ? (String) map.get("textcn") : null);
            jsonTreeObject.setStatus(map.containsKey("status") ? (String) map.get("status") : null);
            jsonTreeObject.setData(map.getOrDefault("data", null));
            jsonTreeObject.setXh(map.containsKey("xh") ? (Integer) map.get("xh") : null);
            jsonTreeObject.setType(map.containsKey("type") ? (String) map.get("type") : null);
            jsonTreeObject.setDrag(map.containsKey("drag") ? (String) map.get("drag") : null);
            jsonTreeObject.setIsRoot(map.containsKey("isroot") ? Boolean.valueOf(map.get("isroot").toString()) : null);
            jsonTreeObject.setExternalId(map.containsKey("externalId") ? (String) map.get("externalId") : null);
            jsonTreeObject.setDbwid(map.containsKey("dbwid") ? (String) map.get("dbwid") : null);
            jsonTreeObject.setDb_type(map.containsKey("db_type") ? (String) map.get("db_type") : null);
            jsonTreeObject.setIcon_type(map.containsKey("icon_type") ? (String) map.get("icon_type") : null);
            jsonTreeObject.setChildren(map.containsKey("children") && map.get("children") != null ? (List<JsonTreeObject>) map.get("children") : Collections.emptyList());
            jsonTreeObjectList.add(jsonTreeObject);
        }
        return jsonTreeObjectList;
    }

    /**
     * 直接转换List<Map<String,Object>>成json树或森林
     *
     * @param treeMap
     * @return
     */
    public static List<JsonTreeObject> transformMapListToJsonTree(List<Map<String, Object>> treeMap) {
        return getJsonTree(transformMapToJsonTreeObject(treeMap));
    }

    /**
     * 无层数限制
     * List<JsonTreeObject> 集合数据转为层次结构，即设置children
     *
     * @param jsonTreeObjectList 顶层节点的pid必须为null
     * @return
     */
    public static List<JsonTreeObject> getJsonTree(List<JsonTreeObject> jsonTreeObjectList) {
        return jsonTreeObjectList.stream().filter(object -> object.getPid() == null)
                .peek(parent -> getJsonTreeObjects(parent, jsonTreeObjectList))
                .collect(Collectors.toList());
    }

    /**
     * List<JsonTreeObject> 集合数据转为层次结构，即设置children
     *
     * @param parent       父节点
     * @param iteratorList 遍历集合
     * @return
     */
    private static void getJsonTreeObjects(JsonTreeObject parent, List<JsonTreeObject> iteratorList) {
        parent.setChildren(iteratorList.stream().filter(object -> parent.getId().equals(object.getPid())).collect(Collectors.toList()));
        if (!parent.getChildren().isEmpty()) {
            parent.getChildren().forEach(child -> {
                child.setPname(parent.getText());
                getJsonTreeObjects(child, iteratorList);
            });
        }
    }
    public void setIsLeft(boolean isLeaf){
        this.isLeaf=isLeaf;
    }
}
