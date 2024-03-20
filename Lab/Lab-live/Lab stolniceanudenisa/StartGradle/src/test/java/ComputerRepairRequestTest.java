//import model.ComputerRepairRequest;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;


import model.ComputerRepairRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerRepairRequestTest {
    @Test
    @DisplayName("Test 1")
    public void testExample1() {
      ComputerRepairRequest request1 = new ComputerRepairRequest();
        ComputerRepairRequest request = new ComputerRepairRequest(1, "Ana", "Str. Mihai Viteazu 1", "0756789456", "Lenovo", "12.12.2020", "Laptopul nu porneste");
        assertEquals(1, request.getID());
        assertEquals("Ana", request.getOwnerName());
        assertEquals("Str. Mihai Viteazu 1", request.getOwnerAddress());
        assertEquals("0756789456", request.getPhoneNumber());
        assertEquals("Lenovo", request.getModel());
        assertEquals("12.12.2020", request.getDate());
        assertEquals("Laptopul nu porneste", request.getProblemDescription());

        assertEquals("", request1.getOwnerName());
        assertEquals("", request1.getOwnerAddress());
    }

    @Test
    @DisplayName("Test 2")
    public void testExample2() {
       assertEquals(2,2,"Numerele ar trebui sa fie egale");
    }

}