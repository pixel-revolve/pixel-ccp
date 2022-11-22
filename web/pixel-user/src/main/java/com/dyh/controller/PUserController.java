package com.dyh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PUser;
import com.dyh.entity.dto.LoginFormDTO;
import com.dyh.entity.dto.PUserDTO;
import com.dyh.service.PUserService;
import com.dyh.utils.UserHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户(PUser)表控制层
 *
 * @author makejava
 * @since 2022-11-15 11:12:57
 */
@RestController
@RequestMapping("/api/pUser")
public class PUserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PUserService pUserService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pUser 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public R selectAll(Page<PUser> page, PUser pUser) {

//        if(pUser.getIsDel()==0) {
        return success(this.pUserService.page(page, new QueryWrapper<>(pUser)));
//        } else {
//            return R.failed("该用户已经被删除");
//        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public R selectOne(@PathVariable Serializable id) {
        PUser user = this.pUserService.getById(id);
        if(user.getIsDel() == 1) {
            return failed("该用户已经被删除");
        }
        return success(user);
    }

    /**
     * 通过电话查询单条数据
     *
     * @param phone 电话
     * @return {@link R}
     */
    @GetMapping("/selectByPhone/{phone}")
    public R selectByPhone(@PathVariable String phone){
        return this.pUserService.getByPhone(phone);
    }

    /**
     * 新增数据
     *
     * @param pUser 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public R insert(@RequestBody PUser pUser) {
        return success(this.pUserService.save(pUser));
    }

    @PostMapping("/createUserByPhone/{phone}")
    public R createUserByPhone(@PathVariable String phone){
        return this.pUserService.createUserByPhone(phone);
    }

    /**
     * 修改数据
     *
     * @param pUser 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public R update(@RequestBody PUser pUser) {

        if(pUser.getIsDel()==1) {
            return failed("该用户已经被删除");
        }
        return success(this.pUserService.updateById(pUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pUserService.removeByIds(idList));
    }

    // toDO: 实现逻辑删除

    @PostMapping("/login")
    public R login(@RequestBody LoginFormDTO loginFormDTO) throws JsonProcessingException {
        return pUserService.login(loginFormDTO);
    }

    @PostMapping("/logout")
    public R logout(HttpServletRequest request) {
        return pUserService.logout(request);
    }
    /**
     * 获取当前登录用户
     *
     * @return {@link R}
     */
    @GetMapping("/me")
    public R me(){
        // 获取当前登录的用户并返回
        PUserDTO user = UserHolder.getUser();
        return R.ok(user);
    }
}

