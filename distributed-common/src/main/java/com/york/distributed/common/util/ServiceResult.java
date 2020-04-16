package com.york.distributed.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


import java.io.Serializable;

/**
 * @author houzhenjing
 */
public class ServiceResult<T> implements Serializable {

    private static final long serialVersionUID = -1954065564856833013L;

    public static final int SC_SUCCESS = 0;
    public static final int SC_NON_AUTH = 203;

    public static final int SC_BAD_REQUEST = 400;
    public static final int SC_FORBIDDEN = 403;
    public static final int SC_TIMEOUT = 408;
    public static final int SC_GONE = 410;

    public static final int SC_SERVER_ERROR = 500;
    public static final int SC_DATA_ERROR = 501;

    private boolean success;
    private int code = -1;

    private String message = "";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ServiceResult() {
    }

    public static <T> ServiceResult<T> success() {
        return success(null);
    }

    public static <T> ServiceResult<T> success(T data) {
        return success(data, StringUtils.EMPTY);
    }

    public static <T> ServiceResult<T> success(T data, String message) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCodeSuccess().setSuccess(true).setMessage(message).setData(data);
        return result;
    }

    public static <T> ServiceResult<T> fail(int code, String message) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCode(code).setSuccess(false).setMessage(message);
        return result;
    }

    public static <T> ServiceResult<T> authFailed(String message) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCode(SC_NON_AUTH).setSuccess(false).setMessage(message);
        return result;
    }

    public static <T> ServiceResult<T> badRequest(String message) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCode(SC_BAD_REQUEST).setSuccess(false).setMessage(message);
        return result;
    }

    public static <T> ServiceResult<T> forbidden(String message) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCodeForbidden().setSuccess(false).setMessage(message);
        return result;
    }

    public static <T> ServiceResult<T> timeout(String message) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCodeTimeout().setSuccess(false).setMessage(message);
        return result;
    }

    public static <T> ServiceResult<T> serverError(String message) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCode(SC_SERVER_ERROR).setSuccess(false).setMessage(message);
        return result;
    }

    public static <T> ServiceResult<T> dataError(String message) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCode(SC_DATA_ERROR).setSuccess(false).setMessage(message);
        return result;
    }

    public static <T> ServiceResult<T> gone(String message) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCode(SC_GONE).setSuccess(false).setMessage(message);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public ServiceResult<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ServiceResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public ServiceResult<T> setCodeSuccess() {
        this.code = SC_SUCCESS;
        this.success = true;
        return this;
    }

    public ServiceResult<T> setCodeForbidden() {
        this.code = SC_FORBIDDEN;
        this.success = false;
        return this;
    }

    public ServiceResult<T> setCodeServerError() {
        this.code = SC_SERVER_ERROR;
        this.success = false;
        return this;
    }

    public ServiceResult<T> setCodeTimeout() {
        this.code = SC_TIMEOUT;
        this.success = false;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServiceResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ServiceResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServiceResult<?> that = (ServiceResult<?>) o;

        return new EqualsBuilder()
                .append(success, that.success)
                .append(code, that.code)
                .append(message, that.message)
                .append(data, that.data)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(success)
                .append(code)
                .append(message)
                .append(data)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}