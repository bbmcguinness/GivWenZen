package org.givwenzen;

public class IgnoreScriptTestGivWenZenException extends GivWenZenException {
    private static final String IGNORE_EXCEPTION_TAG = "IGNORE_TEST";

    public IgnoreScriptTestGivWenZenException(String message){
        super(message, IGNORE_EXCEPTION_TAG);
    }
}
