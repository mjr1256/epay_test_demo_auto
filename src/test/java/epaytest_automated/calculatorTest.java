package epaytest_automated;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class calculatorTest {
	Calculator calc;
	@Before
	public void setUp() {
		calc = new Calculator();
	}

	String[] command_smartcard= {"smartcard","psam1","psam2","psam3","psam4"};
	@Test
	public void smartCardTest() { assertEquals(0, calc.adb_test2("IccardTest."+command_smartcard[0]));}

	/* SAM_TEST*/
	@Test 
	public void sam1CardTest() { assertEquals(0, calc.adb_test2("IccardTest."+command_smartcard[1]));}
	@Test 
	public void sam2CardTest() { assertEquals(0, calc.adb_test2("IccardTest."+command_smartcard[2]));}
	@Test 
	public void sam3CardTest() { assertEquals(0, calc.adb_test2("IccardTest."+command_smartcard[3]));}
	@Test 
	public void sam4CardTest() { assertEquals(0, calc.adb_test2("IccardTest."+command_smartcard[4]));}
	

	String[] command_rfcard= {"poll"};
	@Test 
	public void rfcardTestPoll() { assertEquals(0, calc.adb_test2("ClessTest."+command_rfcard[0]));}

	String[] command_lifecycle= {"getState","getWarning"};
	@Test 
	public void lifecycleTestsGetState() {assertEquals(0, calc.adb_test2("Lifecycle."+ command_lifecycle[0]));} // Liifecycle Get state

	@Test 
	public void lifecycleTestsGetCurrentTamperState() {assertEquals(0, calc.adb_test2("Lifecycle."+ command_lifecycle[1]));} // Liifecycle Get warning 

	@Test
	public void touchPanelAutoTest() {assertEquals(0, calc.adb_test2("TpTest.autotest"));} // Touch Panel test 
	@Test
	public void touchPanelTestNormal() {assertEquals(0, calc.adb_test2("TpTest.normal"));} // Touch Panel test 
	
	String[] command_crypto= {"rsa2048","aes","stress","rsa1024","tdes"}; //test RSA

	@Test
	public void cryptographicTestRsa2048() {assertEquals(0, calc.adb_test2("AlgoTest."+command_crypto[0]));} //Test crypto rsa 20248

	@Test
	public void cryptographicTestAesEcb() {assertEquals(0, calc.adb_test2("AlgoTest."+command_crypto[1]));} //Test AES ECB

	@Test
	public void cryptographicStressTest() {assertEquals(0, calc.adb_test2("AlgoTest."+command_crypto[2]));} //Test STRESS TEST 
	
	@Test
	public void cryptographicsRsaKeyGenTest() {assertEquals(0, calc.adb_test2("AlgoTest.*"));} //Test Key gen *
	
	@Test
	public void cryptographicTestRsa1024() {assertEquals(0, calc.adb_test2("AlgoTest."+command_crypto[3]));} //Test crypto rsa 1024
	
	@Test
	public void cryptographicTestTdes() {assertEquals(0, calc.adb_test2("AlgoTest."+command_crypto[4]));} //Test crypto tdes
	
	@Test
	public void SwitchSeToRunModeTest() {assertEquals(0, calc.adb_test2("MiscTest.wakeup"));} //Test SWITCH SE TO RUN MODE
	
	@Test
	public void SwitchSeToSleepModeTest() {assertEquals(0, calc.adb_test2("MiscTest.sleep"));} //Test SWITCH SE TO low mode 
	
	@Test
	public void GetBatteryVoltageTest() {assertEquals(0, calc.adb_test2("MiscTest.coinVol"));} //Test get battery voltage
	@Test
	public void GetSeDataTimeTest() {assertEquals(0, calc.adb_test2("MiscTest.rtc"));} //Test get data time 
}