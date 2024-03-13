package com.moments.claw.domain.common.response;

import java.io.Serializable;

public interface IResponse extends Serializable {
    void setRequestId(String var1);

    String getRequestId();
}
