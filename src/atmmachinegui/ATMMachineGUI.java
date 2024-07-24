/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atmmachinegui;

/**
 *
 * @author RohanThapa
 */
public class ATMMachineGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        View view = new View();
        Model model = new Model();
        
        new Controller(view , model);
    }
    
}
