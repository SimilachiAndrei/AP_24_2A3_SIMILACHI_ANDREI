package org.Bonus;

import lombok.AllArgsConstructor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
@AllArgsConstructor
public class View implements Command{
    String path;
    @Override
    public void execute() throws IOException, InvalidCommandException, InvalidDataException {
        File file = new File(path);
        if(file.exists())
        {
            Desktop.getDesktop().open(file);
        }
        else {
            throw new IOException("File not found: " + file);
        }
    }
}
