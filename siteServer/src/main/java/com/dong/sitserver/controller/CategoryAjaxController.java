package com.dong.sitserver.controller;

import com.dong.sitserver.bean.CategoryBean;
import com.dong.sitserver.common.BackJsonBean;
import com.dong.sitserver.common.util.JacksonUtil;
import com.dong.sitserver.common.util.StringTools;
import com.dong.sitserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by rxia on 2015/9/2.
 */

/**
 * 类别
 */
@Controller
public class CategoryAjaxController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/categoryAjax/list.action")
    @ResponseBody
    protected Object list(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String category = (String) req.getParameter("category");
        String remark = (String) req.getParameter("remark");

        CategoryBean bean = new CategoryBean();

        bean.setName(category);
        bean.setRemark(remark);
        List<CategoryBean> beans = categoryService.queryCategorys(bean);

  /*      req.setAttribute("json", JacksonUtil.objToJson(beans));
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }       try {
*/

        return JacksonUtil.objToJson(beans);

    }


    @RequestMapping("/categoryAjax/add.action")
    @ResponseBody
    public Object add(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        String objJson = (req.getParameter("bean"));
        if (StringTools.isEmptyOrNone(objJson)) {
            //return  new BackJsonBean("无数据，不能添加").toJson();
            return new BackJsonBean("无数据，不能添加").toJson();
        }

        CategoryBean categoryBean = (CategoryBean) JacksonUtil.jsonToObj(objJson,
                CategoryBean.class);
        System.out.println("===================================================================");
        System.out.println(categoryBean.getName());

        //判断是否可以添加
        CategoryBean beanQ = new CategoryBean();
        beanQ.setName(categoryBean.getName());
        beanQ = categoryService.findCategory(beanQ);
        if (beanQ != null) {
            //  return new BackJsonBean("已经存在该分类，添加失败").toJson();
            return new BackJsonBean("已经存在该分类，添加失败").toJson();
        }

        //添加
        categoryService.addCategory(categoryBean);

        return new BackJsonBean().toJson();


    }

    @RequestMapping("/categoryAjax/update.action")
    @ResponseBody
    public Object update(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String objJson = (req.getParameter("bean"));
        System.out.println("=========" + objJson);

        CategoryBean categoryBean = (CategoryBean) JacksonUtil.jsonToObj(objJson,
                CategoryBean.class);
        categoryService.updateCategory(categoryBean);
        return new BackJsonBean().toJson();
    }

    @RequestMapping("/categoryAjax/delete.action")
    @ResponseBody
    public Object delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");

        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setId(id);
        categoryService.deleteCategory(categoryBean);
        return new BackJsonBean().toJson();
    }


/*    public CategoryBean find(HttpServletRequest req, HttpServletResponse resp)
    {
        String id = req.getParameter("id");
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setId(id);
        categoryBean = categoryDAO.findBean(categoryBean);

        return categoryBean;

    }*/


}
