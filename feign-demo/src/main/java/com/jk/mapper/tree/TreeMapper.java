package com.jk.mapper.tree;

import com.jk.model.tree.TreeBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Component
public interface TreeMapper {

	@Select("SELECT id,title,icon,href,spread,pid FROM t_tree")
	List<TreeBean> getTree();

}
