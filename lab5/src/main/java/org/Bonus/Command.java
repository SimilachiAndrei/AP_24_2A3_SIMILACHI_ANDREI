package org.Bonus;

import java.io.IOException;

public interface Command {
    void execute() throws IOException, InvalidCommandException, InvalidDataException;
}
