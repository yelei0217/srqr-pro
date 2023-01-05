package com.kingdee.eas.custom;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class BadDebtsCollection extends AbstractObjectCollection 
{
    public BadDebtsCollection()
    {
        super(BadDebtsInfo.class);
    }
    public boolean add(BadDebtsInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(BadDebtsCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(BadDebtsInfo item)
    {
        return removeObject(item);
    }
    public BadDebtsInfo get(int index)
    {
        return(BadDebtsInfo)getObject(index);
    }
    public BadDebtsInfo get(Object key)
    {
        return(BadDebtsInfo)getObject(key);
    }
    public void set(int index, BadDebtsInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(BadDebtsInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(BadDebtsInfo item)
    {
        return super.indexOf(item);
    }
}