package com.example.monitor.service.impl;

import com.example.monitor.DTO.DischargeDTO;
import com.example.monitor.DTO.TerminalDTO;
import com.example.monitor.dao.TerminalMapper;
import com.example.monitor.pojo.Terminal;
import com.example.monitor.service.TerminalService;
import com.example.monitor.core.AbstractService;
import com.example.monitor.vo.ListRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on
 */
@Service
@Transactional
public class TerminalServiceImpl extends AbstractService<Terminal> implements TerminalService {
    @Resource
    private TerminalMapper terminalMapper;

    @Override
    public List<TerminalDTO> list() {
        TerminalDTO terminalDTO=new TerminalDTO();
        List<TerminalDTO> list =terminalMapper.list(terminalDTO);
        return list;
    }



    /**
     * 端子箱温度模糊查询信息
     * @param request
     * @return
     */
    @Override
    public List<TerminalDTO> tempList(ListRequest request) {

        TerminalDTO terminalDTO=new TerminalDTO();
        if (terminalDTO.getName()==null || terminalDTO.getName().isEmpty()){
            request.setName("");
        }else{
            terminalDTO.setName("%" + terminalDTO.getName() + "%");
        }
        List<TerminalDTO> list=terminalMapper.tempList(terminalDTO);
        return list;

    }

    /**
     * 端子箱湿度查询信息
     * @param request
     * @return
     */
    @Override
    public List<TerminalDTO> humidityList(ListRequest request) {

        TerminalDTO terminalDTO=new TerminalDTO();
        if (terminalDTO.getName()==null || terminalDTO.getName().isEmpty()){
            request.setName("");
        }else{
            terminalDTO.setName("%" + terminalDTO.getName() + "%");
        }
        List<TerminalDTO> list=terminalMapper.humidityList(terminalDTO);
        return list;
    }
}
