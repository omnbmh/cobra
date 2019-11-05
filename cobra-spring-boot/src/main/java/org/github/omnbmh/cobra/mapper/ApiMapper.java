package org.github.omnbmh.cobra.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.github.omnbmh.cobra.entity.Api;
import org.github.omnbmh.cobra.entity.ApiExample;

@Mapper
public interface ApiMapper {
    int countByExample(ApiExample example);

    int deleteByExample(ApiExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Api record);

    int insertSelective(Api record);

    List<Api> selectByExample(ApiExample example);

    Api selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Api record, @Param("example") ApiExample example);

    int updateByExample(@Param("record") Api record, @Param("example") ApiExample example);

    int updateByPrimaryKeySelective(Api record);

    int updateByPrimaryKey(Api record);
}