package cn.com.kind.android.library.generate.compile;

import com.squareup.javapoet.ClassName;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2020/4/7.
 * @Copyright: 2020 www.kind.com.cn Inc. All rights reserved.
 */
public class ListActivityModel {
    private String[] busikey;
    private String className;
    private String classFullName;
    private ClassName classNameObj;

    public ListActivityModel(String[] busikey, String className, String classFullName, ClassName classNameObj) {
        this.busikey = busikey;
        this.className = className;
        this.classFullName = classFullName;
        this.classNameObj = classNameObj;
    }

    public String[] getBusikey() {
        return busikey;
    }

    public void setBusikey(String[] busikey) {
        this.busikey = busikey;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public ClassName getClassNameObj() {
        return classNameObj;
    }

    public void setClassNameObj(ClassName classNameObj) {
        this.classNameObj = classNameObj;
    }
}
