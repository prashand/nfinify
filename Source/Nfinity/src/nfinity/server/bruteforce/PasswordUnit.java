/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nfinity.server.bruteforce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import nfinity.server.Foo;
import nfinity.server.WorkUnit;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 *
 * @author Prashan
 */
public class PasswordUnit implements WorkUnit {

    private Generator<String> generator;
    private long start;
    private long end;
    private int current;

    public PasswordUnit(Generator<String> generator, long start, long end) {
        this.generator = generator;
        this.start = start;
        this.end = end;
    }

    @Override
    public float getProgress() {
        return current / (end - start) * 100;
    }

    @Override
    public void execute() {
        FileInputStream inputStream;
        File inputFile;
        try {
            inputStream = new FileInputStream(inputFile = new File("c:\\users\\prashan\\desktop\\bar.txt"));
            byte[] inputBytes;
            inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            String password = "";
            for (ICombinatoricsVector<String> vec : generator.generateAllObjects()) {
                Iterator<String> iterator = vec.iterator();
                while (iterator.hasNext()) {
                    password += iterator.next();
                }
            }

            SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), "AES");
            try {
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                byte[] outputBytes = cipher.doFinal(inputBytes);

                File outputFile;
                FileOutputStream outputStream = new FileOutputStream(outputFile = new File("c:\\users\\prashan\\desktop\\bar2.txt"));
                outputStream.write(outputBytes);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Foo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Foo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Foo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Foo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(Foo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PasswordUnit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PasswordUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
