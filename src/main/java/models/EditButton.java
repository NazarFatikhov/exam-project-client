package models;


import javafx.scene.control.Button;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditButton extends Button {

    public EditButton() {
    }

    public EditButton(String text) {
        super(text);
    }

}
