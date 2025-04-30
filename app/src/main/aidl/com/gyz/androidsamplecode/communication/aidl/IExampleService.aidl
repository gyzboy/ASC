// IExampleService.aidl
package com.gyz.androidsamplecode.communication.aidl;

import com.gyz.androidsamplecode.communication.aidl.MyData;
interface IExampleService {
    void processData(in MyData inputData);
    void updateData(inout MyData outputData);
    void modifyData(inout MyData data);
}