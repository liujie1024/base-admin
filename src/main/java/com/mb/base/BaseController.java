package com.mb.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public abstract class BaseController<T> {

    /**
     * 分页
     *
     * @param request
     * @param response
     * @param t
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(HttpServletRequest request, HttpServletResponse response, T t) {
        // easy-UI 传过来的分页数据
        Integer pageSize = request.getParameter("rows") == null ? 10 : Integer.valueOf(request.getParameter("rows"));// 页面大小,10
        Integer pageNumber = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));// 在分页控件创建的时候显示的页数,1
        Page<T> page = PageHelper.startPage(pageNumber, pageSize, true); // 核心分页代码
        List<T> pageList = this.setPageList(request, response, t);// 分页数据
        long total = page.getTotal();// 总数
        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("total", new Long(total).intValue());
        pageMap.put("rows", pageList);
        return pageMap;
    }

    /**
     * 不分页
     *
     * @param request
     * @param response
     * @param t
     * @return
     */
    @ResponseBody
    @RequestMapping("/notPageList")
    public Map<String, Object> notPageList(HttpServletRequest request, HttpServletResponse response, T t) {
        List<T> pageList = this.setPageList(request, response, t);// 不分页数据
        Integer total = pageList.size();
        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("total", total);
        pageMap.put("rows", pageList);
        return pageMap;
    }

    /**
     * 抽象方法，设置分页的数据
     *
     * @return
     */
    public abstract List<T> setPageList(HttpServletRequest request, HttpServletResponse response, T entity);

}
