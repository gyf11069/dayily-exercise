package com.gyunf.service;

import com.gyunf.dao.AddEmpDao;
import com.gyunf.domain.EmPloyees;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2021-03-22 19:49
 */
public class AddEmpService{

    private AddEmpDao addEmpDao = new AddEmpDao();

    public int add(EmPloyees employees) {
        int result = addEmpDao.add(employees);
        return result;//返回给servlet
    }
}
