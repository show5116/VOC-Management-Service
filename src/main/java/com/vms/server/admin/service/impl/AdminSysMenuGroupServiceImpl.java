package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.dao.AdminSysMenuGroupDao;
import com.vms.server.admin.repository.jpa.AdminSysMenuGroupRepository;
import com.vms.server.admin.service.AdminSysMenuGroupService;
import com.vms.server.domain.dto.SysMenuDto;
import com.vms.server.domain.dto.SysMenuGroupDto;
import com.vms.server.domain.entity.sys.SysMenuGroup;
import com.vms.server.domain.vo.SysMenuVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminSysMenuGroupServiceImpl implements AdminSysMenuGroupService {

    private final AdminSysMenuGroupRepository adminSysMenuGroupRepository;
    private final AdminSysMenuGroupDao adminSysMenuGroupDao;
    private final ModelMapper modelMapper;

    @Override
    public List<SysMenuGroupDto> getSysMenuGroup(SysMenuGroupDto sysMenuGroupDto) {
        return adminSysMenuGroupDao.getSysMenuGroup(sysMenuGroupDto);
    }

    @Override
    public void insertSysMenuGroup(SysMenuGroupDto sysMenuGroupDto) {
        if (sysMenuGroupDto != null) {
            sysMenuGroupDto.setVisibleFlag("Y");
            SysMenuGroup sysMenuGroup = modelMapper.map(sysMenuGroupDto, SysMenuGroup.class);
            adminSysMenuGroupRepository.save(sysMenuGroup);
        }
    }

    @Override
    public void deleteSysMenuGroup(SysMenuGroupDto sysMenuGroupDto) {
        if (sysMenuGroupDto != null) {
            SysMenuGroup sysMenuGroup = modelMapper.map(sysMenuGroupDto, SysMenuGroup.class);
            adminSysMenuGroupRepository.delete(sysMenuGroup);
        }
    }

    @Override
    public List<SysMenuVo> getMenuTree(SysMenuGroupDto sysMenuGroupDto) {
        List<SysMenuDto> menuTree = adminSysMenuGroupDao.getMenuTree(sysMenuGroupDto);
        List<SysMenuVo> result = new ArrayList<>();

        int id = 1;
        for (SysMenuDto menuData : menuTree) {
            SysMenuVo vo = new SysMenuVo();
            if(menuData.getParentKey() != null) {
                String[] levels = menuData.getMenuId().split("-");
                int displayDepth = menuData.getDisplayDepth() - 1;
                String parentId = String.join("-", Arrays.copyOfRange(levels, 0, displayDepth));

                for (SysMenuVo sysMenuVo : result) {
                    if(sysMenuVo.getSysMenuDtos().getMenuId().equals(parentId)) {
                        vo.setId(id++);
                        vo.setParent(sysMenuVo.getId());
                        vo.setText(menuData.getMenuName());
                        vo.setSysMenuDtos(menuData);
                    }
                }

            }else{
                vo.setId(id++);
                vo.setParent(0);
                vo.setText(menuData.getMenuName());
                vo.setSysMenuDtos(menuData);
            }
            result.add(vo);
        }
        return result;

//        List<SysMenuDto> result = new ArrayList<SysMenuDto>();
//
//        String parentKey = "";
//
//        for (SysMenuDto menuData : menuTree) {
//            if(menuData.getParentKey() != null) {
//                appendChild(result.get(Integer.parseInt(parentKey)-1), menuData, parentKey, menuData.getDisplayDepth()-1, 1);
//            }else{
//                parentKey = menuData.getMenuId();
//                result.add(menuData);
//            }
//        }
//
//        return result;
    }

    void appendChild(SysMenuDto children, SysMenuDto menuData, String parentKey,  int depth, int count) {
        if(count == depth){
            children.getChildren().add(menuData);
            return;
        }
        String[] menuIdArr = menuData.getMenuId().split("-");
        appendChild(children.getChildren().get(Integer.parseInt(menuIdArr[count])-1), menuData, parentKey, depth, count+1);
    }

    @Override
    public List<SysMenuGroupDto> getConditionModule(SysMenuGroupDto sysMenuGroupDto) {
        return adminSysMenuGroupDao.getConditionModule(sysMenuGroupDto);
    }

    @Override
    public List<SysMenuGroupDto> getGroupId(SysMenuGroupDto sysMenuGroupDto) {
        String plant = sysMenuGroupDto.getPlant();
        String moduleId = sysMenuGroupDto.getModuleId();
        List<SysMenuGroupDto> result = new ArrayList<>();
        List<SysMenuGroup> groupIdList = adminSysMenuGroupRepository.findByPlantAndModuleId(plant, moduleId);
        groupIdList.forEach(sysMenuGroup -> result.add(modelMapper.map(sysMenuGroup, SysMenuGroupDto.class)));
        return result;
    }
}
