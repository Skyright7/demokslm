package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.MessageCard;
import com.demo.demokslm.pojo.ResponseResult;
import com.demo.demokslm.service.MessageCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageCardController {
    @Autowired
    private MessageCardService messageCardService;

    @GetMapping(value = "/list/{id}") //这里是通过userID联级查找
    public ResponseResult<List<Integer>> findMyMessageCardList(@PathVariable Integer id){
        List<Integer> result = messageCardService.findMessageCardIdList(id);
        return ResponseResult.success(result);
    }
    @GetMapping(value = "/{id}")
    public ResponseResult<MessageCard> findOneMessageCardById(@PathVariable Integer id){
        return ResponseResult.success(messageCardService.findMessageCardById(id));
    }

    @PostMapping
    public ResponseResult<String> addMessageCard(@RequestBody MessageCard messageCard){
        messageCardService.addMessageCard(messageCard);
        return ResponseResult.success("Successful add one Message");
    }

    @PutMapping
    public ResponseResult<String> adjustMessageCard(@RequestBody MessageCard messageCard){
        messageCardService.updateMessageCard(messageCard);
        return ResponseResult.success("Successful adjust one MessageCard");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult<String> deleteMessageCardById(@PathVariable Integer id){
        messageCardService.deleteMessageCardById(id);
        return ResponseResult.success("Successful delete one MessageCard");
    }

}
