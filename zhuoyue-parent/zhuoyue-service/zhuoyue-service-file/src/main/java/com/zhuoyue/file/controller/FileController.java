package com.zhuoyue.file.controller;

import com.zhuoyue.file.FastFdsFile;
import com.zhuoyue.file.pojo.Filelog;
import com.zhuoyue.file.service.FilelogService;
import com.zhuoyue.file.util.FdfsUtil;
import com.zhuoyue.file.util.ImageUtil;
import entity.Page;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/file")
@Api(value = "文件服务器接口")
public class FileController {

    @Autowired
    private FilelogService fileService;

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", notes = "实现文件的上传")
    public Result<String> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException, MyException {
        String originalFilename = file.getOriginalFilename();
        FastFdsFile sjh = new FastFdsFile(originalFilename, file.getBytes(), StringUtils.getFilenameExtension(originalFilename), null, null);
        String s = FdfsUtil.upLoad(sjh);
        //记录操作
        fileService.log(request, originalFilename, "upload", s);
        return new Result<>(true, StatusCode.OK, "上传文件成功", s);
    }

    @ApiOperation(value = "下载文件", notes = "实现文件的下载")
    @GetMapping("/download")
    public Result<String> downLoad(@RequestParam(name = "group") String group,
                                   @RequestParam(name = "remotepath") String path) throws IOException, MyException {
        byte[] bytes = FdfsUtil.downLoadToArray(group, path);
        String substring = path.substring(path.lastIndexOf('.'));
        String s = ImageUtil.base64(bytes, substring);
        return new Result<String>(true, StatusCode.OK, "文件下载成功", s);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除文件", notes = "实现文件的删除")
    public Result deleteFile(@RequestParam(name = "group") String group,
                             @RequestParam(name = "remotepath") String path,
                             HttpServletRequest request) throws IOException, MyException {
        FdfsUtil.delFile(group, path);
        fileService.log(request, null, "delete", "/" + group + "/" + path);
        return new Result(true, StatusCode.OK, "文件删除成功");
    }

}
