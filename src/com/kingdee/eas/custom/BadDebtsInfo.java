package com.kingdee.eas.custom;

import java.io.Serializable;

public class BadDebtsInfo extends AbstractBadDebtsInfo implements Serializable 
{
    public BadDebtsInfo()
    {
        super();
    }
    protected BadDebtsInfo(String pkField)
    {
        super(pkField);
    }
}