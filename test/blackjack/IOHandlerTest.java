/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package blackjack;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import static junit.framework.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Ran Ren
 */
public class IOHandlerTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testInputNameGood() {
        ByteArrayInputStream testInput = new ByteArrayInputStream("Ran Ren".getBytes());
        IOHandler io = IOHandler.getInstance();
        String expResult = "Ran Ren";
        String result = io.getString(testInput);
        assertEquals(expResult, result);
        System.out.println("Testing string input");
    }

    @Test
    public void testInputChoiceYesGood() {
        ByteArrayInputStream testInput = new ByteArrayInputStream("yes".getBytes());
        IOHandler io = IOHandler.getInstance();
        String[] choices = {"yes", "no"};
        String expResult = "yes";
        String result = io.selectOption(testInput, choices);
        assertEquals(expResult, result);
        System.out.println("Testing choice input (yes)");
    }

    @Test
    public void testInputChoiceNoGood() {
        ByteArrayInputStream testInput = new ByteArrayInputStream("no".getBytes());
        IOHandler io = IOHandler.getInstance();
        String[] choices = {"yes", "no"};
        String expResult = "no";
        String result = io.selectOption(testInput, choices);
        assertEquals(expResult, result);
        System.out.println("Testing choice input (no)");
    }

    // Testing inputting after several invalid selections
    @Test
    public void testInputChoiceBoundary() {
        ByteArrayInputStream testInput = new ByteArrayInputStream("hi\nhitt\nhit".getBytes());
        IOHandler io = IOHandler.getInstance();
        String[] choices = {"hit", "stand"};
        String expResult = "hit";
        String result = io.selectOption(testInput, choices);
        assertEquals(expResult, result);
        System.out.println("Testing choice input (hit)");
    }

    // Tests that method throws exception if it reaches end of input w/o valid selection
    @Test
    public void testInputChoiceBad() {
        exceptionRule.expect(NoSuchElementException.class);
        exceptionRule.expectMessage("No line found");
        ByteArrayInputStream testInput = new ByteArrayInputStream("invalidinput\ninvalidinput\ninvalidinput".getBytes());
        IOHandler io = IOHandler.getInstance();
        String[] choices = {"hit", "stand"};
        String result = io.selectOption(testInput, choices);
        System.out.println("Testing choice input (invalid)");
    }
}
