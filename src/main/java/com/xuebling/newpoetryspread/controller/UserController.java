package com.xuebling.newpoetryspread.controller;


import com.xuebling.newpoetryspread.dao.UserRepository;
import com.xuebling.newpoetryspread.pojo.enums.ResponseMsg;
import com.xuebling.newpoetryspread.pojo.result.Response;
import com.xuebling.newpoetryspread.pojo.result.ResponseData;
import com.xuebling.newpoetryspread.pojo.user.Editor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    /* 用户激活
    @PutMapping(path = "/{userId}/valid")
    public Object validUser(@PathVariable(value = "userId") String userId) {
        try{
            // ...
            return new ResponseData(ResponseMsg.SUCCESS, userId);
        } catch (Exception e){
            return new Response(ResponseMsg.FAIL);
        }
    } */

    /* 添加用户 */
    @PutMapping(path = "")
    public Object addUser(@RequestBody @Valid Editor editor, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseData(ResponseMsg.FAIL, bindingResult.getFieldError().getDefaultMessage());
        }
        try{
            userRepository.save(editor);
            return new Response(ResponseMsg.SUCCESS);
        } catch (Exception e){
            return new Response(ResponseMsg.FAIL);
        }
    }

    /* 修改用户 */
    @PostMapping(path = "/{userId}")
    public Object updateUser(@PathVariable(value = "userId") String userId, @RequestBody @Valid Editor editor, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseData(ResponseMsg.FAIL, bindingResult.getFieldError().getDefaultMessage());
        }
        try{
            userRepository.updateById(userId, editor);
            return new Response(ResponseMsg.SUCCESS);
        } catch (Exception e){
            return new Response(ResponseMsg.FAIL);
        }
    }

    /* 删除用户 */
    @DeleteMapping(path = "/{userId}")
    public Object deleteUser(@PathVariable(value = "userId") String userId) {
        try{
            userRepository.deleteById(userId);
            return new ResponseData(ResponseMsg.SUCCESS, userId);
        }catch (Exception e){
            return new Response(ResponseMsg.FAIL);
        }
    }

    /* 用户权限分配 */
    @PostMapping(path = "/{userId}/level/{level}")
    public Object setLevel(@PathVariable(value = "userId") @NotEmpty String userId, @PathVariable(value = "level") Integer level) {
        if(level > 3 || level < 0) {
            return new Response(ResponseMsg.FAIL);
        }
        try{
            userRepository.updateLevelById(userId, level);
            return new Response(ResponseMsg.SUCCESS);
        }catch (Exception e){
            return new Response(ResponseMsg.FAIL);
        }
    }

    /* 用户锁定 */
    @PutMapping(path = "/{userId}/lock")
    public Object lockUser(@PathVariable(value = "userId") @NotEmpty String userId) {
        try{
            userRepository.updateLockById(userId, 1);
            return new Response(ResponseMsg.SUCCESS);
        }catch (Exception e){
            return new Response(ResponseMsg.FAIL);
        }
    }
    /* 用户解锁 */
    @DeleteMapping(path = "/{userId}/lock")
    public Object unlockUser(@PathVariable(value = "userId") @NotEmpty String userId) {
        try{
            userRepository.updateLockById(userId, 0);
            return new Response(ResponseMsg.SUCCESS);
        }catch (Exception e){
            return new Response(ResponseMsg.FAIL);
        }
    }
}
