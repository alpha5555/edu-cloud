package net.edu.system.convert;

import net.edu.system.entity.SysDictDataEntity;
import net.edu.system.vo.SysDictDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictDataConvert {
    SysDictDataConvert INSTANCE = Mappers.getMapper(SysDictDataConvert.class);

    SysDictDataVO convert(SysDictDataEntity entity);

    SysDictDataEntity convert(SysDictDataVO vo);
    
    List<SysDictDataVO> convertList(List<SysDictDataEntity> list);

}
