package com.donggukthon.Showman.global.base;

public record ApiResponse<T> (
        T data
){

}