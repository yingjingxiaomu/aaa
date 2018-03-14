package com.jk.service.tree;

import com.jk.mapper.tree.TreeMapper;
import com.jk.model.tree.TreeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {


	@Autowired
	private TreeMapper treeMapper;


	public List<TreeBean> getTree() {

			return treeMapper.getTree();

	}

}
