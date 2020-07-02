package com.mb.broadcast.controller;

import com.mb.base.BaseController;
import com.mb.broadcast.entity.BroadcastEntity;
import com.mb.broadcast.service.IBroadcastService;
import com.mb.util.CommonUtil;
import com.mb.util.DateUtil;
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
@RequestMapping("/broadcast")
public class BroadcastController extends BaseController<BroadcastEntity> {

    private final static Logger LOGGER = LoggerFactory.getLogger(BroadcastController.class);

    @Autowired
    private IBroadcastService broadcastService;

    @RequestMapping("/gotoList")
    public String gotoList(HttpServletRequest request) {
        return "broadcast/broadcastList";
    }

    @RequestMapping("/gotoAddPage")
    public String gotoAddPage(HttpServletRequest request) {
        return "broadcast/addBroadcast";
    }

    @RequestMapping("/gotoUpdatePage/{id}")
    public String gotoUpdatePage(Model model, @PathVariable("id") String id) {
        LOGGER.info("BroadcastController goto update page，id >>>>> " + id);
        BroadcastEntity entity = new BroadcastEntity();
        if (StringUtils.isNotBlank(id)) {// 前台传来的id不为空
            entity = broadcastService.findById(id);
            entity.setBroadcastDateStr(DateUtil.getDateStrByDate(entity.getBroadcastDate(), "yyyy-MM-dd"));
            entity.setBroadcastBeginTimeStr(DateUtil.getDateStrByDate(entity.getBroadcastBeginTime(), "yyyy-MM-dd HH:mm:ss"));
            entity.setBroadcastEndTimeStr(DateUtil.getDateStrByDate(entity.getBroadcastEndTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        model.addAttribute("broadcast", entity);
        return "broadcast/updateBroadcast";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public JsonResult save(HttpServletRequest request, HttpServletResponse response, BroadcastEntity broadcast) {
        JsonResult result = new JsonResult(false, "保存失败");
        try {
            boolean rtn = broadcastService.save(broadcast);
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
    public JsonResult update(HttpServletRequest request, HttpServletResponse response, BroadcastEntity entity) {
        JsonResult result = new JsonResult(false, "更新失败");
        try {
            boolean rtn = broadcastService.update(entity);
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
    public JsonResult deleteById(HttpServletRequest request, HttpServletResponse response, BroadcastEntity broadcast) {
        LOGGER.info("BroadcastController delete broadcast");
        JsonResult result = new JsonResult(false, "删除失败");
        boolean rtn = broadcastService.deleteById(broadcast.getId());
        if (rtn) {
            result.setSuccess(true);
            result.setMsg("删除成功");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/initPageData", method = {RequestMethod.POST})
    public Map<String, Object> initPageData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> dateMap = new HashMap<>();
        dateMap.put("progress", CommonUtil.setComboValue("broadcast_progress"));//进度
        dateMap.put("region", CommonUtil.setComboValue("broadcast_region"));//区域
        dateMap.put("company", CommonUtil.setComboValue("broadcast_company"));//分公司
        dateMap.put("platform", CommonUtil.setComboValue("broadcast_platform"));//直播平台
        dateMap.put("onlineshop", CommonUtil.setComboValue("broadcast_onlineshop"));//网店名称
        return dateMap;
    }

    @Override
    public List<BroadcastEntity> setPageList(HttpServletRequest request, HttpServletResponse response, BroadcastEntity entity) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<BroadcastEntity> pageList = null;
        try {
            paramMap.put("progressSearch", entity.getProgress());//进度查询
            pageList = broadcastService.getListByParam(paramMap);
            for (BroadcastEntity broadcastEntity : pageList) {
                broadcastEntity.setBroadcastPlatform(CommonUtil.getTypeNameByTypeGroup("broadcast_platform", broadcastEntity.getBroadcastPlatform()));
                broadcastEntity.setBroadcastOnlineshop(CommonUtil.getTypeNameByTypeGroup("broadcast_onlineshop", broadcastEntity.getBroadcastOnlineshop()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageList;
    }

}