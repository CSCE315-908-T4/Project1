package edu.tamu.csce315_908_t4.gui.backend;

public class BackendErrorData<T>{
    public final BackendError error;
    public final T data;

    public BackendErrorData(BackendError error, T data){
        this.error = error;
        this.data = data;
    }

    public boolean isError(){
        return error.isError();
    }

    @Override
    public String toString(){
        if(isError()){
            throw new RuntimeException(error.exception);
        }
        return data.toString();
    }
}
