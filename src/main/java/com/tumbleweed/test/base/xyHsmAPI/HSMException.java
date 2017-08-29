package com.tumbleweed.test.base.xyHsmAPI;

import java.lang.*;

public class   HSMException   extends  Exception
{
    private   String   errCode   =   null;

    private   Throwable   rootCause   =   null;

    //private   Log   log   =   new   Log(); 

    HSMException()
    {
        super();
    }
    HSMException(String   errCode)
    {
        super(errCode);
        this.errCode = errCode;
        //System.out.println(errCode);
        //log.log(errCode);

    }
    public String getErrorCode ()
    {
        return errCode;
    }

    public   Throwable   getRootCause()
    {
        return   rootCause;
    }

}   

