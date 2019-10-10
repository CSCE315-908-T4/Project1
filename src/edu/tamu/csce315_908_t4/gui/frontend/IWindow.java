package edu.tamu.csce315_908_t4.gui.frontend;

import edu.tamu.csce315_908_t4.gui.backend.IBackend;
import javafx.scene.Scene;

public interface IWindow{
    Scene getScene(IBackend backend);
}
