/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nfinity.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Prashan
 */
public class Foo {
    public static void main(String[] args) {
        FileInputStream inputStream;
        try {
            File inputFile;
            inputStream = new FileInputStream(inputFile=new File("c:\\users\\prashan\\desktop\\bar.txt"));
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            
            SecretKeySpec secretKeySpec = new SecretKeySpec("Mary has one cat".getBytes(), "AES");
            
            try {
                
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
                byte[] outputBytes = cipher.doFinal(inputBytes);
                
                File outputFile;
                FileOutputStream outputStream = new FileOutputStream(outputFile=new File("c:\\users\\prashan\\desktop\\bar2.txt"));
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
            Logger.getLogger(Foo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Foo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
