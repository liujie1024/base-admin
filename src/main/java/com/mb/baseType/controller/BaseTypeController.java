package com.mb.baseType.controller;

import com.mb.base.BaseController;
import com.mb.baseType.entity.BaseType;
import com.mb.baseType.entity.BaseTypeGroup;
import com.mb.baseType.service.IBaseTypeGroupService;
import com.mb.baseType.service.IBaseTypeService;
import com.mb.util.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/baseType")
public class BaseTypeController extends BaseController<BaseType> {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseTypeController.class);

    @Autowired
    private IBaseTypeService baseTypeService;

    @RequestMapping("/gotoList")
    public String gotoList(HttpServletRequest request) {
        return "baseType/baseTypeList";
    }

    @RequestMapping(value = "/gotoAddPage")
    public String gotoAddPage(Model model, BaseTypeGroup entity) {
        model.addAttribute("entity", entity);
        return "baseType/addBaseType";
    }

    @RequestMapping("/gotoUpdatePage/{id}")
    public String gotoUpdatePage(Model model, @PathVariable("id") String id, String typeGroupName) {
        BaseType entity = new BaseType();
        if (StringUtils.isNotBlank(id)) {// 前台传来的id不为空
            entity = baseTypeService.findById(id);
        }
        model.addAttribute("typeGroupName", typeGroupName);//数据字典名称
        model.addAttribute("entity", entity);
        return "baseType/updateBaseType";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public JsonResult save(HttpServletRequest request, HttpServletResponse response, BaseType entity) {
        JsonResult result = new JsonResult(false, "保存失败");
        try {
            boolean rtn = baseTypeService.save(entity);
            if (rtn) {
                result.setSuccess(true);
                result.setMsg("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public JsonResult update(HttpServletRequest request, HttpServletResponse response, BaseType entity) {
        JsonResult result = new JsonResult(false, "更新失败");
        try {
            boolean rtn = baseTypeService.update(entity);
            if (rtn) {
                result.setSuccess(true);
                result.setMsg("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById", method = {RequestMethod.POST})
    public JsonResult deleteById(HttpServletRequest request, HttpServletResponse response, BaseType entity) {
        JsonResult result = new JsonResult(false, "删除失败");
        boolean rtn = baseTypeService.deleteById(entity.getId());
        if (rtn) {
            result.setSuccess(true);
            result.setMsg("删除成功");
        }
        return result;
    }

    @Override
    public List<BaseType> setPageList(HttpServletRequest request, HttpServletResponse response, BaseType entity) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<BaseType> pageList = null;
        try {
            paramMap.put("typeGroupId", entity.getTypeGroupId());
            pageList = baseTypeService.getListByParam(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageList;
    }

}