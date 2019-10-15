package edu.tamu.csce315_908_t4.gui.backend;

import edu.tamu.csce315_908_t4.gui.backend.arguments.IArgument;

import java.sql.SQLException;

public class BackendError{
    private static final BackendError NO_ERROR_INSTANCE = new BackendError(EErrorType.NO_ERROR, null);
    public final EErrorType type;
    public final Exception exception;

    private BackendError(EErrorType type, Exception exception){
        this.type = type;
        this.exception = exception;
    }

    public static BackendError NO_ERROR(){
        return NO_ERROR_INSTANCE;
    }

    public static BackendError SQL_ERROR(SQLException e){
        return new BackendError(EErrorType.SQL_ERROR, e);
    }

    public static BackendError GENERAL_ERROR(Exception e){
        return new BackendError(EErrorType.GENERAL_ERROR, e);
    }

    public static BackendError NULL_ARGS_ERROR(Class<? extends IArgument> type){
        return new BackendError(EErrorType.NULL_ARGS_ERROR, new Exception("Null Args of type: " + type.getName()));
    }

    public static <T> BackendError LIMIT_ERROR(String limitName, T value){
        return new BackendError(EErrorType.LIMIT_ERROR, new Exception(limitName + " limit reached: " + value));
    }

    public static BackendError NOT_FOUND_ERROR(){
        return new BackendError(EErrorType.NOT_FOUND_ERROR, new Exception("Target not found"));
    }

    public boolean isError(){
        return type != EErrorType.NO_ERROR;
    }

    public enum EErrorType{
        NO_ERROR,
        GENERAL_ERROR,
        SQL_ERROR,
        NULL_ARGS_ERROR,
        LIMIT_ERROR,
        NOT_FOUND_ERROR
    }
}
