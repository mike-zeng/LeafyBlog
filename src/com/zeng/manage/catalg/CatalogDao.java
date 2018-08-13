package com.zeng.manage.catalg;

import com.zeng.manage.catalg.CatalogBean;

//对目录的操作
public interface CatalogDao {
    //新增
    public Boolean setCatalog(CatalogBean catalogBean) throws Exception;

    //查询
    public String queryCatalog(int page) throws Exception;

    //修改
    public String alterCatalog(CatalogBean catalogBean) throws Exception;

    //删除
    public String delCatalog(String id) throws Exception;
}
