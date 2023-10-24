package com.esjang.sthome.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.esjang.sthome.domain.User;

@Mapper
public interface UserMapper {
	
	@Select(value = "select * from tbl_user")
	public Optional<User> m_findById(String id);
}
