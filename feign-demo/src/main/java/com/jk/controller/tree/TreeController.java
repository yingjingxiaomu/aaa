package com.jk.controller.tree;

import com.jk.model.tree.TreeBean;

import com.jk.service.tree.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "tree")
public class TreeController {
	
	@Autowired
	private TreeService treeService;

	/**
	 * 查询树
	 * @return
	 */
	@RequestMapping("getTree")
	@ResponseBody
	public List<Map<String,Object>> getTree(){
		List<TreeBean> list = treeService.getTree();
		return treeStr(list,0);
	}
	
	public List<Map<String,Object>> treeStr(List<TreeBean> list, Integer pid){
		List<Map<String,Object>> newlist = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			//获取单个tree对象
			TreeBean tree = list.get(i);
			Map<String,Object> map = null;
			//判断当前tree对象的pid是否和传过来的pid相等，相等的保存到map中
			if(tree.getPid() == pid){
				map = new HashMap<String, Object>();
				map.put("id", tree.getId());
				map.put("title", tree.getTitle());
				map.put("href", tree.getHref());
				map.put("icon", tree.getIcon());
				map.put("spread", tree.getSpread());
				map.put("children", treeStr(list,tree.getId()));
			}
			if(map != null){
				List<Map<String,Object>> li = (List<Map<String, Object>>) map.get("children");
				if(li.size()<=0){
					map.remove("children");
				}
				newlist.add(map);
			}
		}
		return newlist;
	}

}
