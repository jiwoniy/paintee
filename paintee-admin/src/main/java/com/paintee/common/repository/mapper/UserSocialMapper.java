package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.UserSocial;
import com.paintee.common.repository.entity.UserSocialExample;
import com.paintee.common.repository.entity.UserSocialKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSocialMapper {
    int countByExample(UserSocialExample example);

    int deleteByExample(UserSocialExample example);

    int deleteByPrimaryKey(UserSocialKey key);

    int insert(UserSocial record);

    int insertSelective(UserSocial record);

    List<UserSocial> selectByExample(UserSocialExample example);

    UserSocial selectByPrimaryKey(UserSocialKey key);

    int updateByExampleSelective(@Param("record") UserSocial record, @Param("example") UserSocialExample example);

    int updateByExample(@Param("record") UserSocial record, @Param("example") UserSocialExample example);

    int updateByPrimaryKeySelective(UserSocial record);

    int updateByPrimaryKey(UserSocial record);
}