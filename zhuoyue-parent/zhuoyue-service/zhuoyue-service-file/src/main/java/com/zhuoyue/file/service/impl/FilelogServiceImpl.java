package com.zhuoyue.file.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuoyue.file.dao.FileLogMapper;
import com.zhuoyue.file.pojo.Filelog;
import com.zhuoyue.file.service.FilelogService;
import com.zhuoyue.file.util.IpUtils;
import entity.IdWorker;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FilelogServiceImpl implements FilelogService {
    @Resource
    private FileLogMapper filelogMapper;

    private IdWorker idWorker = new IdWorker(1, 0);
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public void log(HttpServletRequest httpRequest, String file, String method, String url) {
        //记录操作
        executorService.submit(() -> {
            String ipAddr = IpUtils.getIpAddr(httpRequest);
            Filelog fileOperateInfo = new Filelog();
            fileOperateInfo.setId(idWorker.nextId());
            fileOperateInfo.setFileName(file);
            fileOperateInfo.setIp(ipAddr);
            fileOperateInfo.setTime(new Date());
            fileOperateInfo.setUrl(url);
            fileOperateInfo.setMethod(method);
            filelogMapper.insertSelective(fileOperateInfo);
        });

    }

    /**
     * 根据时间查找
     *
     * @param bg   大于
     * @param lg   小于
     * @param num
     * @param size
     * @return
     */
    @Override
    public PageInfo<Filelog> getByTime(Date bg, Date lg, int num, int size) {
        Example example = new Example(Filelog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("time", bg, lg);
        PageHelper.startPage(num, size);
        return new PageInfo<>(filelogMapper.selectByExample(example));
    }

    @Override
    public void add(Filelog filelog) {
        filelogMapper.insertSelective(filelog);
    }

    @Override
    public void delById(Long id) {
        filelogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Filelog filelog) {
        filelogMapper.updateByPrimaryKeySelective(filelog);
    }

    @Override
    public Filelog findById(Long id) {
        return filelogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Filelog> findAll() {
        return filelogMapper.selectAll();
    }

    @Override
    public PageInfo<Filelog> findAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findAll());
    }

    @Override
    public List<Filelog> findByInfo(Filelog filelog) {
        Example example = new Example(Filelog.class);
        Example.Criteria criteria = example.createCriteria();
        if (filelog.getIp() != null) {
            criteria.andLike("ip", "%" + filelog.getIp() + "%");
        }
        if (filelog.getFileName() != null) {
            criteria.andLike("fileName", "%" + filelog.getFileName() + "%");
        }
        if (filelog.getUrl() != null) {
            criteria.andLike("url", "%" + filelog.getUrl() + "%");
        }
        if (filelog.getMethod() != null) {
            criteria.andLike("method", "%" + filelog.getMethod() + "%");
        }
        return filelogMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Filelog> findInfoByPage(Filelog filelog, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findByInfo(filelog));
    }
}