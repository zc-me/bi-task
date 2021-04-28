package com.timing.bitaskcore.biz;


import com.timing.bitaskcore.biz.model.HandleCallbackParam;
import com.timing.bitaskcore.biz.model.RegistryParam;
import com.timing.bitaskcore.biz.model.ReturnT;

import java.util.List;

public interface AdminBiz {

    /**
     * callback
     *
     * @param callbackParamList
     * @return
     */
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList);

    /**
     * registry
     *
     * @param registryParam
     * @return
     */
    public ReturnT<String> registry(RegistryParam registryParam);

    /**
     * registry remove
     *
     * @param registryParam
     * @return
     */
    public ReturnT<String> registryRemove(RegistryParam registryParam);

}
