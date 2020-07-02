package com.mb.baseType.controller;

import com.mb.base.BaseController;
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
@RequestMapping("/baseTypeGroup")
public class BaseTypeGroupController extends BaseController<BaseTypeGroup> {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseTypeGroupController.class);

    @Autowired
    private IBaseTypeGroupService baseTypeGroupService;

    @RequestMapping("/gotoAddPage")
    public String gotoAddPage(HttpServletRequest request) {
        return "baseType/addBaseTypeGroup";
    }

    @RequestMapping("/gotoUpdatePage/{id}")
    public String gotoUpdatePage(Model model, @PathVariable("id") String id) {
        BaseTypeGroup entity = new BaseTypeGroup();
        if (StringUtils.isNotBlank(id)) {// 前台传来的id不为空
            entity = baseTypeGroupService.findById(id);
        }
        model.addAttribute("entity", entity);
        return "baseType/updateBaseTypeGroup";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public JsonResult save(HttpServletRequest request, HttpServletResponse response, BaseTypeGroup entity) {
        JsonResult result = new JsonResult(false, "保存失败");
        try {
            boolean rtn = baseTypeGroupService.save(entity);
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
    public JsonResult update(HttpServletRequest request, HttpServletResponse response, BaseTypeGroup entity) {
        JsonResult result = new JsonResult(false, "更新失败");
        try {
            boolean rtn = baseTypeGroupService.update(entity);
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
    public JsonResult deleteById(HttpServletRequest request, HttpServletResponse response, BaseTypeGroup entity) {
        JsonResult result = new JsonResult(false, "删除失败");
        boolean rtn = baseTypeGroupService.deleteById(entity.getId());
        if (rtn) {
            result.setSuccess(true);
            result.setMsg("删除成功");
        }
        return result;
    }

    @Override
    public List<BaseTypeGroup> setPageList(HttpServletRequest request, HttpServletResponse response, BaseTypeGroup entity) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<BaseTypeGroup> pageList = null;
        try {
            paramMap.put("typeGroupName", entity.getTypeGroupName());
            paramMap.put("typeGroupCode", entity.getTypeGroupCode());
            pageList = baseTypeGroupService.getListByParam(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageList;
    }

}