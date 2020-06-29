package net.xaviersala.Vaca.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @author mcargol based on xavier's VacaProject
 *
 */

public class CamioTests {
    Camio sut;

	@BeforeEach
	public void setUp() throws Exception {

    }
    //  Ha de poder entrar i treure vaques del camió    
    // Primer provarem de treure una vaca quan el camio es buit
	@Test
	public void testTreueVacaCamioBuit() {

		// ARRANGE
		sut = new Camio(3500);
        Vaca vaca = new Vaca("Paca",1,new Raca("Angus",1));

		// ACT
        Boolean resultat = sut.TreuVaca(vaca);

		// ASSERT
		assertFalse(resultat, "Ha sortit una vaca d'un camió buit");
    }

        // Primer provarem de treure una vaca quan el camio es buit
	@Test
	public void testAfegirTreueVacaCamioBuit() {

		// ARRANGE
		sut = new Camio(3500);
        Vaca vacaReal = new Vaca("Paca",1,new Raca("Angus",1));

        // ACT
        Boolean resultatEntra = sut.EntraVaca(vacaReal);
        Boolean resultatSurt = sut.TreuVaca(vacaReal);

		// ASSERT
        assertTrue(resultatEntra, "No ha pogut entrar una vaca d'1 Kg");
        assertTrue(resultatSurt, "No ha pogut treure una vaca que hi és");
    }
    
//	Ha de controlar el límit de pes (no es permeten més vaques si se supera el pes)
    @Test
    public void testControlaPesPassa() {

        // ARRANGE ... provarem amb "vaques fake" i un valor límit
        sut = new Camio(300);
        Vaca vacaFake = Mockito.mock(Vaca.class);
        Mockito.when(vacaFake.getPes()).thenReturn(150.0);
        Vaca vacaFake2 = Mockito.mock(Vaca.class);
        Mockito.when(vacaFake.getPes()).thenReturn(150.0);

        // ACT
        Boolean resultat = sut.EntraVaca(vacaFake);
        Boolean resultat2 = sut.EntraVaca(vacaFake2);

        // ASSERT
        assertTrue(resultat, "La vaca no ha entrat");
        assertTrue(resultat2, "La vaca no ha entrat");
    }

    @Test
    public void testControlaPesNoPassa() {

        // ARRANGE ... provarem amb "vaques fake" i un valor límit
        sut = new Camio(300);
        Vaca vacaFake = Mockito.mock(Vaca.class);
        Mockito.when(vacaFake.getPes()).thenReturn(150.0);
        Vaca vacaFake2 = Mockito.mock(Vaca.class);
        Mockito.when(vacaFake2.getPes()).thenReturn(150.5);

        // ACT
        Boolean resultat = sut.EntraVaca(vacaFake);
        Boolean resultat2 = sut.EntraVaca(vacaFake2);

        // ASSERT
        assertTrue(resultat, "La vacafake no ha entrat");
        assertFalse(resultat2, "La vacafake2 SÍ ha entrat");
    }

//	Ha de comptabilitzar la llet que porta 
    @Test
	public void testComptaLlet() {

        // ARRANGE ... provarem amb "vaques fake"
        sut = new Camio(300);
		Vaca vacaFake = Mockito.mock(Vaca.class);
        Mockito.when(vacaFake.GetLitres()).thenReturn(20.00);
        Vaca vacaFake2 = Mockito.mock(Vaca.class);
		Mockito.when(vacaFake2.GetLitres()).thenReturn(50.00);

		// ACT
        Boolean resultat = sut.EntraVaca(vacaFake);
        Boolean resultat2 = sut.EntraVaca(vacaFake2);
		Double litres = sut.GetLitres();

		// ASSERT
        assertTrue(resultat, "La vacafake no ha entrat");
        assertTrue(resultat2, "La vacafake2 no ha entrat");
        assertEquals(70.00, litres, "Els litres nocoincideixen");
	}
}

