package org.givwenzen;

public class IgnoreAllTestsGivWenZenException extends GivWenZenException {
    private static final String IGNORE_ALL_EXCEPTION_TAG = "IGNORE_ALL_TESTS";

    public IgnoreAllTestsGivWenZenException(String message){
        super(message, IGNORE_ALL_EXCEPTION_TAG);
    }
}
