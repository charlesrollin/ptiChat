package main.java.fr.mrc.ptichat.test;

import main.java.fr.mrc.ptichat.processing.ResponseHandler;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ResponseHandlerTest {

    private ResponseHandler rh = new ResponseHandler();

    @Test
    public void getTerminationBytes() throws Exception {
        assertArrayEquals("Q".getBytes() , rh.getTerminationBytes());
    }

    @Test
    public void formatMessage() throws Exception {
        assertArrayEquals("M".getBytes() , rh.formatMessage(null));
        assertArrayEquals("Mtoto".getBytes() , rh.formatMessage("toto"));
    }

}