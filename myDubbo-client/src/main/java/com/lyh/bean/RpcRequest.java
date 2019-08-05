package com.lyh.bean;

import java.io.Serializable;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 传的类名，方法名，方法参数，方法参数类型
 * @create 2019-08-05 10:01
 * @since 1.7
 */
public class RpcRequest implements Serializable {


    private static final long serialVersionUID = -2387838986532200300L;

    private String className;
    private String methodName;
    private Class<?>[] types;
    private Object[] params;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getTypes() {
        return types;
    }

    public void setTypes(Class<?>[] types) {
        this.types = types;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
