package com.vimpirate;

import java.io.Serial;

/**
 * Name: David Graham
 * Description: A checked exception for invalid time formats.
 */
public class InvalidTime extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an InvalidTime exception.
     *
     * @param message The exception message.
     */
    public InvalidTime(String message) {
        super(message);
    }
}
