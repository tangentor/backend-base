package cn.swunlp.backend.base.web.entity;

import cn.swunlp.backend.base.web.constant.ResultCode;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

/**
 * 功能描述：
 *    统一返回结果类
 * @author TangXi
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(){}
    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<?> instance(Integer code, String msg, T data){
        return new Result<>(code,msg,data);
    }

    public static <T> Result<?> error(String msg){
        return new Result<>(ResultCode.BUSINESS_ERROR,msg,null);
    }

    public static <T> Result<?> error(int code,String msg){
        return new Result<>(code,msg,null);
    }

    public static <T> Result<?> success(T data){
        return new Result<>(ResultCode.SUCCESS,null,data);
    }

    public static <T> Result<?> adjust(T data){
        // 判断是否为空
        if(Objects.isNull(data)){
            return error(ResultCode.BUSINESS_ERROR,"对象为空");
        }
        if(data instanceof List){
            if(((List<?>) data).size() == 0){
                return error(ResultCode.BUSINESS_ERROR,"数据为空");
            }
        }
        return success(data);
    }
}