package edu.tamu.csce315_908_t4.gui.backend;

public interface ICallback<T>{
    void callback(BackendError error, T data);
}
