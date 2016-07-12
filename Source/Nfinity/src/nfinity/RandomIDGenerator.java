/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nfinity;

import java.util.Random;

/**
 *
 * @author lumesh
 */
public class RandomIDGenerator {

    public RandomIDGenerator() {
    }
    
    public String Generate(int IDLength)
    {
        String ID="";
        char character[]="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        Random rnd = new Random();
        for(int i=0;i<IDLength;i++)
        {
            ID = character[rnd.nextInt(character.length)]+ID;
        }
        return ID;
    }
}
