/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nfinity.server.bruteforce;

import nfinity.server.WorkUnit;
import nfinity.server.WorkUnitGenerator;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 *
 * @author Prashan
 */
public class PasswordGenerator implements WorkUnitGenerator{
    
    private long current;
    
    private static String chars[] = new String[]{
                        "a","b","c","d","e",
                        "f","g","h","i","j",
                        "k","l","m","n","o",
                        "p","q","r","s","t",
                        "u","v","w","x","y","z"};
//    private static String chars[] = new String[]{"a","b","c","d"};

    @Override
    public WorkUnit generateNext() {
        ICombinatoricsVector<String> initialVector = Factory.createVector(chars);
        Generator<String> gen = Factory.createPermutationWithRepetitionGenerator(initialVector,16);
        current+=1000;
        return new PasswordUnit(gen, current-1000, current);
    }
    
}
