package com.lyh.loadbalance;

import java.util.List;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @create 2019-08-05 8:46
 * @since 1.7
 */
public interface LoadBalance {

    String select(List<String> repos);
}
