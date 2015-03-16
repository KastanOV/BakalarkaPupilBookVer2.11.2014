/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testvytizenipopapu;

/**
 *
 * @author Jaroslav
 */
public class TestVytizeniPopapu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < DbTable.userCount; i++) {
        new CommonUser().start();
      }
    }
    
}
