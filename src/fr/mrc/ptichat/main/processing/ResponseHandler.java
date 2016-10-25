package fr.mrc.ptichat.main.processing;

import fr.mrc.ptichat.main.utils.MessageSignatureController;

import java.io.*;

public class ResponseHandler {
    private MessageSignatureController msc;

    public ResponseHandler() {
        this.msc = new MessageSignatureController();
    }

    /**
     * Returns the termination signal for the Peer to Peer chat.
     * @return the <code>byte[]</code> representation of the termination signal
     */
    public byte[] getTerminationBytes() {
        return String.valueOf(msc.getTerminationSignature()).getBytes();
    }

    /**
     * Formats a string for the Peer to Peer chat.
     * @param msg the <code>String</code> to format
     * @return the representation of <code>msg</code>
     */
    public byte[] formatMessage(String msg) {
        return (msc.getMessageSignature() + ((msg == null) ? "" : msg)).getBytes();
    }

    /**
     * Encodes a file into an array of bytes.
     * @param filename the location of the file
     * @return the <code>byte[]</code> representation of the file
     */
    public byte[] fileToByteArray(String filename) {
        File fileToSend = new File(filename);
        int n;
        byte[] result = new byte[(int) fileToSend.length() + 1];
        result[0] = (byte) 'F';
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileToSend))) {
            n = bis.read(result, 1, result.length - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}