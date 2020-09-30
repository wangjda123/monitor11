package ${basePackage}.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${basePackage}.annotation.Auth;
import ${basePackage}.core.result.*;
import ${basePackage}.pojo.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@Auth("admin")
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/add")
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultUtil.success();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody ${modelNameUpperCamel} request) {
        ${modelNameLowerCamel}Service.deleteById(request.getId());
        return ResultUtil.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ${modelNameUpperCamel} request) {
        ${modelNameLowerCamel}Service.update(request);
        return ResultUtil.success();
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody ${modelNameUpperCamel} request) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(request.getId());
        return ResultUtil.success("成功",${modelNameLowerCamel});
    }

    @PostMapping("/list")
    public Result list(@RequestBody PageRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);

        PageResponse<${modelNameUpperCamel}> response=new PageResponse<${modelNameUpperCamel}>();
        response.setList(pageInfo.getList());
        response.setTotalCount(pageInfo.getTotal());
        return ResultUtil.success("成功",response);
    }
}
