/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nfinity.server.bruteforce;

import nfinity.server.WorkExecutor;
import nfinity.server.WorkUnit;

/**
 *
 * @author Prashan
 */
public class PasswordExecutor implements WorkExecutor{

    @Override
    public void execute(WorkUnit workUnit) {
        workUnit.execute();
    }
    
}
